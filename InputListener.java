package RogueGame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * Class that reads keyboard input for moving.
 */
public class InputListener extends KeyAdapter {

    //Hold input values.
    private boolean input[] = new boolean[6];

    //Todo: remove test code
    private boolean spaceBuffer = true;


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
                //input[4] = true;

                //Buffer space bar
                if (spaceBuffer) {
                    input[4] = true;
                    spaceBuffer = false;
                } else {
                    input[4] = false;
                }

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
                //Buffer space bar
                input[4] = false;
                spaceBuffer = true;
                break;
            case KeyEvent.VK_ESCAPE:
                input[5] = false;
                break;
        }
    }

    //Don't allow spamming
    public void bufferInput() {

        //Currently, only space bar is buffered
        spaceBuffer = false;
        input[4] = false;

    }

    public boolean checkInput(String s) {

        switch (s) {
            case "right":
                return input[0];
            case "left":
                return input[1];
            case "up":
                return input[2];
            case "down":
                return input[3];
            case "space":
                return input[4];
            case "escape":
                return input[5];
        }

        return false;
    }

    //Return input values.
    public boolean[] getInput() {
        return input;
    }
}

