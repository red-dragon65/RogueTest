package RogueGame;

import javax.swing.*;
import java.awt.*;


abstract class SpriteImage extends Sprite {

    //Set sprite image
    protected ImageIcon IMAGE;

    /*
     * Setters.
     */
    protected void setIMAGE(ImageIcon image) {
        IMAGE = image;
    }


    /*
     * Getters.
     */
    //Returns image width, else 20.
    protected int getWidth() {

        if (IMAGE == null)
            return 20;
        else
            return IMAGE.getIconWidth();
    }

    //Returns image height, else 20.
    protected int getHeight() {

        if (IMAGE == null)
            return 20;
        else
            return IMAGE.getIconHeight();
    }

    //Method to put graphic onto panel.
    protected void paint(Graphics g, JPanel panel) {
        if (show) {
            if (IMAGE == null) {
                g.drawRect(getX(), getY(), 20, 20);
            } else {
                IMAGE.paintIcon(panel, g, getX(), getY());
            }
        }
    }
}
