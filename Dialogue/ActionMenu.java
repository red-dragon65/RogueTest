package RogueGame.Dialogue;

import RogueGame.InputListener;

import javax.swing.*;
import java.awt.*;

public class ActionMenu extends DialogueSelector {

    //Need dialogues for options below

    //InventorySelector inventory;
    private static DialogueSelector inventory;
    private DialogueSelector options;

    private ynMenu selected;



    public ActionMenu(String type) {
        super();

        info.disable();

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
    }

    public void run(InputListener in) {


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


        /*

        Inventory will handle ynMenu.

        Inventory will handle ynMenu differently for dungeon, and town.

        In town:
            - Item info can be shown
            - Item can be thrown away

        In dungeon:
            - Item info can be shown
            - Item can be used
            - Item can be thrown away

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

        //Todo: load this data from a file
        //TODO: Items are objects that hold name and description data
        inventory.allOptions.add("Oran Berry");
        inventory.allOptions.add("Oran Berry");
        inventory.allOptions.add("Blast Seed");
        inventory.allOptions.add("Big Apple");
        inventory.allOptions.add("Warp Orb");

        inventory.description.add("A berry that heals the user.");
        inventory.description.add("A berry that heals the user.");
        inventory.description.add("Causes 50 damage. Range: 1 tile.");
        inventory.description.add("Fills your belly 50%.");
        inventory.description.add("Causes user to warp to a random room.");

        inventory.initializeList();


        options = new DialogueSelector();
        options.setImage(new ImageIcon(getClass().getResource("../Assets/Other/Dialogue/MenuBox.png")));
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

            if (inventory.allOptions.size() > 0) {

            } else {
                options.reset(in);
            }

            //Run ynMenu
            if (selected.isActive()) {
                selected.run(in);

                if (selected.yes) {
                    if (options.selections.get(options.selectorFlag).equals("Throw Away")) {
                        if (inventory.allOptions.size() > 0) {

                            inventory.allOptions.remove(inventory.selectorFlag);
                            inventory.description.remove(inventory.selectorFlag);
                            inventory.initializeList();
                            inventory.getPage();
                        }
                    }
                }

            } else {

                //Run options menu
                options.run(in);

                if (in.checkInput("space")) {

                    switch (options.selections.get(options.selectorFlag)) {
                        case "Use":
                            System.out.println("Use selected");

                            break;
                        case "Throw Away":
                            System.out.println("Throw away selected");
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

                //Todo: remove this test
                //System.out.println(inventory.selections.get(inventory.selectorFlag));

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
        }
    }

}
