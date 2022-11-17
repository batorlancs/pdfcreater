package base;

import java.io.File;
import java.util.Scanner;

/**
 * Reads the file line by line, and updates myPdfWriter.
 * Sets the instructions, and texts.
 */
public class FileReader {

    private Scanner scan;
    private File file;
    private MyPdfWriter myPdfWriter;

    /**
     * Creates a scanner that will go through the file.
     * @param filePath File to read the instructions and text.
     * @param myPdfWriter Current Format of the PDF characters/paragraph.
     */
    public FileReader(String filePath, MyPdfWriter myPdfWriter) {
        this.file = new File(filePath);
        this.myPdfWriter = myPdfWriter;
        try {
            this.scan = new Scanner(file);
        } catch (Exception e) {
            System.out.println("file not found");
        }
    }

    /**
     * Checks if the file has a next line.
     * @return Does file has next line.
     */
    public boolean hasNextLine() {
        return scan.hasNextLine();
    }

    /**
     * Reads the next line of the file.
     * If it is an instruction: do the written task.
     * If it is a text: add it to the paragraph with the appropriate style.
     */
    public void readNextLine() {
        String temp = scan.nextLine();
        String[] a = temp.split(" "); // to check indent value

        // paragraph
        if (temp.equals(".paragraph")) {
            myPdfWriter.addNewParagraph(false);
        }
        // font style
        else if (temp.equals(".regular") || temp.equals(".italic") || temp.equals(".bold")) {
            myPdfWriter.setFontStyle(temp.substring(1));
        }
        // font size
        else if (temp.equals(".small") || temp.equals(".normal") || temp.equals(".large")) {
            myPdfWriter.setFontSize(temp.substring(1));
        }
        // text justified / filled
        else if (temp.equals(".fill") || temp.equals(".nofill")) {
            myPdfWriter.setIsFilled(temp.equals(".fill"));
        }
        // indent
        else if (a.length == 2 && a[0].equals(".indent")) {
            try {
                int amount = Integer.parseInt(a[1]);
                myPdfWriter.changeIndentBy(amount);

            } catch (Exception ignored) {}
        }
        // text
        else {
            myPdfWriter.addTextToParagraph(temp);
        }
    }
}
