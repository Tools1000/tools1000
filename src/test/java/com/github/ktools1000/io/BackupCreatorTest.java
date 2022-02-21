package com.github.ktools1000.io;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

import static org.junit.Assert.*;

public class BackupCreatorTest {

    protected static final File testFile01 = new File("src/test/resources/UPPERCASE.txt");

    private BackupCreator bc;

    @Before
    public void setUp() throws Exception {
        bc = new BackupCreator();
    }

    @After
    public void tearDown() throws Exception {
        bc = null;
    }

    @Test(expected = NullPointerException.class)
    public void makeBackupWithNullFile() throws FileNotFoundException {
       File file = bc.makeBackup(null);
       assertNotNull(file);
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