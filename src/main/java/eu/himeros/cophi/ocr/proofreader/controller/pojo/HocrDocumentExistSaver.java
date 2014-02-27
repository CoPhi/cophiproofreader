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

import eu.himeros.cophi.ocr.proofreader.model.OcrPage;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.exist.xmldb.EXistResource;
import org.jdom2.Document;
import org.jdom2.output.XMLOutputter;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

/**
 *
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public class HocrDocumentExistSaver implements HocrDocumentSaver<Map<String,Object>> {
    
    //public static void main(String[] args) throws Exception{
        //HashMap<String,String> pageInfoMap=new HashMap<>();
        //pageInfoMap.put("library","xmldb:exist://localhost:8088/xmlrpc/db/perseus-ocr");
       // pageInfoMap.put("book","Euclides-Opera1.book");
       // pageInfoMap.put("page","p0208.html");
       // pageInfoMap.put("login","federico");
        //pageInfoMap.put("password","ociredef");
        
       // SAXBuilder builder=new SAXBuilder();
       // Document doc=builder.build("/opt/junk/Euclides-Opera1.book/p0208.html");
      //  HocrDocumentExistSaver hdes=new HocrDocumentExistSaver();
      //  hdes.save(doc,pageInfoMap);        
    //}
    @Override
    public void save(Document hocrDocument, Map<String,Object> destination) {
        try{
            //String library=destination.get("library");
            //String book=library+"/"+destination.get("book");
            //String page=destination.get("page");
            //String login=destination.get("login");
            //String password=destination.get("password");
            //Database database = (Database)(Class.forName("org.exist.xmldb.DatabaseImpl").newInstance());
            //DatabaseManager.registerDatabase(database);
            //Collection col = DatabaseManager.getCollection(book,login,password);
            Collection libCol=(Collection)destination.get("library");
            System.err.println(destination.get("library"));
            //System.err.println(((Collection)destination.get("library")).toString());
            System.err.println((String)destination.get("book"));
            System.err.println(((Collection)libCol.getChildCollection((String)destination.get("book"))).toString());
            System.err.println("err");
            Collection bookCol=libCol.getChildCollection((String)destination.get("book"));
            XMLResource res=(XMLResource)bookCol.createResource((String)destination.get("page"),"XMLResource");
            //DOMOutputter domOutputter=new DOMOutputter();
            XMLOutputter xmlOutputter=new XMLOutputter();
            String resStr=xmlOutputter.outputString(hocrDocument);
            //org.w3c.dom.Document domDoc=domOutputter.output(hocrDocument);
            res.setContent(resStr);
            System.err.println(resStr);
            bookCol.storeResource(res);
            ((EXistResource)res).freeResources();
        }catch(XMLDBException ex){
            Logger.getLogger(HocrDocumentExistSaver.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void save(OcrPage ocrPage, Map<String,Object> destination) {
        Document doc=ocrPage.getHocrDocument();
        save(doc,destination);
    }


}
