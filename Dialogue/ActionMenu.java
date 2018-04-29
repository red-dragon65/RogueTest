package RogueGame.Dialogue;

import RogueGame.InputListener;
import RogueGame.ItemDB;
import RogueGame.UserData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ActionMenu extends DialogueSelector {

    //InventorySelector inventory;
    private static DialogueSelector inventory;
    private DialogueSelector options;
    private DialogueSelector money;

    private ynMenu selected;
    private String type;

    private boolean init = true;

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

        } else if (in.checkInput("space")) {

            //Load specified dialogue
            switch (selections.get(selectorFlag)) {
                case "Inventory":
                    inventory.activate();
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

        Inventory will handle ynMenu.

        Inventory will handle ynMenu differently for dungeon, and town.

        In town:
            - ItemDB info can be shown
            - ItemDB can be thrown away

        In dungeon:
            - ItemDB info can be shown
            - ItemDB can be used
            - ItemDB can be thrown away

         */

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

            //inventory.allOptions.add(UserData.getItem(i));
            inventory.allOptions.add(ItemDB.getItem(UserData.getItem(i)));

            //inventory.allOptions.add(ItemDB.getItem(temp.get(i)));
            inventory.description.add(ItemDB.getDescription(UserData.getItem(i)));
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

                if (!selected.isActive()) {

                    options.reset(in);
                }

            } else {

                //Run options menu
                options.run(in);

                if (in.checkInput("space")) {

                    switch (options.selections.get(options.selectorFlag)) {
                        case "Use":
                            //TODO: make this work
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

            if (money.isActive() && !inventory.isActive()) {
                money.draw(g, p);
            }
        }
    }

}
