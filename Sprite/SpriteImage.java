package RogueGame.Sprite;

import javax.swing.*;
import java.awt.*;


public class SpriteImage extends Sprite {

    //Set sprite image
    protected ImageIcon IMAGE;

    public SpriteImage() {

    }

    public SpriteImage(ImageIcon img) {

        IMAGE = img;
    }

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
    public static void paint(Graphics g, JPanel panel, SpriteImage s) {
        if (s.show) {
            if (s.IMAGE == null) {
                g.drawRect(s.getX(), s.getY(), 20, 20);
            } else {
                s.IMAGE.paintIcon(panel, g, s.getX(), s.getY());
            }
        }
    }
}
