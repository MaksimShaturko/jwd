package com.epam.information_handling.logic;

import com.epam.information_handling.entity.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <p>A class for solving task No.8. The words of the text starting with
 * vowel letters should be sorted alphabetically by the first consonant
 * letter of the word.</p>
 */
public class Variant8 {
    private static final Logger logger =
            LogManager.getLogger(Variant8.class);
    private static final String VOWELS_REG_EX = "^[AaEeIiOoUuYy].+";

    /**
     * <p>The method gets the entire text as a Text text object. We get all
     * the words of the text using the getAllWordsOfText method, which is in
     * the Variant 3 class. </p>
     *
     * <p>We get a List<String> listWordsStartWithVowels of
     * words starting with vowels using the getWordsStartWithVowels method.</p>
     *
     * @param text All text in the form of a Text text object
     * @return List<String> listWordsStartWithVowels sorted by consonant letters
     */
    public List<String> sortVowelByConsonant(Text text) {
        Optional<Text> textOpt = Optional.ofNullable(text);
        text = textOpt.orElse(new Text(new ArrayList<>()));

        logger.debug("Start doing Variant8");

        Variant3 variant3 = new Variant3();
        String[] allWords = variant3.getAllWordsOfText(text);

        List<String> listWordsStartWithVowels =
                getWordsStartWithVowels(allWords);

        Collections.sort(listWordsStartWithVowels,new SorterByConsonants());

        logger.debug("Words starting with vowels has been sorted by " +
                "consonants");
        logger.debug("Variant8 has been finished");

        return listWordsStartWithVowels;
    }

    /**
     * <p>The method gets an array of all words of the text in the form of
     * String[]. We check each word for compliance with the regular
     * expression VOWELS_REG_EX. All the words that pass the check add into
     * the final list, which is returned by the method.</p>
     *
     * @param words Array of all words of the text
     * @return List<String> listWordsStartWithVowels all words starting with
     * vowels
     */
    public List<String> getWordsStartWithVowels(String[] words) {
        Optional<String[]> textOpt = Optional.ofNullable(words);
        words = textOpt.orElse(new String[]{""});

        logger.debug("Start forming list of words starting with vowels");

        List<String> listWordsStartWithVowels =
                Arrays.stream(words)
                        .filter(word -> word
                                .matches(VOWELS_REG_EX))
                                .collect(Collectors.toList());

        logger.debug("List of words starting with vowels has been formed");
        return listWordsStartWithVowels;
    }
}

/**
 * A class for sorting.
 *
 * We look for the first consonant letter in each word and compare them. A
 * word without consonants will go earlier than with a consonant.
 */
class SorterByConsonants implements Comparator<String>{
    private final static String CONSONANTS_REG_EX = "[BbCcDdFfGgHhJjKkLlMmNnPpQqRrSsTtVvWwXxZz]";

    @Override
    public int compare(String s1, String s2) {
        Pattern consonant = Pattern.compile(
                CONSONANTS_REG_EX);
        Matcher matcher1 = consonant.matcher(s1);
        Matcher matcher2 = consonant.matcher(s2);

        if (matcher1.find() && matcher2.find()) {
            if (matcher1.group().getBytes()[0] > matcher2.group().getBytes()[0]) {
                return 1;
            }
            if (matcher1.group().getBytes()[0] < matcher2.group().getBytes()[0]) {
                return -1;
            }
        }
        if (matcher1.find() && !matcher2.find()) {
            return 1;
        }
        if (!matcher1.find() && matcher2.find()) {
            return -1;
        }
        return 0;
    }
}
