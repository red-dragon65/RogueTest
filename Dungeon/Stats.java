package RogueGame.Dungeon;

public class Stats {


    private int level;

    private int currentExp;
    private int expLimit;

    private int currentHealth;
    private int healthLimit;

    private int currentAP;
    private int APLimit;

    private int[] attackID = new int[4];

    //TODO: Add this here?
    //private int attackMoves;


    public Stats() {

    }

    public Stats(int[] stats, int[] attacks) {

        this.level = stats[0];

        this.currentExp = this.expLimit = stats[1];

        this.currentHealth = this.healthLimit = stats[2];

        this.currentAP = this.APLimit = stats[3];

        attackID = attacks;
    }


    public int getLevel() {
        return level;
    }


    //Add or remove exp
    public void updateEXP(int offset) {

        //Update exp
        currentExp += offset;

        //Roll over to next level if necessary
        while (currentExp > expLimit) {

            level++;

            //Rollover exp
            currentExp -= expLimit;


            //TODO: Update this game mechanic later
            //Update expLimit
            expLimit += (int) ((float) level * 1.1);
            //Update health
            healthLimit += (int) ((float) level * 1.2);
            //Update AP
            APLimit += (int) ((float) level * 1.2);
        }
    }

    public int[] getEXP() {

        int[] temp = new int[2];

        temp[0] = currentExp;
        temp[1] = expLimit;

        return temp;
    }


    public void updateHealth(int offset) {

        currentHealth += offset;

        //Prevent overflow
        if (currentHealth > healthLimit) {
            currentHealth = healthLimit;
        }

        //Prevent underflow
        if (currentHealth < 0) {
            currentHealth = 0;
        }
    }

    public int[] getHealth() {

        int[] temp = new int[2];

        temp[0] = currentHealth;
        temp[1] = healthLimit;

        return temp;
    }


    public void updateAP(int offset) {

        currentAP += offset;

        //Prevent overflow
        if (currentAP > APLimit) {
            currentAP = APLimit;
        }

        //Prevent underflow
        if (currentAP < 0) {
            currentAP = 0;
        }

    }

    public int[] getAP() {

        int[] temp = new int[2];

        temp[0] = currentAP;
        temp[1] = APLimit;

        return temp;
    }


    //TODO: remove this test code
    public void showStats() {

        System.out.println("Level: " + level);
        System.out.println("Health: Current: " + currentHealth + " Limit: " + healthLimit);
        System.out.println("AP: Current: " + currentAP + " Limit: " + APLimit);
        System.out.println("Exp: Current: " + currentExp + " Limit: " + expLimit);
    }

}
