package com.epam.information_handling.parser;

import com.epam.information_handling.entity.CodeElement;
import com.epam.information_handling.entity.TextElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * The ParserCodeBlock class divides Code Blocks into Code Elements
 */
public class ParserCodeBlock implements Parser {
    private final static String WHITESPACE = "\\u0020";
    private static final Logger logger =
            LogManager.getLogger(ParserCodeBlock.class);

    /**
     * <p>The method gets a block of Code as a String as input.
     * We divide the Code Block into elements using the split() method. We
     * fill List<TextElement> listOfCodeElementsObjects with CodeElement
     * objects containing text in the form of a StringBuilder value variable
     * .</p>
     *
     * <p>Based on the listOfCodeElementsObjects in the ParserText class in the
     * parse method a CodeBlock object will be created </p>
     *
     * @param codeBlock the text of the Code Block in the form String
     * @return List<TextElement> listOfCodeElementsObjects with CodeElement
     * objects
     */
    @Override
    public List<TextElement> parse(String codeBlock) {
        Optional<String> textOpt = Optional.ofNullable(codeBlock);
        codeBlock = textOpt.orElse("");

        List<TextElement> listOfCodeElementsObjects = new ArrayList<>();

        logger.debug("Start dividing String codeBlock into CodeElements and " +
                "forming List<TextElement> of CodeElements");

        String[] tempCodeBlocks = codeBlock.split(WHITESPACE);
        Arrays.stream(tempCodeBlocks)
              .forEach(codeElement -> listOfCodeElementsObjects
              .add(new CodeElement((new StringBuilder(codeElement)))));

        logger.debug("List<TextElement> of CodeElements has been formed", listOfCodeElementsObjects);

        return listOfCodeElementsObjects;
    }
}
