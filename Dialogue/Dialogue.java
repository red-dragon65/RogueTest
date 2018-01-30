package RogueGame.Dialogue;

import javax.swing.*;
import java.awt.*;

abstract public class Dialogue {

    //Variables
    private ImageIcon dialog;

    private String message;
    private boolean active;
    public boolean yes;


    //TODO make text  scroll


    //Constructor
    public Dialogue() {
        active = false;
        yes = false;
        message = "null";

        dialog = new ImageIcon(getClass().getResource("../test/DialogBox.png"));
    }

    //Set message
    public void setMessage(String message) {
        this.message = message;
    }


    public void run(boolean in[]) {

        //Load dungeon
        if (in[4]) {
            yes = true;
            active = false;
        }

        //Don't load dungeon
        if (in[5]) {
            yes = false;
            active = false;
        }

    }


    public boolean isActive() {
        return active;
    }

    public void activate() {
        active = true;
    }


    //Paint method
    public void draw(Graphics g, JPanel p) {


        int x = 500;
        int y = 500;
        int size = 25;
        int padding = 10;


        dialog.paintIcon(p, g, x, y);

        g.setColor(Color.white);
        g.setFont(new Font("Aerial", Font.PLAIN, size));
        g.drawString(message, x + 5 + padding, y + size + padding);

    }


}


/*

This class is just for dialogue.

Reads passed in script.

Displays passed in script every window.



Town only shows:
    - Dialogue class
    - Selector class

Selector class shows:
    - Yes/no
    - Description class




Dialogue class - Only checks for one input (select)
    Character dialogue


Selector class - Checks for three input (up, down, select)
    Big
        Dungeon selector
        Inventory selector

    Small
        Options for selection


Yes/no class - Checks for three input (up, down, select)
    Simple yes no dialogue


Description class - No input check


*/

