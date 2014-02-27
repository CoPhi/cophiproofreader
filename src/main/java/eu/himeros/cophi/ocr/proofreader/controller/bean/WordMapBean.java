/*
 * This file is part of CoPhiProofReader
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

package eu.himeros.cophi.ocr.proofreader.controller.bean;

import eu.himeros.cophi.ocr.proofreader.model.OcrWord;
import java.util.HashMap;

/**
 *
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public class WordMapBean {
    private HashMap<String, OcrWord> wordMap=new HashMap<>();
    
    public void init(){
        wordMap=new HashMap<>();
    }
    
    public void putWord(String id,OcrWord word){
        wordMap.put(id, word);
    }
    
    public OcrWord getWord(String id){
        return wordMap.get(id);
    }
}
