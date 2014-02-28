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
 *
 * last update 20140228100000
 *
*/

package eu.himeros.cophi.ocr.proofreader.controller.bean;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */

@ManagedBean(name="initBean")
@ApplicationScoped

/**
 * Manages application variables, such as the path root of the entire collection
 * (the library), book, page and image filters to apply to the repository
 * that contains these objects
 */
public class InitBean implements Serializable{

    private final static transient Logger log= Logger.getLogger(InitBean.class.getName());
    //private String root="/opt/junk";
    private Map<String,String> libraryAddress=new HashMap<>();
    private String bookFilter=".book";
    private String pageFilter=".html";
    private String imageFilter=".png";
    //TODO: properties must be read from a property file or from the main webage
    
    /**
     * Get the bookFilter
     * @return the book filter, evaluated by an .endsWith(bookFilter) test
     */
    public String getBookFilter() {
        return bookFilter;
    }

    /**
     * Set the bookFilter
     * @param bookFilter the book filter, evaluated by an .endsWith(bookFilter) test
     */
    public void setBookFilter(String bookFilter) {
        this.bookFilter = bookFilter;
    }

    /**
     * Get the pageFilter 
     * @return the page filter, evaluated by an .endsWith(pageFilter) test
     */
    public String getPageFilter() {
        return pageFilter;
    }

    /**
     * Set the pageFilter
     * @param pageFilter the page filter, evaluated by an .endsWith(pageFilter) test
     */
    public void setPageFilter(String pageFilter) {
        this.pageFilter = pageFilter;
    }


    /**
     * Get the imageFilter 
     * @return the image filter, evaluated by an .endsWith(imageFilter) test
     */ 
    public String getImageFilter() {
        return imageFilter;
    }

    /**
     * Set the imageFilter 
     * the image filter, evaluated by an .endsWith(imageFilter) test
     * @param imageFilter the image filter, evaluated by an .endsWith(imageFilter) test
     */
    public void setImageFilter(String imageFilter) {
        this.imageFilter = imageFilter;
    }

    public Map<String, String> getLibraryAddress() {
        Properties properties=new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream("/eu/himeros/cophi/ocr/proofreader/controller/bean/resources/hidden-config.properties"));
        } catch (IOException ex) {
            Logger.getLogger(InitBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        libraryAddress.put("library",properties.getProperty("library"));
        libraryAddress.put("login",properties.getProperty("login"));
        libraryAddress.put("password",properties.getProperty("password"));
        return libraryAddress;
    }

    public void setLibraryAddress(Map<String, String> libraryAccess) {
        this.libraryAddress = libraryAccess;
    }
    
   private static class MessageProvider{
       private static ResourceBundle bundle;

	public static ResourceBundle getBundle() {
		if (bundle == null) {
                    FacesContext context = FacesContext.getCurrentInstance();
                    bundle = context.getApplication().getResourceBundle(context, "config");
		}
		return bundle;
	}

	public static String getValue(String key) {
		String result = null;
		try {
			result = getBundle().getString(key);
		} catch (MissingResourceException e) {
			result = "???" + key + "??? not found";
			e.printStackTrace();
		}
		return result;
	}
   }    
}
