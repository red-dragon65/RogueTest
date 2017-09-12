package RogueGame;

import javax.swing.*;
import java.awt.*;

public class Dialogue {

    //Variables
    private String message;
    private String message2;
    public boolean show;

    //TODO: keep track of time in here


    //Constructor
    public Dialogue() {
        show = false;
        message = "null";
    }

    //Set message
    public void setMessage(String message, String message2) {
        this.message = message;
        this.message2 = message2;
        show = true;
    }

    //Paint method
    public void draw(Graphics g, JPanel p) {

        int x = 0;
        int y = 0;
        int width = 300;
        int height = 100;
        int size = 25;
        int arcW = 25, arcH = 25;
        int padding = 10;


        //TODO: Change rectangle to bitmap image.

        g.setColor(Color.darkGray);
        g.fillRoundRect(x, y, width, height, arcW, arcH);

        g.setColor(Color.cyan);
        g.drawRoundRect(x, y, width, height, arcW, arcH);


        g.setColor(Color.white);
        g.setFont(new Font("Aerial", Font.PLAIN, size));
        g.drawString(message, x + 5 + padding, y + size + padding);
        g.drawString(message2, x + 5 + padding, y + (size * 2) + padding);

    }

}


