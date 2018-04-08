package RogueGame.Dungeon;

public class CollisionMask {

    private int[][] mask;

    private int[] heroLoc;
    private int[] stairs;
    private int[] mapDimensions;


    public CollisionMask(int[][] mask, int xWidth, int yWidth) {

        this.mask = mask;

        heroLoc = new int[2];
        stairs = new int[2];
        mapDimensions = new int[2];

        mapDimensions[0] = xWidth;
        mapDimensions[1] = yWidth;

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
    }
*/
}