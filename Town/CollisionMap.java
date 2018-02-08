package RogueGame.Town;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class CollisionMap {


    //Hold map collision data (bitmap)
    private int[][] mask;
    private int[] heroLoc;
    private int[] mapSize;

    //Resolution of the collision mask.
    public final int tileSize = 12;


    //Default constructor
    public CollisionMap() {

        heroLoc = new int[2];

        try {
            convert();
        } catch (Exception e) {
            System.out.println("IMAGE BOUNDS CONVERSION FAILED.");
        }

        showMap();
    }


    //Extract image data into integer matrix collision mask
    private void convert() throws IOException {

        //Get image
        BufferedImage image = ImageIO.read(getClass().getResource("../test/townBounds.png"));

        //Initialize variables
        int width = image.getWidth();
        int height = image.getHeight();

        int offset = tileSize / 2;

        int maskx = height / tileSize;
        int masky = width / tileSize;

        mapSize = new int[2];
        mapSize[0] = maskx;
        mapSize[1] = masky;

        mask = new int[maskx][masky];


        //Populate matrix (does this by looking at the center of each 'tile' for black)
        for (int x = offset; x < height - offset; x += tileSize) {

            for (int y = offset; y < width - offset; y += tileSize) {

                if (image.getRGB(y, x) == Color.BLACK.getRGB()) {
                    mask[(x - offset) / tileSize][(y - offset) / tileSize] = 1;
                } else {
                    mask[(x - offset) / tileSize][(y - offset) / tileSize] = 0;
                }
            }
        }

    }


    //Update hero position on the collision mask matrix
    public void updateHero(String walk) {

        mask[heroLoc[0]][heroLoc[1]] = 0;

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

    //Convert hero location to collision mask
    public void setHero(int x, int y) {

        heroLoc[0] = (y / tileSize);//83
        heroLoc[1] = (x / tileSize);//55

        mask[heroLoc[0]][heroLoc[1]] = 5;

        showMap();
    }


    //Check if hero can move in that direction
    public boolean checkHero(String direction) {

        switch (direction) {
            case "right":
                return (mask[heroLoc[0]][heroLoc[1] + 1] != 1);
            case "left":
                return (mask[heroLoc[0]][heroLoc[1] - 1] != 1);
            case "up":
                return (mask[heroLoc[0] - 1][heroLoc[1]] != 1);
            case "down":
                return (mask[heroLoc[0] + 1][heroLoc[1]] != 1);
        }

        return false;
    }


    //Todo: Disable this test code
    //Print collision mask matrix
    public void showMap() {

        for (int x = 0; x < mapSize[0]; x++) {
            for (int y = 0; y < mapSize[1]; y++) {

                if (mask[x][y] != 0)
                    System.out.print(mask[x][y] + " ");
                else
                    System.out.print("  ");
            }
            System.out.print("\n");
        }
        System.out.flush();
    }


}

