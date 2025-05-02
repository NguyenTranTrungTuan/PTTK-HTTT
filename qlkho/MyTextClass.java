package qlkho;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.PDDocument;

public class MyTextClass {
    PDDocument document;
    PDPageContentStream contentStream;

    public MyTextClass(PDDocument document, PDPageContentStream contentStream) {
        this.document = document;
        this.contentStream = contentStream;
    }

    public void addSingleLineText(String text, int xPositon, int yPositon, PDFont font, float fontSize, Color color)
            throws IOException {
        contentStream.beginText();
        contentStream.setFont(font, fontSize);
        contentStream.setNonStrokingColor(color);
        contentStream.newLineAtOffset(xPositon, yPositon);
        contentStream.showText(text);
        contentStream.endText();
        contentStream.moveTo(0, 0);
    }

    public void addMultiLineText(String[] textarr, float leading, int xPositon, int yPositon, PDFont font,
            float fontSize, Color color) throws IOException {
        contentStream.beginText();
        contentStream.setFont(font, fontSize);
        contentStream.setNonStrokingColor(color);
        contentStream.setLeading(leading);
        contentStream.newLineAtOffset(xPositon, yPositon);

        for (String text : textarr) {
            contentStream.showText(text);
            contentStream.newLine();
        }

        contentStream.endText();
        contentStream.moveTo(0, 0);
    }

    public float getTextWidth(String text, PDFont font, float fontSize) throws IOException {
        return font.getStringWidth(text) / 1000 * fontSize;
    }
}
