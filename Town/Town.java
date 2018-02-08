package RogueGame.Town;

import RogueGame.Dialogue.BoolDialogue;
import RogueGame.Sprite.SpriteImage;

import javax.swing.*;
import java.awt.*;

public class Town {

    //Collision mask
    private CollisionMap mapMask;

    //Sprites
    private SpriteImage townMap;
    private SpriteImage townOverlay;
    private Hero hero;

    //Sprite Images
    private ImageIcon townImg;
    private ImageIcon heroImg;
    private ImageIcon townOvImg;

    //Town running info
    public boolean townRun;

    //TODO: remove this test code
    private BoolDialogue dialog;


    /*
     * Default constructor
    * */
    public Town() {

        heroImg = new ImageIcon(getClass().getResource("../Assets/hero.png"));
        townImg = new ImageIcon(getClass().getResource("../test/town.bin"));
        townOvImg = new ImageIcon(getClass().getResource("../test/townOverlay.png"));

        mapMask = new CollisionMap();

        hero = new Hero();
        townMap = new SpriteImage();
        townOverlay = new SpriteImage();

        hero.setIMAGE(heroImg);
        townMap.setIMAGE(townImg);
        townOverlay.setIMAGE(townOvImg);

        townMap.setX(0);
        townMap.setY(0);
        townOverlay.setX(0);
        townOverlay.setY(0);

        townRun = true;

        init();
    }

    public void init() {
        hero.resetLocation(mapMask);

        dialog = new BoolDialogue();
    }


    //Game loops calls this function
    public void run(boolean in[]) {

        /*

        TODO: remove this old code
        checkMove();

        hero.moveInBounds(in);


        //Stop moving
        if (!canMove) {
            hero.setVy(0);
            hero.setVx(0);
        }


        //Load dungeon if space is pressed
        if (in[4] && !canMove) {
            talk.show = false;
            canMove = true;
            townRun = false;
        }

        if (in[5]) {
            talk.show = false;
            canMove = true;
        }

        */





/*
        Yes no test
        -----------

        if(test.isActive()){

            //Run dialog box
            hero.stop();//Stop hero from moving
            test.run(in);

            //Load dungeon if necessary
            townRun = !test.yes;

        }else{

            //Run hero
            hero.moveInBounds(in);

            //Activate dialogue if necessary
            loadDungeon();

        }
*/


        if (dialog.isActive()) {

            //Run dialog box
            hero.stop();//Stop hero from moving
            dialog.run(in);

            //Load dungeon if necessary
            townRun = !dialog.yes;

        } else {

            //Run hero
            hero.moveInBounds(in, mapMask);

            //Activate dialogue if necessary
            //TODO: restore this
            loadDungeon();

        }



        /*
        General layout
        --------------

        What can user do?

        move

        access menu

        activate actions

        go through dialogue box

        go through store

        select dungeon


        if(dialogue.active()){

            //If dialogue is active and action is pressed
            //Show next part of dialogue
            if(in[4]){

                dialogue.next()

            }

        }else if(menu.active()){


            //If menu is active
            menu.run(input, inventory_stats)


        }else if(store.active()){

            //If stare is active
            store.run(input, inventory_stats)


        }else if(dungeonSelect.active()){

            //Load selected dungeon
            boolean loadDungeon = dungeonSelect.run(input)

            if(loadDungeon){
                selected = dungeonSelect.get();
                townRun = false;
            }

        }else{

            //Check for action or movement
            if(in[7]){

                //Check if event is possible
                for(action : actionSpots){

                    //See if valid
                    if(action.bounds(hero.loc)){

                        switch(action.type){

                            case "dialogue":
                                dialogue = init_dialogue(action.script)
                            break;

                            case "store":
                                store.init()
                            break;

                            case "dungeon":
                                dungeonSelect.init();
                            break;
                        }
                    }
                }

            }else if(in[5]){ //Check for menu access

                menu.init()

            }else{

                hero.move(input, mapBounds)
            }

        }

        */

    }


    public void draw(Graphics g, JPanel p) {

        townMap.paint(g, p);
        hero.paint(g, p);
        townOverlay.paint(g, p);


        //Draw dialog
        dialog.draw(g, p);

    }


    //TODO: remove this code?
    private void loadDungeon() {

        //Bound to load dungeon
        int bound = 1085;

        if (hero.getY() > bound) {

            //Push hero back
            hero.setY(hero.getY() - 5);
            hero.traverseTileY -= 5;

            dialog.setMessage("Teleport to dungeon?");
            dialog.activate();
        }
    }



}