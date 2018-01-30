package RogueGame.Dungeon;

public class CollisionMask {


    int[][] mask;

    int[] heroLoc;
    int[] stairs;
    int[] mapDimensions;


    public CollisionMask(int[][] mask, int xWidth, int yWidth) {

        this.mask = mask;

        heroLoc = new int[2];
        stairs = new int[2];
        mapDimensions = new int[2];

        mapDimensions[0] = xWidth;
        mapDimensions[1] = yWidth;

    }


    public void updateMask(int[][] mask) {
        this.mask = mask;
    }

    public void setHero(int[] loc) {

        heroLoc[0] = loc[0];
        heroLoc[1] = loc[1];

        mask[loc[0]][loc[1]] = 5;
    }

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

    public boolean onStairs() {
        return (heroLoc[0] == stairs[0] && heroLoc[1] == stairs[1]);
    }

    public void setStairs(int[] loc) {

        stairs[0] = loc[0];
        stairs[1] = loc[1];

        mask[loc[0]][loc[1]] = 3;
    }


    //Draws mask to terminal
    //TODO: Remove this test code
    public void draw() {

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

}