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

package eu.himeros.cophi.image.model;

/**
 * A scan is a box on an image of type T1 with coords of type T2.
 * Degenerate boxes are the empty box (0,0,0,0) and the entire image (0,0,width,hight).
 * The semantics of the coords (e.g. upper or lower corner), possible converters
 * and additional methods are defined in the classes that implement Coords.
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public interface Scan<T1,T2 extends Coords> {
    
    /**
     * Get the image of type T1. It can be an actual image (e.g. a BufferedImage)
     * or a reference to an image (e.g. a String with the file name of the image).
     * In the latter case, controllers will load and render the image.
     * @return the image.
     */
    public T1 getImage();

    /**
     * Set the image of type T1.
     * @see getImage()
     * @param image 
     */
    public void setImage(T1 image);
    
    /**
     * Get the coordinates of the box that contains this scan.
     * @return the coordinates of the box.
     */
    public Coords getCoords();
    
    /**
     * Set the coordinates of the box that contains this scan.
     * @param coords the coordinates of the box.
     */
    public void setCoords(T2 coords);
}
