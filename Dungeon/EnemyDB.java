package RogueGame.Dungeon;

import java.util.ArrayList;

public class EnemyDB {


    private static ArrayList<String> name, description, imageURL;
    private static ArrayList<Integer> ID, ap, hp, exp, lvl;
    private static ArrayList<int[]> attackID;

    private static boolean init = true;


    public EnemyDB() {


        if (init) {

            name = new ArrayList<>();
            description = new ArrayList<>();
            imageURL = new ArrayList<>();
            ID = new ArrayList<>();
            ap = new ArrayList<>();
            hp = new ArrayList<>();
            exp = new ArrayList<>();
            lvl = new ArrayList<>();
            attackID = new ArrayList<>();

            int[] temp = new int[4];

            ID.add(1);
            name.add("Chu");
            description.add("The Zelda munster!");
            imageURL.add("../Assets/Other/Characters/white.png");
            ap.add(50);
            hp.add(10);
            exp.add(10);
            lvl.add(5);
            temp[0] = 1;
            temp[1] = 1;
            temp[2] = 3;
            temp[3] = 3;
            attackID.add(temp);

            ID.add(2);
            name.add("Cthulu");
            description.add("Squid from hell boi.");
            imageURL.add("../Assets/Other/Characters/black.png");
            ap.add(50);
            hp.add(35);
            exp.add(10);
            lvl.add(5);
            temp[0] = 2;
            temp[1] = 2;
            temp[2] = 2;
            temp[3] = 4;
            attackID.add(temp);

            ID.add(3);
            name.add("Choop");
            description.add("Herp-a-derp certified.");
            imageURL.add("../Assets/Other/Characters/black.png");
            ap.add(50);
            hp.add(50);
            exp.add(20);
            lvl.add(5);
            temp[0] = 2;
            temp[1] = 3;
            temp[2] = 3;
            temp[3] = 3;
            attackID.add(temp);

            ID.add(4);
            name.add("Rocky");
            description.add("A rock. Has a face drawn on it.");
            imageURL.add("../Assets/Other/Characters/white.png");
            ap.add(50);
            hp.add(15);
            exp.add(20);
            lvl.add(5);
            temp[0] = 4;
            temp[1] = 4;
            temp[2] = 4;
            temp[3] = 4;
            attackID.add(temp);

            ID.add(5);
            name.add("Bee");
            description.add("Don't let it sting you!");
            imageURL.add("../Assets/Other/Characters/white.png");
            ap.add(50);
            hp.add(20);
            exp.add(25);
            lvl.add(5);
            temp[0] = 1;
            temp[1] = 1;
            temp[2] = 1;
            temp[3] = 1;
            attackID.add(temp);

            init = false;
        }

    }


    public static int[] getStats(int id) {

        int[] data = new int[4];

        int temp = 0;

        for (int i = 0; i < ID.size(); i++) {
            if (ID.get(i) == id) {
                temp = i;
                break;
            }
        }


        data[0] = lvl.get(temp);
        data[1] = exp.get(temp);
        data[2] = hp.get(temp);
        data[3] = ap.get(temp);

        return data;
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

    public static int[] getAttacks(int id) {

        int temp = 0;

        for (int i = 0; i < ID.size(); i++) {
            if (ID.get(i) == id) {
                temp = i;
                break;
            }
        }

        return attackID.get(temp);
    }


}
