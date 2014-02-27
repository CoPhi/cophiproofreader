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

import java.util.HashMap;
import java.util.Map;
import org.jdom2.Document;
import org.jdom2.input.DOMBuilder;
import org.jdom2.output.XMLOutputter;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.modules.XMLResource;

/**
 *
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public class HocrDocumentExistLoader implements HocrDocumentLoader<Map<String,Object>> {

    //public static void main(String args[]) throws Exception {
    //    HashMap<String, String> pageInfoMap = new HashMap<>();
    //    pageInfoMap.put("library", "xmldb:exist://cophi.ilc.cnr.it:8088/xmlrpc/db/perseus-ocr");
    //    pageInfoMap.put("book", "Euclides-Opera1.book");
    //    pageInfoMap.put("page", "p0208.html");
    //    pageInfoMap.put("login", "user01");
    //    pageInfoMap.put("password", "01resu");
    //    HocrDocumentExistLoader hdel = new HocrDocumentExistLoader();
    //    Document doc = hdel.load(pageInfoMap);
    //    XMLOutputter xmlOutputter = new XMLOutputter();
    //    String res = xmlOutputter.outputString(doc);
    //    System.out.println(res);
    //}

    @Override
    public Document load(Map<String,Object> origin) {
        try {
            //String library = origin.get("library");
            //String book = library + "/" + origin.get("book");
            //String page = origin.get("page");
            //String login = origin.get("login");
            //String password = origin.get("password");
            //Database database = (Database) (Class.forName("org.exist.xmldb.DatabaseImpl").newInstance());
            //DatabaseManager.registerDatabase(database);
            //Collection col = DatabaseManager.getCollection(book, login, password);
            Collection libCol=(Collection)origin.get("library");
            Collection bookCol=libCol.getChildCollection((String)origin.get("book"));
            XMLResource res = (XMLResource) bookCol.getResource((String)origin.get("page"));
            DOMBuilder domBuilder = new DOMBuilder();
            Document doc = domBuilder.build((org.w3c.dom.Document) res.getContentAsDOM());
            System.err.println(doc.toString());
            System.err.println("nothing");
            //col.close();
            return doc;
        } catch (Exception ex) {
            return null;
        }
    }
}
