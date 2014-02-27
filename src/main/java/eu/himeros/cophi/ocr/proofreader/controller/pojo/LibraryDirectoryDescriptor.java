/*
 * This file is part of eu.himeros_CoPhiProofReader3_war_1.0-SNAPSHOT
 *
 * Copyright (C) 2013 federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package eu.himeros.cophi.ocr.proofreader.controller.pojo;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Describes the content of a library, providing references to the books.
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public class LibraryDirectoryDescriptor extends Descriptor<String, String, String,String> {

    static final transient Logger logger=Logger.getLogger(LibraryDirectoryDescriptor.class.getName());
    File libraryDir;

    /**
     * Set the repositoryAddress.
     * @param libraryDirName the library directory name. 
     */
    @Override
    public void setRepositoryAddress(String libraryDirName) {
        this.repositoryAddress = libraryDirName;
        libraryDir = new File(libraryDirName);
    }

    /**
     * Initialize the repositoryAddress, reading the references to the books, accessible
     * by setter and getter of the superclass.
     */
    @Override
    public void initRepository() {
        references = new ArrayList<>();
        logger.log(Level.INFO, libraryDir.getAbsoluteFile().toString());
        for (File bookDir : libraryDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if (filter == null) {
                    return true;
                }
                if (name.endsWith(filter)) {
                    return true;
                } else {
                    return false;
                }
            }
        })) {
            logger.log(Level.INFO,"  {0}",bookDir.getName());
            references.add(bookDir.getName());
        }
        Collections.sort(references);
    }
    
}
