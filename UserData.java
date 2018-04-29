package RogueGame;

import java.util.ArrayList;

public class UserData {

    private static ArrayList<Integer> itemsID;
    private static int money;

    //TODO: add other user data
    //private static int[] stats;
    //private static String[] Missions;

    private static boolean init = true;


    public UserData() {


        if (init) {

            //TODO: load this data from a file
            itemsID = new ArrayList<>();

            money = 500;
            itemsID.add(2);
            itemsID.add(2);
            itemsID.add(3);
            itemsID.add(4);
            itemsID.add(5);


            itemsID.sort(Integer::compareTo);
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

}
