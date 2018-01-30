package RogueGame;

//For making windows.

import javax.swing.*;


/**
 * Class to create a window with custom parameters.
 */
public class Window extends JFrame {

    /*
    Default constructor.
     */
    public Window() {
        Loop p = new Loop();

        //Add the display panel to this window.
        add(p);

        //Specify the close button action.
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        //Set the size of the window.
        //setSize(1280, 720);
        setSize(p.width, p.height);

        //Prevent user from changing window size.
        setResizable(false);

        //Displays the window to user.
        setVisible(true);
    }


    /**
     * Main method that creates a new instance
     * of the window class.
     */
    public static void main(String args[]) {

        //Create a new window.
        new Window();
    }
/////
}