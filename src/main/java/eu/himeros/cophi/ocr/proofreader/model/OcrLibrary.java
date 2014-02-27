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

import eu.himeros.cophi.text.model.Library;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains metainformation to build the library, such as root, book filter
 * and page filter. The current book and the index to the current book are
 * provided by get methods.
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public class OcrLibrary<T1,T2,T3,T4> extends Library{

    int currBookReference;
    OcrBook<T3> currBook;
    T1 libraryAddress;
    T2 bookFilter;
    T2 pageFilter;
    T4 repository;
    
    List<OcrBook> ocrBooks;
   
    /**
     * Constructor that initialize the currBookReference (to -1) and
     * the ocr book collection to an empty list.
     */
    public OcrLibrary(){
        currBookReference=-1;
        ocrBooks=new ArrayList<>();
    }

    /**
     * Get the reference to the current book in the list of books as an int index.
     * @return the reference to the current book.
     */
    public int getCurrBookReference() {
        return currBookReference;
    }

    /**
     * Set the reference to the current book in the list of books as in int index.
     * @param currBookReference the reference to the current book.
     */
    public void setCurrBookReference(int currBookReference) {
        this.currBookReference = currBookReference;
        currBook=ocrBooks.get(currBookReference);
    }
    
    /**
     * Get the library address of the collection. Type is generic, because subclasses can
     * implement it in different ways, e.g. a string that defines a path, a File
     * that describes a directory, etc.
     * @return the library address.
     */
    public T1 getLibraryAddress() {
        return libraryAddress;
    }

    /**
     * Set the library address of the collection. Type is generic, because subclasses can
     * implement it in different ways, e.g. a string that defines a path, a File
     * that describes a directory, etc
     * @param libraryAddress the libraryAddress. 
     */
    public void setLibraryAddress(T1 libraryAddress) {
        this.libraryAddress = libraryAddress;
    }

    /**
     * Get the book filter, evaluated by an .endsWith(bookFilter) test.
     * @return the bookFilter.
     */
    public T2 getBookFilter() {
        return bookFilter;
    }

    /**
     * Set the book filter, evaluated by an .endsWith(bookFilter) test.
     * @param bookFilter 
     */
    public void setBookFilter(T2 bookFilter) {
        this.bookFilter = bookFilter;
    }

    /**
     * Get the page filter, evaluated by an .endsWith(pageFilter) test.
     * @return the page filter.
     */
    public T2 getPageFilter() {
        return pageFilter;
    }

    /**
     * Set the page filter. evaluated by an .endsWith(pageFilter) test.
     * @param pageFilter the page filter.
     */
    public void setPageFilter(T2 pageFilter) {
        this.pageFilter = pageFilter;
    }
    
    /**
     * Get the list of books.
     * @return the list of books.
     */
    public List<OcrBook> getOcrBooks() {
        return ocrBooks;
    }

    /**
     * Set the list of books.
     * @param ocrBooks the list of books.
     */
    public void setOcrBooks(List<OcrBook> ocrBooks) {
        this.ocrBooks = ocrBooks;
    }

    /**
     * Get the current book.
     * @return the current book.
     */
    public OcrBook<T3> getCurrBook() {
        return currBook;
    }

    /**
     * Set the current book.
     * @param currBook the current book.
     */
    public void setCurrBook(OcrBook<T3> currBook) {
        this.currBook = currBook;
    }

    public T4 getRepository() {
        return repository;
    }

    public void setRepository(T4 repository) {
        this.repository = repository;
    }

    
}
