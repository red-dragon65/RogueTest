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
    public Stats stats;

    //Dialogues
    private ActionMenu menu;

    //Stats GUI
    private Dialogue[] statGUI;

    public boolean done = false;


    /*
     * Default constructor.
     */
    public DungeonHero() {
        super();

        right = left = up = down = false;
        matLoc = new int[2];

        menu = new ActionMenu("dungeon");

        //Set hero stats
        stats = new Stats(UserData.getStats(), UserData.getAttacks());

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

        setStatGUI();

        if (menu.isActive()) {

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
                    //Apply item damage to enemy TODO: make this work like a regular hero attack
                }

                //Reset selected item
                menu.selectedItem = -1;

                setStatGUI();
            }

            if (menu.rangeSelected) {


                int tempAttack = menu.selectedAttackType;

                //Get attack offset
                int x = mask.getHeroLoc()[1];
                int y = mask.getHeroLoc()[0];
                x += menu.selectedAttackX;
                y += menu.selectedAttackY;

                mask.addAttack(x, y, tempAttack);
                done = true;

                menu.rangeSelected = false;

                if (tempAttack > 0) {
                    stats.updateAP(-AttackDB.getAP(tempAttack));
                }
            }

        } else {


            if (in.checkInput("escape")) {

                //Activate menu
                in.bufferEsc();
                menu.activate();
                menu.setLoc(this.getX(), this.getY());
                menu.setAP(stats.getAP()[0]);

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

            //Let hero only walk one tile at a time
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

                done = true;

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

        if (menu.isActive()) {
            menu.draw(g, p);
        }

        for (int i = 0; i < 3; i++) {
            if (statGUI[i].isActive()) {
                statGUI[i].draw(g, p);
            }
        }
    }

}
