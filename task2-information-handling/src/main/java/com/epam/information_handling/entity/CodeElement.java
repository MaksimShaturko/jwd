package com.epam.information_handling.entity;

import java.util.List;

/**
 * CodeElement implements TextElement
 *
 * <p>An entity class, which is a class with StringBuilder value
 * containing a StringBuilder element of program code
 *
 * @author Maksim Shaturko
 */
public class CodeElement implements TextElement {
    private StringBuilder value;

    /**
     * Constructor initialize StringBuilder value
     *
     * @param value StringBuilder code element
     */
    public CodeElement(StringBuilder value) {
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
