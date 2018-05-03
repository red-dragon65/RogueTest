package RogueGame.Dungeon;

import RogueGame.Dialogue.BoolDialogue;
import RogueGame.InputListener;
import RogueGame.ItemDB;
import RogueGame.Sprite.SpriteImage;
import RogueGame.UserData;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class Dungeon {


    //Map sprite tile matrix
    private Map map;

    //Character sprites
    private DungeonHero hero;
    private SpriteImage stairs;
    private SpriteImage[] items;
    private int numItems;
    private int[][] itemLoc;
    private int[] itemID;

    private Random gen = new Random();

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

        //Initialize sprites
        hero = new DungeonHero();
        stairs = new SpriteImage();


        heroImg = new ImageIcon(getClass().getResource("../Assets/Other/Characters/hero.png"));
        stairsImg = new ImageIcon(getClass().getResource("../Assets/Other/Characters/red.png"));

        hero.setIMAGE(heroImg);
        stairs.setIMAGE(stairsImg);

        stairsDialogue = new BoolDialogue();

        dungeonFinished = true;


        stairsDialogue.setMessage("Do you want to continue?");

    }


    //Re-initializer
    public void init(String dungeonType) {

        //(This codes runs once per selected dungeon)

        setDungeon(dungeonType);

        mask = new CollisionMask(map.collisionMask, map.getSizeX(), map.getSizeY());


        //Initialize items
        items = new SpriteImage[numItems];
        itemLoc = new int[numItems][2];
        itemID = new int[numItems];

        //Initialize items
        for (int i = 0; i < numItems; i++) {
            items[i] = new SpriteImage();
        }


        initMap();

        currentFloor = 1;

        dungeonFinished = false;

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
                numItems = 3;
                break;
            case "Forgotten Forest":
                map.setMap("ForgottenForest", 4, 7, 7, 9);
                maxFloors = 3;
                numItems = 6;
                break;
            case "Old Tower":
                map.setMap("OldTower", 7, 9, 9, 4);
                maxFloors = 4;
                numItems = 8;
                break;
            case "Lava Delta":
                //map.setMap("LavaDelta", 9, 12, 4, 4);
                map.setMap("LavaDelta", 12, 12, 9, 9);
                maxFloors = 5;
                numItems = 12;
                break;
            default:
                map.setMap("TestArea", 2, 5, 6, 6);
                maxFloors = 2;
                numItems = 4;

        }

    }

    public void initMap() {

        //Init rooms
        map.initONE();
        mask.updateMask(map.collisionMask);

        //Set sprites
        mask.setStairs(randomLoc(stairs));

        hero.matLoc = randomLoc(hero);
        mask.setHero(hero.matLoc);


        //Set items
        int[] temp;
        for (int i = 0; i < numItems; i++) {

            temp = randomLoc(items[i]);

            itemLoc[i][0] = temp[0];
            itemLoc[i][1] = temp[1];
        }

        //Generate item types
        for (int i = 0; i < numItems; i++) {
            itemID[i] = (gen.nextInt(ItemDB.itemMaxRange + 1) + ItemDB.itemMinRange - 1);
        }

        //Initialize item images
        for (int i = 0; i < numItems; i++) {

            //items[i] = new SpriteImage(new ImageIcon(getClass().getResource(ItemDB.getImage(itemID[i]))));
            items[i].setIMAGE(new ImageIcon(getClass().getResource(ItemDB.getImage(itemID[i]))));
            items[i].show();
        }



        //Finish init
        map.initTWO();
        mask.updateMask(map.collisionMask);
    }


    //Generate next floor
    private void newFloor() {

        System.out.println("Floor loading");

        currentFloor++;

        if (!checkDungeon()) {

            initMap();

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

        //Load dialogue if hero on stairs
        if (!mask.onStairs())
            loadStairsDialogue = true;


        int[] temp = mask.getHeroLoc();

        //Check if hero on item
        for (int i = 0; i < numItems; i++) {

            if (temp[0] == itemLoc[i][0] && temp[1] == itemLoc[i][1]) {

                //Add item (exclude money, and 0 ID)
                if (itemID[i] > 1) {
                    UserData.addItem(itemID[i]);
                }

                //Add money
                if (itemID[i] == 1) {

                    UserData.addMoney(gen.nextInt(50));
                }

                items[i].hide();
                itemLoc[i][0] = 0;
                itemLoc[i][1] = 0;
                itemID[i] = 0;

                System.out.println("Stepping on item! " + i);
                break;
            }
        }




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


        //Draw items to screen
        if (numItems != 0) {
            for (int i = 0; i < numItems; i++) {

                if (items[i] != null) {
                    SpriteImage.paint(g, p, items[i]);
                }
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


    private int[] randomLoc(SpriteImage h) {


        //Collision map location info
        int[] cM = new int[2];

        //Sprite image location
        int[] xyLoc = new int[2];

        //Get random tile
        do {
            cM[1] = gen.nextInt(map.getSizeX());
            cM[0] = gen.nextInt(map.getSizeY());

        } while (!map.matrix[cM[0]][cM[1]].getName().equals("dirt"));

        xyLoc[0] = cM[1] * Tile.getWidth(map.matrix[0][0]);
        xyLoc[1] = cM[0] * Tile.getHeight(map.matrix[0][0]);

        //Set sprite location. (x, y)
        h.setX(xyLoc[0]);
        h.setY(xyLoc[1]);

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