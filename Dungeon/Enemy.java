package RogueGame.Dungeon;


import RogueGame.InputListener;
import RogueGame.Sprite.SpriteImage;

import javax.swing.*;

/**
 * Class that holds enemy specific data.
 */
public class Enemy extends SpriteImage {


    public Stats stats;

    public static boolean done = false;

    public boolean dead;


    public Enemy() {
        super();
    }


    public void setType(int ID) {

        //TODO: get attacks from enemyDB
        int[] tempy = new int[4];
        tempy[0] = 2;
        tempy[1] = 2;
        tempy[2] = 3;
        tempy[3] = 3;

        dead = false;

        stats = new Stats(EnemyDB.getStats(ID), tempy);

        this.setIMAGE(new ImageIcon(getClass().getResource(EnemyDB.getImage(ID))));

        this.show();
    }


    //Move with bounds.
    protected void act(InputListener in, CollisionMask mask, int index) {
        move();


        //Take damage
        if (mask.checkAttack(mask.getEnemyLoc(index))) {

            stats.updateHealth(-AttackDB.getDamage(mask.getAttack(mask.getEnemyLoc(index))));

        }

        //Check for death
        if (stats.getHealth()[0] == 0) {
            dead = true;
        }

        done = true;

        //Run enemy logic if alive
        if (!dead) {

            //TODO: Attack or move (Check the collision mask, and then make a decision)
        }


    }


/////
}
