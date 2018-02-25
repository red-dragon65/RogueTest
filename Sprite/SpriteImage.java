package RogueGame.Sprite;

import javax.swing.*;
import java.awt.*;


public class SpriteImage extends Sprite {

    //Set sprite image
    protected ImageIcon IMAGE;

    /*
     * Setters.
     */
    public void setIMAGE(ImageIcon image) {
        IMAGE = image;
    }


    /*
     * Getters.
     */
    //Returns image width, else 20.
    public int getWidth() {

        if (IMAGE == null)
            return 20;
        else
            return IMAGE.getIconWidth();
    }

    //Returns image height, else 20.
    public int getHeight() {

        if (IMAGE == null)
            return 20;
        else
            return IMAGE.getIconHeight();
    }

    //Method to put graphic onto panel.
    public void paint(Graphics g, JPanel panel) {
        if (show) {
            if (IMAGE == null) {
                g.drawRect(getX(), getY(), 20, 20);
            } else {
                IMAGE.paintIcon(panel, g, getX(), getY());
            }
        }
    }
}
