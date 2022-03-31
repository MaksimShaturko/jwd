package com.epam.information_handling.view;

import com.epam.information_handling.entity.TextElement;
import com.epam.information_handling.entity.Text;
import com.epam.information_handling.logic.Variant14;
import com.epam.information_handling.logic.Variant3;
import com.epam.information_handling.logic.Variant8;
import com.epam.information_handling.parser.ParserText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Logger logger =
            LogManager.getLogger(Main.class);

    public static void main(String[] args) throws NewException {
        logger.info("The application has been started");

        // Here we will write the text from the file
        StringBuilder textBuilder = new StringBuilder();

        // Here we write the text from the file in textBuilder
        try {
            File inputFile = new File("src/main/resources/InputText.txt");
            Scanner sc = new Scanner(inputFile);
            while (sc.hasNextLine()) {
                textBuilder = textBuilder.append(sc.nextLine()).append("\n");
            }
            sc.close();
        } catch (FileNotFoundException e) {
            logger.error("The main file InputText.txt was not found. App will" +
                    " be stopped.");
            throw new NewException("The main file InputText.txt was not found" +
                    ". App can't continue its work. It will be stopped");
        }

        String inputText = new String(textBuilder);
        ParserText parserText = new ParserText();

        // We pass the entire text inside the String to the
        // parserText.parse() to get the entire text in the form of a list
        // of TextElement entities (Paragraphs and Code Blocks)
        List<TextElement> listOfTextBlocksObjects = parserText.parse(inputText);

        Optional<List<TextElement>> optionalList =
                Optional.ofNullable(listOfTextBlocksObjects);
        listOfTextBlocksObjects = optionalList.orElse(new ArrayList<>());

        // We get all the text in one Text entity
        Text text =
                new Text(listOfTextBlocksObjects);

        // We return the entire text as a String from the Text entity using
        // the getValue method, arranged according to the Composite pattern
        logger.debug("Start building the output text");


        String outputText = text.getValue().toString();
        Optional<String> textOpt = Optional.ofNullable(outputText);
        outputText = textOpt.orElse("");
        logger.debug("The output text has been built");

        // We write the entire text in a file. If the directory is
        // incorrect, the text will be output to the console
        try {
            File outputFile = new File("src/main/resources/OutputText.txt");
            PrintWriter pr = new PrintWriter(outputFile);
            pr.println(outputText);
            pr.close();

        } catch (FileNotFoundException e) {
            logger.error("The output file was not found. The output text will" +
                    " be output to console");
            System.out.println(outputText);
        }

        // Solving task #3. Find a word in the first sentence that is not in any
        // of the other sentences. We get the result in the form of String
        Variant3 variant3 = new Variant3();
        String resultOfVariant3 = variant3.findUniqueWord(text);
        Optional<String> textVariant3 = Optional.ofNullable(resultOfVariant3);
        resultOfVariant3 = textVariant3.orElse("");
        // Writing the result to a file Variant3.txt. If the directory is
        // incorrect, the result will be displayed on the console
        try {
            File variant3File = new File("src/main/resources/Variant3.txt");
            PrintWriter pr = new PrintWriter(variant3File);
            pr.println(resultOfVariant3);
            pr.close();

        } catch (FileNotFoundException e) {
            logger.error("The output file was not found -> The" +
                    "result of Variant3 will be output to the console");
            System.out.println(resultOfVariant3);
        }

        // Solving task #8. The words of the text starting with
        // vowel letters should be sorted alphabetically by the first consonant
        // letter of the word.
        // We get the result in the form of a list of sorted words
        Variant8 variant8 = new Variant8();
        List<String> listOfSortedWords =
                variant8.sortVowelByConsonant(text);

        Optional<List<String>> optionalSortedList =
                Optional.ofNullable(listOfSortedWords);
        listOfSortedWords = optionalSortedList.orElse(new ArrayList<>());
        // Writing the result to a file Variant8.txt. If the directory is
        // incorrect, the result will be displayed on the console
        try {
            File variant8File = new File("src/main/resources/Variant8.txt");
            PrintWriter pr = new PrintWriter(variant8File);
            pr.println(listOfSortedWords);
            pr.close();

        } catch (FileNotFoundException e) {
            logger.error("The output file was not found -> The" +
                    "result of Variant8 will be output to the console");
            System.out.println(listOfSortedWords);
        }

        // Solving problem #14. In a given text, find a substring of maximum
        // length that is a palindrome, i.e. read from left to right and from
        // right to left equally. We get the result in the form of StringBuilder
        Variant14 variant14 = new Variant14();
        StringBuilder largestPalindrome = variant14.findPalindrome(text);
        Optional<StringBuilder> textVariant14 =
                Optional.ofNullable(largestPalindrome);
        largestPalindrome = textVariant14.orElse(new StringBuilder());

        // Writing the result to a file Variant14.txt. If the directory is
        // incorrect, the result will be displayed on the console
        try {
            File variant14File = new File("src/main/resources/Variant14.txt");
            PrintWriter pr = new PrintWriter(variant14File);
            pr.println(largestPalindrome);
            pr.close();
        } catch (FileNotFoundException e) {
            logger.error("The output file was not found -> The" +
                    "result of Variant14 will be output to the console");
            System.out.println(largestPalindrome);
        }

        logger.info("The app has been successfully finished");
    }
}

class NewException extends Exception {
    NewException(String description) {
        super(description);
    }
}