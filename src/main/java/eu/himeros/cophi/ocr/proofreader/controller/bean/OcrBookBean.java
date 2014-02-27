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

import eu.himeros.cophi.ocr.proofreader.controller.pojo.BookExistDescriptor;
import eu.himeros.cophi.ocr.proofreader.controller.pojo.Descriptor;
import eu.himeros.cophi.ocr.proofreader.model.OcrBook;
import eu.himeros.cophi.ocr.proofreader.model.OcrLibrary;
import eu.himeros.cophi.ocr.proofreader.model.OcrPage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.xmldb.api.base.Collection;

/**
 * Manages the current book in the library, which must be provided to the user.
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
@ManagedBean(name = "bookBean")
@SessionScoped
public class OcrBookBean implements Serializable {

    private static final transient Logger logger = Logger.getLogger(OcrBookBean.class.getCanonicalName());
    @ManagedProperty(value = "#{libraryBean}")
    private OcrLibraryBean libraryBean;

    /**
     * Default constructor 
     */
    public OcrBookBean() {
    }

    /**
     * Get ocrPages.
     * @return ocrPages, the list of pages of the current book.
     */
    public List<OcrPage> getOcrPages() {
        if (libraryBean.getLibrary().getCurrBook().getOcrPages() == null) {
            initPages();
        }
        return libraryBean.getLibrary().getCurrBook().getOcrPages();
    }

    /**
     * Set ocrPages.
     * @param ocrPages ocrPages, the list of pages of the current book. 
     */
    public void setOcrPages(List<OcrPage> ocrPages) {
        libraryBean.getLibrary().getCurrBook().setOcrPages(ocrPages);
    }

    /**
     * Get the libraryBean, which is the manager of the book collection.
     * @return the libraryBean. 
     */
    public OcrLibraryBean getLibraryBean() {
        return libraryBean;
    }

    /**
     * Set the libraryBean, which is the manager of the book collection.
     * @param libraryBean the libraryBean.
     */
    public void setLibraryBean(OcrLibraryBean libraryBean) {
        this.libraryBean = libraryBean;
    }

    /**
     * Get the reference (expressed by an int value) to the currentPage.
     * It is the index of the list containing the pages.
     * @return the reference to the current page.
     */
    public int getCurrPageReference() {
        return libraryBean.getLibrary().getCurrBook().getCurrPageReference();
    }

    /**
     * Set the reference (expresssed by an int value) to the currentPage.
     * It is the index of the list containing the pages.
     * @param currPageReference  the reference to the current page.
     */
    public void setCurrPageReference(int currPageReference) {
        System.err.println("currPageReference=" + currPageReference);
        libraryBean.getLibrary().getCurrBook().setCurrPageReference(currPageReference);
    }

    /**
     * Initializes the pages, adding in each page just the minimal information:
     * id (an int), an identifier of the page (e.g. the file name related to the page)
     * and a page label, currently based on the page identifier by the application
     * of a regular expression.
     */
    public void initPages() {
        OcrLibrary<Map<String,String>, String, String,Collection> library = libraryBean.getLibrary();
        OcrBook<String> currBook = library.getCurrBook();
        Descriptor bookDescriptor = new BookExistDescriptor();
        bookDescriptor.setFilter(library.getPageFilter());
        System.err.println(library.getPageFilter());
        library.getLibraryAddress().put("book",currBook.getOcrBookId());
        System.err.println(currBook.getOcrBookId());
        bookDescriptor.setRepositoryAddress(library.getLibraryAddress());
        System.err.println(library.getLibraryAddress().get("library"));
        bookDescriptor.setRepository(library.getRepository());
        System.err.println(">"+library.getRepository().toString()+"<");
        bookDescriptor.initRepository();
        List<String> references = bookDescriptor.getReferences();
        int i = 0;
        Collections.sort(references);
        libraryBean.getLibrary().getCurrBook().setOcrPages(new ArrayList<OcrPage>());
        for (String pageId : references) {
            OcrPage page = new OcrPage();
            page.setId(i);
            page.setOcrPageId(pageId);
            String label = pageId.replaceAll("[^1-9]*([1-9][0-9]*)" + library.getPageFilter().replaceAll("\\.", "\\."), "$1");
            //TODO: make a more general filter!
            page.setOcrPageLabel(label);
            i++;
            libraryBean.getLibrary().getCurrBook().getOcrPages().add(page);
        }
        libraryBean.getLibrary().getCurrBook().setCurrPageReference(0);
    }
    
}
