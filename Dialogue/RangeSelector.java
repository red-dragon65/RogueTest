package RogueGame.Dialogue;

import RogueGame.InputListener;
import RogueGame.Sprite.SpriteImage;

import javax.swing.*;
import java.awt.*;

public class RangeSelector {


    private SpriteImage[] tiles;
    private SpriteImage cursor;

    private int range;
    private final int maxTiles = 41; //Max range: 4 tiles all around
    private int tileLimit = 0;

    private boolean test = false;

    private boolean active;
    public boolean yes = false;
    public boolean no = false;
    public int cursorLocX;
    public int cursorLocY;


    public RangeSelector() {

        active = false;
        tiles = new SpriteImage[maxTiles];
        cursor = new SpriteImage(new ImageIcon(getClass().getResource("../Assets/Other/Dialogue/Cursor.png")));
    }

    public void run(InputListener in) {

        //Buffer input
        in.turnOnVBuffer();
        in.turnOnHBuffer();

        //Exit
        if (in.checkInput("escape")) {
            in.bufferEsc();
            reset();
        }

        //Exit
        if (in.checkInput("space")) {
            in.bufferSpace();
            yes = true;
        }


        //Up
        if (in.checkInput("up")) {
            in.upBuffer();

            test = false;

            cursor.setY(cursor.getY() - 24);
            cursorLocY--;

            for (int i = 0; i < tileLimit; i++) {

                if (cursor.getY() == tiles[i].getY() && cursor.getX() == tiles[i].getX()) {
                    test = true;
                    break;
                }
            }
            if (!test) {
                cursor.setY(cursor.getY() + 24);
                cursorLocY++;
            }

        }

        //Down
        if (in.checkInput("down")) {
            in.downBuffer();

            test = false;

            cursor.setY(cursor.getY() + 24);
            cursorLocY++;

            for (int i = 0; i < tileLimit; i++) {

                if (cursor.getY() == tiles[i].getY() && cursor.getX() == tiles[i].getX()) {
                    test = true;
                    break;
                }
            }
            if (!test) {
                cursor.setY(cursor.getY() - 24);
                cursorLocY--;
            }

        }


        //Left
        if (in.checkInput("left")) {
            in.leftBuffer();

            test = false;

            cursor.setX(cursor.getX() - 24);
            cursorLocX--;

            for (int i = 0; i < tileLimit; i++) {

                if (cursor.getX() == tiles[i].getX() && cursor.getY() == tiles[i].getY()) {
                    test = true;
                    break;
                }
            }
            if (!test) {
                cursor.setX(cursor.getX() + 24);
                cursorLocX++;
            }

        }

        //Right
        if (in.checkInput("right")) {
            in.rightBuffer();

            test = false;

            cursor.setX(cursor.getX() + 24);
            cursorLocX++;

            for (int i = 0; i < tileLimit; i++) {

                if (cursor.getX() == tiles[i].getX() && cursor.getY() == tiles[i].getY()) {
                    test = true;
                    break;
                }
            }
            if (!test) {
                cursor.setX(cursor.getX() - 24);
                cursorLocX--;
            }

        }


    }

    public void reset() {
        yes = false;
        no = false;
        active = false;
    }


    //Set range of attack
    public void setRange(int range, int[] loc) {

        //Set range
        this.range = range;


        //Get number of tiles in all around range
        tileLimit = ((((range - 1) * 2) + 4) * range) + 1;

        /*
        //Get number of tiles in linear range
        tileLimit = 5 + ((range - 1) * 4)
         */


        //Initialize tiles
        for (int i = 0; i < tileLimit; i++) {
            tiles[i] = new SpriteImage(new ImageIcon(getClass().getResource("../Assets/Other/Dialogue/Range.png")));
        }


        int locX = loc[0];
        int locY = loc[1];


        //Set cursor location
        cursor.setX(locX);
        cursor.setY(locY);


        //Offset tiles
        int position = range;
        int offsetLocX = 0;
        int offsetLocY = 0;

        int loops = 1;
        int end = 1;

        int start = 0;
        int startOffset = 0;

        int index = 0;

        while (position >= 0) {


            //Get offset
            for (int i = 0; i <= position; i++) {
                offsetLocX += 24;
            }

            offsetLocX -= 24;

            //Get range of tiles to set
            for (int i = start; i < end; i++) {
                offsetLocX += 24;
                tiles[index].setX(locX + offsetLocX);
                tiles[index].setY(locY + offsetLocY);
                index++;
            }
            offsetLocX = 0;

            offsetLocY += 24;

            position -= 1;

            startOffset++;
            start = startOffset * startOffset;


            loops++;
            end = loops * loops;
        }


        //Offset tiles (reverse)
        position = 1;

        startOffset -= 2;
        start = startOffset * startOffset;

        loops -= 2;
        end = loops * loops;


        while (position <= range) {


            //Get offset
            for (int i = 0; i <= position; i++) {
                offsetLocX += 24;
            }

            offsetLocX -= 24;


            //Get range of tiles to set
            for (int i = start; i < end; i++) {
                offsetLocX += 24;
                tiles[index].setX(locX + offsetLocX);
                tiles[index].setY(locY + offsetLocY);
                index++;
            }
            offsetLocX = 0;

            offsetLocY += 24;


            position += 1;

            startOffset--;
            start = startOffset * startOffset;


            loops--;
            end = loops * loops;
        }


        for (int i = 0; i < tileLimit; i++) {

            tiles[i].setX(tiles[i].getX() - ((range + 1) * 24));
            tiles[i].setY(tiles[i].getY() - (range * 24));
        }



        /*

        //Array end
        1 1
        2 4
        3 9
        4 16

        //Array start
        1 0 +1
        2 1 +3
        3 4 +5
        4 9 +7
        5 16 +9

         */


    }

    public boolean isActive() {
        return active;
    }

    public void activate() {
        active = true;
    }


    //Paint method
    protected void draw(Graphics g, JPanel p) {

        if (this.active) {

            //Draw every tiles the screen
            for (int i = 0; i < tileLimit; i++) {
                SpriteImage.paint(g, p, tiles[i]);
            }

            //Draw tile selector
            SpriteImage.paint(g, p, cursor);

        }
    }
}
