package com.epam.information_handling.logic;

import com.epam.information_handling.entity.TextElement;
import com.epam.information_handling.entity.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

/**
 * A class for solving task No. 14. In a given text, find a substring of
 * maximum length that is a palindrome, i.e. read from left to right and from
 * right to left equally.
 */
public class Variant14 {
    private static final Logger logger =
            LogManager.getLogger(Variant14.class);

    private static final String ONLY_LETTERS = "((\\u0020?\\()|(\\)" +
            "\\u0020?)|(\\s+)|(-)|([:\\.,\";'](\\u0020)?))";
    private static final String EXCEPT_CODEBLOCKS_REG_EX = "^(void|\\nclass|1\\.\\u0020(.+))(.+)";

    /**
     * <p>The method gets the entire text as a Text text object. We get the
     * entire text as a String using the getAllTextWithoutCode method. We go
     * through each line of text using Scanner. We remove the extra
     * characters, leaving only a solid string of letters without spaces,
     * commas, etc.</p>
     *
     * <p>Creating a reverse string. Creating a narrowing cycle from the length
     * of the string to the lengthLargestPalindrome.
     * To begin with, lengthLargestPalindrome is set to 3.</p>
     *
     * <p>We make an internal cycle from 0 to the length of the string.
     * Substrings are created from the main string and the reverse string.</p>
     *
     * <p>First, the substrings are equal to the size of the string. Then they
     * are reduced by 1 and possible matches are checked, passing through the
     * entire line. And so lower and lower by 1 until we reach the
     * coincidence of the substring and the reverse substring, which
     * indicates the finding of a palindrome. We fix the palindrome and its
     * lengthLargestPalindrome.</p>
     *
     * <p>We switch to the next line and repeat the narrowing cycle again from
     * the length of the new line to the new lengthLargestPalindrome value
     * (there is no need in checking palindromes with length <
     * lengthLargestPalindrome, because the task condition says to find
     * the length of the largest palindrome).</p>
     *
     * <p>After passing the strings, the method returns String
     * largestPalindrome, lengthLargestPalindrome and the string itself on
     * which the palindrome is located.</p>
     *
     * @param text All text in the form of an object Text
     * @return StringBuilder containing a palindrome, its length and the
     * string on which it was found</p>
     */
    public StringBuilder findPalindrome(Text text) {
        Optional<Text> textOpt = Optional.ofNullable(text);
        text = textOpt.orElse(new Text(new ArrayList<>()));

        logger.debug("Start doing Variant14");
        String textStr = getAllTextWithoutCode(text);
        Scanner sc = new Scanner(textStr);

        String subStr;
        String subStrReverse;
        String lineWithLargestPalindrome = "";

        int lengthLargestPalindrome = 3;
        String largestPalindrome = "";
        logger.debug("Starting parsing String text for finding the largest " +
                "palindrome and its size");
        while (sc.hasNextLine()) {
            String line = sc.nextLine();

            // Creating a solid string of letters
            String str = line.replaceAll(ONLY_LETTERS, "").toLowerCase();

            // Creating a reverse string
            String strReverse = new StringBuilder(str).reverse().toString().toLowerCase();

            // The cycle of creating substrings with decreasing size from the
            // size of the entire string to the size of the largest
            // palindrome found
            for (int i = str.length(); i >= lengthLargestPalindrome; i--) {
                boolean bool = false;

                for (int j = 0; j <= str.length() - i; j++) {
                    subStr = str.substring(j, i + j);
                    subStrReverse =
                            strReverse.substring(str.length() - i - j,
                                    str.length() - j);

                    // If a palindrome is found, bool = true
                    bool = subStr.equals(subStrReverse);

                    // If a palindrome is found, then we fix its value, its
                    // size and its string.
                    if (bool) {
                        lengthLargestPalindrome = subStr.length();
                        largestPalindrome = subStr;
                        lineWithLargestPalindrome = line;
                        break;
                    }
                }
                if (bool) {
                    break;
                }
            }
        }

        logger.debug("Largest palindrome has been found");

        return new StringBuilder("The largest palindrome: [")
                .append(largestPalindrome)
                .append("]")
                .append("\n")
                .append("Length of the largest palindrome: [")
                .append(lengthLargestPalindrome).append("]")
                .append("\n")
                .append("In the line: ")
                .append(lineWithLargestPalindrome);
    }

    /**
     * The method gets the text as a Text object
     *
     * @param text text as a Text object
     * @return Весь String text without code blocks
     */
    public String getAllTextWithoutCode(Text text) {
        Optional<Text> textOpt = Optional.ofNullable(text);
        text = textOpt.orElse(new Text(new ArrayList<>()));

        StringBuilder allText = new StringBuilder();
        logger.debug("Start getting all text without CodeBlocks in String");
        for (TextElement textBlock : text.getListOfTexts()) {
            if (!textBlock.getValue().substring(0, 10).matches(EXCEPT_CODEBLOCKS_REG_EX)) {
                allText = allText.append(textBlock.getValue());
            }
        }
        logger.debug("String text without CodeBlocks has been got");
        return new String(allText);
    }
}


