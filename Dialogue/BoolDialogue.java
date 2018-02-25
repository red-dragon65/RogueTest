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

        dialog = new ImageIcon(getClass().getResource("../test/DialogBox.png"));
    }

    //Set message
    public void setMessage(String message) {
        this.message = message;
    }


    public void run(InputListener in) {

        //Load ynMenu
        select.run(in.getInput());


        //Load dungeon
        if (!select.isActive())
            if (select.yes) {

                yes = true;
                active = false;

            } else {

                //Don't load dungeon
                yes = false;
                active = false;
            }

    }

    //Todo: remove this old code
    public void run(boolean in[]) {

        //Load ynMenu
        select.run(in);


        //Load dungeon
        if (!select.isActive())
            if (select.yes) {

                yes = true;
                active = false;

            } else {

                //Don't load dungeon
                yes = false;
                active = false;
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

            select.draw(g, p);
        }
    }


//--------------------------------------------------------------------------------


    class ynMenu {

        //Variables
        private ImageIcon dialog;

        private boolean active;
        public boolean yes;

        private Color yeet, neet;


        //Constructor
        public ynMenu() {
            active = false;
            yes = false;

            yeet = Color.gray;
            neet = Color.white;

            dialog = new ImageIcon(getClass().getResource("../test/DialogBox.png"));
        }

        public void run(boolean in[]) {

            //Load dungeon
            if (in[4]) {
                if (yeet == Color.white) {
                    yes = true;
                    active = false;
                } else {
                    yes = false;
                    active = false;
                }
            }

            //Up
            if (in[2] && !in[3])
                if (yeet == Color.gray) {
                    yeet = Color.white;
                    neet = Color.gray;
                }

            //Down
            if (in[3] && !in[2])
                if (neet == Color.gray) {
                    neet = Color.white;
                    yeet = Color.gray;
                }

        }


        public boolean isActive() {
            return active;
        }

        public void activate() {
            select.active = true;
            select.yes = false;

            yeet = Color.gray;
            neet = Color.white;
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

            g.setColor(yeet);
            g.drawString("Yes", x + paddingx, y + size + paddingy);

            g.setColor(neet);
            g.drawString("No", x + paddingx, y + 30 + size + paddingy);
        }
    }


}

