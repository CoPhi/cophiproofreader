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

package eu.himeros.cophi.core.model;

import java.io.Serializable;

/**
 * Item wraps an object of class T, associating an int id to it.
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public class Item<T> implements Serializable{
    
    private int id;
    private T content;

    /**
     * Constructor that sets both the parameters.
     * @param id the int identifier.
     * @param content the object of class T.
     */
    public Item(int id, T content){
        this.id=id;
        this.content=content;
    }
    
    /**
     * Get the id.
     * @return the id.
     */
    public int getId() {
        return id;
    }

    /**
     * Set the id.
     * @param id the id.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the object of class T.
     * @return the object of class T.
     */
    public T getContent() {
        return content;
    }

    /**
     * Set the object of class T.
     * @param content the object of class T.
     */
    public void setContent(T content) {
        this.content = content;
    }

}
