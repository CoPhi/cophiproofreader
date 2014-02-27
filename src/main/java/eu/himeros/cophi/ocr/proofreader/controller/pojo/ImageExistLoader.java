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

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.modules.BinaryResource;

/**
 *
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public class ImageExistLoader implements ImageLoader<Map<String,Object>> {    
           
//    public static void main(String args[]) throws Exception{
//            HashMap<String,String> pageInfoMap=new HashMap<>();
//            pageInfoMap.put("library","xmldb:exist://cophi.ilc.cnr.it:8088/xmlrpc/db/perseus-ocr");
//            pageInfoMap.put("book","Euclides-Opera1.book");
//            pageInfoMap.put("image","i0208.png");
//            pageInfoMap.put("login","user01");
//            pageInfoMap.put("password","01resu");
//            ImageExistLoader ie=new ImageExistLoader();
//            Image img=ie.load(pageInfoMap);
//        }
            
   @Override
   public Image load(Map<String,Object> origin) {
        try{
            //String library=origin.get("library");
            //String book=library+"/"+origin.get("book");
            //String image=origin.get("image");
            //String login=origin.get("login");
            //String password=origin.get("password");
            //Database database = (Database)(Class.forName("org.exist.xmldb.DatabaseImpl").newInstance());
            //DatabaseManager.registerDatabase(database);
            //Collection col = DatabaseManager.getCollection(book,login,password);
            //Resource resX=col.getResource(image);
            //System.out.println(resX.getResourceType());
            Collection libCol=(Collection)origin.get("library");
            Collection bookCol=libCol.getChildCollection((String)origin.get("book"));
            BinaryResource res= (BinaryResource)bookCol.getResource((String)origin.get("image"));
            byte[] bs=(byte[])res.getContent();
            BufferedImage img=ImageIO.read(new ByteArrayInputStream(bs));
            System.out.println(img.toString());
            //col.close();
            return img;
        }catch(Exception ex){
            ex.printStackTrace(System.err);
            return null;
        }
    }
}
