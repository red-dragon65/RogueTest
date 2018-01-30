package RogueGame.Dungeon;

import RogueGame.Sprite.SpriteImage;

public class DungeonHero extends SpriteImage {

    private int speed = 8;

    int step = 0;
    boolean right, left, up, down;

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
    protected void act(boolean in[], CollisionMask mask) {


        //Move character (if input && if collision mask)
        if (in[0] && !left && !up && !down) {

            if (mask.mask[mask.heroLoc[0]][mask.heroLoc[1] + 1] != 0)
                right = true;
        }

        if (in[1] && !right && !up && !down) {

            if (mask.mask[mask.heroLoc[0]][mask.heroLoc[1] - 1] != 0)
                left = true;
        }

        if (in[2] && !right && !left && !down) {

            if (mask.mask[mask.heroLoc[0] - 1][mask.heroLoc[1]] != 0)
                up = true;
        }

        if (in[3] && !right && !left && !up) {

            if (mask.mask[mask.heroLoc[0] + 1][mask.heroLoc[1]] != 0)
                down = true;
        }


        //Step and update mask
        if (right) {

            step++;

            if (step == (24 / speed) + 1) {

                right = false;
                step = 0;

                this.setVx(0);

                mask.updateHero("right");
                mask.draw();

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
                mask.draw();

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
                mask.draw();

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
                mask.draw();

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
