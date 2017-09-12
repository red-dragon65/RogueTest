package RogueGame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * Class that reads keyboard input for moving.
 */
public class InputListener extends KeyAdapter {

    //Hold input values.
    private boolean input[] = new boolean[6];

    /*
     * Event that occurs when the user presses a key.
     * Used to move hero.
     */
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                input[0] = true;
                break;
            case KeyEvent.VK_LEFT:
                input[1] = true;
                break;
            case KeyEvent.VK_UP:
                input[2] = true;
                break;
            case KeyEvent.VK_DOWN:
                input[3] = true;
                break;
            case KeyEvent.VK_SPACE:
                input[4] = true;
                break;
            case KeyEvent.VK_ESCAPE:
                input[5] = true;
                break;
        }
    }


    /*
     * Event that occurs when the user releases a key.
     * Used to stop the hero.
     */
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                input[0] = false;
                break;
            case KeyEvent.VK_LEFT:
                input[1] = false;
                break;
            case KeyEvent.VK_UP:
                input[2] = false;
                break;
            case KeyEvent.VK_DOWN:
                input[3] = false;
                break;
            case KeyEvent.VK_SPACE:
                input[4] = false;
                break;
            case KeyEvent.VK_ESCAPE:
                input[5] = false;
                break;
        }
    }


    //Return input values.
    public boolean[] getInput() {
        return input;
    }
}

