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

import eu.himeros.cophi.ocr.proofreader.model.OcrPage;
import org.jdom2.Document;

/**
 * A saver that saves a DOM document
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public interface HocrDocumentSaver<T> extends Saver<Document,T>{
    
    /**
     * Save an hocr document.
     * @param hocrDocument the hocrDocument.
     * @param destination the destination of the hocrDocument (e.g. a file name, etc.)
     */
    @Override
    public void save(Document hocrDocument, T destination);
    
    /**
     * Save an ocrPage. 
     * @param ocrPage the ocrPage.
     * @param destination the destination.
     */
    public void save(OcrPage ocrPage, T destination);
}
