package RogueGame;

import javax.swing.*;
import java.awt.*;

public class Town {

    //Collision mask
    //TODO: add collision mask

    //Sprites
    private SpriteImage townMap;
    private Hero hero;
    private int speed;

    //Sprite Images
    private ImageIcon townImg;
    private ImageIcon heroImg;

    public boolean townRun;
    public boolean canMove;


    //TODO: remove this test code
    Dialogue talk;


    /*
    * Default contsructor
    * */
    public Town() {

        heroImg = new ImageIcon(getClass().getResource("Assets/hero.png"));
        townImg = new ImageIcon(getClass().getResource("test/town.bin"));

        hero = new Hero();
        townMap = new SpriteImage();

        hero.setIMAGE(heroImg);
        townMap.setIMAGE(townImg);

        townMap.setX(0);
        townMap.setY(0);


        townRun = true;
        speed = 3;

        talk = new Dialogue();
        talk.setMessage("", "");
        talk.show = false;

        canMove = true;


        init();
    }

    public void init() {
        hero.setX(650);
        hero.setY(1000);
        talk.show = false;
    }


    //Game loops calls this function
    public void run(boolean in[]) {

        checkMove();


        //Right
        if (in[0] && !in[1])
            hero.setVx(speed);

        //Left
        if (in[1] && !in[0])
            hero.setVx(-speed);

        //Stop moving
        if (!in[0] && !in[1])
            hero.setVx(0);


        //Up
        if (in[2] && !in[3])
            hero.setVy(-speed);

        //Down
        if (in[3] && !in[2])
            hero.setVy(speed);

        //Stop moving
        if (!in[2] && !in[3])
            hero.setVy(0);


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



        /*

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
            if(in[4]){

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


        //Update hero location
        hero.move();

        //TODO: restore this
        loadDungeon();


    }


    public void draw(Graphics g, JPanel p) {

        townMap.paint(g, p);
        hero.paint(g, p);


        if (talk.show) {
            talk.draw(g, p);
        }
    }


    //TODO: remove this code?
    private void loadDungeon() {

        if (hero.getY() > 1100) {

            hero.setY(1099);

            canMove = false;

            talk.setMessage("Press space to load...", "Press escape to quit...");
            talk.show = true;
            //townRun = false;

            //talk.setMessage("Set Message.");
            //talk.show = true;
        }
    }


    private void checkMove() {

        canMove = !talk.show;
    }


}

/*
*
* //Prevent movement when necessary.
* if(dialogue || animation){
*     buffer = true;
* }
*
* //Other input.
* if(input && !buffer){
*   //Process input
* }
*
*
* //Show object message to screen if necessary.
* if(user press == A){
*   if(object exists){
*       dialogue.setMessage(object.message.pop());
*       dialogue.show;
*   }
* }
*
* Create scene generator for town.
*
*
*
*/