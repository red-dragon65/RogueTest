package RogueGame.Dungeon;

import RogueGame.Dialogue.ActionMenu;
import RogueGame.InputListener;
import RogueGame.Sprite.SpriteImage;

import javax.swing.*;
import java.awt.*;

public class DungeonHero extends SpriteImage {

    private int speed = 8;

    //How many steps the character should take from one tile to the next.
    private int step = 0;

    //Direction movable allowed
    private boolean right, left, up, down;

    //Matrix location
    int[] matLoc;

    private boolean showMenu;
    private ActionMenu menu;


    /*
     * Default constructor.
     */
    public DungeonHero() {
        super();

        right = left = up = down = false;
        matLoc = new int[2];

        showMenu = false;
        menu = new ActionMenu("dungeon");
    }


    //Move with bounds.
    protected void act(InputListener in, CollisionMask mask) {


        if (showMenu && menu.isActive()) {

            //Run menu
            menu.run(in);

        } else {

            //Check for inputs
            if (in.checkInput("escape")) {

                //Activate menu
                in.bufferEsc();
                showMenu = true;
                menu.activate();

            } else {

                //Move character (if input && if collision mask)
                if (in.getInput()[0] && !left && !up && !down)
                    right = mask.checkHero("right");

                if (in.getInput()[1] && !right && !up && !down)
                    left = mask.checkHero("left");

                if (in.getInput()[2] && !right && !left && !down)
                    up = mask.checkHero("up");

                if (in.getInput()[3] && !right && !left && !up)
                    down = mask.checkHero("down");

                walk(right, "right", mask);
                walk(left, "left", mask);
                walk(up, "up", mask);
                walk(down, "down", mask);
            }
        }


        /*

        How input is switched

        //Check for damage
        if(collisionMap.attack = this.loc){
            collisionMap.attack = null;

            while(damage not done)
                damage.animate()
        }

        //Either attack or move
        if(this.attack){

            //Set attack
            collisionMap.[hero + direction] = attack;

            while(attack not done)
                attack.animate()

        }else{

            //Move a tile
            while(moving not done)
                this.move()
        }


        //Stop focus
        done = true

     */

        move();


    }


    private void walk(boolean direction, String type, CollisionMask mask) {

        if (direction) {

            step++;

            if (step == (24 / speed) + 1) {

                //If walk not possible
                switch (type) {
                    case "right":
                        right = false;
                        this.setVx(0);
                        mask.updateHero("right");
                        break;
                    case "left":
                        left = false;
                        this.setVx(0);
                        mask.updateHero("left");
                        break;
                    case "up":
                        up = false;
                        this.setVy(0);
                        mask.updateHero("up");
                        break;
                    case "down":
                        down = false;
                        this.setVy(0);
                        mask.updateHero("down");
                        break;
                }

                step = 0;

            } else {

                //If walk possible
                switch (type) {
                    case "right":
                        this.setVx(speed);
                        break;
                    case "left":
                        this.setVx(-speed);
                        break;
                    case "up":
                        this.setVy(-speed);
                        break;
                    case "down":
                        this.setVy(speed);
                        break;
                }

            }

        }

    }

    public void draw(Graphics g, JPanel p) {

        if (showMenu) {
            menu.draw(g, p);
        }
    }

}
