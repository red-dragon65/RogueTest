package RogueGame;

//Allow drawing to panel.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//For game loop.
//Keyboard input listener.
//Panel that goes into JFrame.


/**
 * Class that creates a custom panel to go into
 * a window (JFrame). Holds main gameloop.
 */
public class Loop extends JPanel implements ActionListener {


    //Gameloop
    private Timer gameLoop;

    //Maps
    private Dungeon dungeon;
    private Town town;

    InputListener input;


    public final int width = 1704; // 640
    public final int height = 1320; // 360


    /*
     * Default constructor.
     */
    public Loop() {

        //Initialize keyboard input.
        input = new InputListener();
        addKeyListener(input);

        //Sets the focus to the JPanel (this).
        setFocusable(true);

        //Makes the movement smooth.
        setDoubleBuffered(true);

        //Set panel size. (Set to same size as window).
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

        town = new Town();
        dungeon = new Dungeon();
    }


    /*
     * Game loop timer. Runs at 50 intervals a second.
     */
    public void actionPerformed(ActionEvent e) {

        //TODO: remove old code
        //dungeon.run(input.getInput());
        //town.run(input.getInput());


        //Decide which game logic to run.
        if (town.townRun)
            town.run(input.getInput());
        else {
            if (dungeon.dungeonFinished) {
                dungeon.init();
            } else {

                dungeon.run(input.getInput());
                dungeon.checkDungeon();
                town.townRun = dungeon.dungeonFinished;

                if (town.townRun) {
                    town.init();
                }

            }
        }


        //refreshes the screen.
        render();
    }


    //TODO: Make this skip frame if necessary
    /*
    Skip frames if necessary.
     */
    private void render() {
        repaint();
    }


    /*
     * Draw graphics onto screen using sprite paint method.
     * NOTE: Whatever is painted last will be on top of the other sprites.
     */
    protected void paintComponent(Graphics g) {
        //Erases the previous screen.
        super.paintComponent(g);

        //Stop linux lagging.
        Toolkit.getDefaultToolkit().sync();

        //Decide which game area to paint.
        if (town.townRun) {
            town.draw(g, this);
        } else {
            if (dungeon != null)
                dungeon.draw(g, this);
        }


    }
/////
}