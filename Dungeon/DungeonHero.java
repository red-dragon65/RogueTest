package RogueGame.Dungeon;

import RogueGame.Dialogue.ActionMenu;
import RogueGame.Dialogue.Dialogue;
import RogueGame.InputListener;
import RogueGame.ItemDB;
import RogueGame.Sprite.SpriteImage;
import RogueGame.UserData;

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

    //Stats
    private Stats stats;

    //Dialogues
    private boolean showMenu;
    private ActionMenu menu;

    //Stats GUI
    private Dialogue[] statGUI;


    /*
     * Default constructor.
     */
    public DungeonHero() {
        super();

        right = left = up = down = false;
        matLoc = new int[2];

        showMenu = false;
        menu = new ActionMenu("dungeon");

        //Set hero stats
        stats = new Stats(UserData.getStats());

        statGUI = new Dialogue[3];
        for (int i = 0; i < 3; i++) {
            statGUI[i] = new Dialogue(new ImageIcon(getClass().getResource("../Assets/Other/Dialogue/Options.png")));
        }

        statGUI[0].activate();
        statGUI[0].offSetDraw(-100, -300);


        statGUI[1].activate();
        statGUI[1].offSetDraw(100, -300);

        statGUI[2].activate();
        statGUI[2].offSetDraw(300, -300);

        setStatGUI();
    }

    private void setStatGUI() {
        statGUI[0].setMessage("LEVEL - - - - - - - - - - - - - - - - - - -       " + stats.getLevel());
        statGUI[1].setMessage("HP - - - - - - - - - - - - - - - - - - -      " + stats.getHealth()[0] + "/" + stats.getHealth()[1]);
        statGUI[2].setMessage("AP - - - - - - - - - - - - - - - - - - -      " + stats.getAP()[0] + "/" + stats.getAP()[1]);
    }

    //Move with bounds.
    protected void act(InputListener in, CollisionMask mask) {


        if (showMenu && menu.isActive()) {

            //Run menu
            menu.run(in);


            //Check if item is selected
            if (menu.selectedItem > 0) {

                //Get Item
                int temp = menu.selectedItem;

                //Update ap stats
                if (ItemDB.getItemStats(temp)[0] != 0) {

                    stats.updateAP(ItemDB.getItemStats(temp)[0]);

                }

                //Update hp stats
                if (ItemDB.getItemStats(temp)[1] != 0) {

                    stats.updateHealth(ItemDB.getItemStats(temp)[1]);
                }

                //Check dmg
                if (ItemDB.getItemStats(temp)[2] != 0) {

                    //Stop menu
                    //Apply damage to enemy TODO: make this work like a regular hero attack
                }

                //Reset selected item
                menu.selectedItem = -1;

                setStatGUI();

            }


        } else {

            //Check for inputs
            if (in.checkInput("escape")) {

                //Activate menu
                in.bufferEsc();
                showMenu = true;
                menu.activate();

                //TODO: remove this test code
                System.out.println("\n");
                System.out.println("Level: " + stats.getLevel());
                System.out.println("EXP: " + stats.getEXP()[0] + " Limit: " + stats.getEXP()[1]);
                System.out.println("Health: " + stats.getHealth()[0] + " Limit: " + stats.getHealth()[1]);
                System.out.println("AP: " + stats.getAP()[0] + " Limit: " + stats.getAP()[1]);

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

            if (step == (Tile.getSize() / speed) + 1) {

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

        for (int i = 0; i < 3; i++) {
            if (statGUI[i].isActive()) {
                statGUI[i].draw(g, p);
            }
        }
    }

}
