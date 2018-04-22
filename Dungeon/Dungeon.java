package RogueGame.Dungeon;

import RogueGame.Dialogue.BoolDialogue;
import RogueGame.InputListener;
import RogueGame.Sprite.SpriteImage;

import javax.swing.*;
import java.awt.*;


public class Dungeon {


    //Map sprite tile matrix
    private Map map;

    //Character sprites
    private DungeonHero hero;
    private SpriteImage stairs;

    //Sprite images.
    private ImageIcon heroImg;
    private ImageIcon stairsImg;

    //Dungeon data
    public boolean dungeonFinished;
    private int maxFloors;
    private int currentFloor;

    private CollisionMask mask;

    //Dialogue box
    private BoolDialogue stairsDialogue;
    private boolean loadStairsDialogue = true;



    /*
    Default constructor.
     */
    public Dungeon() {

        map = new Map();
        mask = new CollisionMask(map.collisionMask, map.getSizeX(), map.getSizeY());

        hero = new DungeonHero();
        stairs = new SpriteImage();

        heroImg = new ImageIcon(getClass().getResource("../Assets/Other/Characters/hero.png"));
        stairsImg = new ImageIcon(getClass().getResource("../Assets/Other/Characters/red.png"));

        hero.setIMAGE(heroImg);
        stairs.setIMAGE(stairsImg);

        stairsDialogue = new BoolDialogue();

        dungeonFinished = true;

    }


    //Re-initializer
    public void init(String dungeonType) {

        setDungeon(dungeonType);

        mask = new CollisionMask(map.collisionMask, map.getSizeX(), map.getSizeY());

        //Initialize location
        mask.setStairs(randomLoc(stairs));
        do {
            hero.matLoc = randomLoc(hero);
        } while (stairs.getX() == hero.getX() && stairs.getY() == hero.getY());

        mask.setHero(hero.matLoc);

        currentFloor = 1;

        dungeonFinished = false;

        stairsDialogue.setMessage("Do you want to continue?");
    }

    //Set variables for dungeon, tiles, and map according to user selection
    private void setDungeon(String dungeonType) {

        //Todo: remove this test
        System.out.println("Selected Dungeon: " + dungeonType);

        //Max room size: 9 NOT 10. Offset is 1.
        //I DON'T know what the min room size is.

        //What is the random offset for the room size?

        switch (dungeonType) {
            case "Test Area":
                map.setMap("TestArea", 2, 4, 6, 6);
                maxFloors = 2;
                break;
            case "Forgotten Forest":
                map.setMap("ForgottenForest", 4, 7, 7, 9);
                maxFloors = 3;
                break;
            case "Old Tower":
                map.setMap("OldTower", 7, 9, 9, 4);
                maxFloors = 4;
                break;
            case "Lava Delta":
                //map.setMap("LavaDelta", 9, 12, 4, 4);
                map.setMap("LavaDelta", 12, 12, 9, 9);
                maxFloors = 5;
                break;
            default:
                map.setMap("TestArea", 2, 5, 6, 6);
                maxFloors = 2;

        }

        map.init();
    }


    //Generate next floor
    private void newFloor() {

        currentFloor++;

        if (!checkDungeon()) {

            map.init();
            mask.updateMask(map.collisionMask);

            mask.setStairs(randomLoc(stairs));
            do {
                hero.matLoc = randomLoc(hero);
            } while (stairs.getX() == hero.getX() && stairs.getY() == hero.getY());

            mask.setHero(hero.matLoc);
        } else {
            Tile.init = false;
        }

    }


    //Game loop calls this function.
    public void run(InputListener in) {


        /*

        Decide what can run with input

        What can the hero do?
        -move to next floor
        -access menu
        -move


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





        /*
            Run stairs dialogue, or character.
        */
        if (stairsDialogue.isActive()) {

            //Show stairs dialogue
            stairsDialogue.run(in);

            if (stairsDialogue.yes) {
                newFloor();
                loadStairsDialogue = true;
            }

        } else if (loadStairsDialogue && mask.onStairs() && !stairsDialogue.isActive()) {

            //Activate stairs dialogue
            stairsDialogue.activate();

            loadStairsDialogue = false;

        } else {

            //Run hero
            hero.act(in, mask);

        }

        //Check if dialogue can be shown
        if (!mask.onStairs())
            loadStairsDialogue = true;




        /*

        //Adjust collision map for move or attack
        //Animate hero
        hero.act(in, collisionMap)


        //If collision map has changed, then act
        enemy.act()


        //Check for stairs interaction
        if(collisionMap.stairs == collisionMap.hero)
            stairs.init();


         */


        /*

        What is a collisionMap?

        Matrix of map floor locations

        Holds sprite location

        How many matrices does the collision mask need?
         - One for floor layout
         - One for item layout
         - One for attack layout
         - One for enemy layout



        TODO
        Hero act method
        CollisionMap class


        *Map returns matrix

        *Matrix initializes collisionMap.layout


        Hero location goes into collisionMap.characters

        Stairs location goes into collisionMap.characters


        Hero moves:
            - Check collision map if you can move
            - If yes, update hero loc in collision map
            - If no, don't do anything

        Check if hero and stairs have same location.
        If yes, show dialogue



        */


        //TODO: Make this dynamic like drawing code
        /*
        //Move all tiles
        //Move tiles instead of character
        for(int i = 0; i < 64; i++){
            for(int z = 0; z < 64; z++){
                map.matrix[i][z].move();
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

        /*
        //TODO: remove this test code (draws all tiles)
        for (int i = 0; i < map.getSizeY(); i++) {
            for (int z = 0; z < map.getSizeX(); z++) {
                SpriteImage.paint(g, p, map.matrix[i][z]);
            }
        }*/


        //Test code: Dynamic tile render
        for (int i = 0; i < map.getSizeY(); i++) {
            for (int z = 0; z < map.getSizeX(); z++) {
                Tile.paint(g, p, map.matrix[i][z], z * Tile.getWidth(map.matrix[0][0]), i * Tile.getHeight(map.matrix[0][0]));
            }
        }




        //Draw stairs
        SpriteImage.paint(g, p, stairs);

        //Draw hero
        SpriteImage.paint(g, p, hero);

        hero.draw(g, p);

        //Draw dialogue
        stairsDialogue.draw(g, p);
    }


    //Randomize sprite location
    private int[] randomLoc(SpriteImage h) {

        //Collision map info
        int[] cM = new int[2];

        //Sprite image location
        int[] temp = new int[2];

        //Get data from map
        int[] result = map.getRandomRoom();
        cM[0] = result[0];
        cM[1] = result[1];
        temp[0] = result[2];
        temp[1] = result[3];

        //Set hero starting location.
        h.setX(temp[0]);
        h.setY(temp[1]);

        return cM;
    }

    //Check for finished dungeon
    public boolean checkDungeon() {
        if (currentFloor > maxFloors) {
            dungeonFinished = true;
            return true;
        }
        return false;
    }

}