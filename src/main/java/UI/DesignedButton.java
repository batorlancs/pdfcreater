package UI;

import javax.swing.JButton;
import java.awt.*;

/**
 * Pre-designed selector button, which stores file name and path.
 */
public class DesignedButton extends JButton {

    private String filePath = "";
    private String fileName = "";

    /**
     * Design the button.
     * @param text Displayed text on button.
     * @param XPos Button x position.
     * @param YPos Button y position.
     * @param background Color of background.
     * @param foreground Color of foreground (Text).
     * @param application Instance of Application for action listener.
     */
    public DesignedButton(String text, int XPos, int YPos, Color background, Color foreground, Application application) {
        this.setBounds(XPos, YPos, 200, 40);
        this.setText(text);
        this.setFocusable(true);
        this.setOpaque(true);
        this.setBorderPainted(false);
        this.setBackground(foreground);
        this.setForeground(background);
        this.addActionListener(application);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        this.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    /**
     * Change the button's design after choosing a file.
     * @param fontSize Change the button's font size to this value.
     */
    public void changeSizeAndColor(int fontSize) {
        this.setBounds(25, this.getY(), 550, 40);
        this.setFont(new Font("Arial", Font.PLAIN, fontSize));
        this.setBackground(Color.lightGray);
    }

    /**
     * Set selected file path.
     * @param filePath Change button filePath to this value.
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Get selected file path.
     * @return Value of button's filePath.
     */
    public String getFilePath() {
        return this.filePath;
    }

    /**
     * Set selected file name.
     * @param fileName Change button fileName to this value.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Get selected file name.
     * @return Value of button's fileName to this value.
     */
    public String getFileName() {
        return this.fileName;
    }

}
