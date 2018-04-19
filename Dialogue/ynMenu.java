package RogueGame.Dialogue;

import RogueGame.InputListener;

import javax.swing.*;
import java.awt.*;

public class ynMenu {

    //Variables
    private ImageIcon dialog;

    public boolean active;
    public boolean yes;
    public boolean no;

    private Color unSelectedColor, selectedColor;


    //Constructor
    public ynMenu() {
        active = false;
        yes = false;
        no = false;

        unSelectedColor = Color.gray;
        selectedColor = Color.white;

        dialog = new ImageIcon(getClass().getResource("../Assets/Other/Dialogue/DialogBox.png"));
    }

    public void run(InputListener in) {


        //Exit
        if (in.checkInput("escape")) {
            no = true;
            yes = false;
            active = false;
            in.bufferEsc();
        }

        //Update selection
        if (in.checkInput("space")) {

            if (unSelectedColor == Color.white) {
                yes = true;
                no = false;
                active = false;
            } else {
                yes = false;
                no = true;
                active = false;
            }
            in.bufferSpace();
        }

        //Up
        if (in.checkInput("up")) {
            in.upBuffer();
            if (unSelectedColor == Color.gray) {
                unSelectedColor = Color.white;
                selectedColor = Color.gray;
            }
        }

        //Down
        if (in.checkInput("down")) {
            in.downBuffer();
            if (selectedColor == Color.gray) {
                selectedColor = Color.white;
                unSelectedColor = Color.gray;
            }
        }


    }


    public boolean isActive() {
        return active;
    }

    public void activate() {
        active = true;
        yes = false;
        no = false;

        unSelectedColor = Color.gray;
        selectedColor = Color.white;
    }

    //Paint method
    public void draw(Graphics g, JPanel p) {

        int x = 500;
        int y = 600;
        int size = 20;
        int paddingy = 15;
        int paddingx = 125;


        dialog.paintIcon(p, g, x, y);

        g.setFont(new Font("Aerial", Font.PLAIN, size));

        g.setColor(unSelectedColor);
        g.drawString("Yes", x + paddingx, y + size + paddingy);

        g.setColor(selectedColor);
        g.drawString("No", x + paddingx, y + 30 + size + paddingy);
    }
}

