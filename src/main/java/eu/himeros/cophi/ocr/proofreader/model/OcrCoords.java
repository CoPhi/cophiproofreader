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

import eu.himeros.cophi.image.model.Coords;

/**
 * This class contains the coordinates of the box on the scan image.
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public class OcrCoords implements Coords{
    String bbox;
    int x1;
    int y1;
    int x2;
    int y2;
    
    /**
     * Default constructor.
     */
    public OcrCoords(){}
    
    /**
     * Constructor that sets the coordinates.
     * @param x1
     * @param y1
     * @param x2
     * @param y2 
     */
    public OcrCoords(int x1, int y1, int x2, int y2){
        this.x1=x1;
        this.y1=y1;
        this.x2=x2;
        this.y2=y2;
    }
    
    /**
     * Constructor that sets the coordinates, parsing a string conformant to the hocr microformat.
     * @param bbox 
     */
    public OcrCoords(String bbox){
        this.bbox=bbox;
        parseBbox(bbox);
    }

    private void parseBbox(String bbox){
        String[] bboxCoords=bbox.split(" ");
        x1=Integer.parseInt(bboxCoords[1]);
        y1=Integer.parseInt(bboxCoords[2]);
        x2=Integer.parseInt(bboxCoords[3]);
        y2=Integer.parseInt(bboxCoords[4]);
    }
    
    /**
     * Get the bbox string.
     * @return the bbox.
     */
    public String getBbox() {
        return bbox;
    }

    /**
     * Set the bbox string. Automatically set the coordinates.
     * @param bbox 
     */
    public void setBbox(String bbox) {
        this.bbox = bbox;
        parseBbox(bbox);
    }

    /**
     * Get x1.
     * @return x1. 
     */
    @Override
    public int getX1() {
        return x1;
    }

    /**
     * Set x1 and update the bbox string.
     * @param x1 
     */
    @Override
    public void setX1(int x1) {
        this.x1 = x1;
        bboxUpdate();
    }

    /**
     * Get x2.
     * @return x2. 
     */
    @Override
    public int getX2() {
        return x2;
    }

    /**
     * Set x2 and update the bbox string.
     * @param x2 
     */
    @Override
    public void setX2(int x2) {
        this.x2 = x2;
        bboxUpdate();
    }

    /**
     * Get y1.
     * @return y1. 
     */
    @Override
    public int getY1() {
        return y1;
    }

    /**
     * Set y1 and update the bbox string.
     * @param y1 
     */
    @Override
    public void setY1(int y1) {
        this.y1 = y1;
        bboxUpdate();
    }

    /**
     * Get y2.
     * @return y2. 
     */
    @Override
    public int getY2() {
        return y2;
    }

    /**
     * Set y2 and update the bbox string.
     * @param y2 
     */
    @Override
    public void setY2(int y2) {
        this.y2 = y2;
        bboxUpdate();
    }
    
    /**
     * Update the bbox string using the current values of the coordinates.
     */
    private void bboxUpdate(){
        StringBuilder sb=new StringBuilder("bbox ");
        bbox=sb.append(x1).append(" ").append(y1).append(" ").append(x2).append(" ").append(y2).toString();
    }
    
}
