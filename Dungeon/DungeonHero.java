package RogueGame.Dungeon;

import RogueGame.InputListener;
import RogueGame.Sprite.SpriteImage;

public class DungeonHero extends SpriteImage {

    private int speed = 8;

    //How many steps the character should take from one tile to the next.
    private int step = 0;

    //Direction movable allowed
    private boolean right, left, up, down;

    //Matrix location
    int[] matLoc;


    /*
     * Default constructor.
     */
    public DungeonHero() {
        super();

        right = left = up = down = false;
        matLoc = new int[2];
    }


    //Move with bounds.
    protected void act(InputListener in, CollisionMask mask) {

        //Move character (if input && if collision mask)
        if (in.getInput()[0] && !left && !up && !down)
            right = mask.checkHero("right");

        if (in.getInput()[1] && !right && !up && !down)
            left = mask.checkHero("left");

        if (in.getInput()[2] && !right && !left && !down)
            up = mask.checkHero("up");

        if (in.getInput()[3] && !right && !left && !up)
            down = mask.checkHero("down");


        //Step and update mask
        if (right) {

            step++;

            if (step == (24 / speed) + 1) {

                right = false;
                step = 0;

                this.setVx(0);

                mask.updateHero("right");

            } else {

                this.setVx(speed);
            }


        }

        if (left) {

            step++;

            if (step == (24 / speed) + 1) {

                left = false;
                step = 0;

                this.setVx(0);

                mask.updateHero("left");

            } else {

                this.setVx(-speed);
            }

        }

        if (up) {

            step++;

            if (step == (24 / speed) + 1) {

                up = false;
                step = 0;

                this.setVy(0);

                mask.updateHero("up");

            } else {

                this.setVy(-speed);
            }


        }

        if (down) {

            step++;

            if (step == (24 / speed) + 1) {

                down = false;
                step = 0;

                this.setVy(0);

                mask.updateHero("down");

            } else {

                this.setVy(speed);
            }

        }


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

}
