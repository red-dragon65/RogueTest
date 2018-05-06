package RogueGame;

import java.util.ArrayList;

public class UserData {

    private static ArrayList<Integer> itemsID;
    private static int money;
    private static int[] stats;
    private static int[] attacks;

    //TODO: add other user data
    //private static String[] Missions;

    private static boolean init = true;


    public UserData() {


        if (init) {

            //TODO: load this data from a file
            itemsID = new ArrayList<>();

            itemsID.add(2);
            itemsID.add(2);
            itemsID.add(3);
            itemsID.add(4);
            itemsID.add(5);

            itemsID.sort(Integer::compareTo);


            money = 500;


            stats = new int[4];
            stats[0] = 5; //level
            stats[1] = 50; //exp
            stats[2] = 100; //health
            stats[3] = 60; //ap

            attacks = new int[4];
            attacks[0] = 1;
            attacks[1] = 2;
            attacks[2] = 3;
            attacks[3] = 4;


            init = false;
        }

    }

    public static ArrayList<Integer> getItems() {
        return itemsID;
    }

    public static void addItem(int ID) {

        //Don't add money to inventory
        if (ID != 1) {
            itemsID.add(ID);
            itemsID.sort(Integer::compareTo);
        }
    }

    public static void removeItem(int index) {

        itemsID.remove(index);
    }

    public static int getItem(int index) {

        return itemsID.get(index);
    }

    public static void addMoney(int amount) {
        money += amount;
    }

    public static void removeMoney(int amount) {
        money -= amount;
    }

    public static int getMoney() {
        return money;
    }

    public static int[] getStats() {
        return stats;
    }

    public static int[] getAttacks() {
        return attacks;
    }

}
