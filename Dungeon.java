package RogueGame;

import javax.swing.*;
import java.awt.*;

public class Dungeon {


    //Map sprite tile matrix.
    private Map map;

    //Sprites.
    private Hero hero;
    private SpriteImage stairs;

    //Sprite images.
    private ImageIcon heroImg;
    private ImageIcon stairsImg;

    public boolean dungeonFinished;
    private int maxFloors;
    private int currentFloor;
    private int speed;






    /*
    Default constructor.
     */
    public Dungeon() {

        map = new Map();

        hero = new Hero(10);
        stairs = new SpriteImage();

        heroImg = new ImageIcon(getClass().getResource("Assets/hero.png"));
        stairsImg = new ImageIcon(getClass().getResource("test/point.png"));

        //Set hero image.
        hero.setIMAGE(heroImg);
        stairs.setIMAGE(stairsImg);

        init();

        speed = 5;

    }

    public void init() {
        //map.init();

        randomLoc(stairs);
        do {
            randomLoc(hero);
        } while (stairs.getX() == hero.getX() && stairs.getY() == hero.getY());
        //randomLoc(hero);
/*
        if(hero.getX() == stairs.getX())
            hero.setX(hero.getX()+1);
*/

        maxFloors = 2;
        currentFloor = 1;

        dungeonFinished = false;
    }

    //Game loop calls this function.
    public void run(boolean input[]) {


        //Right
        if (input[0] && !input[1])
            hero.setVx(speed);
        //Left
        if (input[1] && !input[0])
            hero.setVx(-speed);

        if (!input[0] && !input[1])
            hero.setVx(0);


        //Up
        if (input[2] && !input[3])
            hero.setVy(-speed);

        //Down
        if (input[3] && !input[2])
            hero.setVy(speed);

        if (!input[2] && !input[3])
            hero.setVy(0);


        //Update hero location
        hero.move();


        //Initialize new map
        if (hero.isCollision(stairs)) {
            map.init();

            randomLoc(stairs);
            do {
                randomLoc(hero);
            } while (stairs.getX() == hero.getX() && stairs.getY() == hero.getY());

            currentFloor++;
        }


        //TODO: Make this dynamic like drawing code
        /*
        //Move all tiles
        for(int i = 0; i < 64; i++){
            for(int z = 0; z < 64; z++){
                map.matrix[i][z].move();
            }
        }*/


        //TODO: call load to re-initialize variables

/*
        //Check for collisions.
        for(Enemy s : enemies) {

            if(hero.isCollision(s)){

                /**If they instersect, do something.*/

        //* if(other.right > thisSprite.left)
        /*
                if((s.getX() + (s.getWidth()) > hero.getX())){
                    //Reverse direction.
                    s.setVx(-(s.getVx()));
                }
                //* if(other.left < thisSprite.right)
                if(s.getX() < (hero.getX() + hero.getWidth())){
                    //Reverse direction.
                    s.setVx(-s.getVx());
                }
            }

        }*/

    }


    /*
    Draws dungeon to JPanel.
     */
    public void draw(Graphics g, JPanel p) {

        //TODO: this dynamically draws tiles when in bounds
        /*
        for(int i = 0; i < 64; i++){
            for(int z = 0; z < 64; z++){
                if(map.matrix[i][z].getX() > 0 && map.matrix[i][z].getX() < 1200)
                    if(map.matrix[i][z].getY() > 0 && map.matrix[i][z].getY() < 480)
                    map.matrix[i][z].paint(g, this);
            }
        }
        */

        //TODO: remove this test code (draws all tiles)
        for (int i = 0; i < map.getSizeY(); i++) {
            for (int z = 0; z < map.getSizeX(); z++) {
                map.matrix[i][z].paint(g, p);
            }
        }

        stairs.paint(g, p);

        //Draw hero
        hero.paint(g, p);

    }


    private void randomLoc(SpriteImage h) {

        int[] temp = map.getRandomRoom();
        //Set hero starting location.
        h.setX(temp[0]);
        h.setY(temp[1]);
    }

    public void checkDungeon() {
        if (currentFloor > maxFloors) {
            dungeonFinished = true;
        }
    }

}