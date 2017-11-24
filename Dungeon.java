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

    }

    //Re-initializer
    public void init() {


        randomLoc(stairs);
        do {
            randomLoc(hero);
        } while (stairs.getX() == hero.getX() && stairs.getY() == hero.getY());

        maxFloors = 2;
        currentFloor = 1;

        dungeonFinished = false;
    }

    //Generate next floor
    private void newFloor() {

        map.init();

        randomLoc(stairs);
        do {
            randomLoc(hero);
        } while (stairs.getX() == hero.getX() && stairs.getY() == hero.getY());

        currentFloor++;
    }


    //Game loop calls this function.
    public void run(boolean in[]) {


        //Move hero
        hero.moveInBounds(in);



        //Initialize new map
        if (hero.isCollision(stairs)) {
            newFloor();
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







        /*

        What can the hero do?

        move to next floor

        access menu

        move


        if(stairs.active()){

            //Decide if you should move to the next floor

            stairs.run(input) //Either updates nextFloor, or set active to false

            if(stairs.nextFloor){
                map.init()
                randomLoc(hero, stairs)
            }

        }else if(menu.active()){


            //Attack or check inventory
            menu.run(collisionMap, inventory_stats)


            //Update collision map
            if(!menu.active()){
                collisionMap = menu.getCollisionMap()
                hero.attack = true;
            }

        }else{


            //Toggle between enemy and hero
            if(switch){


                if(in[5]){

                    menu.init()

                }else{

                    hero.move();

                    if(hero.done){
                        switch = false
                        hero.done = false
                    }

                    //Show stairs if necessary
                    if(hero.loc == collisionMap.stairs)
                        stairs.init()
                }

            }else{

                enemy.move();

                if(enemy.done){
                    switch = true;
                    enemy.done = false
                }

            }
        }


        */



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


    //Randomize sprite location
    private void randomLoc(SpriteImage h) {

        int[] temp = map.getRandomRoom();
        //Set hero starting location.
        h.setX(temp[0]);
        h.setY(temp[1]);
    }

    //Check for finished dungeon
    public void checkDungeon() {
        if (currentFloor > maxFloors) {
            dungeonFinished = true;
        }
    }

}