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
package eu.himeros.cophi.ocr.proofreader.model;

import eu.himeros.cophi.text.model.Token;

/**
 * Abstract class extended by Insertion and Deletion. The most probable
 * alternative (=highest nlp value) is an insertion; other alternatives are
 * deletions.
 *
 * @author federico[DOT]boschetti[DOT]73[AT]gmail[DOT]com
 */
public class Alternative extends Token<String> {

    protected String text;
    protected String nlp;
    protected double nlpDbl;
    protected String nlpStyleClass;
    protected String nlpColor;

    /**
     * Get the text of the alternative.
     *
     * @return the text of the alternative.
     */
    public String getText() {
        return text;
    }

    /**
     * Set the text of the alternative.
     *
     * @param text the text of the alternative.
     */
    public void setText(String text) {
        //FB START -- PAY ATTENTION: WORKAROUND!!!
        text=text.replaceAll("’([,·.]’)","$1");
        text=text.replaceAll("☛","♻");
        //FB STOP -- PAY ATTENTION: WORKAROUND!!!
        this.text = text;
    }
    
    /**
     * Get content
     * @return the content
     */
    //TODO: getText() should be substituted by this method
    @Override
    public String getContent(){
        return getText();
    }

    /**
     * Set content
     * @param content the content 
     */
    //TODO: setText() should be substituted by this method
    @Override
    public void setContent(String content){
        setText(content);
    }

    /**
     * Get the nlp evaluation related to the alternative.
     *
     * @return
     */
    public String getNlp() {
        return nlp;
    }

    /**
     * Set the nlp (i.e. natural language processing evalution) related to the
     * alternative. styleClass and colors are automatically updated.
     *
     * @param nlp
     */
    //TODO: colors are not necessary: they can be expressed in the .css file.
    public void setNlp(String nlp) {
        this.nlp = nlp;
        String[] labelValue = nlp.split(" ");
        nlpDbl = Double.parseDouble(labelValue[1]);
        nlpStyleClass = nlp.replaceAll("[ \\.]", "");
        switch (nlpStyleClass) {
            case "nlp100":
                nlpColor = "black";
                break;
            case "nlp099":
                nlpColor = "blue";
                break;
            case "nlp098":
                nlpColor = "brown";
                break;
            case "nlp097":
                nlpColor = "red";
                break;
            case "nlp070":
                nlpColor="green";
                break;
            case "nlp010":
                nlpColor="gray";
                break;
            default:
                nlpColor = "red";
        }
    }

    /**
     * Get the nlp evaluation expressed by a double related to this alternative.
     * @return the nlp evalution expressed by a double.
     */
    public double getNlpDbl() {
        return nlpDbl;
    }

    /**
     * Set the nlp evaluation expressed by a double related to this alternative.
     * @param nlpDbl the nlp evaluation expressed by a double.
     */
    //TODO: manage it by properties file
    public void setNlpDbl(double nlpDbl) {
        this.nlpDbl = nlpDbl;
        switch ((int) (nlpDbl * 100)) {
            case 100:
                nlp = "nlp 1.00";
                nlpColor = "black";
                break;
            case 99:
                nlp = "nlp 0.99";
                nlpColor = "blue";
                break;
            case 98:
                nlp = "nlp 0.98";
                nlpColor = "brown";
                break;
            case 97:
                nlp = "nlp 0.97";
                nlpColor = "red";
                break;
            case 70:
                nlp="nlp 0.70";
                nlpColor="green";
                break;
            case 10:
                nlp="nlp 0.10";
                nlpColor="gray";
                break;
            default:
                nlpColor = "red";
        }
        nlpStyleClass = nlp.replaceAll("[ \\.]", "");
    }

    /**
     * Get the nlp style class related to this alternative, 
     * in order to render it by different colors.
     * @return the nlp style class.
     */
    public String getNlpStyleClass() {
        return nlpStyleClass;
    }

    /**
     * Set the nlp style class related to this alternative,
     * in order to render it by different colors.
     * @param nlpStyleClass the nlp style class.
     */
    //TODO: manage it by properties file
    public void setNlpStyleClass(String nlpStyleClass) {
        this.nlpStyleClass = nlpStyleClass;
                switch (nlpStyleClass) {
            case "nlp100":
                nlpDbl = 1.00;
                nlpColor = "black";
                break;
            case "nlp099":
                nlpDbl = 0.99;
                nlpColor = "blue";
                break;
            case "nlp098":
                nlpDbl=0.98;
                nlpColor = "brown";
                break;
            case "nlp097":
                nlpDbl=0.97;
                nlpColor = "red";
                break;
            case "nlp070":
                nlpDbl=0.70;
                nlpColor="greek";
                break;
            case "nlp010":
                nlpDbl=0.10;
                nlpColor="gray";
                break;
            default:
                nlpColor = "red";
        }
        nlp = nlpStyleClass.replaceAll("nlp(.)(..)", "nlp $1.$2");
    }

    /**
     * Get the color associated to the nlp evaluation of the alternative.
     * @return the color associated to the nlp evaluation.
     */
    public String getNlpColor() {
        return nlpColor;
    }

    /**
     * Set the color associated to the nlp evaluation of the alternative.
     * @param nlpColor the color associated to the nlp evaluation.
     */
    public void setNlpColor(String nlpColor) {
        this.nlpColor = nlpColor;
    }
}
