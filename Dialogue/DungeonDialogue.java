package RogueGame.Dialogue;

import RogueGame.InputListener;

import javax.swing.*;
import java.awt.*;


public class DungeonDialogue extends DialogueSelector {

    private ynMenu select;

    public DungeonDialogue() {
        super();

        select = new ynMenu();


        title = "Destinations:";

        //TODO: load this from a file
        allOptions.add("Test Area");
        allOptions.add("Old Tower");
        allOptions.add("Forgotten Forest");
        allOptions.add("*Shiny Cave");
        allOptions.add("Lava Delta");
        allOptions.add("*Ice Berg");
        allOptions.add("*Misery Mire");
        allOptions.add("*Turtle Rock");

        allOptions.add("*Hidden Palace");
        allOptions.add("*Tail Cave");
        allOptions.add("*Pirate Swamp");
        allOptions.add("*Crown Path");
        allOptions.add("*Mermaid Tomb");
        allOptions.add("*Roost Tower");
        allOptions.add("*Desert Crypt");
        allOptions.add("*Shadow Plains");

        allOptions.add("*Ship of Ordeals");
        allOptions.add("*Ghost Stronghold");
        allOptions.add("*Riverside Ruins");
        allOptions.add("*Snowpeak Hideout");


        //TODO: load this from a file
        description.add("Dungeon for testing purposes.");
        description.add("A tower from a forgotten era. It is said to hold a great secret.");
        description.add("A dense forest watched over by a great spirit.");
        description.add("Placeholder dungeon");
        description.add("A huge cavern filled with lava. It was found under Turtle Rock.");
        description.add("Placeholder dungeon");
        description.add("Placeholder dungeon");
        description.add("Placeholder dungeon");

        description.add("Placeholder dungeon");
        description.add("Placeholder dungeon");
        description.add("Placeholder dungeon");
        description.add("Placeholder dungeon");
        description.add("Placeholder dungeon");
        description.add("Placeholder dungeon");
        description.add("Placeholder dungeon");
        description.add("Placeholder dungeon");

        description.add("Placeholder dungeon");
        description.add("Placeholder dungeon");
        description.add("Placeholder dungeon");
        description.add("Placeholder dungeon");

        initializeList();
    }

    public void run(InputListener in) {


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

    }

    //Paint method
    public void draw(Graphics g, JPanel p) {

        if (this.isActive()) {

            super.draw(g, p);

            //Draw yes/no dialogue if necessary
            if (select.active) {
                select.draw(g, p);
            }
        }
    }

}
