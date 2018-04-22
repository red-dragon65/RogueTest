package RogueGame.Dialogue;

import RogueGame.InputListener;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

abstract class DialogueSelector {


    //Variables
    private ImageIcon dialog;

    private boolean active;
    public boolean yes;

    protected String title;
    //Shown items
    protected ArrayList<String> selections;
    //All items
    protected ArrayList<String> allOptions;
    protected int selectorFlag;
    private int maxSelection;
    private int page;

    public String finalChoice;

    //TODO make text  scroll


    //Constructor
    protected DialogueSelector() {

        dialog = new ImageIcon(getClass().getResource("../Assets/Other/Dialogue/DungeonDialogue.png"));

        active = false;
        yes = false;

        //Add all options to list
        allOptions = new ArrayList<>();

        selections = new ArrayList<>();

        selectorFlag = 0;
        page = 0;

        finalChoice = "";

    }

    //Set items that will be shown
    protected void initializeList() {

        int size;
        size = 8;
        if (size > allOptions.size()) {
            size = allOptions.size();
        }

        //Initialize shown list
        for (int i = 0; i < size; i++) {
            selections.add(allOptions.get(i));
        }

        if (selections.size() < size) {
            maxSelection = selections.size();
        } else {
            maxSelection = size - 1;
        }
    }

    protected void run(InputListener in) {

        //Buffer input
        in.turnOnVBuffer();
        in.turnOnHBuffer();

        //Exit
        if (in.checkInput("escape")) {
            in.bufferEsc();
            reset(in);
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
            if (page == 0 && allOptions.size() > 8) {
                page = allOptions.size() / selections.size();
            } else {
                if (allOptions.size() > 8) {
                    page--;
                }
            }
            getPage();
        }

        //Right
        if (in.checkInput("right")) {
            in.rightBuffer();

            if (page < allOptions.size() / selections.size() && allOptions.size() > 8) {
                page++;
            } else {
                page = 0;
            }
            getPage();
        }


    }

    protected void setImage(ImageIcon image) {
        this.dialog = image;
    }

    protected void reset(InputListener in) {

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

        int size;
        size = 8;
        if (size > allOptions.size()) {
            size = allOptions.size();
        }


        for (int i = 0; i < size; i++) {

            if (temp < allOptions.size()) {
                selections.set(i, allOptions.get(temp));
            } else {
                selections.set(i, "");
            }

            temp++;
        }

        //Update values that keep track of highlighting list
        int listSize = 0;
        while (listSize < size && !selections.get(listSize).equals("")) {
            listSize++;
        }
        listSize--;
        maxSelection = listSize;

        if (selectorFlag > maxSelection) {
            selectorFlag = maxSelection;
        }

    }


    //Paint method
    protected void draw(Graphics g, JPanel p) {

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

        }
    }

}