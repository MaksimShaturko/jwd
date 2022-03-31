package com.epam.information_handling.entity;

import java.util.List;
import java.util.Objects;

/**
 * Sentence implements TextElement
 *
 * <p>An entity class, which is a class containing a variable with a list of
 * words, that form a sentence.</p>
 *
 * <p>It is divided into Words objects. They are contained in the form of
 * a List<TextElement> listOfWords, which make up this Sentence object</p>
 *
 * @author Maksim Shaturko
 */
public class Sentence implements TextElement {
    private List<TextElement> listOfWords;

    /**
     * Constructor initialize List<TextElement> listOfWords
     *
     * @param words words (List<TextElement>), that make up this Sentence
     */
    public Sentence(List<TextElement> words) {
        listOfWords = words;
    }

    @Override
    public void add(TextElement textElement) {
        listOfWords.add(textElement);
    }

    /**
     * This method passes through the entire listOfWords, the getValue()
     * method of each Word is called on each object inside the list
     * (Word), thereby collecting the entire Sentence in the form in
     * which it was originally
     *
     * @return Sentence as a StringBuilder string assembled from the
     * StringBuilder value of each Word
     */
    @Override
    public StringBuilder getValue() {
        StringBuilder result = new StringBuilder();

        listOfWords.stream().forEach(s -> result.append(s.getValue())
                                                         .append(" "));
        return result;
    }

    @Override
    public List<TextElement> getListOfTexts() {
        return listOfWords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return Objects.equals(listOfWords, sentence.listOfWords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listOfWords);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Sentence{");
        sb.append("listOfWords=").append(listOfWords);
        sb.append('}');
        return sb.toString();
    }
}
