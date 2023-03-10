

package tools1000;


import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO: move to commons
 */
@Slf4j
public class ToolsUtil {

    public static <T> T[] concatenateArray(T[] a, T[] b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }
        int aLen = a.length;
        int bLen = b.length;

        @SuppressWarnings("unchecked")
        T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
    }

    public static Path rename(final Path file, String newFileName) throws IOException {
        return Files.move(file, file.resolveSibling(newFileName));
    }

    public static void deleteRecursively(Path path) throws IOException {
        try (var dirStream = Files.walk(path)) {
            dirStream
                    .map(Path::toFile)
                    .sorted(Comparator.reverseOrder())
                    .forEach(File::delete);
        }
    }

    public static String stackTraceToString(Throwable e) {
        return Arrays.stream(e.getStackTrace())
                .map(StackTraceElement::toString)
                .collect(Collectors.joining("\n"));
    }

    public static <T> List<T> getSubList(List<T> list, int size) {
        if (list == null || list.size() <= size) {
            return list;
        }
        return list.subList(0, size);
    }

    /**
     * <p>
     * Checks, whether the provided path points to a case-sensitive file system or not.
     * </p>
     * <p>
     * It does so by creating two temp-files in the provided  path and comparing them. Note that you need to have write
     * access to the provided path.
     *
     * @param pathToFileSystem path to the file system to test
     * @param tmpFileName      name of the temp file that is created
     * @return {@code true}, if the provided path points to a case-sensitive file system; {@code false} otherwise
     * @throws IOException if IO operation fails
     */
    public static boolean fileSystemIsCaseSensitive(Path pathToFileSystem, String tmpFileName) throws IOException {

        Path a = Files.createFile(pathToFileSystem.resolve(Paths.get(tmpFileName.toLowerCase())));
        Path b = null;
        try {
            b = Files.createFile(pathToFileSystem.resolve(Paths.get(tmpFileName.toUpperCase())));
        } catch (FileAlreadyExistsException e) {
            return false;
        } finally {
            Files.deleteIfExists(a);
            if (b != null) Files.deleteIfExists(b);
        }
        return true;
    }

    public static String formatSize(long v) {
        if (v < 1024) return v + " B";
        int z = (63 - Long.numberOfLeadingZeros(v)) / 10;
        return String.format("%.1f %sB", (double)v / (1L << (z*10)), " KMGTPE".charAt(z));
    }

    public static void runOpenFolderCommandMacOs(Path path) {
        try {
            new CommandRunner().runCommand(new String[]{"open", "-R", path + File.separator});
        } catch (IOException e) {
            log.error("Failed execute command ", e);
        }
    }

    public static void runOpenFolderCommandLinux(Path path) {
        try {
            new CommandRunner().runCommand(new String[]{"xdg-open", path + File.separator});
        } catch (IOException e) {
            log.error("Failed execute command ", e);
        }
    }

    public static void runOpenFolderCommandWindows(Path path) {
        try {
            new CommandRunner().runCommand(new String[]{"explorer.exe", "/select", path + File.separator});
        } catch (IOException e) {
            log.error("Failed execute command ", e);
        }
    }
}
