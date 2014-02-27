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

import java.util.Collection;

/**
 * This is the Composite in the Composite pattern.
 * It is composed by a Collection of Components of Type T.
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public interface Composite<T extends Component<?>,C extends Collection<T>> extends Component<T>{
    
    public final boolean atomic=false;
    
    /**
     * Get the collection of components.
     * @return the collection of components.
     */
    public C getChildren();
    
    /**
     * Set the collection of components.
     * @param collection the collection of components.
     */
    public void setChildren(C collection);
}
