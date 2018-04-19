package RogueGame.Dialogue;

import RogueGame.InputListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DungeonDialogue {


    //Variables
    private ImageIcon dialog;

    private boolean active;
    public boolean yes;
    private ynMenu select;

    private String title;
    //Shown items
    private ArrayList<String> selections;
    //All items
    private ArrayList<String> allOptions;
    private int selectorFlag;
    private int maxSelection;
    private int page;

    public String finalChoice;


    //TODO make text  scroll


    //Constructor
    public DungeonDialogue() {

        dialog = new ImageIcon(getClass().getResource("../Assets/Other/Dialogue/DungeonDialogue.png"));

        active = false;
        yes = false;
        select = new ynMenu();

        title = "Destinations:";

        //Add all options to list
        allOptions = new ArrayList<>();
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


        selections = new ArrayList<>();

        //Initialize shown list
        for (int i = 0; i < 8; i++) {
            selections.add(allOptions.get(i));
        }

        if (selections.size() < 8) {
            maxSelection = selections.size();
        } else {
            maxSelection = 7;
        }

        selectorFlag = 0;
        page = 0;

        finalChoice = "";

    }

    public void run(InputListener in) {


        //Run selector if ynMenu not active
        if (!select.isActive()) {


            //Buffer input
            in.turnOnVBuffer();
            in.turnOnHBuffer();

            //Exit
            if (in.checkInput("escape")) {
                reset(in);
                in.bufferEsc();
            }

            //Load ynMenu
            if (in.checkInput("space")) {
                select.activate();
                in.bufferSpace();
            }


            //Up
            if (in.checkInput("up")) {
                in.upBuffer();
                if (selectorFlag == 0) {
                    selectorFlag = maxSelection;
                } else {
                    selectorFlag--;
                }

            }

            //Down
            if (in.checkInput("down")) {
                in.downBuffer();
                if (selectorFlag == maxSelection) {
                    selectorFlag = 0;
                } else {
                    selectorFlag++;
                }
            }


            //Left
            if (in.checkInput("left")) {
                in.leftBuffer();
                if (page == 0) {
                    page = allOptions.size() / selections.size();
                } else {
                    page--;
                }
                getPage();
            }

            //Right
            if (in.checkInput("right")) {
                in.rightBuffer();

                if (page < allOptions.size() / selections.size()) {
                    page++;
                } else {
                    page = 0;
                }
                getPage();
            }

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

    public void reset(InputListener in) {

        in.turnOffVBuffer();
        in.turnOffHBuffer();
        selectorFlag = 0;
        page = 0;
        this.active = false;

        getPage();

    }

    public boolean isActive() {
        return active;
    }

    public void activate() {
        active = true;
        yes = false;
    }

    //Update items shown to user
    private void getPage() {

        int temp = page * 8;

        for (int i = 0; i < 8; i++) {

            if (temp < allOptions.size()) {
                selections.set(i, allOptions.get(temp));
            } else {
                selections.set(i, "");
            }

            temp++;
        }

        //Update values that keep track of highlighting list
        int listSize = 0;
        while (listSize < 8 && !selections.get(listSize).equals("")) {
            listSize++;
        }
        listSize--;
        maxSelection = listSize;

        if (selectorFlag > maxSelection) {
            selectorFlag = maxSelection;
        }

    }


    //Paint method
    public void draw(Graphics g, JPanel p) {

        if (this.active) {

            int x = 500;
            int y = 500;
            int size = 18;
            int padding = 10;
            int spacing = 30;


            dialog.paintIcon(p, g, x, y);

            g.setColor(Color.white);
            g.setFont(new Font("Aerial", Font.PLAIN, size));

            g.drawString(title, x + 5 + padding, y + size + padding);
            y += spacing;

            //Draw items to be shown
            for (int i = 0; i < selections.size(); i++) {

                //Highlight item being hovered
                if (i == selectorFlag) {
                    g.setColor(Color.white);
                } else {
                    g.setColor(Color.gray);
                }

                g.drawString(selections.get(i), x + 5 + padding, y + size + padding);

                y += spacing;
            }


            //Draw yes/no dialogue if necessary
            if (select.active) {
                select.draw(g, p);
            }
        }
    }

}



