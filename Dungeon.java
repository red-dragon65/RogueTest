package RogueGame;

import javax.swing.*;
import java.awt.*;

public class Dungeon {


    //Map sprite tile matrix.
    private Map map;

    //Sprites.
    private Hero hero;

    //Sprite images.
    private ImageIcon heroImg;

    boolean finished = false;



    /*
    Default constructor.
     */
    public Dungeon() {

        map = new Map();

        hero = new Hero(10);

        heroImg = new ImageIcon(getClass().getResource("Assets/hero.png"));

        //Set hero image.
        hero.setIMAGE(heroImg);

        int[] temp = map.getRandomRoom();

        //Set hero starting location.
        hero.setX(temp[0]);
        hero.setY(temp[1]);
    }


    //Game loop calls this function.
    public void run(boolean input[]) {


        //Right
        if (input[0] && !input[1])
            hero.setVx(5);
        //Left
        if (input[1] && !input[0])
            hero.setVx(-5);

        if (!input[0] && !input[1])
            hero.setVx(0);


        //Up
        if (input[2] && !input[3])
            hero.setVy(-5);

        //Down
        if (input[3] && !input[2])
            hero.setVy(5);

        if (!input[2] && !input[3])
            hero.setVy(0);


        //Update hero location
        hero.move();

    }


    /*
    Draws dungeon to JPanel.
     */
    public void draw(Graphics g, JPanel p) {

        //TODO: remove this test code (draws all tiles)
        for (int i = 0; i < map.getSizeY(); i++) {
            for (int z = 0; z < map.getSizeX(); z++) {
                map.matrix[i][z].paint(g, p);
            }
        }

        //Draw hero
        hero.paint(g, p);
    }

}