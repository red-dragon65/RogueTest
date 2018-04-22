package RogueGame.Town;


import RogueGame.Dialogue.ActionMenu;
import RogueGame.InputListener;
import RogueGame.Sprite.SpriteImage;

import javax.swing.*;
import java.awt.*;

/**
 * Class that holds hero specific data.
 */
public class Hero extends SpriteImage {

    private int speed = 5;
    private int traverseTileX = 0;

    private ActionMenu menu;

    public boolean free;

    //Todo: make this private?
    int traverseTileY = 0;


    //Default constructor
    public Hero() {
        super();
        menu = new ActionMenu("town");
        free = true;
    }



    //Move with bounds.
    protected void moveInBounds(InputListener in, CollisionMap mapMask, NPCmap n) {


        if (menu.isActive()) {
            //menu
            stop();
            menu.run(in);

            if (!menu.isActive()) {
                free = true;
            }

        } else {

            //walk
            if (in.checkInput("escape")) {
                in.bufferEsc();
                menu.activate();
                free = false;

            } else {
                walk(in.checkInput("right"), !n.isCollision(this).equals("right"), "right", mapMask);
                walk(in.checkInput("left"), !n.isCollision(this).equals("left"), "left", mapMask);
                walk(in.checkInput("up"), !n.isCollision(this).equals("top"), "up", mapMask);
                walk(in.checkInput("down"), !n.isCollision(this).equals("bottom"), "down", mapMask);
                stop();
            }
        }

    }


    private void walk(boolean direction, boolean check, String type, CollisionMap mapMask) {

        boolean flag = true;

        stop();

        //Move
        if (direction && check) {

            //Check if move is valid
            switch (type) {
                case "right":
                    traverseTileX += speed;
                    if (traverseTileX < mapMask.tileSize / 2) {

                        this.setVx(speed);
                        move();
                        flag = false;
                    }
                    break;
                case "left":
                    traverseTileX -= speed;
                    if (traverseTileX > -mapMask.tileSize / 2) {

                        this.setVx(-speed);
                        move();
                        flag = false;
                    }
                    break;
                case "up":
                    traverseTileY -= speed;
                    if (traverseTileY > -mapMask.tileSize / 2) {

                        this.setVy(-speed);
                        move();
                        flag = false;
                    }
                    break;
                case "down":
                    traverseTileY += speed;
                    if (traverseTileY < mapMask.tileSize / 2) {

                        this.setVy(speed);
                        move();
                        flag = false;
                    }
                    break;
            }

            if (flag) {

                //Check for collision
                if (mapMask.checkHero(type)) {

                    switch (type) {
                        case "right":
                            traverseTileX -= mapMask.tileSize;
                            mapMask.updateHero("right");
                            this.setVx(speed);
                            break;
                        case "left":
                            traverseTileX += mapMask.tileSize;
                            mapMask.updateHero("left");
                            this.setVx(-speed);
                            break;
                        case "up":
                            traverseTileY += mapMask.tileSize;
                            mapMask.updateHero("up");
                            this.setVy(-speed);
                            break;
                        case "down":
                            traverseTileY -= mapMask.tileSize;
                            mapMask.updateHero("down");
                            this.setVy(speed);
                            break;
                    }

                    move();

                } else {

                    //Undo theoretical move
                    switch (type) {
                        case "right":
                            traverseTileX -= speed;
                            break;
                        case "left":
                            traverseTileX += speed;
                            break;
                        case "up":
                            traverseTileY += speed;
                            break;
                        case "down":
                            traverseTileY -= speed;
                            break;
                    }
                }
            }
        }

    }


    //Stop character movement
    public void stop() {

        if (this.getVx() != 0 || this.getVy() != 0) {
            this.setVx(0);
            this.setVy(0);
        }
    }

    public void resetLocation(CollisionMap mapMask) {

        //Set image location
        /*
        These numbers are offset by 6.
        This allows collision to seem to occur at the
        center of the character sprite.
        */
        setX(654);
        setY(990);

        //Reset tile offset
        traverseTileX = 0;
        traverseTileY = 0;

        //Set collision mask location
        mapMask.setHero(660, 996); //offset from 'setX' and 'setY' by 6

    }

    public void draw(Graphics g, JPanel p) {

        if (menu.isActive()) {
            menu.draw(g, p);
        }
    }

}
