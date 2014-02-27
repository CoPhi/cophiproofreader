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

package eu.himeros.cophi.text.model;

import eu.himeros.cophi.core.model.Atom;
import eu.himeros.cophi.core.model.Physical;
import eu.himeros.cophi.core.model.TextualUnit;

/**
 * The physical textual atom.
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public abstract class Token<T> implements TextualUnit, Physical, Atom<T> {
    
    protected T content; 
    
    /**
     * Get the string that represents this token.
     * @return the content.
     */
    @Override
    public T getContent() {
        return content;
    }

    /**
     * Set the string that represents this token.
     * @param content the content.
     */
    @Override
    public void setContent(T content) {
        this.content=content;
    }

    /**
     * Return true.
     * @return true.
     */
    @Override
    public boolean isAtomic() {
        return atomic;
    }
    
}
