package RogueGame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * Class that reads keyboard input for moving.
 */
public class InputListener extends KeyAdapter {

    //Hold input values.
    private boolean input[] = new boolean[6];

    private boolean spaceBuffer = true;
    private boolean escBuffer = true;

    private boolean verticalBuffer = false;
    private boolean downBuffer = true;
    private boolean upBuffer = true;

    private boolean horizontalBuffer = false;
    private boolean leftBuffer = true;
    private boolean rightBuffer = true;



    /*
     * Event that occurs when the user presses a key.
     * Used to move hero.
     */
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                //input[0] = true;

                if (!horizontalBuffer) {
                    input[0] = true;
                } else {

                    //Buffer down
                    if (rightBuffer) {
                        input[0] = true;
                        rightBuffer = false;
                    } else {
                        input[0] = false;
                    }
                }

                break;
            case KeyEvent.VK_LEFT:
                //input[1] = true;

                if (!horizontalBuffer) {
                    input[1] = true;
                } else {

                    //Buffer down
                    if (leftBuffer) {
                        input[1] = true;
                        leftBuffer = false;
                    } else {
                        input[1] = false;
                    }
                }

                break;
            case KeyEvent.VK_UP:
                //input[2] = true;

                if (!verticalBuffer) {
                    input[2] = true;
                } else {

                    //Buffer down
                    if (upBuffer) {
                        input[2] = true;
                        upBuffer = false;
                    } else {
                        input[2] = false;
                    }
                }


                break;
            case KeyEvent.VK_DOWN:
                //input[3] = true;

                if (!verticalBuffer) {
                    input[3] = true;
                } else {

                    //Buffer down
                    if (downBuffer) {
                        input[3] = true;
                        downBuffer = false;
                    } else {
                        input[3] = false;
                    }
                }


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
                //input[5] = true;

                //Buffer esc
                if (escBuffer) {
                    input[5] = true;
                    escBuffer = false;
                } else {
                    input[5] = false;
                }

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
                //Buffer up
                input[2] = false;
                break;
            case KeyEvent.VK_DOWN:
                //Buffer down
                input[3] = false;
                break;
            case KeyEvent.VK_SPACE:
                //Buffer space bar
                input[4] = false;
                spaceBuffer = true;
                break;
            case KeyEvent.VK_ESCAPE:
                //Buffer esc
                input[5] = false;
                escBuffer = true;
                break;
        }

        //Set buffer on release
        if (verticalBuffer) {
            downBuffer = true;
            upBuffer = true;
            leftBuffer = true;
            rightBuffer = true;
        } else {
            downBuffer = false;
            upBuffer = false;
            leftBuffer = false;
            rightBuffer = false;
        }
    }


    /**
     * Prevent buttons from being spammed when necessary
     */
    public void bufferSpace() {

        spaceBuffer = false;
        input[4] = false;
    }

    public void bufferEsc() {

        escBuffer = false;
        input[5] = false;
    }


    public void turnOnVBuffer() {

        if (!verticalBuffer) {
            if (input[0] || input[1] || input[2] || input[3] || input[4] || input[5]) {

                verticalBuffer = true;

                upBuffer();
                downBuffer();

            } else {

                verticalBuffer = true;

                upBuffer = true;
                downBuffer = true;

            }
        }
    }

    public void turnOffVBuffer() {

        if (verticalBuffer) {
            verticalBuffer = false;
            upBuffer();
            downBuffer();
        }
    }

    public void upBuffer() {

        upBuffer = false;
        input[2] = false;
    }

    public void downBuffer() {

        downBuffer = false;
        input[3] = false;
    }


    public void turnOnHBuffer() {

        if (!horizontalBuffer) {
            if (input[0] || input[1] || input[2] || input[3] || input[4] || input[5]) {

                horizontalBuffer = true;

                upBuffer();
                downBuffer();

            } else {

                horizontalBuffer = true;

                leftBuffer = true;
                rightBuffer = true;

            }
        }
    }

    public void turnOffHBuffer() {

        if (horizontalBuffer) {
            horizontalBuffer = false;
            upBuffer();
            downBuffer();
        }
    }

    public void leftBuffer() {

        leftBuffer = false;
        input[1] = false;
    }

    public void rightBuffer() {

        rightBuffer = false;
        input[0] = false;
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

