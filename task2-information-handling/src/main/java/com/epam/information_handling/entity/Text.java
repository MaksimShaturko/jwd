package com.epam.information_handling.entity;

import java.util.List;

/**
 * <p>An entity class, which is a class, containing a variable with list of
 * TextElement text blocks (Paragraph or CodeBlock).
 * It is divided into Paragraphs and CodeBlocks. They are contained in the form
 * of  List<TextElement> listOfTextBlocks, which make up all this Text</p>
 */
public class Text implements TextElement {

    private List<TextElement> listOfTextBlocks;

    /**
     * Constructor initialize List<TextElement> listOfTextBlocks
     *
     * @param textBlocks List<TextElement> with Paragraphs or CodeBlocks
     */
    public Text(List<TextElement> textBlocks) {
        listOfTextBlocks = textBlocks;
    }

    @Override
    public void add(TextElement textElement) {
        listOfTextBlocks.add(textElement);
    }

    /**
     * This method passes through the entire listOfTextBlocks, the getValue()
     * method of each Paragraph or CodeBLock is called on each object inside
     * the list, thereby collecting the entire Text in the form in
     * which it was originally
     *
     * @return
     */
    @Override
    public StringBuilder getValue() {
        StringBuilder result = new StringBuilder("");

        listOfTextBlocks.stream().forEach(s -> result.append(s.getValue()));

        return result;
    }

    public List<TextElement> getListOfTexts() {
        return listOfTextBlocks;
    }
}
