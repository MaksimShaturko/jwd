package com.epam.information_handling.parser;

import com.epam.information_handling.entity.Sentence;
import com.epam.information_handling.entity.TextElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * The ParserParagraph class divides a Paragraph into Sentences
 */
public class ParserParagraph implements Parser {
    private static final Logger logger =
            LogManager.getLogger(ParserParagraph.class);

    //Regular expressions for Paragraph search
    private static final String ONE_SENTENCE_PARAGRAPH_REG_EX = "(.+(:)$)|^[1].+";
    private static final String ONE_SENTENCE_REG_EX = "((\\.\\s))";

    private Parser parseSentence = new ParserSentence();

    /**
     * <p>The method gets a Paragraph as a String as input.</p>
     *
     * <p>We check the conditions. If the text of the Paragraph matches the
     * regular expression ONE_SENTENCE_PARAGRAPH_REG_EX, then this is a
     * paragraph with one sentence and then we will add all this text to the
     * List<String> listOfSentences. You will get a list with one String
     * element. </p>
     *
     * <p>A Sentence object is created from it. We will add this
     * object to the List<TextElement> listOfSentencesObjects. You will also
     * get a list with a single Sentence object.
     * The method will return this list, based on this list with one
     * Sentence object, a Paragraph object will be created (in the parse()
     * method of the ParserText class).</p>
     *
     * <p>If the Paragraph text does not match the regular expression
     * ONE_SENTENCE_PARAGRAPH_REG_EX, then this is a paragraph with several
     * sentences and we divide them with the regular expression
     * ONE_SENTENCE_REG_EX.</p>
     *
     * <p>We fill in List<String> listOfSentences, then going through each
     * sentence from listOfSentences we create Sentence objects and fill in
     * List<TextElement> listOfSentencesObjects with them, which our method
     * returns.</p>
     *
     * @param paragraph The text of the paragraph in the form of a String
     * @return List<TextElement> listOfSentencesObjects с объектами Sentence
     */
    @Override
    public List<TextElement> parse(String paragraph) {

        Optional<String> textOpt = Optional.ofNullable(paragraph);
        paragraph = textOpt.orElse("");

        List<String> listOfSentences = new ArrayList<>();
        List<TextElement> listOfSentencesObjects = new ArrayList<>();

        logger.debug("Start dividing String paragraph into Sentences and " +
                "forming List<TextElement> of Sentences");

        // We check the condition whether the Paragraph text matches the
        // regular expression. If yes, then this is a paragraph with one
        // sentence
        if (paragraph.matches(ONE_SENTENCE_PARAGRAPH_REG_EX)) {
            listOfSentences.add(paragraph);
        } else {

            // If the first condition did not pass, then we divide the entire
            // text in the paragraph by regular ex ONE_SENTENCE_REG_EX
            String[] tempArr = paragraph.split(ONE_SENTENCE_REG_EX);
            Arrays.stream(tempArr).forEach(listOfSentences::add);
        }

        listOfSentences.stream().forEach(sentence ->
                listOfSentencesObjects.add(new Sentence(parseSentence.parse(sentence))));

        logger.debug("List<TextElement> of Sentences has been formed:",
                listOfSentencesObjects);

        return listOfSentencesObjects;
    }
}
