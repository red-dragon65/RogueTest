package RogueGame;

//Allow panel drawing.

//Collision detection.

//Used for setting sprite image.

//Used for panel drawing.


/**
 * ABSTRACT: Class that holds data about a 'sprite' object.
 */
abstract class Sprite {

    //Hold sprite location.
    private int x, y;

    //Hold sprite velocity. (Value that increments location value.)
    private int vx, vy;

    //Set to display sprite.
    boolean show = true;


    /*
     * Default constructor.
     */
    protected Sprite() {

        x = 0;
        y = 0;
        vx = 0;
        vy = 0;
    }


    /*
     * Setters.
     */
    protected void setX(int x) {
        this.x = x;
    }

    protected void setY(int y) {
        this.y = y;
    }

    protected void setVx(int vx) {
        this.vx = vx;
    }

    protected void setVy(int vy) {
        this.vy = vy;
    }


    /*
     * Getters.
     */
    protected int getX() {
        return x;
    }

    protected int getY() {
        return y;
    }

    protected int getVx() {
        return vx;
    }

    protected int getVy() {
        return vy;
    }


    /*
     * Methods.
     */
    //Update location based on velocity.
    protected void move() {

        x += vx;
        y += vy;
    }


/////
}