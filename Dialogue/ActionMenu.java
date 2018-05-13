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
    private RangeSelector rangeDialogue;

    //Dialogues
    private ynMenu selected;
    private String type;


    private boolean init = true;

    public int selectedItem = -1;

    public int selectedAttackType = -1;
    public boolean rangeSelected = false;
    public int selectedAttackX = 0;
    public int selectedAttackY = 0;

    //Hero location to set range dialogue
    private int[] heroLoc = new int[2];

    private int apLeft = 0;

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

        for (int i = 0; i < UserData.attackSize; i++) {
            attackSelector.allOptions.add(AttackDB.getName(UserData.getAttacks()[i]));
            attackSelector.description.add(AttackDB.getDescription(UserData.getAttacks()[i]));

        }

        attackSelector.initializeList();

        rangeDialogue = new RangeSelector();

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


        if (rangeDialogue.isActive()) {

            rangeDialogue.run(in);

            //Stop
            if (rangeDialogue.yes) {

                attackSelector.reset(in);
                rangeDialogue.reset();
                this.reset(in);
                selectedAttackX = rangeDialogue.cursorLocX;
                selectedAttackY = rangeDialogue.cursorLocY;
                rangeSelected = true;
            }

        } else {

            attackSelector.run(in);

            if (in.checkInput("space")) {

                if (attackSelector.allOptions.size() > 0) {

                    //Get attack type
                    selectedAttackType = UserData.getAttacks()[attackSelector.selectorFlag];


                    //Make sure their is enough ap for attack
                    if (apLeft >= AttackDB.getAP(selectedAttackType)) {

                        //Run range dialogue selector
                        rangeDialogue.setRange(AttackDB.getRange(selectedAttackType), heroLoc);
                        rangeDialogue.activate();
                    }


                }

                in.bufferSpace();
            }
        }
    }

    public void setAP(int ap) {
        this.apLeft = ap;
    }

    public void setLoc(int x, int y) {

        this.heroLoc[0] = x;
        this.heroLoc[1] = y;
    }

    //Paint method
    public void draw(Graphics g, JPanel p) {

        if (this.isActive()) {

            if (!rangeDialogue.isActive()) {

                super.draw(g, p);
            }

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

            if (money.isActive() && !inventory.isActive() && !attackSelector.isActive() && !rangeDialogue.isActive()) {
                money.draw(g, p);
            }

            if (attackSelector.isActive() && !rangeDialogue.isActive()) {
                attackSelector.draw(g, p);
            }

            if (rangeDialogue.isActive()) {
                rangeDialogue.draw(g, p);
            }
        }
    }

}
