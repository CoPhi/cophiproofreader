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
import javax.swing.ImageIcon;
import sun.awt.image.ToolkitImage;

/**
 * Loads an image from an image file name.
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public class ImageFileNameLoader implements ImageLoader<String> {
    BufferedImage pageImage;

    /**
     * Get the buffered image of the page.
     * @return 
     */
    public BufferedImage getPageImage() {
        return pageImage;
    }

    /**
     * Set the buffered image of the page.
     * @param pageImage 
     */
    public void setPageImage(BufferedImage pageImage) {
        this.pageImage = pageImage;
    }
    
    /**
     * Loads the Image from an image file name.
     * @param imgFileName
     * @return 
     */
    @Override
    public synchronized Image load(String imgFileName){
        System.err.println(imgFileName);
        pageImage = null;
        try {
            ImageIcon iic = new ImageIcon(imgFileName);
            pageImage = ((ToolkitImage) iic.getImage()).getBufferedImage();
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
        return pageImage;
    }
    
}
