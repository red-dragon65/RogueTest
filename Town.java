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


    public Town() {

        heroImg = new ImageIcon(getClass().getResource("Assets/hero.png"));
        townImg = new ImageIcon(getClass().getResource("test/town.bin"));

        hero = new Hero(10);
        townMap = new SpriteImage();

        hero.setIMAGE(heroImg);
        townMap.setIMAGE(townImg);


        //TODO: remove this code
        townMap.setX(0);
        townMap.setY(0);

        init();

        townRun = true;
        speed = 3;
    }

    public void init() {
        hero.setX(650);
        hero.setY(1000);
    }


    //Game loops calls this function
    public void run(boolean input[]) {


        //Right
        if (input[0] && !input[1])
            hero.setVx(speed);
        //Left
        if (input[1] && !input[0])
            hero.setVx(-speed);

        if (!input[0] && !input[1])
            hero.setVx(0);


        //Up
        if (input[2] && !input[3])
            hero.setVy(-speed);

        //Down
        if (input[3] && !input[2])
            hero.setVy(speed);

        if (!input[2] && !input[3])
            hero.setVy(0);


        //Update hero location
        hero.move();

        if (hero.getY() > 1100) {
            townRun = false;
        }


    }


    public void draw(Graphics g, JPanel p) {

        townMap.paint(g, p);
        hero.paint(g, p);
    }


    public void loadDungeon() {
        //TODO: if y is greater than 1100
        if (hero.getY() > 1100) {
            townRun = false;
        }
    }


}
