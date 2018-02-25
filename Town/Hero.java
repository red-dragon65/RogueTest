package RogueGame.Town;


import RogueGame.InputListener;
import RogueGame.Sprite.SpriteImage;

/**
 * Class that holds hero specific data.
 */
public class Hero extends SpriteImage {

    private int speed = 5;
    private int traverseTileX = 0;

    //Todo: make this private?
    int traverseTileY = 0;


    //Default constructor
    public Hero() {
        super();
    }


    //Buffer movement between different directions.
    private void bufferMove() {
        this.setVx(0);
        this.setVy(0);
    }


    //Move with bounds.
    protected void moveInBounds(InputListener in, CollisionMap mapMask, NPCmap n) {


        bufferMove();

        //Right
        if (in.checkInput("right") && !n.isCollision(this).equals("right")) {

            traverseTileX += speed;

            //See if collision check is necessary
            if (traverseTileX < mapMask.tileSize / 2) {

                this.setVx(speed);
                move();

            } else {

                //Check for collision
                if (mapMask.checkHero("right")) {

                    //Move
                    traverseTileX -= mapMask.tileSize;
                    mapMask.updateHero("right");
                    this.setVx(speed);
                    move();

                } else {

                    //Undo theoretical move
                    traverseTileX -= speed;
                }
            }
        }


        bufferMove();


        //Left
        if (in.checkInput("left") && !n.isCollision(this).equals("left")) {

            traverseTileX -= speed;

            //Check if move is valid
            if (traverseTileX > -mapMask.tileSize / 2) {

                //Move
                this.setVx(-speed);
                move();

            } else {

                if (mapMask.checkHero("left")) {

                    //Move
                    traverseTileX += mapMask.tileSize;
                    mapMask.updateHero("left");
                    this.setVx(-speed);
                    move();
                } else {

                    //Undo theoretical move
                    traverseTileX += speed;
                }
            }
        }


        bufferMove();


        //Up
        if (in.checkInput("up") && !n.isCollision(this).equals("top")) {

            traverseTileY -= speed;

            //Check if move is valid
            if (traverseTileY > -mapMask.tileSize / 2) {

                this.setVy(-speed);
                move();

            } else {

                if (mapMask.checkHero("up")) {

                    //Move
                    traverseTileY += mapMask.tileSize;
                    mapMask.updateHero("up");
                    this.setVy(-speed);
                    move();

                } else {

                    //Undo theoretical move
                    traverseTileY += speed;
                }
            }
        }


        bufferMove();


        //Down
        if (in.checkInput("down") && !n.isCollision(this).equals("bottom")) {

            traverseTileY += speed;

            //Check if move is valid
            if (traverseTileY < mapMask.tileSize / 2) {

                this.setVy(speed);
                move();

            } else {

                //Check for collision
                if (mapMask.checkHero("down")) {

                    //Move
                    traverseTileY -= mapMask.tileSize;
                    mapMask.updateHero("down");
                    this.setVy(speed);
                    move();

                } else {

                    //Undo theoretical change
                    traverseTileY -= speed;
                }

            }
        }


        bufferMove();

/*
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
*/


    }


    //Stop character movement
    public void stop() {

        if (this.getVx() != 0 && this.getVy() != 0) {
            this.setVx(0);
            this.setVy(0);
        }
    }

    public void resetLocation(CollisionMap mapMask) {

        //Set image location
        /*
        These numbers are offset by 6.
        This allows collision to seem to occur at the
        center of the character sprite.
        */
        setX(654);
        setY(990);

        //Reset tile offset
        traverseTileX = 0;
        traverseTileY = 0;

        //Set collision mask location
        mapMask.setHero(660, 996);
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
