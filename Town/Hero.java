package RogueGame.Town;


import RogueGame.Sprite.SpriteImage;

/**
 * Class that holds hero specific data.
 */
public class Hero extends SpriteImage {

    private int speed = 5;

    /*
     * Default constructor.
     */
    public Hero() {
        super();
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

    public void stop() {

        if (this.getVx() != 0 && this.getVy() != 0) {
            this.setVx(0);
            this.setVy(0);
        }
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


    /*
     * Methods.TODO: remove collision methods?

    //Method to check for collisions.
    public boolean isCollision(SpriteImage other) {

        Rectangle thisSprite = new Rectangle(getX(), getY(), getWidth(), getHeight());
        Rectangle otherSprite = new Rectangle(other.getX(), other.getY(), other.getWidth(), other.getHeight());

        //See if images intersect.
        return thisSprite.intersects(otherSprite);
    }*/

/////
}
