package RogueGame;


/**
 * Class that holds enemy specific data.
 */
public class Enemy extends SpriteImage {


    static protected float mult = 1.5f;
    protected int lvl;

    protected int health;
    protected int mana;

    static private Type moves;

    private int expval;


    /*
     * Default constructor.
     */
    public Enemy(int lvl) {
        super();

        initialize(lvl);

    }

    protected void initialize(int lvl) {

        this.lvl = lvl;
        health = (int) (lvl * mult);
        mana = (int) (lvl * mult);

        expval = (int) (lvl * mult) / 2;

    }


    //Move with bounds.
    protected void moveInBounds() {
        move();


    }


    /*
    protected void act(collisionMap){

        //Check for damage


        //Decide to attack or move


        //Stop focus
        done = true

    }


    */


    public int attack(int option) {

        //Auto attack
        if (option == -1) {
            //random generate option
        }

        //Normal attack
        switch (option) {
            case 1:
                return moves.swing();
            case 2:
                return moves.flame();
            case 3:
                return moves.punch();
            default:
                return 0;
        }
    }


    //Increase - decrease health
    public void adjustHealth(int amount) {

        health += amount;
    }

    //Increase - decrease mana
    public void adjustMana(int amount) {

        mana += amount;
    }


    public int getExpval() {
        return expval;
    }


/////
}
