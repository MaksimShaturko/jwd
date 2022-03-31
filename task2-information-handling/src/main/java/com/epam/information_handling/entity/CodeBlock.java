package com.epam.information_handling.entity;

import java.util.List;
import java.util.Objects;

/**
 * CodeBlock implements TextElement
 *
 * <p>An entity class, which is a class of a text fragment (code block),
 * containing a list of elements of program code that form a CodeBlock text
 * fragment.
 *
 * It is divided into code fragments CodeElements. They are
 * contained in the form of a List<TextElement> listOfCodeElements, which
 * make up this fragment of the CodeBlock text</p>
 *
 * @author Maksim Shaturko
 */
public class CodeBlock implements TextElement {
    private List<TextElement> listOfCodeElements;

    /**
     * Constructor Initialize List<TextElement> listOfCodeElements
     *
     * @param codeElements code elements (List<TextElement>),
     *                     that make up this CodeBlock
     */
    public CodeBlock(List<TextElement> codeElements) {
        listOfCodeElements = codeElements;
    }

    @Override
    public void add(TextElement textElement) {
        listOfCodeElements.add(textElement);
    }

    /**
     * This method passes through the entire listOfCodeElements, the getValue()
     * method of each CodeElement is called on each object inside the list
     * (CodeElement), thereby collecting the entire CodeBlock in the form in
     * which it was originally
     *
     * @return CodeBlock as a StringBuilder string assembled from the
     * StringBuilder value of each CodeElement
     */
    @Override
    public StringBuilder getValue() {
        StringBuilder result = new StringBuilder();

        listOfCodeElements.stream()
                          .forEach(codeElement -> result
                                  .append(codeElement
                                          .getValue()
                                          .append(" ")));
        return result.append("\n");
    }

    public List<TextElement> getListOfTexts() {
        return listOfCodeElements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CodeBlock codeBlock = (CodeBlock) o;
        return listOfCodeElements.equals(codeBlock.listOfCodeElements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(listOfCodeElements);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CodeBlock{");
        sb.append("listOfCodeElements=").append(listOfCodeElements);
        sb.append('}');
        return sb.toString();
    }
}
