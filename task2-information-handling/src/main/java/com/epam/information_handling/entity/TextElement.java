package com.epam.information_handling.entity;

import java.util.List;

/**
 * Interface TextElement.
 *
 * Every class implementing this interface is an entity with a value variable
 * containing text in the form of a StringBuilder for the possibility of
 * dividing the text into parts and restoring it using the getValue() method
 *
 * When working with this interface, the Composite pattern is implemented
 */
public interface TextElement {
    StringBuilder getValue();
    void add(TextElement textElement);
    List<TextElement> getListOfTexts();

}
