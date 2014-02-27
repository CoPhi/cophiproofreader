/*
 * This file is part of hocrinfoaggregator
 *
 * Copyright (C) 2014 federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
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

package eu.himeros.exist;

/**
 *
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
import java.io.File;
import org.exist.xmldb.EXistResource;
import org.xmldb.api.base.*;
import org.xmldb.api.modules.*;
import org.xmldb.api.*;
public class ExistDbStore {
    
    private static String URI = "xmldb:exist://localhost:8088/xmlrpc";
    private static String login="admin";
    private static String password="nimda";
    
    /**
     * args[0] Should be the name of the collection to access
     * args[1] Should be the name of the file to read and store in the collectio
n
     */
    public static void main(String args[]) throws Exception {
        args=new String[2];
        args[0]="/db/perseus-ocr/Ioannes_Zonaras-Epitome_Historiarum_5.book";
        args[1]="/home/federico/lab058/test/p0005.html";
        if(args.length < 2) {
            System.out.println("usage: ExistDbStore collection-path document");
            System.exit(1);
        }
        final String driver = "org.exist.xmldb.DatabaseImpl";
        
        // initialize database driver
        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
        //database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
        Collection col = null;
        XMLResource res = null;
        try { 
            col = getOrCreateCollection(args[0]);
            
            // create new XMLResource; an id will be assigned to the new resource
            res = (XMLResource)col.createResource(null, "XMLResource");
            File f = new File(args[1]);
            if(!f.canRead()) {
                System.out.println("cannot read file " + args[1]);
                return;
            }
            
            res.setContent(f);
            System.out.print("storing document " + res.getId() + "...");
            col.storeResource(res);
            System.out.println("ok.");
        } finally {
            //dont forget to cleanup
            if(res != null) {
                try { ((EXistResource)res).freeResources(); } catch(XMLDBException xe) {xe.printStackTrace();}
            }
            
            if(col != null) {
                try { col.close(); } catch(XMLDBException xe) {xe.printStackTrace();}
            }
        }
    }
    
    private static Collection getOrCreateCollection(String collectionUri) throws 
XMLDBException {
        return getOrCreateCollection(collectionUri, 0);
    }
    
    private static Collection getOrCreateCollection(String collectionUri, int 
pathSegmentOffset) throws XMLDBException {
        Collection col = DatabaseManager.getCollection(URI + collectionUri,login,password);
        if(col == null) {
            if(collectionUri.startsWith("/")) {
                collectionUri = collectionUri.substring(1);
            }
            String pathSegments[] = collectionUri.split("/");
            if(pathSegments.length > 0) {
                StringBuilder path = new StringBuilder();
                for(int i = 0; i <= pathSegmentOffset; i++) {
                    path.append("/" + pathSegments[i]);
                }
                Collection start = DatabaseManager.getCollection(URI + path);
                if(start == null) {
                    //collection does not exist, so create
                    String parentPath = path.substring(0, path.lastIndexOf("/"
));
                    Collection parent = DatabaseManager.getCollection(URI + 
parentPath);
                    CollectionManagementService mgt = (CollectionManagementService) parent.getService("CollectionManagementService", "1.0");
                    col = mgt.createCollection(pathSegments[pathSegmentOffset]);
                    col.close();
                    parent.close();
                } else {
                    start.close();
                }
            }
            return getOrCreateCollection(collectionUri, ++pathSegmentOffset);
        } else {
            return col;
        }
    }
}
