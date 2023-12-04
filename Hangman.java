import javax.swing.*;
import java.awt.*; 
/**
 * Hangman picture object 
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Hangman
{
    // instance variables - replace the example below with your own
    private JLabel image;
    private String[] iconNames = {"1.png","2.png","3.png","4.png","5.png","6.png","7.png","8.png"};
    private int iconIndex;
    
    
    /**
     * Constructor for objects of class Hangman
     */
    public Hangman()
    {
        // initialise instance variables
        iconIndex = 0;
        image = new JLabel(new ImageIcon(iconNames[0]));
        image.setSize(300,450);
        image.setLocation(50,10);
        image.setBackground(Color.white);
        image.setOpaque(true);
    }
        
    
    
    /**
     * Changes image to the next image in the list
     * Returns false if the image is the last one in list
     */
    public boolean updateImage() {
        if (this.iconIndex == iconNames.length - 2) {
            image.setIcon(new ImageIcon(iconNames[iconIndex + 1]));
            return false;
        } else {
            iconIndex ++;
            image.setIcon(new ImageIcon(iconNames[iconIndex]));
        }
        return true;
    }
    
    public JLabel getImage() {
        return this.image;
    }
}
