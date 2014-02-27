/*
 * This file is part of eu.himeros_CoPhiProofReader_war_1.0-SNAPSHOT
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

package eu.himeros.cophi.ocr.proofreader.model;

import eu.himeros.cophi.text.model.Book;
import java.util.List;

/**
 * An OcrBook is constituted by OcrPages and provides a bookmark to the current page.
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
//TODO: generic must be revised, because T ocrBookId is important - OcrBook<T1,T2> is possible
  public class OcrBook<T> extends Book<OcrPage>{
    int id;
    T ocrBookId;
    String ocrBookLabel;
    int currPageReference;
    OcrPage currPage;
    List<OcrPage> ocrPages;
    
    /**
     * Default Constructor.
     * Initialize the pages to null.
     */
    //TODO: verify if this initialization can be avoided.
    public OcrBook(){
        ocrPages=null;
    }

    /**
     * Get the list of the pages.
     * @return the list of the pages.
     */
    public List<OcrPage> getOcrPages() {
        return ocrPages;
    }

    /**
     * Set the list of the pages.
     * @param ocrPages the list of the pages.
     */
    public void setOcrPages(List<OcrPage> ocrPages) {
        this.ocrPages = ocrPages;
    }

    /**
     * Get the current page.
     * @return the current page.
     */
    public OcrPage getCurrPage() {
        return currPage;
    }

    /**
     * Set the current page.
     * @param currPage the current page.
     */
    public void setCurrPage(OcrPage currPage) {
        currPageReference=currPage.getId();
        this.currPage = currPage;
    }

    /**
     * Get the current page reference, which is an index to the list of pages.
     * @return the current page reference.
     */
    public int getCurrPageReference() {
        return currPageReference;
    }

    /**
     * Set the current page reference, which is an index to the list of pages.
     * @param currPageReference 
     */
    public void setCurrPageReference(int currPageReference) {
        this.currPageReference = currPageReference;
        currPage=ocrPages.get(currPageReference);
    }

    /**
     * Get the id of this book.
     * @return the id of this book.
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id of this book.
     * @param id the id of this book.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the ocr book id.
     * Usually it is a string representing a path to this resource.
     * @return the ocr book id.
     */
    //TODO: id and ocrBookId have confusing names: refactory is necessary.
    public T getOcrBookId() {
        return ocrBookId;
    }

    /**
     * Set the ocr book id.
     * @see getOcrBookId()
     * @param ocrBookId the ocr book id.
     */
    public void setOcrBookId(T ocrBookId) {
        this.ocrBookId = ocrBookId;
    }

    /**
     * Get the ocr book label.
     * It is the description of the book displayed to the user.
     * @return the ocr book label.
     */
    public String getOcrBookLabel() {
        return ocrBookLabel;
    }

    /**
     * Set the ocr book label.
     * @param ocrBookLabel the ocr book label.
     */
    public void setOcrBookLabel(String ocrBookLabel) {
        this.ocrBookLabel = ocrBookLabel;
    }

    /**
     * Return false because a book is a Composite.
     * @return false.
     */
    @Override
    public boolean isAtomic() {
        return atomic;
    }
    
}
