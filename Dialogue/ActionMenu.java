package RogueGame.Dialogue;

import RogueGame.InputListener;

import javax.swing.*;
import java.awt.*;

public class ActionMenu extends DialogueSelector {

    //Need dialogues for options below


    public ActionMenu(String type) {
        super();

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
    }

    public void run(InputListener in) {

        super.run(in);




        /*

        //How input is switched to make code work

        if(inventoryMenu.isActive()){

            inventoryMenu.run(in);

        }else if(missionsMenu.isActive()){

            missionsMenu.run(in);

        }else if(in.getInput("space")){

            switch(finalChoice){
                case "Inventory":
                    inventoryMenu.activate();
                    break;
                case "Missions":
                    missionsMenu.activate();
                    break;
            }
        }


         */



/*
        //This is how a selection is handled.
        //If space is pressed, load the appropriate dialogue.

        //Run selector if ynMenu not active
        if (!select.isActive()) {

            //Load ynMenu
            if (in.checkInput("space")) {
                select.activate();
                in.bufferSpace();
            }

            //Run selector
            super.run(in);
        } else {
            select.run(in);
        }


        //Get specified dungeon if yes selected
        if (select.yes) {

            this.yes = true;
            finalChoice = selections.get(selectorFlag);
            reset(in);

        } else if (select.no) {
            this.yes = false;
            select.active = false;
        }
*/


    }

    //Paint method
    public void draw(Graphics g, JPanel p) {

        if (this.isActive()) {

            super.draw(g, p);

            //Draw other dialogues
        }
    }

}
