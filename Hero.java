package RogueGame;


import java.awt.*;

/**
 * Class that holds hero specific data.
 */
public class Hero extends Enemy {

    private int exp = 0;

    private int speed = 5;

    /*
     * Default constructor.
     */
    public Hero(int lvl) {
        super(lvl);
    }



    //Move with bounds.
    protected void moveInBounds(boolean in[]) {

        //Right
        if (in[0] && !in[1])
            this.setVx(speed);
        //Left
        if (in[1] && !in[0])
            this.setVx(-speed);

        //Stop moving
        if (!in[0] && !in[1])
            this.setVx(0);


        //Up
        if (in[2] && !in[3])
            this.setVy(-speed);

        //Down
        if (in[3] && !in[2])
            this.setVy(speed);

        //Stop moving
        if (!in[2] && !in[3])
            this.setVy(0);

        move();


    }

    /*

    protected void move(input, collisionMap){




        //Check for damage
        if(collisionMap.attack = this.loc){
            collisionMap.attack = null;

            while(damage not done)
                damage.animate()
        }




        //Either attack or move
        if(this.attack){

            //Set attack
            collisionMap.[hero + direction] = attack;

            while(attack not done)
                attack.animate()

        }else{

            //Move a tile
            while(moving not done)
                this.move()
        }


        //Stop focus
        done = true


    }

     */


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
