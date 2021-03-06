package RogueGame.Town;

import RogueGame.Sprite.SpriteImage;

import javax.swing.*;
import java.awt.*;

public class NPCmap {


    //Characters
    private NPC[] NPCs;
    private final int numOfChars = 5;


    //Default constructor
    public NPCmap() {

        init();


    }


    private void init() {

        //Initialize
        NPCs = new NPC[numOfChars];

        for (int i = 0; i < numOfChars; i++) {
            NPCs[i] = new NPC();
        }

        String fileLocation = "../Assets/Other/Characters/";

        //TODO: Load this information from a file
        //Set NPC location
        NPCs[0].setX(292);
        NPCs[0].setY(623);
        NPCs[0].setIMAGE(new ImageIcon(getClass().getResource(fileLocation + "green.png"))); //Green
        NPCs[0].setName("Bob");

        NPCs[1].setX(1149);
        NPCs[1].setY(646);
        NPCs[1].setIMAGE(new ImageIcon(getClass().getResource(fileLocation + "purple.png"))); //Purple
        NPCs[1].setName("Sally");

        NPCs[2].setX(880);
        NPCs[2].setY(647);
        NPCs[2].setIMAGE(new ImageIcon(getClass().getResource(fileLocation + "red.png"))); //Red
        NPCs[2].setName("Sue");

        NPCs[3].setX(463);
        NPCs[3].setY(490);
        NPCs[3].setIMAGE(new ImageIcon(getClass().getResource(fileLocation + "yellow.png"))); //Yellow
        NPCs[3].setName("Drake");

        NPCs[4].setX(726);
        NPCs[4].setY(854);
        NPCs[4].setIMAGE(new ImageIcon(getClass().getResource(fileLocation + "blue.png"))); //Blue
        NPCs[4].setName("Nathan");

        //Green
        NPCs[0].setDialogue("Why is it that the quick brown fox jumps over the lazy dog? * Sounds stupid if you ask me. *");

        //Purple
        NPCs[1].setDialogue("A one. A two. A three! * OW! * I broke my teeth! But that's okay, cause I'm wearin' me dentures! * HAHAHA! *");

        //Red
        NPCs[2].setDialogue("What do you want! * Can't you see I'm busy! * (mumble... mumble...) * I recently lost a very important item at my shop and I can't open until it is found! *");

        //Yellow
        NPCs[3].setDialogue("Why hello! Are you interested in purchasing an item today? * Wait a minute! * Your broke as a rock! * Get get some real money before you hassle me you fool! * ... * What's that you say? * Your going to cut of my head and shove it up my ass? * Well why didn't you say so sooner! * Buy any item you'd like sir! *");

        //Blue
        NPCs[4].setDialogue("So there was this one time I was on an adventure and I twisted my ankle pretty bad. * I could hardly move, but I knew that if i stayed in the dungeon, it would be the end of me. * So I did what I had to do. * I chewed my leg off and made like a rat out of there! *");


    }


    //Dialogue activation bounds
    public String[] bounds(Hero h) {

        String[] temp = new String[2];
        temp[0] = "";
        temp[1] = "";

        //Radius around character
        int leftOffset = 24;
        int rightOffset = leftOffset / 2;

        Rectangle hero = new Rectangle(h.getX(), h.getY(), h.getWidth(), h.getHeight());
        Rectangle guy;


        //Cycle through characters
        for (int i = 0; i < numOfChars; i++) {

            //Get next character
            guy = new Rectangle(NPCs[i].getX() - rightOffset, NPCs[i].getY() - rightOffset, NPCs[i].getWidth() + leftOffset, NPCs[i].getHeight() + leftOffset);

            //Check for collision
            if (hero.intersects(guy)) {

                temp[0] = NPCs[i].getDialogue();
                temp[1] = NPCs[i].getName();
            }
        }


        return temp;
    }


    /*
    Checks to see if the character is colliding with a character.
    If there is a a collision, it checks if it is right, left, up, or down.
    */
    public String isCollision(Hero h) {

        int offset = 10;
        int reset = offset / 2;

        int buff = 5;


        Rectangle hero = new Rectangle(h.getX(), h.getY(), h.getWidth(), h.getHeight());
        Rectangle guy;

        //Cycle through characters
        for (int i = 0; i < numOfChars; i++) {

            //Get next character
            guy = new Rectangle(NPCs[i].getX() - reset, NPCs[i].getY() - reset, NPCs[i].getWidth() + offset, NPCs[i].getHeight() + offset);

            //Check for collision
            if (hero.intersects(guy)) {

                guy = new Rectangle(NPCs[i].getX(), NPCs[i].getY(), NPCs[i].getWidth(), NPCs[i].getHeight());

                //Right
                if (new Rectangle(h.getX() + h.getWidth() + buff, h.getY(), h.getWidth(), h.getHeight()).intersects(guy)) {

                    return "right";
                }

                //Left
                if (new Rectangle(h.getX() - h.getWidth() - buff, h.getY(), h.getWidth(), h.getHeight()).intersects(guy)) {

                    return "left";
                }

                //Top
                if (new Rectangle(h.getX(), h.getY() - h.getHeight() - buff, h.getWidth(), h.getHeight()).intersects(guy)) {

                    return "top";
                }

                //Bottom
                if (new Rectangle(h.getX(), h.getY() + h.getHeight() + buff, h.getWidth(), h.getHeight()).intersects(guy)) {

                    return "bottom";
                }

            }
        }

        return "";

    }

    //Draw characters onto screen
    public void paint(Graphics g, JPanel p) {

        for (int i = 0; i < 5; i++) {
            SpriteImage.paint(g, p, NPCs[i]);
        }

    }

}
