package com.epam.information_handling.entity;

import java.util.List;

/**
 * Word implements TextElement
 *
 * <p>An entity class, which is a class with StringBuilder value
 * containing a StringBuilder word *
 */
public class Word implements TextElement {
    private StringBuilder value;

    /**
     * Constructor initialize StringBuilder value
     *
     * @param value StringBuilder word
     */
    public Word(StringBuilder value) {
        this.value = value;
    }

    public StringBuilder getValue(){
        return value;
    }

    @Override
    public void add(TextElement textElement) {
    }

    @Override
    public List<TextElement> getListOfTexts() {
        return null;
    }
}


