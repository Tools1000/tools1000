package tools1000.io;



import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tools1000.io.BackupCreator;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class BackupCreatorTest {

    protected static final File testFile01 = new File("src/test/resources/UPPERCASE.txt");

    private BackupCreator bc;

    @BeforeEach
    public void setUp() throws Exception {
        bc = new BackupCreator();
    }

    @AfterEach
    public void tearDown() throws Exception {
        bc = null;
    }

    @Test
    public void makeBackup() throws FileNotFoundException {
        File file = bc.makeBackup(testFile01);
        assertNotNull(file);
        assertEquals(testFile01.getName() + ".bak", file.getName());
    }

    @Test
    public void restoreBackup() {
    }
}