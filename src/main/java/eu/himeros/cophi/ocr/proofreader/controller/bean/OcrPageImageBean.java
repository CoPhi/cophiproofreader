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
package eu.himeros.cophi.ocr.proofreader.controller.bean;

import eu.himeros.cophi.ocr.proofreader.controller.pojo.ImageExistLoader;
import eu.himeros.cophi.ocr.proofreader.controller.pojo.ImageLoader;
import eu.himeros.cophi.ocr.proofreader.model.OcrLine;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import org.primefaces.model.DefaultStreamedContent;

/**
 * This bean loads and renders the line images of the page. 
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
@ManagedBean(name = "pageImageBean")
@SessionScoped
public class OcrPageImageBean implements Serializable {

    private static final transient Logger log = Logger.getLogger(OcrPageImageBean.class.getName());
    BufferedImage pageImage;
    @ManagedProperty(value = "#{libraryBean}")
    OcrLibraryBean libraryBean;
    DefaultStreamedContent defStreamContent;
    ImageLoader<Map<String,Object>> il;

    /**
     * 
     */
    public OcrPageImageBean() {
        il=new ImageExistLoader();
    }

    /**
     * Get the DefaultStreamedContent that represents the line image.
     * @return the DefaultStreamedContent.
     */
    public DefaultStreamedContent getDef() {
        return defStreamContent;
    }

    /**
     * Set the DefaultStreamedContent that represents the line image.
     * @param def the DefaultStreamedContent.
     */
    public void setDef(DefaultStreamedContent def) {
        this.defStreamContent = def;
    }

    /**
     * Get the library bean injected in this bean.
     * (Necessary for injection).
     * @return the library bean.
     */
    public OcrLibraryBean getLibraryBean() {
        return libraryBean;
    }

    /**
     * Set the library bean injected in this bean.
     * (Necessary for injection).
     * @param libraryBean the library bean.
     */
    public void setLibraryBean(OcrLibraryBean libraryBean) {
        this.libraryBean = libraryBean;
    }

    /**
     * Provides the line image by receiving the coordinates of the box on the page from the web page.
     * This method is called in two different phases: in the first one it renders an empty box,
     * in the second one it renders the actual image.
     * @return the image.
     */
    public DefaultStreamedContent getImage() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getRenderResponse()) {
            return new DefaultStreamedContent();
        } else {
            int lineId = Integer.parseInt(context.getExternalContext().getRequestParameterMap().get("lineId"));
            List<OcrLine> lines = libraryBean.getLibrary().getCurrBook().getCurrPage().getOcrLines();
            OcrLine line = lines.get(lineId);
            int x1 = Integer.parseInt(context.getExternalContext().getRequestParameterMap().get("x1"));
            int y1 = Integer.parseInt(context.getExternalContext().getRequestParameterMap().get("y1"));
            int x2 = Integer.parseInt(context.getExternalContext().getRequestParameterMap().get("x2"));
            int y2 = Integer.parseInt(context.getExternalContext().getRequestParameterMap().get("y2"));
            pageImage = null;
            try {
                Map<String,Object> pageInfoMap=new HashMap<>();
                pageInfoMap.put("library",libraryBean.getLibrary().getRepository());
                pageInfoMap.put("book",libraryBean.getLibrary().getCurrBook().getOcrBookId());
                pageInfoMap.put("image",(String)(libraryBean.getLibrary().getCurrBook().getCurrPage().getScan().getImage()));
                pageImage=(BufferedImage)il.load(pageInfoMap);
                BufferedImage bimg = ((BufferedImage) pageImage).getSubimage(x1, y1, x2 - x1, y2 - y1);
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(bimg, "png", os);
                defStreamContent = new DefaultStreamedContent(new ByteArrayInputStream(os.toByteArray()), "image/png");
            } catch (Exception ex) {
                defStreamContent = null;
                ex.printStackTrace(System.err);
            }
            return defStreamContent;
        }
    }
    
        public BufferedImage getImage(String lineId, int x1, int y1, int x2, int y2) {
            List<OcrLine> lines = libraryBean.getLibrary().getCurrBook().getCurrPage().getOcrLines();
            pageImage = null;
            BufferedImage bimg=null;
            try {
                Map<String,Object> pageInfoMap=new HashMap<>();
                pageInfoMap.put("library",libraryBean.getLibrary().getRepository());
                pageInfoMap.put("book",libraryBean.getLibrary().getCurrBook().getOcrBookId());
                pageInfoMap.put("image",(String)(libraryBean.getLibrary().getCurrBook().getCurrPage().getScan().getImage()));
                pageImage=(BufferedImage)il.load(pageInfoMap);
                bimg = ((BufferedImage) pageImage).getSubimage(x1, y1, x2 - x1, y2 - y1);
            } catch (Exception ex) {
                ex.printStackTrace(System.err);
            }
            return bimg;
        }
}
