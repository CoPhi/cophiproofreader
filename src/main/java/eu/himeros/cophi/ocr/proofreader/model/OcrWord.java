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

import eu.himeros.cophi.ocr.model.Ocr;
import eu.himeros.cophi.text.model.Token;
import java.util.List;

/**
 * An ocr word, despite its name, is a token, a physical component.
 * The name word derives by the hocr microformat.
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public class OcrWord extends Token<String> implements Ocr<PageScan<?>>{

    String id;
    Insertion insertion;
    List<Deletion> deletions;
    PageScan<?> scan;

    /**
     * Get the id.
     * @return the id.
     */
    public String getId() {
        return id;
    }

    /**
     * Set the id.
     * @param id the id.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the list of alternatives but the main one.
     * @see getInsertions()
     * @return the list of alternatives but the main one. 
     */
    public List<Deletion> getDeletions() {
        return deletions;
    }
    
    /**
     * Set the list of alternatives but the main one.
     *
     * @param the list of alternatives but the main one. 
     */
    public void setDeletions(List<Deletion> deletions) {
        this.deletions = deletions;
    }

    /**
     * Get the main alternative.
     * Usually the main alternative is the ocr output, but it can be the automatic
     * correction with the highest score, etc.
     * @return the main alternative
     */
    public Insertion getInsertion() {
        return insertion;
    }

    /**
     * Set the main alternative.
     * @param the main alternative.
     */
    public void setInsertion(Insertion insertion) {
        this.insertion = insertion;
    }

    /**
     * Get the scan of the page with the coordinates related to this word.
     * @return the scan of the page.
     */
    @Override
    public PageScan<?> getScan() {
        return scan;
    }

    /**
     * Set the scan of the page with the coordinates related to this word.
     * @param scan the scan of the page.
     */
    @Override
    public void setScan(PageScan<?> scan) {
        this.scan = scan;
    }
    
}
