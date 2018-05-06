package RogueGame.Dialogue;

import RogueGame.Dungeon.AttackDB;
import RogueGame.InputListener;
import RogueGame.ItemDB;
import RogueGame.UserData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ActionMenu extends DialogueSelector {

    //InventorySelector inventory;
    private static DialogueSelector inventory;
    private static DialogueSelector attackSelector;
    private DialogueSelector options;
    private DialogueSelector money;

    //Dialogues
    private ynMenu selected;
    private String type;


    private boolean init = true;

    public int selectedItem = -1;

    public int selectedAttackType = -1;
    public int selectedAttackX = 0;
    public int selectedAttackY = 0;

    //TODO: add dialogues for all actions




    public ActionMenu(String type) {
        super();

        info.disable();

        this.type = type;

        loadInventory(type);

        title = "Menu";

        if (type.equals("dungeon")) {
            allOptions.add("Attack");
        } else {
            allOptions.add("Save");
        }

        allOptions.add("Inventory");
        allOptions.add("Missions");
        allOptions.add("Stats");
        allOptions.add("Log");

        initializeList();

        setImage(new ImageIcon(getClass().getResource("../Assets/Other/Dialogue/MenuBox.png")));

        selected = new ynMenu();


        money = new DialogueSelector();

        money.title = "Money:";
        money.info.disable();
        money.setImage(new ImageIcon(getClass().getResource("../Assets/Other/Dialogue/Options.png")));
        money.offSetDraw(150, 0);
        money.initializeList();
        money.activate();
    }

    public void run(InputListener in) {


        if (this.isActive() && init) {

            loadInventory(type);
            init = false;

            money.allOptions.clear();
            money.allOptions.add("" + UserData.getMoney());
            money.initializeList();
        }

        if (inventory.isActive()) {

            runInventory(in);

        } else if (attackSelector.isActive()) {

            runAttack(in);

        } else if (in.checkInput("space")) {

            //Load specified dialogue
            switch (selections.get(selectorFlag)) {
                case "Inventory":
                    inventory.activate();
                    break;
                case "Attack":
                    attackSelector.activate();
                    break;
            }

            in.bufferSpace();

        } else {

            //Run this menu
            super.run(in);


        }


        if (!this.isActive()) {
            init = true;
        }




        /*


        ActionMenu ()
            - Attack
                - Options Dialogue
            - Save (Bool Dialogue)
            - Inventory
                - Options Dialogue
            - Missions
                -
            - Stats
            - Log



         */


    }

    private void loadInventory(String type) {

        inventory = new DialogueSelector();
        inventory.title = "Inventory";

        //Load user data into UI
        ArrayList<Integer> temp = UserData.getItems();

        for (int i = 0; i < temp.size(); i++) {

            inventory.allOptions.add(ItemDB.getItem(UserData.getItem(i)));

            String description = "";

            if (ItemDB.getItemStats(UserData.getItem(i))[0] != 0)
                description += "AP: " + ItemDB.getItemStats(UserData.getItem(i))[0] + " ";
            if (ItemDB.getItemStats(UserData.getItem(i))[1] != 0)
                description += "HP: " + ItemDB.getItemStats(UserData.getItem(i))[1] + " ";
            if (ItemDB.getItemStats(UserData.getItem(i))[2] != 0)
                description += "DMG: " + ItemDB.getItemStats(UserData.getItem(i))[2] + " ";

            description += ItemDB.getDescription(UserData.getItem(i));

            inventory.description.add(description);
        }

        inventory.initializeList();



        options = new DialogueSelector();
        options.setImage(new ImageIcon(getClass().getResource("../Assets/Other/Dialogue/Options.png")));
        options.info.disable();
        options.offSetDraw(150, 0);
        options.title = "Options";

        if (type.equals("dungeon")) {
            options.allOptions.add("Use");
        }
        options.allOptions.add("Throw Away");

        options.initializeList();


        attackSelector = new DialogueSelector();
        attackSelector.title = "Moves";

        attackSelector.allOptions.add(AttackDB.getName(UserData.getAttacks()[0]));
        attackSelector.allOptions.add(AttackDB.getName(UserData.getAttacks()[1]));
        attackSelector.allOptions.add(AttackDB.getName(UserData.getAttacks()[2]));
        attackSelector.allOptions.add(AttackDB.getName(UserData.getAttacks()[3]));

        attackSelector.description.add(AttackDB.getDescription(UserData.getAttacks()[0]));
        attackSelector.description.add(AttackDB.getDescription(UserData.getAttacks()[1]));
        attackSelector.description.add(AttackDB.getDescription(UserData.getAttacks()[2]));
        attackSelector.description.add(AttackDB.getDescription(UserData.getAttacks()[3]));

        attackSelector.initializeList();

    }

    //Inventory logic
    private void runInventory(InputListener in) {

        if (options.isActive()) {

            //If inventory empty, don't run options
            if (!(inventory.allOptions.size() > 0)) {
                options.reset(in);
            }

            //Run ynMenu
            if (selected.isActive()) {
                selected.run(in);

                if (selected.yes) {
                    if (options.selections.get(options.selectorFlag).equals("Throw Away")) {
                        if (inventory.allOptions.size() > 0) {

                            //Update inventory
                            UserData.removeItem(inventory.selectorFlag);
                            inventory.allOptions.remove(inventory.selectorFlag);
                            inventory.description.remove(inventory.selectorFlag);
                            inventory.initializeList();
                            inventory.getPage();
                        }
                    }
                }

                if (selected.yes) {
                    if (options.selections.get(options.selectorFlag).equals("Use")) {
                        if (inventory.allOptions.size() > 0) {


                            //Update item selected
                            selectedItem = UserData.getItem(inventory.selectorFlag);

                            //Update inventory
                            UserData.removeItem(inventory.selectorFlag);
                            inventory.allOptions.remove(inventory.selectorFlag);
                            inventory.description.remove(inventory.selectorFlag);
                            inventory.initializeList();
                            inventory.getPage();
                        }
                    }
                }

                if (!selected.isActive()) {

                    options.reset(in);
                }

            } else {

                //Run options menu
                options.run(in);

                if (in.checkInput("space")) {

                    switch (options.selections.get(options.selectorFlag)) {
                        case "Use":
                            selected.activate();
                            break;
                        case "Throw Away":
                            selected.activate();
                            break;
                    }

                    in.bufferSpace();
                }
            }


        } else {

            //Run inventory dialogue
            inventory.run(in);

            if (in.checkInput("space")) {

                if (inventory.allOptions.size() > 0) {

                    options.activate();
                }

                in.bufferSpace();
            }
        }


    }

    public void runAttack(InputListener in) {

        attackSelector.run(in);

        if (in.checkInput("space")) {

            if (attackSelector.allOptions.size() > 0) {

                selectedAttackType = UserData.getAttacks()[attackSelector.selectorFlag];

                attackSelector.reset(in);
                this.reset(in);

                //TODO: run range selector here
            }

            in.bufferSpace();
        }
    }

    //Paint method
    public void draw(Graphics g, JPanel p) {

        if (this.isActive()) {

            super.draw(g, p);

            //Draw other dialogues
            if (inventory.isActive()) {
                inventory.draw(g, p);
            }

            if (options.isActive()) {
                options.draw(g, p);
            }

            if (selected.isActive()) {
                selected.draw(g, p);
            }

            if (money.isActive() && !inventory.isActive() && !attackSelector.isActive()) {
                money.draw(g, p);
            }

            if (attackSelector.isActive()) {
                attackSelector.draw(g, p);
            }
        }
    }

}
