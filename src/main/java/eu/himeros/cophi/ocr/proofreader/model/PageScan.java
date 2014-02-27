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

package eu.himeros.cophi.ocr.proofreader.model;

import eu.himeros.cophi.image.model.Scan;

/**
 * The scan of the page, with variable coordinates that move the box
 * on different areas of the pages (lines, single words, etc.)
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public class PageScan<T> implements Scan<T,OcrCoords> {

    protected T image;
    protected OcrCoords coords;

    /**
     * Default Constructor.
     */
    public PageScan(){}
    
    /**
     * Constructor that passes the coordinates of an area on the page.
     * @param coords 
     */
    public PageScan(OcrCoords coords){
        this.coords=coords;
    }
    
    /**
     * Constructor that passes the image.
     * Image of the page or simple reference to the image (T can be a String or a File).
     * @param image 
     */
    public PageScan(T image){
        this.image=image;
    }
    
    /**
     * Constructor that passes an image (or a reference to the resource)
     * and the coordinates related to an area of the image.
     * @param image the image.
     * @param coords the coordinates.
     */
    public PageScan(T image, OcrCoords coords){
        this.image=image;
        this.coords=coords;
    }
    
    /**
     * Get the coordinates of an area on the image (usually a line, a word, etc.).
     * @return the coordinates 
     */
    @Override
    public OcrCoords getCoords() {
        return coords;
    }

    /**
     * Set the coordinates of an area on the image.
     * @param coords the coordinates.
     */
    @Override
    public void setCoords(OcrCoords coords) {
        this.coords = coords;
    }

    /**
     * Get the image (T can be an Image) or a reference to the image resource (T can be String or File)
     * @return the image or a reference to the resource.
     */
    @Override
    public T getImage() {
        return image;
    }

    /**
     * Set the image or a reference to the image resource.
     * @param image the image or a reference to the resource.
     */
    @Override
    public void setImage(T image) {
        this.image = image;
    }

}
