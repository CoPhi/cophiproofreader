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
package eu.himeros.cophi.ocr.proofreader.controller.pojo;

import eu.himeros.cophi.ocr.proofreader.model.Deletion;
import eu.himeros.cophi.ocr.proofreader.model.OcrLine;
import eu.himeros.cophi.ocr.proofreader.model.OcrPage;
import eu.himeros.cophi.ocr.proofreader.model.OcrWord;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;

/**
 * A hocr document builder, used to marshall the ocr document in order to store it.
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public class HocrDocumentBuilder {

    OcrPage ocrPage;
    Namespace xmlns;

    /**
     * Default constructor.
     */
    public HocrDocumentBuilder() {
    }

    /**
     * Constructs the hocr document using an ocrPage.
     * @param ocrPage the ocrPage.
     */
    public HocrDocumentBuilder(OcrPage ocrPage) {
        this.ocrPage = ocrPage;
    }

    /**
     * Get the ocrPage;
     * @return the ocrPage;
     */
    public OcrPage getOcrPage() {
        return ocrPage;
    }

    /**
     * Set the ocrPage.
     * @param ocrPage the ocrPage.
     */
    public void setOcrPage(OcrPage ocrPage) {
        this.ocrPage = ocrPage;
    }

    /**
     * Builds the document using an ocrPage previously stored;
     * @return the resulting hocr document.
     */
    public Document build() {
        return build(ocrPage);
    }

    /**
     * Builds the document using an ocrPage.
     * @param ocrPage the ocrPage.
     * @return 
     */
    public Document build(OcrPage ocrPage) {
        this.ocrPage = ocrPage;
        Document hocrDocument = ocrPage.getHocrDocument();
        return build(hocrDocument);
    }
    
    /**
     * Builds the hocr document, extracting the header from the old hocr document.
     * @param the old hocrDocument
     * @return the new ohocrDocument
     */
    public Document build(Document hocrDocument){
        Element root = hocrDocument.getRootElement();
        xmlns = root.getNamespace();
        Element bodyEl = root.getChildren().get(1);
        Element ocrPageEl = bodyEl.getChildren().get(0).detach();
        String pageId = ocrPageEl.getAttributeValue("id");
        ocrPageEl = new Element("div", xmlns);
        ocrPageEl.setAttribute("class", "ocr_page");
        ocrPageEl.setAttribute("id", pageId);
        for (OcrLine ocrLine : ocrPage.getOcrLines()) {
            ocrPageEl.addContent(makeOcrLineEl(ocrLine));
            ocrPageEl.addContent(new Element("br", xmlns));
        }
        bodyEl.addContent(ocrPageEl);
        return hocrDocument;
    }

    /**
     * Makes the line.
     * @param ocrLine
     * @return the related element.
     */
    private Element makeOcrLineEl(OcrLine ocrLine) {
        Element ocrLineEl = new Element("span", xmlns);
        ocrLineEl.setAttribute("class", "ocr_line");
        ocrLineEl.setAttribute("title", ocrLine.getScan().getCoords().getBbox());
        for (OcrWord ocrWord : ocrLine.getOcrWords()) {
            ocrLineEl.addContent(makeOcrWordEl(ocrWord));
        }
        return ocrLineEl;
    }

    /**
     * Makes the token.
     * @param ocrWord
     * @return the related element.
     */
    private Element makeOcrWordEl(OcrWord ocrWord) {
        Element ocrWordEl = new Element("span", xmlns);
        String wordId = ocrWord.getId();
        ocrWordEl.setAttribute("id", wordId);
        ocrWordEl.setAttribute("class", "ocr_word");
        ocrWordEl.setAttribute("title", ocrWord.getScan().getCoords().getBbox());
        Element alternativeEl = new Element("span", xmlns);
        alternativeEl.setAttribute("class", "alternatives");
        Element alternativeInsertionEl = new Element("ins",xmlns);
        alternativeInsertionEl.setAttribute("class", "alt");
        alternativeInsertionEl.setAttribute("title", ocrWord.getInsertion().getNlp());
        alternativeInsertionEl.setText(ocrWord.getInsertion().getText());
        alternativeEl.addContent(alternativeInsertionEl);
        for (Deletion alternativeDeletion : ocrWord.getDeletions()) {
            alternativeEl.addContent(makeAlternativeDeletionEl(alternativeDeletion));
        }
        ocrWordEl.addContent(alternativeEl);
        return ocrWordEl;
    }

    /**
     * Makes the alternative deletion.
     * @param alternativeDeletion
     * @return an alternative deletion.
     */
    private Element makeAlternativeDeletionEl(Deletion alternativeDeletion) {
        Element alternativeDeletionEl = new Element("del",xmlns);
        //alternativeDeletionEl.setAttribute("class", "alt");
        alternativeDeletionEl.setAttribute("title", alternativeDeletion.getNlp());
        alternativeDeletionEl.setText(alternativeDeletion.getText());
        return alternativeDeletionEl;
    }
    
}
