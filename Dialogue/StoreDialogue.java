package RogueGame.Dialogue;

import RogueGame.InputListener;
import RogueGame.ItemDB;
import RogueGame.UserData;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class StoreDialogue extends DialogueSelector {

    private DialogueSelector options;
    private ynMenu selected;
    private ArrayList<Integer> itemID;

    private DialogueSelector money;

    private final int maxItems = 8;


    public StoreDialogue() {
        super();

        title = "Store";


        /**
         * Generate random items and sort them
         */
        Random gen = new Random();
        itemID = new ArrayList<>();

        for (int i = 0; i < maxItems; i++) {

            itemID.add(gen.nextInt(ItemDB.itemMaxRange) + ItemDB.itemMinRange);
        }

        itemID.sort(Integer::compareTo);

        for (int i = 0; i < itemID.size(); i++) {

            allOptions.add(ItemDB.getPrice(itemID.get(i)) + "  " + ItemDB.getItem(itemID.get(i)));
            description.add(ItemDB.getDescription(itemID.get(i)));
        }

        initializeList();


        options = new DialogueSelector();
        options.setImage(new ImageIcon(getClass().getResource("../Assets/Other/Dialogue/Options.png")));
        options.info.disable();
        options.offSetDraw(150, 0);

        options.title = "Options";
        options.allOptions.add("Buy");
        options.initializeList();


        selected = new ynMenu();

        money = new DialogueSelector();

        money.title = "Money:";
        money.info.disable();
        money.setImage(new ImageIcon(getClass().getResource("../Assets/Other/Dialogue/Options.png")));
        money.offSetDraw(150, 165);
        money.allOptions.add("" + UserData.getMoney());
        money.initializeList();
        money.activate();
    }

    public void run(InputListener in) {


        if (options.isActive()) {

            //If inventory empty, don't run options
            if (!(allOptions.size() > 0)) {
                options.reset(in);
            }

            //Run ynMenu
            if (selected.isActive()) {
                selected.run(in);

                if (selected.yes) {
                    if (options.selections.get(options.selectorFlag).equals("Buy")) {
                        if (allOptions.size() > 0) {

                            //Update user inventory
                            UserData.addItem(itemID.get(selectorFlag));
                            UserData.removeMoney(ItemDB.getPrice(itemID.get(selectorFlag)));

                            //Update user money
                            money.allOptions.clear();
                            money.allOptions.add("" + UserData.getMoney());
                            money.initializeList();


                            //Update store
                            itemID.remove(selectorFlag);
                            allOptions.remove(selectorFlag);
                            description.remove(selectorFlag);
                            initializeList();
                            getPage();
                        }
                    }
                }

                if (!selected.isActive()) {

                    options.reset(in);
                }

            } else {

                //Check if user has enough money
                if (!(ItemDB.getPrice(itemID.get(selectorFlag)) > UserData.getMoney())) {

                    if (in.checkInput("space")) {

                        switch (options.selections.get(options.selectorFlag)) {
                            case "Buy":
                                selected.activate();
                                break;
                        }

                        in.bufferSpace();
                    }

                }

                //Run options menu
                options.run(in);

            }


        } else if (in.checkInput("space")) {

            //Load dialogue
            if (allOptions.size() > 0) {

                if (ItemDB.getPrice(itemID.get(selectorFlag)) > UserData.getMoney()) {
                    //Disable 'Buy' highlight
                    options.selectorFlag = -1;
                }

                options.activate();
            }

            in.bufferSpace();

        } else {

            //Run this menu
            super.run(in);
        }


    }

    public void draw(Graphics g, JPanel p) {

        if (this.isActive()) {
            super.draw(g, p);

            //Draw options dialogue
            if (options.isActive()) {
                options.draw(g, p);
            }

            if (selected.isActive()) {
                selected.draw(g, p);
            }

            if (money.isActive()) {
                money.draw(g, p);
            }
        }

    }
}
