package RogueGame;


import java.awt.*;

/**
 * Class that holds hero specific data.
 */
public class Hero extends Enemy {

    private int exp = 0;

    /*
     * Default constructor.
     */
    public Hero(int lvl) {
        super(lvl);
    }


    //Move with bounds.
    protected void moveInBounds() {
        move();


    }


    public int getHealth() {
        return health;
    }

    public int getMana() {
        return mana;
    }

    public int getLvl() {
        return lvl;
    }


    public void addExp(int expval) {

        exp += expval;

        if (exp > (int) (lvl * mult)) {
            exp = 0;
            lvl += 1;
        }
    }


    /*
     * Methods.TODO: remove collision methods?
     */
    //Method to check for collisions.
    public boolean isCollision(SpriteImage other) {

        Rectangle thisSprite = new Rectangle(getX(), getY(), getWidth(), getHeight());
        Rectangle otherSprite = new Rectangle(other.getX(), other.getY(), other.getWidth(), other.getHeight());

        //See if images intersect.
        return thisSprite.intersects(otherSprite);
    }

/////
}
