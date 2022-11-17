package base;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

/**
 * Writes to the document based on the instructions read in base.FileReader.
 * Stores the current format values.
 */
public class MyPdfWriter {
    // text formats
    private String fontSize = "normal"; // small, normal, large
    private String fontStyle = "regular"; // regular, italic, bold
    private PdfFont pdfFont;
    // paragraph formats
    private int indent = 0;
    private boolean isFill = false;

    private Document document;
    private Paragraph currParagraph = new Paragraph();

    /**
     * Creates the document, and stores it in this class.
     * Generates and stores Pdf font, that will be used for text.
     * @param outputPath writes the Pdf to this location
     */
    public MyPdfWriter(String outputPath) {
        try {
            PdfWriter pdfWriter = new PdfWriter(outputPath);
            PdfDocument pdfDocument = new PdfDocument(pdfWriter);
            pdfDocument.addNewPage();

            document = new Document(pdfDocument);

            try {
                pdfFont = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
            } catch (Exception e) {
                System.out.println("creating pdf font failed");
                System.exit(-2);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            System.exit(-1);
        }

    }

    /**
     * Prints out the current formatting values (For Debugging Purposes).
     */
    public void printCurrentFormat() {
        System.out.println("fontSize: " + this.fontSize);
        System.out.println("fontStyle: " + this.fontStyle);
        System.out.println("indent: " + this.indent);
        System.out.println("isFill: " + this.isFill);
        System.out.println("------------------------------------------------");
    }

    /**
     * Adds a new paragraph to the document with the current styling.
     * These styles can be: .fill, .nofill, .indent.
     *
     * @param writingFinished If true close the document at the end, since it is the last paragraph (end of the loop).
     *                        If false make a new paragraph (.paragraph was called).
     */
    public void addNewParagraph(boolean writingFinished) {
        if (currParagraph != null && !currParagraph.isEmpty()) {
            try {
                if (isFill) {
                    currParagraph.setTextAlignment(TextAlignment.JUSTIFIED);
                }
                currParagraph.setMarginLeft(indent * 10);
                this.document.add(currParagraph);
            } catch (Exception e) {
                System.out.println(e.toString());
            }

        }
        if (writingFinished)
            this.document.close();
        else
            this.currParagraph = new Paragraph();
    }

    /**
     * Adds the styled text (based on the current format) to the current paragraph.
     * This does not include paragraph related styling such as: .fill, .nofill, .indent.
     * Only Text styling is added.
     *
     * @param text Set the text to this value.
     */
    public void addTextToParagraph(String text) {
        Style style = new Style();
        // font style
        if (fontStyle.equals("bold"))
            style.setBold();
        if (fontStyle.equals("italic"))
            style.setItalic();
        // font size
        if (fontSize.equals("large"))
            style.setFontSize(20f);
        if (fontSize.equals("normal"))
            style.setFontSize(12f);
        if (fontSize.equals("small"))
            style.setFontSize(8f);

        this.currParagraph.add(new Text(text).setFont(pdfFont).addStyle(style));
    }

    /**
     * Change fill (isFill) to the given value.
     * @param isFill Set isFill -->.fill = true, .nofill = false.
     */
    public void setIsFilled(boolean isFill) {
        this.isFill = isFill;
    }

    /**
     * Changes font style to the given value.
     * @param fontStyle Set regular, italic, bold font style.
     */
    public void setFontStyle(String fontStyle) {
        this.fontStyle = fontStyle;
    }

    /**
     * Changes the font size to the given size.
     * @param fontSize Set small, normal, large.
     */
    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }

    /**
     * Changes Indent by the given value (can be positive or negative).
     * @param amount Change indent by this value.
     */
    public void changeIndentBy(int amount) {
        this.indent += amount;
    }
}
