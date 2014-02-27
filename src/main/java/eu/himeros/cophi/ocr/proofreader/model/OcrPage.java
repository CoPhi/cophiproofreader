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
import eu.himeros.cophi.text.model.Page;
import java.util.List;
import org.jdom2.Document;

/**
 * The textual object related to a page, associated to the scan of the page.
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public class OcrPage extends Page<OcrLine> implements Ocr<PageScan<?>> {

    int id;
    String ocrPageId;
    String ocrPageLabel;
    Document hocrDocument;
    PageScan scan;
    List<OcrLine> ocrLines;

    /**
     * Get the id.
     * @return the id. 
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id.
     * @param id the id. 
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the ocr page id.
     * Usually, a string that identifies the resource.
     * @return the ocr page id.
     */
    //TODO: id and ocr page id have confusing names: refactory is necessary.
    public String getOcrPageId() {
        return ocrPageId;
    }

    /**
     * Set the ocr page id.
     * @param ocrPageId 
     */
    public void setOcrPageId(String ocrPageId) {
        this.ocrPageId = ocrPageId;
    }

    /**
     * Get the ocr page label.
     * This information is displayed to the user.
     * @return the ocr page label.
     */
    public String getOcrPageLabel() {
        return ocrPageLabel;
    }

    /**
     * Set the ocr page label.
     * @param ocrPageLabel the ocr page label.
     */
    public void setOcrPageLabel(String ocrPageLabel) {
        this.ocrPageLabel = ocrPageLabel;
    }
    
    /**
     * Get the lines of text.
     * @return the list of lines of text.
     */
    public List<OcrLine> getOcrLines() {
        return ocrLines;
    }

    /**
     * Set the lines of text.
     * @param ocrLines the list of lines of text.
     */
    public void setOcrLines(List<OcrLine> ocrLines) {
        this.ocrLines = ocrLines;
    }

    /**
     * Get the scan of the page.
     * @return the scan of the page.
     */
    @Override
    public PageScan getScan() {
        return scan;
    }

    /**
     * Set the scan of the page.
     * @param scan the scan of the page.
     */
    @Override
    public void setScan(PageScan scan) {
        this.scan = scan;
    }

    /**
     * Get the hocrDocument.
     * @return the hocrDocument.
     */
    public Document getHocrDocument() {
        return hocrDocument;
    }

    /**
     * Set the hocrDocument.
     * @param hocrDocument the hocrDocument.
     */
    public void setHocrDocument(Document hocrDocument) {
        this.hocrDocument = hocrDocument;
    }

}
