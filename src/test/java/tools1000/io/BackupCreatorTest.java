package tools1000.io;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.*;


public class BackupCreatorTest {

    File testFile01 = new File("src/test/resources/UPPERCASE.txt");

    File testFile01Backup = new File("src/test/resources/UPPERCASE.txt.bak");

    File testEmptyDir01 = new File("src/test/resources/test-empty-dir01");

    File testEmtpyDir01Backup = new File("src/test/resources/test-empty-dir01.bak");

    File testNonEmptyDir01 = new File("src/test/resources/test-empty-dir01");

    File testNonEmptyDir01Backup = new File("src/test/resources/test-empty-dir01.bak");

    BackupCreator bc;

    @BeforeEach
    public void setUp() {
        bc = new BackupCreator();
    }

    @AfterEach
    public void tearDown() throws Exception {
        bc = null;
        Files.deleteIfExists(testFile01Backup.toPath());
    }

    @Test
    public void testMakeBackupFile() throws FileNotFoundException {
        File file = bc.makeBackup(testFile01);
        assertTrue(Files.exists(file.toPath()));
        assertTrue(Files.isRegularFile(file.toPath()));
        assertEquals(testFile01.getName() + ".bak", file.getName());
    }

    @Test
    public void testRestoreBackupFile() throws FileNotFoundException {
        bc.makeBackup(testFile01);
        File file = bc.restoreBackup(testFile01Backup);
        assertTrue(Files.exists(file.toPath()));
        assertTrue(Files.isRegularFile(file.toPath()));
        assertEquals(testFile01.getName(), file.getName());
    }

    @Test
    public void testMakeBackupEmptyDir() throws FileNotFoundException {
        File file = bc.makeBackup(testEmptyDir01);
        assertTrue(Files.exists(file.toPath()));
        assertTrue(Files.isDirectory(file.toPath()));
        assertEquals(testEmptyDir01.getName() + ".bak", file.getName());
    }

    @Test
    public void testRestoreBackupEmptyDir() throws FileNotFoundException {
        bc.makeBackup(testEmptyDir01);
        File file = bc.restoreBackup(testEmtpyDir01Backup);
        assertTrue(Files.exists(file.toPath()));
        assertTrue(Files.isDirectory(file.toPath()));
        assertEquals(testEmptyDir01.getName(), file.getName());
    }

    @Test
    public void testMakeBackupNotEmptyDir() throws FileNotFoundException {
        File file = bc.makeBackup(testNonEmptyDir01);
        assertTrue(Files.exists(file.toPath()));
        assertTrue(Files.isDirectory(file.toPath()));
        assertEquals(testNonEmptyDir01.getName() + ".bak", file.getName());
    }

    @Disabled // empty dirs are not maintained by git
    @Test
    public void testRestoreBackupNotEmptyDir() throws FileNotFoundException {
        bc.makeBackup(testNonEmptyDir01);
        File file = bc.restoreBackup(testNonEmptyDir01Backup);
        assertTrue(Files.exists(file.toPath()));
        assertTrue(Files.isDirectory(file.toPath()));
        assertEquals(testNonEmptyDir01.getName(), file.getName());
    }
}