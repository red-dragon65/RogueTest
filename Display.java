package RogueGame;

//Allow drawing to panel.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//For game loop.
//Keyboard input listener.
//Panel that goes into JFrame.


/**
 * Class that creates a custom panel to go into
 * a window (JFrame). Holds main gameloop.
 */
public class Display extends JPanel implements ActionListener {


    //Gameloop
    private Timer gameLoop;

    //Maps
    private Map map;

    //Sprites.
    private Hero hero;

    //Sprite images.
    private ImageIcon heroImg;


    public final int width = 1704; // 640
    public final int height = 1320; // 360


    /*
     * Default constructor.
     */
    public Display() {

        //Initialize keyboard input.
        addKeyListener(new KeyPressing());

        //Sets the focus to the JPanel (this).
        setFocusable(true);

        //Makes the movement smooth.
        setDoubleBuffered(true);

        //Set panel size. (Set to same size as window).
        //setSize(1280, 720);
        setSize(width, height);

        initializeSprites();

        //Create the game loop at 20 milliseconds intervals.
        //1,000 milliseconds in a second. 1,000 / 20 = 50 intervals. A.K.A 50fps.
        gameLoop = new Timer(20, this);
        gameLoop.start();
    }


    /*
     * Method to set sprite data.
     */
    private void initializeSprites() {

        map = new Map();

        hero = new Hero(10);

        heroImg = new ImageIcon(getClass().getResource("Assets/hero.png"));

        //Set hero image.
        hero.setIMAGE(heroImg);

        int[] temp = map.getRandomRoom();

        //Set hero starting location.
        hero.setX(temp[0]);
        hero.setY(temp[1]);
        //hero.setX(startx);
        //hero.setY(starty);
    }


    /*
     * Game loop timer. Runs at 50 intervals a second.
     */
    public void actionPerformed(ActionEvent e) {

        //Makes the hero move.
        hero.moveInBounds();


        //TODO: Make this dynamic like drawing code
        /*
        for(int i = 0; i < 64; i++){
            for(int z = 0; z < 64; z++){
                map.matrix[i][z].move();
            }
        }*/


        //TODO: call load to re-initialize variables

/*
        //Check for collisions.
        for(Enemy s : enemies) {

            if(hero.isCollision(s)){

                /**If they instersect, do something.*/

        //* if(other.right > thisSprite.left)
        /*
                if((s.getX() + (s.getWidth()) > hero.getX())){
                    //Reverse direction.
                    s.setVx(-(s.getVx()));
                }
                //* if(other.left < thisSprite.right)
                if(s.getX() < (hero.getX() + hero.getWidth())){
                    //Reverse direction.
                    s.setVx(-s.getVx());
                }
            }

        }*/

        //refreshes the screen.
        repaint();
    }


    /*
     * Draw graphics onto screen using sprite paint method.
     * NOTE: Whatever is painted last will be on top of the other sprites.
     */
    public void paintComponent(Graphics g) {
        //Erases the previous screen.
        super.paintComponent(g);

        //Stop linux lagging.
        Toolkit.getDefaultToolkit().sync();


        //TODO: this dynamically draws tiles when in bounds
        /*
        for(int i = 0; i < 64; i++){
            for(int z = 0; z < 64; z++){
                if(map.matrix[i][z].getX() > 0 && map.matrix[i][z].getX() < 1200)
                    if(map.matrix[i][z].getY() > 0 && map.matrix[i][z].getY() < 480)
                    map.matrix[i][z].paint(g, this);
            }
        }
        */


        //TODO: remove this test code (draws all tiles)
        for (int i = 0; i < map.getSizeY(); i++) {
            for (int z = 0; z < map.getSizeX(); z++) {
                map.matrix[i][z].paint(g, this);
            }
        }


        //Draw hero.
        hero.paint(g, this);
    }


    /**
     * Class that reads keyboard input for moving.
     */
    public class KeyPressing extends KeyAdapter {

        int speed = 5;

        /*
         * Event that occurs when the user presses a key.
         * Used to move hero.
         */
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                    hero.setVx(speed);
                    break;
                case KeyEvent.VK_LEFT:
                    hero.setVx(-speed);
                    break;
                case KeyEvent.VK_UP:
                    hero.setVy(-speed);
                    break;
                case KeyEvent.VK_DOWN:
                    hero.setVy(speed);
                    break;
                case KeyEvent.VK_SPACE:
                    map.init();

                    int[] temp = map.getRandomRoom();

                    //Set hero starting location.
                    hero.setX(temp[0]);
                    hero.setY(temp[1]);
                    break;

            }
            repaint();
        }


        /*
         * Event that occurs when the user releases a key.
         * Used to stop the hero.
         */
        public void keyReleased(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                    hero.setVx(0);
                    break;
                case KeyEvent.VK_LEFT:
                    hero.setVx(0);
                    break;
                case KeyEvent.VK_UP:
                    hero.setVy(0);
                    break;
                case KeyEvent.VK_DOWN:
                    hero.setVy(0);
                    break;
            }
        }
    }
/////
}