package com.epam.information_handling.parser;

import com.epam.information_handling.entity.TextElement;
import com.epam.information_handling.entity.Word;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * The ParserSentence class is created to divide a Sentence into Words
 */
public class ParserSentence implements Parser {
    private static final Logger logger =
            LogManager.getLogger(ParserSentence.class);

    private static final String WHITESPACE = "\\u0020";

    /**
     * <p>The method gets an input Sentence in the form of a String.
     * We divide sentences into words using the split() method.
     * We fill List<TextElement> listOfWordsObjects with Word objects
     * containing text in the form of a StringBuilder value variable.</p>
     *
     * <p>Based on the listOfWordsObjects in the ParserParagraph class in the
     * parse() method. a Sentence object will be created </p>
     *
     * @param sentence The text of the sentence in the form of a String
     * @return List<TextElement> listOfWordsObjects with Word objects
     */
    @Override
    public List<TextElement> parse(String sentence) {
        Optional<String> sentenceOpt = Optional.ofNullable(sentence);
        sentence = sentenceOpt.orElse("");

        List<TextElement> listOfWordsObjects =new ArrayList<>();

        logger.debug("Start dividing String sentence into Words and " +
                "forming List<TextElement> of Words");

        String[] tempWords = sentence.split(WHITESPACE);

        Arrays.stream(tempWords)
              .forEach(word -> listOfWordsObjects
              .add(new Word(new StringBuilder(word))));

        logger.debug("List<TextElement> of Words has been formed",
                listOfWordsObjects);

        return listOfWordsObjects;
    }
}
