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

package eu.himeros.cophi.core.model;

import java.io.Serializable;

/**
 * This is the Component in the Composite pattern.
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public interface Component<T> extends Serializable{
   
    /**
     * Determine whether the component is atomic (a leaf) or not (a composite).
     * Subinterfaces Atom and Composite provide a final boolean,
     * which is true for Atom and false for Composite.
     * Interfaces and Classes that extend or implement them must override
     * this method, returning the atomic value.
     * @return 
     */
    public boolean isAtomic();

}
