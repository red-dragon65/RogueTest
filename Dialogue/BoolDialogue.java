package RogueGame.Dialogue;

import RogueGame.InputListener;

import javax.swing.*;
import java.awt.*;

public class BoolDialogue {


    //Variables
    private ImageIcon dialog;

    private String message;
    private boolean active;
    public boolean yes;
    private ynMenu select;


    //TODO make text  scroll


    //Constructor
    public BoolDialogue() {
        active = false;
        yes = false;
        message = "null";

        select = new ynMenu();

        dialog = new ImageIcon(getClass().getResource("../Assets/Other/Dialogue/DialogBox.png"));
    }

    //Set message
    public void setMessage(String message) {
        this.message = message;
    }


    public void run(InputListener in) {

        in.turnOnVBuffer();

        //Load ynMenu
        select.run(in);


        if (!select.isActive()) {
            if (select.yes) {

                yes = true;
                active = false;

            } else if (select.no) {

                yes = false;
                active = false;
            }
            in.turnOffVBuffer();
        }

    }


    public boolean isActive() {
        return active;
    }

    public void activate() {
        active = true;
        yes = false;

        select.activate();


    }


    //Paint method
    public void draw(Graphics g, JPanel p) {

        if (this.active) {

            int x = 500;
            int y = 500;
            int size = 18;
            int padding = 10;


            dialog.paintIcon(p, g, x, y);

            g.setColor(Color.white);
            g.setFont(new Font("Aerial", Font.PLAIN, size));
            g.drawString(message, x + 5 + padding, y + size + padding);

            if (select.active) {
                select.draw(g, p);
            }
        }
    }

}

