package base;

import UI.Application;

import java.awt.*;
import java.io.File;

/**
 * Reads through the input text with base.FileReader.
 */
public class Main {

    /**
     * Generate the PDF with FileReader and MyPdfWriter.
     * @param inputPath The Selected input (.txt) file
     * @param outputPath The Selected output path and name (ending with .pdf)
     */
    public static void generatePDF(String inputPath, String outputPath){
        MyPdfWriter myPdfWriter = new MyPdfWriter(outputPath);
        FileReader fileReader = new FileReader(inputPath, myPdfWriter);
        // loop through the file
        while (fileReader.hasNextLine()) {
            fileReader.readNextLine();
        }
        // add last paragraph and close the document
        myPdfWriter.addNewParagraph(true);
        // opens generated file
        try {
            Desktop.getDesktop().open(new File(outputPath));
        } catch (Exception e) {
            System.out.println("failed to open folder");
        }

        System.exit(0);

    }

    /**
     * Creates frame / Application.
     * @param args No arguments expected
     */
    public static void main(String[] args) {
        Application application = new Application();
    }
}
