package RogueGame.Dungeon;

import java.util.ArrayList;

public class AttackDB {

    private static ArrayList<String> name, description, imageURL;
    private static ArrayList<Integer> ID, ap, dmg, range;

    private static boolean init = true;

    //TODO: imageURL should be animation sprite sheet

    public AttackDB() {


        if (init) {

            name = new ArrayList<>();
            description = new ArrayList<>();
            imageURL = new ArrayList<>();
            ID = new ArrayList<>();
            dmg = new ArrayList<>();
            range = new ArrayList<>();
            ap = new ArrayList<>();

            ID.add(0);
            name.add("Default");
            description.add("A weak punch.");
            imageURL.add("");
            dmg.add(5);
            range.add(1);
            ap.add(0);

            ID.add(1);
            name.add("Fire Ball");
            description.add("An orb of plasma a thousand degrees!");
            imageURL.add("");
            dmg.add(50);
            range.add(1);
            ap.add(35);

            ID.add(2);
            name.add("Re-gen");
            description.add("Is that a blunt?");
            imageURL.add("");
            dmg.add(-50);
            range.add(2);
            ap.add(15);

            ID.add(3);
            name.add("Evil eye");
            description.add("Sticks and stones may break my bones, and words can sometimes hurt me.");
            imageURL.add("");
            dmg.add(10);
            range.add(4);
            ap.add(10);

            ID.add(4);
            name.add("Thunder Beam");
            description.add("It's really just static electricity...");
            imageURL.add("");
            dmg.add(30);
            range.add(3);
            ap.add(15);


            init = false;
        }

    }

    public static String getName(int id) {

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

    public static int getDamage(int id) {

        int temp = 0;

        for (int i = 0; i < ID.size(); i++) {
            if (ID.get(i) == id) {
                temp = i;
                break;
            }
        }

        return dmg.get(temp);
    }

    public static int getRange(int id) {

        int temp = 0;

        for (int i = 0; i < ID.size(); i++) {
            if (ID.get(i) == id) {
                temp = i;
                break;
            }
        }

        return range.get(temp);

    }

    public static int getAP(int id) {

        int temp = 0;

        for (int i = 0; i < ID.size(); i++) {
            if (ID.get(i) == id) {
                temp = i;
                break;
            }
        }

        return ap.get(temp);

    }


}
