package RogueGame;


import java.util.ArrayList;

public class ItemDB {

    private static ArrayList<String> name, description;
    private static ArrayList<Integer> ID, price;
    private static ArrayList<String> imageURL;
    //private static String[] name, description;
    //private static int[] ID, price;
    private static boolean init = true;

    public static final int itemMinRange = 2;
    public static final int itemMaxRange = 4;

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
            imageURL = new ArrayList<>();


            ID.add(1);
            name.add("Money");
            description.add("It's money!");
            price.add(-1);
            imageURL.add("../Assets/Other/Characters/yellow.png");

            ID.add(2);
            name.add("Oran Berry");
            description.add("A berry that heals the user.");
            price.add(50);
            imageURL.add("../Assets/Other/Characters/blue.png");

            ID.add(3);
            name.add("Blast Seed");
            description.add("Causes 50 damage. Range: 1 tile.");
            price.add(150);
            imageURL.add("../Assets/Other/Characters/purple.png");

            ID.add(4);
            name.add("Big Apple");
            description.add("Fills your belly 50%.");
            price.add(200);
            imageURL.add("../Assets/Other/Characters/green.png");

            ID.add(5);
            name.add("Warp Orb");
            description.add("Causes user to warp to a random room.");
            price.add(350);
            imageURL.add("../Assets/Other/Characters/green.png");



            init = false;
        }

    }

    public static String getItem(int id) {

        int temp = 0;

        for (int i = 0; i < ID.size(); i++) {
            if (ID.get(i) == id) {
                temp = i;
                break;
            }
        }

        return name.get(temp);
    }

    public static String getDescription(int id) {

        int temp = 0;

        for (int i = 0; i < ID.size(); i++) {
            if (ID.get(i) == id) {
                temp = i;
                break;
            }
        }

        return description.get(temp);
    }

    public static int getPrice(int id) {

        int temp = 0;

        for (int i = 0; i < ID.size(); i++) {
            if (ID.get(i) == id) {
                temp = i;
                break;
            }
        }

        return price.get(temp);
    }

    public static String getImage(int id) {

        int temp = 0;

        for (int i = 0; i < ID.size(); i++) {
            if (ID.get(i) == id) {
                temp = i;
                break;
            }
        }

        return imageURL.get(temp);
    }
}
