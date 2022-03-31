package com.epam.information_handling.logic;

import com.epam.information_handling.entity.TextElement;
import com.epam.information_handling.entity.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A class for solving task No.3. Find a word in the first sentence that is not
 * in any of the other sentences.
 */
public class Variant3 {
    private static final Logger logger =
            LogManager.getLogger(Variant3.class);

    private static final String REMAIN_ONLY_LETTERS_WORDS_REG_EX = "(\\d(.+)" +
            "?)|(\\u0020)?";
    private static final String EXCEPT_CODEBLOCKS_REG_EX = "^(void|\\nclass|1" +
            "\\.\\u0020(.+))(.+)";
    private static final String TO_WORDS_REG_EX = "(,|\\.|:|;)?(\\u0020)";

    /**
     * <p></p>The method gets the entire text as an input in the form of a Text
     * object. We get the words of the first sentence in the form of
     * List<TextElement> listOfFirstSentenceWords. </p>
     *
     * <p>We get all the words of the text (with the exception of the words of
     * the first sentence and code blocks), remove extra characters (spaces,
     * dots, commas, etc.) in the form of an array String[] all Words are.</p>
     *
     * <p> In the cycle, we go  through each word of the first sentence, and
     * in the inner cycle we compare it with each word from allWordsArr. If
     * there is a match, then we interrupt the cycle. If there are no
     * matches, the word is found.</p>
     *
     * @param text All text in the form of a Text text object
     * @return String resultBuilder. Contains all information about the first
     * line, about the presence or absence of matches of each word of the
     * first line
     */
    public String findUniqueWord(Text text) {

        Optional<Text> textOpt = Optional.ofNullable(text);
        text = textOpt.orElse(new Text(new ArrayList<>()));

        logger.debug("Start doing Variant3");

        String result = "";
        StringBuilder resultBuilder = new StringBuilder();

        // I create and fill in the sheet with the words of the first sentence
        List<TextElement> listOfFirstSentenceWords =
                text.getListOfTexts().get(0).getListOfTexts().get(0).getListOfTexts();

        resultBuilder =
                resultBuilder.append("First line is: ")
                        .append(text.getListOfTexts()
                                .get(0)
                                .getListOfTexts()
                                .get(0).getValue().append("\n"));

        logger.debug("Got the first sentence and put it into resultBuilder");

        String[] allWordsArr = getAllWordsOfText(text);

        // In this cycle, I walk through the list of words from the first
        // sentence, excluding words-numbers, spaces.
        // I check every word from the first sentence through the passing of
        // the array with all the words of the text. If there is a match,
        // then we interrupt the cycle and assign the boolean
        // variable isEquals the value true. After the cycle, we check this
        // boolean variable. If it remains false, then there were no matches,
        // then this is the word you are looking for.
        logger.debug("Starting parsing the text for match every word from the" +
                " first sentence");
        for (TextElement word : listOfFirstSentenceWords) {
            String wordToFind = new String(word.getValue());
            if (!wordToFind.matches(REMAIN_ONLY_LETTERS_WORDS_REG_EX)) {
                boolean isEquals = false;

                for (String wordFromArr : allWordsArr) {
                    if (wordToFind.trim().equals(wordFromArr.trim())) {
                        resultBuilder.append("\"")
                                .append(wordToFind)
                                .append("\" is not a required word \n");
                        isEquals = true;
                        break;
                    }
                }
                if (isEquals == false) {
                    resultBuilder.append("The required word is [")
                            .append(wordToFind)
                            .append("]");
                }
            }
            result = new String(resultBuilder);
        }
        logger.debug("Parsing text for the first sentence words match has " +
                "been finished");
        logger.debug("Variant3 has been completed");

        return result;
    }

    /**
     * You need to go through all the words of the text (except Code Blocks)
     * to do this, I go through a list of Text blocks and filter out those
     * with the first word "class" or "void". And we also exclude the first
     * sentence, because it should not participate in parsing, so that words
     * are not duplicated. I sequentially throw all paragraphs into the
     * StringBuilder allWords, split allWords into words via split() and
     * return the resulting array.
     *
     * @param text
     * @return String[] allWordsArr Все слова текста
     */
    public String[] getAllWordsOfText(Text text) {

        Optional<Text> textOpt = Optional.ofNullable(text);
        text = textOpt.orElse(new Text(new ArrayList<>()));

        // In order not to create too many objects through String
        // concatenation of strings, we will work with the StringBuilder
        // variable
        StringBuilder allWords = new StringBuilder();

        logger.debug("Starting forming an array of all the words of the text" +
                "for parsing them for the first sentence words match");
        for (TextElement textBlock : text.getListOfTexts()) {
            if (!textBlock.getValue().substring(0, 10).matches(EXCEPT_CODEBLOCKS_REG_EX)) {
                allWords = allWords.append(textBlock.getValue());
            }
        }

        String allWordsString = new String(allWords);

        // I break the whole text into words, excluding spaces, dots, colons
        // and semicolons
        String[] allWordsArr = allWordsString.split(TO_WORDS_REG_EX);

        logger.debug("The Array of all text words has been formed");

        return allWordsArr;
    }


}
