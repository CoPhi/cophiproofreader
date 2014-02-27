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
package eu.himeros.cophi.ocr.proofreader.controller.pojo;

import java.util.List;

/**
 * Describes resources.
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public abstract class Descriptor<T1, T2, T3, T4> {

    T1 repositoryAddress;
    T2 filter;
    List<T3> references;
    T4 repository;

    /**
     * Get the repositoryAddress.
     * @return the repositoryAddress.
     */
    public T1 getRepositoryAddress() {
        return repositoryAddress;
    }

    /**
     * Set the repositoryAddress.
     * @param repositoryAddress the repositoryAddress.
     */
    public void setRepositoryAddress(T1 repositoryAddress) {
        this.repositoryAddress = repositoryAddress;
    }

    /**
     * Get the filter to test which components must be loaded.
     * @return the filter.
     */
    public T2 getFilter() {
        return filter;
    }

    /**
     * Set the filter to test which components must be loaded.
     * @param filter 
     */
    public void setFilter(T2 filter) {
        this.filter = filter;
    }

    /**
     * Get the references to the resources.
     * E.g. file names.
     * @return 
     */
    public List<T3> getReferences() {
        return references;
    }

    /**
     * Set the references to the resources.
     * @param references the references.
     */
    public void setReferences(List<T3> references) {
        this.references = references;
    }

    public T4 getRepository() {
        return repository;
    }

    public void setRepository(T4 repository) {
        this.repository = repository;
    }
    
    /**
     * Initialize the repositoryAddress
     */
    public abstract void initRepository();
    
}
