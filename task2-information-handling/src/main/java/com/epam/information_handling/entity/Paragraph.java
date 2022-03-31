package com.epam.information_handling.entity;

import java.util.List;

/**
 * Paragraph implements TextElement
 *
 * <p>An entity class, which is a class, containing a variable with list of
 * Sentences that form a Paragraph.
 *
 * It is divided into Sentences. They are contained in the form of a
 * List<TextElement> listOfSentences, which make up this Paragraph</p>
 *
 * @author Maksim Shaturko
 */
public class Paragraph implements TextElement {

    private List<TextElement> listOfSentences;

    /**
     * Constructor initialize List<TextElement> listOfSentences
     *
     * @param sentences List<TextElement> sentences, make up this Paragraph
     */
    public Paragraph(List<TextElement> sentences) {
        listOfSentences = sentences;
    }

    @Override
    public void add(TextElement textElement) {
        listOfSentences.add(textElement);
    }

    /**
     * This method passes through the entire listOfSentences, the getValue()
     * method of each Sentence is called on each object inside the list
     * (Sentence), thereby collecting the entire Paragraph in the form in
     * which it was originally
     *
     * @return StringBuilder Paragraph as a StringBuilder string assembled from
     * StringBuilder values of Sentences
     */
    @Override
    public StringBuilder getValue() {
        final String ONE_SENTENCE_PARAGRAPH_REG_EX = "(.+(:)$)|(^[1].+)|" +
                "(\\u0020)+.+";
        final String END_SENTENCE_REG_EX = "((\\.)?\\u0020$)";
        final String COLON_POINT_TO_COLON_REG_EX = ":\\.\\u0020$";

        StringBuilder result = new StringBuilder("");

        listOfSentences.stream().forEach(s -> {
            if(s.getValue().toString().matches(ONE_SENTENCE_PARAGRAPH_REG_EX)){
                result.append(s.getValue());
            } else {
                result.append(s.getValue().toString().replaceAll(END_SENTENCE_REG_EX,
                        ". ").replaceAll(COLON_POINT_TO_COLON_REG_EX,": "));
            }
        });
        result.append("\n");

        return result;
    }

    public List<TextElement> getListOfTexts() {
        return listOfSentences;
    }
}
