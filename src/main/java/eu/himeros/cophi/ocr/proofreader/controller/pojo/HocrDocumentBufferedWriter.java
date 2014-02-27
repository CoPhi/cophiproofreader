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
import java.io.*;
import org.jdom2.Document;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 * A document saver that uses a buffered writer to save the hocrDocument.
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public class HocrDocumentBufferedWriter implements HocrDocumentSaver<BufferedWriter> {

    /**
     * Saves the hocrDocument
     * @param hocrDocument the hocr document.
     * @param bw the buffered writer.
     */
    @Override
    public void save(Document hocrDocument, BufferedWriter bw) {
        try{
            XMLOutputter xop = new XMLOutputter(Format.getPrettyFormat().setLineSeparator("\n"));
            xop.output(hocrDocument, bw);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
    
    /**
     * Save the hocrDocument using an output file name.
     * @param hocrDocument the hocr document.
     * @param outFileName the output file name.
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException 
     */
    public void save(Document hocrDocument, String outFileName) throws FileNotFoundException, UnsupportedEncodingException{
        save(hocrDocument, new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFileName),"UTF-8")));
    }

    /**
     * Saves an hocr document, extracting it from an ocrPage and saving it by a BufferedWriter.
     * @param ocrPage
     * @param destination 
     */
    @Override
    public void save(OcrPage ocrPage, BufferedWriter destination) {
       save(ocrPage.getHocrDocument(),destination);
    }
}
