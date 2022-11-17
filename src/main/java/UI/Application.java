package UI;

import base.Main;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.io.File;

/**
 * Controls the UI, File Selection and the PDF Generation Trigger.
 * Two Select Buttons are used. One for the input .txt file, and one for the output path and name of the file.
 * One Generate Button is used to start generating the PDF.
 */
public class Application extends JFrame implements ActionListener {

    private JPanel buttonPanel = new JPanel();
    private DesignedButton fileSelectButton = new DesignedButton("Select Input", 200,50, Color.black, Color.white, this);
    private DesignedButton outputPathSelectButton = new DesignedButton("Save Output", 200, 100, Color.black, Color.white, this);
    private DesignedButton generateButton = new DesignedButton("Generate PDF", 200, 175, Color.white, Color.black, this);

    /**
     * Sets up the UI elements: Frame, Panel and Buttons.
     */
    public Application() {
        // frame
        this.setTitle("PDF Generator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(600, 300);
        this.setLayout(null);
        // panel
        buttonPanel.setBounds(0, 0, 600, 300);
        buttonPanel.setBackground(Color.lightGray);
        buttonPanel.setLayout(null);
        // add buttons to panel
        buttonPanel.add(fileSelectButton);
        buttonPanel.add(outputPathSelectButton);
        buttonPanel.add(generateButton);
        // add panel to frame
        this.add(buttonPanel);

        this.setVisible(true);
    }

    /**
     * When a button is clicked do their assigned task.
     * @param e The clicked button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fileSelectButton)
            selectInputPath(fileSelectButton);

        if (e.getSource() == outputPathSelectButton)
            selectOutputPath(outputPathSelectButton);

        if (e.getSource() == generateButton) {
            if (!fileSelectButton.getFilePath().equals("") && !outputPathSelectButton.getFilePath().equals(""))
                Main.generatePDF(fileSelectButton.getFilePath(), outputPathSelectButton.getFilePath());
        }
    }

    /**
     * Select the input .txt file, then save and display the input name and path
     * @param button The input selection button clicked
     */
    private void selectInputPath(DesignedButton button) {
        // choose file, which can only be a .txt file
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogTitle("Select Text Input");
        FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files", "txt");
        fileChooser.addChoosableFileFilter(restrict);

        int result = fileChooser.showOpenDialog(null);
        // user did not cancel or made error
        if (result == JFileChooser.APPROVE_OPTION) {
            button.changeSizeAndColor(15);
            button.setFilePath(fileChooser.getSelectedFile().getAbsolutePath().toString());
            button.setFileName(fileChooser.getSelectedFile().getName());
            button.setText("Generate from: " + button.getFileName());
        }
    }

    /**
     * Select the output name and path (ending with .pdf), then save and display the output name and path.
     * @param button The output selection button.
     */
    private void selectOutputPath(DesignedButton button) {
        // choose a directory and name, and only show .pdf files
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setAcceptAllFileFilterUsed(false);
        fileChooser.setDialogTitle("Save Output");
        fileChooser.setSelectedFile(new File("Untitled.pdf"));
        FileNameExtensionFilter restrict = new FileNameExtensionFilter(".pdf", "pdf");
        fileChooser.addChoosableFileFilter(restrict);

        int result = fileChooser.showSaveDialog(null);
        // user did not cancel or made error
        if (result == JFileChooser.APPROVE_OPTION) {
            button.changeSizeAndColor(12);
            button.setFilePath(fileChooser.getSelectedFile().getAbsolutePath().toString());
            button.setFileName(fileChooser.getSelectedFile().getName());
            button.setText("Save As " + button.getFilePath());
        }
    }

}
