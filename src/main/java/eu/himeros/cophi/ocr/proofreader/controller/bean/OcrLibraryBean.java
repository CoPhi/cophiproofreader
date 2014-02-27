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
package eu.himeros.cophi.ocr.proofreader.controller.bean;

import eu.himeros.cophi.ocr.proofreader.controller.pojo.Descriptor;
import eu.himeros.cophi.ocr.proofreader.controller.pojo.LibraryExistDescriptor;
import eu.himeros.cophi.ocr.proofreader.model.OcrBook;
import eu.himeros.cophi.ocr.proofreader.model.OcrLibrary;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.xmldb.api.base.Collection;

/**
 * Manages the library object, which contains books, which contains pages.
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
@ManagedBean(name = "libraryBean")
@SessionScoped
public class OcrLibraryBean implements Serializable {

    private static final transient Logger log = Logger.getLogger(InitBean.class.getName());
    @ManagedProperty(value = "#{initBean}")
    private InitBean initBean;
    private OcrLibrary<Map<String,String>, String, String,Collection> library;

    /**
     * Default Constructor
     */
    public OcrLibraryBean() {
    }

    /**
     * Receives the injected property: initBean, which it uses to initialize
     * the library. The library descriptor provides references and information
     * about the books of the collection.
     */
    @PostConstruct
    public void init() {
        library = new OcrLibrary<>();
        library.setLibraryAddress(initBean.getLibraryAddress());
        library.setBookFilter(initBean.getBookFilter());
        library.setPageFilter(initBean.getPageFilter());
        Descriptor<Map<String, String>, String, String, Collection> libDescriptor = new LibraryExistDescriptor();
        libDescriptor.setFilter(library.getBookFilter());
        libDescriptor.setRepositoryAddress(library.getLibraryAddress());
        libDescriptor.initRepository();
        library.setRepository(libDescriptor.getRepository());
        List<String> bookIds = libDescriptor.getReferences();
        int i = 0;
        for (String bookId : bookIds) {
            OcrBook<String> book = new OcrBook<>();
            book.setId(i);
            book.setOcrBookId(bookId);
            String bookLabel = bookId.replaceAll("_", " ").replaceAll("-", ", ").replaceAll("\\..*?$", "");
            book.setOcrBookLabel(bookLabel);
            library.getOcrBooks().add(book);
            i++;
        }
        library.setCurrBookReference(0);
    }

    /**
     * Get the library, which contains books, which contains pages.
     * @return the library.
     */
    public OcrLibrary<Map<String,String>, String, String,Collection> getLibrary() {
        return library;
    }

    /**
     * Set the library, which contains books, which contains pages.
     * @param library 
     */
    public void setLibrary(OcrLibrary<Map<String,String>, String, String,Collection> library) {
        this.library = library;
    }

    /**
     * Getter to the injected property initBean
     * @return 
     */
    public InitBean getInitBean() {
        return initBean;
    }

    /**
     * Setter to the injected property initBean
     * @param initBean 
     */
    public void setInitBean(InitBean initBean) {
        this.initBean = initBean;
    }
    
    /**
     * Updates the reference to the current book. This is a numeric value,
     * provided by the webpage as a string.
     * @param currBookReferenceStr 
     */
    public void update(String currBookReferenceStr){
        int currBookReference=Integer.parseInt(currBookReferenceStr);
        library.setCurrBookReference(currBookReference);
        library.getCurrBook().setCurrPageReference(0);
    }
}
