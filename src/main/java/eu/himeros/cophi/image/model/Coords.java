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

import java.io.Serializable;

/**
 * Coords provides the coordinates of a box.
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public interface Coords extends Serializable{
    
    /**
     * Get the x1 coordinate.
     * @return the x1 coordinate.
     */
    public int getX1();
    
    /**
     * Set the x1 coordinate.
     * @param x1 the x1 coordinate.
     */
    public void setX1(int x1);
    
    /**
     * Get the y1 coordinate.
     * @return the y1 coordinate.
     */
    public int getY1();
    
    /**
     * Set the y1 coordinate.
     * @param y1 the y1 coordinate.
     */
    public void setY1(int y1);
    
    /**
     * Get the x2 coordinate.
     * @return the x2 coordinate.
     */
    public int getX2();
    
    /**
     * Set the x2 coordinate.
     * @param x2 the x2 coordinate.
     */
    public void setX2(int x2);
    
    /**
     * Get the y2 coordinate.
     * @return the y2 coordinate.
     */
    public int getY2();
    
    /**
     * Set the y2 coordinate.
     * @param y2 the y2 coordinate.
     */
    public void setY2(int y2);    
    
}
