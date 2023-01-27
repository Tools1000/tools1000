/*******************************************************************************
 * Copyright (c) 2022 kerner1000. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.github.tools1000.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Provides static methods to create a backup-copy of a file and restore a file
 * from a backup file.
 *
 * @author kerner1000
 *
 */
public class BackupCreator {

    private static final Logger logger = LoggerFactory.getLogger(BackupCreator.class);

    /**
     * Copies given file to a new file with file name suffix '.bak'.
     *
     * @param file
     *            the file to make a backup of
     * @return the new file, or {@code null} in case of error
     */
    public File makeBackup(final File file) throws FileNotFoundException {
	if (file.exists()) {
	    try {
		final File backupFile = new File(file.getParentFile(), file.getName() + ".bak");
		FileUtils.copyFile(file, backupFile);
		if (logger.isDebugEnabled()) {
		    logger.debug("Backup created as " + backupFile);
		}
		return backupFile;
	    } catch (final IOException e) {
		if (logger.isErrorEnabled()) {
		    logger.error(e.getLocalizedMessage(), e);
		}
	    }
	}
	throw new FileNotFoundException(file.getPath());
    }

    /**
     * I case the backup file could not be read or does not have the {@code .back}
     * extension, {@code null} is returned.
     *
     * @param backupFile
     *            the backup file to restore from
     * @return the restored file or {@code null} in case of error
     */
    public File restoreBackup(final File backupFile) throws FileNotFoundException {
	if (backupFile.canRead() && backupFile.getName().endsWith(".bak")) {
	    try {
		final String oldFileName = backupFile.getName().substring(0, backupFile.getName().length() - 4);
		final File file = new File(backupFile.getParentFile(), oldFileName);
		FileUtils.copyFile(backupFile, file);
		if (logger.isDebugEnabled()) {
		    logger.debug("Backup restored to " + file);
		}
		return file;
	    } catch (final IOException e) {
		if (logger.isErrorEnabled()) {
		    logger.error(e.getLocalizedMessage(), e);
		}
	    }
	}
	throw new FileNotFoundException("Cannot read file "+ backupFile);

    }
}