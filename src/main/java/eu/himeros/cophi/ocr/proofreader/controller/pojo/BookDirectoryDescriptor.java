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
 * Manages descriptions of the content of a book, represented by references to the resources.
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public class BookDirectoryDescriptor extends Descriptor<String,String,String,String> {
    private static final transient Logger logger=Logger.getLogger(BookDirectoryDescriptor.class.getName());
    File bookDir;

    /**
     * Set the reference to the book resource.
     * @param bookDirName the reference to the book resource.
     */
    @Override
    public void setRepositoryAddress(String bookDirName) {
        this.repositoryAddress = bookDirName;
        bookDir=new File(bookDirName);
    }
    
    /**
     * Set the references to the file names of the pages.
     * References are accessible by the setter and getter declared in the superclass.
     */
    @Override
    public void initRepository(){
        references=new ArrayList<>();
        for (File pageFile : bookDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                if(name.endsWith(filter)){
                    return true;
                }else{
                    return false;
                }
            }
        })) {
            logger.log(Level.INFO, "page: {0}", pageFile.getName());
            references.add(pageFile.getName());
        }
        Collections.sort(references);
    }
}
