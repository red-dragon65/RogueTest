package RogueGame;


import java.util.ArrayList;

public class ItemDB {

    private static ArrayList<String> name, description;
    private static ArrayList<Integer> ID, price;
    private static boolean init = true;

    //TODO: add image URL's
    //private ArrayList<String> imageLocation;

    //TODO: add status effect
    //private ArrayList<String> status;

    public ItemDB() {

        //Initialize DB


        /*
        TODO: Will database be loaded into memory from here, or will data be retrieved
        TODO: from files when the user requests an item/description?
         */

        if (init) {

            name = new ArrayList<>();
            description = new ArrayList<>();
            ID = new ArrayList<>();
            price = new ArrayList<>();


            ID.add(0);
            name.add("Oran Berry");
            description.add("A berry that heals the user.");
            price.add(50);

            ID.add(1);
            name.add("Blast Seed");
            description.add("Causes 50 damage. Range: 1 tile.");
            price.add(150);

            ID.add(2);
            name.add("Big Apple");
            description.add("Fills your belly 50%.");
            price.add(200);

            ID.add(3);
            name.add("Warp Orb");
            description.add("Causes user to warp to a random room.");
            price.add(350);

            ID.add(4);
            name.add("Money");
            description.add("It's money!");
            price.add(-1);


            init = false;
        }

    }

    public static String getItem(int id) {

        return name.get(id);
    }

    public static String getDescription(int id) {

        return description.get(id);
    }

    public static int getPrice(int id) {

        return price.get(ID.indexOf(id));
        //return price.get(id);
    }
}
