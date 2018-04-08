package RogueGame.Dungeon;




import javax.swing.*;
import java.awt.*;

public class Tile {

    private ImageIcon IMAGE;

    private static boolean init = false;

    //General
    private static ImageIcon tile;
    private static ImageIcon dirt;

    //Edge
    private static ImageIcon topEdge;
    private static ImageIcon bottomEdge;
    private static ImageIcon leftEdge;
    private static ImageIcon rightEdge;

    //Corner
    private static ImageIcon topLeft;
    private static ImageIcon topRight;
    private static ImageIcon bottomLeft;
    private static ImageIcon bottomRight;

    //Reversed corner
    private static ImageIcon topLeftRev;
    private static ImageIcon topRightRev;
    private static ImageIcon bottomLeftRev;
    private static ImageIcon bottomRightRev;

    //Alone
    private static ImageIcon pen;
    private static ImageIcon single;


    public Tile() {

        if (!init) {

            tile = new ImageIcon(getClass().getResource("../Assets2/General/empty.png"));
            dirt = new ImageIcon(getClass().getResource("../Assets2/General/ground.png"));

            topEdge = new ImageIcon(getClass().getResource("../Assets2/Edge/top.png"));
            bottomEdge = new ImageIcon(getClass().getResource("../Assets2/Edge/bottom.png"));
            leftEdge = new ImageIcon(getClass().getResource("../Assets2/Edge/left.png"));
            rightEdge = new ImageIcon(getClass().getResource("../Assets2/Edge/right.png"));


            //Corner
            topLeft = new ImageIcon(getClass().getResource("../Assets2/Corner/1.png"));
            topRight = new ImageIcon(getClass().getResource("../Assets2/Corner/2.png"));
            bottomLeft = new ImageIcon(getClass().getResource("../Assets2/Corner/3.png"));
            bottomRight = new ImageIcon(getClass().getResource("../Assets2/Corner/4.png"));

            //Reversed corner
            topLeftRev = new ImageIcon(getClass().getResource("../Assets2/Corner_reversed/1.png"));
            topRightRev = new ImageIcon(getClass().getResource("../Assets2/Corner_reversed/2.png"));
            bottomLeftRev = new ImageIcon(getClass().getResource("../Assets2/Corner_reversed/3.png"));
            bottomRightRev = new ImageIcon(getClass().getResource("../Assets2/Corner_reversed/4.png"));

            //Alone
            pen = new ImageIcon(getClass().getResource("../Assets2/Alone/pen.png"));
            single = new ImageIcon(getClass().getResource("../Assets2/Alone/single.png"));
            init = true;
        }
    }


    /*

    Methods to set the image of the tile.

    */
    public void tile() {
        IMAGE = tile;
        IMAGE.setDescription("empty");
    }

    public void dirt() {
        IMAGE = dirt;
        IMAGE.setDescription("dirt");
    }


    public void topEdge() {
        IMAGE = topEdge;
    }

    public void bottomEdge() {
        IMAGE = bottomEdge;
    }

    public void leftEdge() {
        IMAGE = leftEdge;
    }

    public void rightEdge() {
        IMAGE = rightEdge;
    }


    public void topLeft() {
        IMAGE = topLeft;
    }

    public void topRight() {
        IMAGE = topRight;
    }

    public void bottomLeft() {
        IMAGE = bottomLeft;
    }

    public void bottomRight() {
        IMAGE = bottomRight;
    }

    public void topLeftRev() {
        IMAGE = topLeftRev;
    }

    public void topRightRev() {
        IMAGE = topRightRev;
    }

    public void bottomLeftRev() {
        IMAGE = bottomLeftRev;
    }

    public void bottomRightRev() {
        IMAGE = bottomRightRev;
    }


    public void pen() {
        IMAGE = pen;
    }

    public void single() {
        IMAGE = single;
    }

    public String getName() {
        return IMAGE.getDescription();
    }

    public static int getHeight(Tile s) {
        return s.IMAGE.getIconHeight();
    }

    public static int getWidth(Tile s) {
        return s.IMAGE.getIconWidth();
    }

    //Method to put graphic onto panel.
    public static void paint(Graphics g, JPanel panel, Tile s, int x, int y) {
        if (s.IMAGE == null) {
            g.drawRect(x, y, 20, 20);
        } else {
            s.IMAGE.paintIcon(panel, g, x, y);
        }
    }


}
