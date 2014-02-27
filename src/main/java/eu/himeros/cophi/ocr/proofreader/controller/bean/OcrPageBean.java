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

//import eu.himeros.cophi.ocr.proofreader.controller.pojo.HocrDocumentBufferedReader;
//import eu.himeros.cophi.ocr.proofreader.controller.pojo.HocrDocumentBufferedWriter;
import eu.himeros.cophi.ocr.proofreader.controller.pojo.HocrDocumentBuilder;
import eu.himeros.cophi.ocr.proofreader.controller.pojo.HocrDocumentExistLoader;
import eu.himeros.cophi.ocr.proofreader.controller.pojo.HocrDocumentExistSaver;
import eu.himeros.cophi.ocr.proofreader.controller.pojo.OcrPageParser;
import eu.himeros.cophi.ocr.proofreader.model.OcrBook;
import eu.himeros.cophi.ocr.proofreader.model.OcrLibrary;
import eu.himeros.cophi.ocr.proofreader.model.OcrPage;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import org.jdom2.Document;
import org.xmldb.api.base.Collection;

/**
 * Manages the current page edited by the user.
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
@ManagedBean(name = "pageBean")
@SessionScoped
public class OcrPageBean implements Serializable {

    private static final transient Logger logger = Logger.getLogger(InitBean.class.getName());
    @ManagedProperty(value = "#{bookBean}")
    private OcrBookBean bookBean;
    //private OcrBook book;
    private OcrPage page;
    private int pageReference;
    //private int prevPageReference = -1;
    private Map<String,Object> pageInfoMap=new HashMap<>();    
    /**
     * Default constructor
     */
    public OcrPageBean(){
    }

    /**
     * Receives the injected property: bookBean, which it uses
     * to set the pageReference (an int corresponding to the index of the
     * list of pages) and the current page. If the page contains
     * just the initialization information, it is filled with
     * the related hocrDocument and other information.
     */
    @PostConstruct
    public void init() {
        try {
            pageReference = bookBean.getCurrPageReference();
            page=bookBean.getLibraryBean().getLibrary().getCurrBook().getCurrPage();
            if(page.getHocrDocument()!=null) return;
            fillPage();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
    
        /**
     * Getter of the injected property: bookBean.
     * @return the bookBean.
     */
    public OcrBookBean getBookBean() {
        return bookBean;
    }

    /**
     * Setter of the injected property: bookBean.
     * @param bookBean the bookBean.
     */
    public void setBookBean(OcrBookBean bookBean) {
        this.bookBean = bookBean;
    }
    

    /**
     * Get the page reference (an int index to the collection).
     * @return the page reference.
     */
    public int getPageReference() {
        return pageReference;
    }

    /**
     * Set the page reference(an int index to the collection).
     * @param pageReference the page reference.
     */
    public void setPageReference(int pageReference) {
        this.pageReference = pageReference;
    }
    
    /**
     * Get the current page.
     * @return the current page.
     */
    public OcrPage getPage() {
        return page;
    }

    /**
     * Set the current page.
     * @param page the current page.
     */
    public void setPage(OcrPage page) {
        this.page = page;
    }

    /**
     * Fills the current page with the relevant information. It loads the hocrDocument
     * and it parses it.
     */
    private void fillPage() {
        //String fileName;
        HocrDocumentExistLoader hocrDocumentLoader = new HocrDocumentExistLoader();
        try {
            OcrLibrary<Map<String,String>, String, String,Collection> library = bookBean.getLibraryBean().getLibrary();
            OcrBook<String> currBook = library.getCurrBook();
            List<OcrPage> ocrPages = bookBean.getOcrPages();
            OcrPage currPage = ocrPages.get(pageReference);
            
            //fileName = library.getLibraryAddress() + File.separator + currBook.getOcrBookId() + File.separator + currPage.getOcrPageId();
            pageInfoMap.put("library",bookBean.getLibraryBean().getLibrary().getRepository());
            pageInfoMap.put("book",bookBean.getLibraryBean().getLibrary().getCurrBook().getOcrBookId());
            pageInfoMap.put("page",bookBean.getOcrPages().get(pageReference).getOcrPageId());
            pageInfoMap.put("page-object",currPage);
            Document inHocrDocument = hocrDocumentLoader.load(pageInfoMap);
            //String path = library.getLibraryAddress() + File.separator + library.getCurrBook().getOcrBookId() + File.separator;
            OcrPageParser opp = new OcrPageParser();
            page = opp.parse(inHocrDocument,pageInfoMap);
        } catch (FileNotFoundException | ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace(System.err);
        }
    }

    /**
     * Update the insertion with the text received from the webpage. Set also the
     * nlp to 1.0: a manual correction is supposed to be equivalent to the ground truth.
     * @param text the word received from the webpage.
     * @param lineIdx the line index of the line in which the correction occurs.
     * @param wordIdx the word index of the line in which the correction occurs.
     */
    public void updateInsertion(String text, int lineIdx, int wordIdx) {
        text = text.replaceAll("[\u261a|\u261b]", "");
        //cancel the little hand (if present, which represent a suggestion due to alignment with another edition).
        page.getOcrLines().get(lineIdx).getOcrWords().get(wordIdx).getInsertion().setText(text);
        page.getOcrLines().get(lineIdx).getOcrWords().get(wordIdx).getInsertion().setNlp("nlp 1.00");
    }   //TODO: Make a more dynamic management of the nlp label.

    /**
     * Save the page.
     */
    //TODO: Save must be robust; currently it is very weak.
    public void save() {
        HocrDocumentBuilder hocrDocBuilder = new HocrDocumentBuilder();
        HocrDocumentExistSaver hocrDocumentExistSaver = new HocrDocumentExistSaver();
        try {
            //String absolutePath=bookBean.getLibraryBean().getLibrary().getLibraryAddress()+File.separator+bookBean.getLibraryBean().getLibrary().getCurrBook().getOcrBookId()+File.separator+page.getOcrPageId();
            pageInfoMap=new HashMap<>();
            pageInfoMap.put("library",(Collection)bookBean.getLibraryBean().getLibrary().getRepository());
            pageInfoMap.put("book",bookBean.getLibraryBean().getLibrary().getCurrBook().getOcrBookId());
            pageInfoMap.put("page",bookBean.getLibraryBean().getLibrary().getCurrBook().getCurrPage().getOcrPageId());
            pageInfoMap.put("page-object",bookBean.getLibraryBean().getLibrary().getCurrBook().getCurrPage());
            hocrDocumentExistSaver.save(hocrDocBuilder.build(page),pageInfoMap);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    } 
}
