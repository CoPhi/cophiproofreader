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
import eu.himeros.cophi.text.model.Line;
import java.util.List;

/**
 * The line of text associated to the page scan.
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public class OcrLine extends Line<OcrWord> implements Ocr<PageScan<?>>{

    String id;
    OcrCoords coords;
    List<OcrWord> ocrWords;
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
     * Get the coordinates.
     * @return the coordinates.
     */
    public OcrCoords getCoords() {
        return coords;
    }

    /**
     * Get the list of words.
     * @return the list of words.
     */
    public List<OcrWord> getOcrWords() {
        return ocrWords;
    }

    /**
     * Set the list of words.
     * @param ocrWords the list of words.
     */
    public void setOcrWords(List<OcrWord> ocrWords) {
        this.ocrWords = ocrWords;
    }

    /**
     * Get the scan of the page with the coordinates of the current line.
     * @return the scan of the page.
     */
    //TODO: verify if this approach, with a scan of the page of type PageScan, is correct.
    @Override
    public PageScan<?> getScan() {
        return scan;
    }

    /**
     * Set the scan of the page with the coordinates of the current line.
     * @param scan the scan of the page.
     */
    @Override
    public void setScan(PageScan<?> scan) {
        this.scan = scan;
    }
    
}
