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
package eu.himeros.cophi.ocr.proofreader.controller.pojo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import org.xmldb.api.base.Collection;

/**
 *
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public class BookExistDescriptor extends Descriptor<Map<String, String>, String, String, Collection> {

    @Override
    public void initRepository() {
        try {
            Collection col = repository.getChildCollection(repositoryAddress.get("book"));
            references = new ArrayList<>();
            for (String page : col.listResources()) {
                if(page.endsWith(filter)){
                    references.add(page);
                }
            }
            Collections.sort(references);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
}
