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

import eu.himeros.cophi.ocr.proofreader.model.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;

/**
 * Parser the hocr document and creates an ocr page.
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public class OcrPageParser implements Serializable {
    static final Logger logger=Logger.getLogger(OcrPageParser.class.getName());
    OcrPage ocrPage;
    BufferedImage ocrPageImage;
    //static String path;
    //ImageLoader il=new ImageFileNameLoader();

    /**
     * Default Constructor.
     */
    public OcrPageParser() {
    }
    
    /**
     * Constructor that passes an ocrPage.
     * @param ocrPage the ocrPage.
     */
    public OcrPageParser(OcrPage ocrPage){
        this.ocrPage=ocrPage;
    }

    /**
     * Constructor that passes an hocrDocument.
     * @param hocrDocument the hocrDocument.
     */
    public OcrPageParser(Document hocrDocument) {
        ocrPage.setHocrDocument(hocrDocument);
    }

    /**
     * Get the ocrPage.
     * @return the ocrPage.
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
     * Get the hocrDocument.
     * @return the hocrDocument.
     */
    public Document getHocrDocument() {
        return ocrPage.getHocrDocument();
    }

    /**
     * Set the hocrDocuemnt.
     * @param hocrDocument the hocrDocument.
     */
    public void setHocrDocument(Document hocrDocument) {
        ocrPage.setHocrDocument(hocrDocument);
    }

    /**
     * Get the absolute path to the resource.
     * @return the path.
     */
    //public String getPath() {
    //    return path;
    //}

    /**
     * Set the absolute path to the resource.
     * @param path the path.
     */
    //public void setPath(String path) {
    //    OcrPageParser.path = path;
    //}
    
    /**
     * Parses the ocrPage previously stored.
     * @return the ocrPage.
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    public OcrPage parse() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        return ocrPage = parse(ocrPage.getHocrDocument());
    }
    
    /**
     * Parser the hocr document and create an ocrPage.
     * @param hocrFileName the hocr file name.
     * @return the ocrPage.
     * @throws JDOMException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
//    public OcrPage parse(String hocrFileName) throws JDOMException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException{
//        SAXBuilder builder=new SAXBuilder();
//        Document hocrDocument=builder.build(new File(hocrFileName));
//        return parse(hocrDocument);
//    }
    
    /**
     * Creates an ocrPage extracting information both from the hocrDocuemnt and from an old ocrPage.
     * @param path the absolute path to the resource.
     * @param hocrDocument the hocr document.
     * @param ocrPage the ocrPage.
     * @return
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    public OcrPage parse(Document hocrDocument,Map<String,Object> pageInfoMap) throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException{
        //setPath(path); !!!PATH!!!
        setOcrPage((OcrPage)pageInfoMap.get("page-object"));
        return parse(hocrDocument);
    }

    /**
     * Parses an hocrDocuemnt, creating an ocrPage.
     * @param hocrDocument
     * @return the ocrPage.
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException 
     */
    public OcrPage parse(Document hocrDocument) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        ocrPage.setHocrDocument(hocrDocument);
        Element root = hocrDocument.getRootElement();
        Namespace xmlns=root.getNamespace();
        String scanId = root.getChildren().get(1).getChildren().get(0).getAttributeValue("id");
        ocrPage.setScan(new PageScan<>(scanId));
        List<OcrLine> ocrLines = new ArrayList<>();
        for (Element ocrLineEl : root.getChildren().get(1).getChildren().get(0).getChildren("span",xmlns)) { //cycle on /html/body/span[@class='ocr_page']/span[@class='ocr_line']
            ocrLines.add(parseOcrLine(ocrLineEl));
        }
        ocrPage.setOcrLines(ocrLines);
        return ocrPage;
    }

    /**
     * Parser an ocr line element and maps it on an OcrLine object.
     * @param ocrLineEl
     * @return the ocrLine.
     */
    private OcrLine parseOcrLine(Element ocrLineEl) {
        OcrLine ocrLine = new OcrLine();       
        OcrCoords ocrLineCoords = new OcrCoords(ocrLineEl.getAttributeValue("title"));
        ocrLine.setScan(new PageScan(ocrPage.getScan().getImage(),ocrLineCoords)); //!!!! PATH!!!!
        List<OcrWord> ocrWords = new ArrayList<>();
        for (Element ocrWordEl : ocrLineEl.getChildren()) {
            ocrWords.add(parseOcrWord(ocrWordEl));
        }
        ocrLine.setOcrWords(ocrWords);
        return ocrLine;
    }

    /**
     * Parses an ocr word element and maps it on an OcrWord object.
     * 
     * @param ocrWordEl the ocr word element.
     * @return the ocrWord.
     */
    private OcrWord parseOcrWord(Element ocrWordEl) {
        OcrWord ocrWord = new OcrWord();
        ocrWord.setId(ocrWordEl.getAttributeValue("id"));
        OcrCoords ocrWordCoords = new OcrCoords(ocrWordEl.getAttributeValue("title"));
        ocrWord.setScan(new PageScan(ocrWordCoords));
        boolean firstLine = true;
        List<Deletion> alternativeDeletions = new ArrayList<>();
        for (Element ocrAlternativeEl : ocrWordEl.getChildren().get(0).getChildren()) {
            if(firstLine){
                ocrWord.setInsertion(parseAlternativeInsertion(ocrAlternativeEl));
                firstLine=false;
            }else{
                alternativeDeletions.add(parseAlternativeDeletion(ocrAlternativeEl));
            }
        }
        ocrWord.setDeletions(alternativeDeletions);
        return ocrWord;
    }
    
    /**
     * Parses an ocr alternative element and maps it on an Insertion.
     * @param ocrAlternativeEl the ocr alternative element.
     * @return the Insertion.
     */
    private Insertion parseAlternativeInsertion(Element ocrAlternativeEl){
        Insertion alternativeInsertion=new Insertion();
        alternativeInsertion.setText(ocrAlternativeEl.getText());
        alternativeInsertion.setNlp(ocrAlternativeEl.getAttributeValue("title"));
        return alternativeInsertion;
    }
    
    /**
     * Parses an ocr alternative element adn maps it on a Deletion.
     * @param ocrAlternativeEl the ocr alternative element.
     * @return the Deletion.
     */
    private Deletion parseAlternativeDeletion(Element ocrAlternativeEl){
        Deletion alternativeDeletion=new Deletion();
        alternativeDeletion.setText(ocrAlternativeEl.getText());
        alternativeDeletion.setNlp(ocrAlternativeEl.getAttributeValue("title"));
        return alternativeDeletion;
    }
    
}
