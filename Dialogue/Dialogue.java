package RogueGame.Dialogue;

import RogueGame.InputListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Dialogue {

    //Variables
    private ImageIcon dialog;

    private boolean active;
    private boolean yes;

    private ArrayList<String> script;

    //TODO make text  scroll

    //Constructor
    public Dialogue() {
        active = false;
        yes = false;

        dialog = new ImageIcon(getClass().getResource("../test/DialogBox.png"));
    }


    //Set message
    public void setMessage(String message) {

        //Initialize
        String m = "";
        int counter = 0;
        script = new ArrayList<>();

        //Tokenize message.
        String[] tokens = message.split(" ");

        //Format message into drawable script.
        for (int i = 0; i < tokens.length; i++) {

            counter += tokens[i].length();

            if (tokens[i].equals("*")) {

                script.add(m);
                m = "";
                counter = 0;
                script.add(" ");

            } else if (counter < 25) {
                m += tokens[i];
                m += " ";
            } else {
                script.add(m);
                m = "";
                counter = 0;
                i--;
            }

        }
        if (!m.equals("")) {
            script.add(m);
        }

    }


    //Run with input
    public void run(InputListener in) {

        if (!script.isEmpty()) {

            if (in.checkInput("space")) {

                /*
                Removes message from script
                Stops if: '*' is found, or until 3 have been deleted
                 */
                for (int i = 0; i < 4; i++) {

                    if (i == 3 && script.get(0).equals(" ")) {
                        script.remove(0);
                        break;
                    }


                    if (i != 3) {
                        if (!script.get(0).equals(" ")) {
                            script.remove(0);
                        } else {
                            script.remove(0);
                            break;
                        }
                    }
                }

                in.bufferInput();

            }

        } else {
            active = false;
            in.bufferInput();
        }


        /*
        if(in.checkInput("space")){
            active = false;
            in.bufferInput();
        }*/



    }


    //Check if the dialogue is showing
    public boolean isActive() {
        return active;
    }

    //Show the dialogue
    public void activate() {
        active = true;
    }


    //Paint method
    public void draw(Graphics g, JPanel p) {

        //Max characters wide: 25

        int x = 500;
        int y = 500;
        int size = 18;
        int padding = 10;

        dialog.paintIcon(p, g, x, y);

        g.setColor(Color.white);
        g.setFont(new Font("Aerial", Font.PLAIN, size));

        //Draw text
        int i = 0;
        while (i < script.size() && i < 3) {

            g.drawString(script.get(i), x + 5 + padding, y + size + padding);

            if (script.get(i).equals(" ")) {
                break;
            }

            y += 25;
            i++;
        }


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

