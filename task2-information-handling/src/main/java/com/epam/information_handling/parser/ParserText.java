package com.epam.information_handling.parser;

import com.epam.information_handling.entity.CodeBlock;
import com.epam.information_handling.entity.Paragraph;
import com.epam.information_handling.entity.TextElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * class ParserText implements Parser
 *
 * <p>A class designed to divide text into text blocks, which can be
 * represented as Paragraphs or Code Blocks, depending on the content.</p>
 */
public class ParserText implements Parser {
    private static final Logger logger =
            LogManager.getLogger(ParserText.class);

    // Regular expression for paragraph search
    private static final String TEXT_BLOCK_REG_EX = "(.+(\\.|:)$)|(^\\}.*)|(^[1].+)";

    // A regular expression to search for a block of code
    private final static String EXCEPT_CODEBLOCKS_REG_EX = "^(void|\\nclass)(.+)";

    private Parser parserParagraph = new ParserParagraph();
    private Parser parserCodeBlock = new ParserCodeBlock();

    /**
     * <p>The parse() method receives the entire text as a String as input. We
     * go through each line of text using the Scanner class. We determine
     * whether the string coincides with a regular expression denoting the end
     * of a Paragraph or a Block of Code.</p>
     *
     * <p>If a string does not match with a regular expression, we accumulate
     * these strings to the line when a match with the regular expression
     * occurs. Then we add the accumulated strings in the form of a single
     * text variable to List<String> listOfTextBlocks.</p>
     *
     * <p>Next comes the formation of text blocks in the form of objects of the
     * Text implements TextElement class.</p>
     *
     * <p>We go through the List<String> listOfTextBlocks, check which type of
     * text blocks this text belongs to (Paragraph or Code Block) and based
     * on this we call the parseCodeBlock() or parseParagraph() method to
     * divide the text into Code Elements and Sentences, respectively.
     * parseCodeBlock() returns a list of CodeElements, which allows you to
     * create a CodeBlock object and add it to the List<Text element>
     * listOfTextBlocks.</p>
     *
     * <p>parserParagraph returns a list of Sentences, which allows you to
     * create a Paragraph object and add it to the List<TextElement>
     * listOfTextBlocks.</p>
     *
     * <p>Generated List<TextElement> listOfTextBlocks (consists of CodeBlock
     * and Paragraph objects. We return from the parse() method to the main()
     * method of the Main class to create an object of the Text class.</p>
     *
     * @param text All text in the form of a variable String
     * @return formed List<TextElement> listOfTextBlocks
     */
    @Override
    public List<TextElement> parse(String text){

        Optional<String> textOpt = Optional.ofNullable(text);
        text = textOpt.orElse("");

        Scanner sc = new Scanner(text);

        // Creating a String list from text blocks
        List<String> listOfTextBlocks = new ArrayList<>();

        String line;
        StringBuilder tempResult = new StringBuilder();

        logger.debug("Start dividing input text into String text blocks");

        // We go through each line to determine Text blocks (Paragraphs or
        // Code Blocks)
        while (sc.hasNextLine()) {
            line = sc.nextLine();

            if (line.matches(TEXT_BLOCK_REG_EX)) {
                tempResult = tempResult.append(line);

                listOfTextBlocks.add(tempResult.toString());
                tempResult = new StringBuilder();
                continue;
            } else {

                //we did not pass either the first or the second condition -
                // we accumulate these lines
                tempResult = tempResult.append(line).append("\n");
            }
        }

        sc.close();

        logger.debug("String text blocks have been formed");

        // Creating a List<TextElement>, which should return the method
        List<TextElement> listOfTextBlocksObjects = new ArrayList<>();

        logger.debug("The app starts forming List<TextElement> of Text Blocks" +
                " " +
                "(Paragraphs and CodeBlocks)");


        // We go through each text block. We define it as a Paragraph or a
        // Block of Code. Depending on this, we call the appropriate method
        // to divide the text into sentences (needed to create a Paragraph
        // object) or code elements (needed to create a Code Block object).
        for (String textBlock: listOfTextBlocks) {

            if(textBlock.substring(0,10).matches(EXCEPT_CODEBLOCKS_REG_EX)){
                List<TextElement> listOfCodeElementsObjects = parserCodeBlock.parse(textBlock);

                listOfTextBlocksObjects.add(new CodeBlock(listOfCodeElementsObjects));

            } else {
                List<TextElement> listOfSentencesObjects =
                        parserParagraph.parse(textBlock);

                listOfTextBlocksObjects.add(new Paragraph(listOfSentencesObjects));
            }
        }

        logger.debug("The List<TextElement> of Text Blocks (Paragraphs and " +
                "CodeBlocks) has been formed");

        return listOfTextBlocksObjects;
    }
}
