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

import eu.himeros.cophi.core.model.Composite;
import eu.himeros.cophi.core.model.Physical;
import eu.himeros.cophi.core.model.TextualUnit;
import java.util.List;

/**
 * A physical composite of tokens.
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public abstract class Line<T extends Token<String>> implements TextualUnit, Physical, Composite<T, List<T>>{
    
    protected List<T> tokens;
    
    /**
     * Get the list of tokens.
     * @return the list of tokens.
     */
    @Override
    public List<T> getChildren() {
        return tokens;
    }

    /**
     * Set the list of tokens.
     * @param tokens the list of tokens.
     */
    @Override
    public void setChildren(List<T> tokens) {
        this.tokens=tokens;
    }

    /**
     * Return false;
     * @return 
     */
    @Override
    public boolean isAtomic() {
        return atomic;
    }

    
    
}
