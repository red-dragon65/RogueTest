package RogueGame.Dungeon;

import java.util.ArrayList;

public class CollisionMask {

    private int[][] mask;

    private int[] heroLoc;
    private int[] stairs;
    private int[] mapDimensions;

    private int[][] enemyLoc;

    private static ArrayList<Integer> attackLocX;
    private static ArrayList<Integer> attackLocY;
    private static ArrayList<Integer> attackID;
    private static boolean init = true;


    public CollisionMask(int[][] mask, int xWidth, int yWidth) {

        this.mask = mask;

        heroLoc = new int[2];
        stairs = new int[2];
        mapDimensions = new int[2];

        mapDimensions[0] = xWidth;
        mapDimensions[1] = yWidth;

        init();

    }

    private void init() {

        if (init) {

            attackLocX = new ArrayList<>();
            attackLocY = new ArrayList<>();
            attackID = new ArrayList<>();

            init = false;
        }
    }

    public void setEnemyLoc(int[][] array) {
        enemyLoc = array;


        //Set enemies on collision mask
        for (int i = 0; i < enemyLoc.length; i++) {

            mask[enemyLoc[i][0]][enemyLoc[i][1]] = 0;
        }
    }

    public void disableEnemy(int index) {

        //Reset collision
        mask[enemyLoc[index][0]][enemyLoc[index][1]] = 1;

        enemyLoc[index][0] = 0;
        enemyLoc[index][1] = 0;

    }

    public int[] getEnemyLoc(int index) {

        return enemyLoc[index];
    }


    public boolean checkAttack(int[] loc) {

        for (int i = 0; i < attackID.size(); i++) {

            if (attackLocX.get(i) == loc[0] && attackLocY.get(i) == loc[1]) {
                return true;
            }
        }

        return false;
    }

    //Return attack ID if sprite is overlapping attack data
    public int getAttack(int[] loc) {

        int temp = 0;

        for (int i = 0; i < attackID.size(); i++) {

            if (attackLocX.get(i) == loc[0] && attackLocY.get(i) == loc[1]) {
                temp = attackID.get(i);
                attackID.remove(i);
                attackLocX.remove(i);
                attackLocY.remove(i);
            }
        }

        return temp;
    }

    public void resetAttacks() {
        attackID.clear();
        attackLocX.clear();
        attackLocY.clear();
    }




    //Updates mask on new floor call
    public void updateMask(int[][] mask) {
        this.mask = mask;
    }


    //Set stairs location on collision mask
    public void setHero(int[] loc) {

        heroLoc[0] = loc[0];
        heroLoc[1] = loc[1];

        mask[loc[0]][loc[1]] = 5;
    }

    public int[] getHeroLoc() {

        return heroLoc;
    }


    //Update hero position on collision mask
    public void updateHero(String walk) {

        mask[heroLoc[0]][heroLoc[1]] = 1;

        switch (walk) {
            case "right":
                heroLoc[1]++;
                break;
            case "left":
                heroLoc[1]--;
                break;
            case "up":
                heroLoc[0]--;
                break;
            case "down":
                heroLoc[0]++;
                break;
        }

        mask[heroLoc[0]][heroLoc[1]] = 5;
    }

    //Check direction hero wants to walk
    public boolean checkHero(String direction) {

        switch (direction) {
            case "right":
                return (mask[heroLoc[0]][heroLoc[1] + 1] != 0);
            case "left":
                return (mask[heroLoc[0]][heroLoc[1] - 1] != 0);
            case "up":
                return (mask[heroLoc[0] - 1][heroLoc[1]] != 0);
            case "down":
                return (mask[heroLoc[0] + 1][heroLoc[1]] != 0);
        }

        return false;
    }

    //Check for collision with stairs
    public boolean onStairs() {
        return (heroLoc[0] == stairs[0] && heroLoc[1] == stairs[1]);
    }


    //Set stairs location on collision mask
    public void setStairs(int[] loc) {

        stairs[0] = loc[0];
        stairs[1] = loc[1];

        mask[loc[0]][loc[1]] = 3;
    }


    //Add attack to queue
    public void addAttack(int x, int y, int type) {

        //X and Y are inverted
        attackLocX.add(y);
        attackLocY.add(x);

        attackID.add(type);

    }

    /*
    //TODO: Disable this test code
    //Draws mask to terminal
    public void showMask() {

        //Initialize collisionMap
        for (int i = 0; i < mapDimensions[1]; i++) {
            for (int z = 0; z < mapDimensions[0]; z++) {


                if (mask[i][z] != 0)
                    System.out.print(mask[i][z]);
                else
                    System.out.print(" ");

            }
            System.out.print("\n");
        }
        System.out.flush();
    }*/

}