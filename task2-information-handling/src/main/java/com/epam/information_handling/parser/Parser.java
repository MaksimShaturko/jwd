package com.epam.information_handling.parser;

import com.epam.information_handling.entity.TextElement;

import java.util.List;

/**
 * A Parser interface with a single parse method. Each type of text parser must
 * implement a Parser interface
 */
public interface Parser {

    List<TextElement> parse(String text);
}
