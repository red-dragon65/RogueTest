package RogueGame.Dungeon;


import java.util.Random;

public class Map {


    private final int sizex = 70, sizey = 53;

    public int[][] collisionMask;
    public Tile[][] matrix;
    private int[][] grid;


    private int imgsize = 24;


    //Number of rooms
    private int rooms, roomMin = 3, roomMax = 6; //1 is min, 12 is max.
    //Size of rooms
    private int roomX, minRoomSizeX = 5, maxRoomSizeX = 10; //5 is min, and 10 is max. Offset for both is 1.
    private int roomY, minRoomSizeY = 5, maxRoomSizeY = 10;

    private Random gen;

    public int getSizeX() {
        return sizex;
    }

    public int getSizeY() {
        return sizey;
    }


    /*
    Constructor
     */
    public Map() {

        collisionMask = new int[sizey][sizex];
        matrix = new Tile[sizey][sizex];

        grid = new int[3][4];

        gen = new Random();

        init();
    }


    //Initialize
    public void init() {

        //Initialize map
        for (int i = 0; i < sizey; i++) {
            for (int z = 0; z < sizex; z++) {
                matrix[i][z] = new Tile();
                matrix[i][z].tile();
                matrix[i][z].setX(z * imgsize);
                matrix[i][z].setY(i * imgsize);
            }
        }

        //Initialize grid.
        for (int z = 0; z < 4; z++) {
            for (int i = 0; i < 3; i++) {
                grid[i][z] = 0;
            }
        }


        rooms();

        halls();

        advanceEdges();


        //Initialize collisionMap
        for (int i = 0; i < sizey; i++) {
            for (int z = 0; z < sizex; z++) {


                if (matrix[i][z].getName().equals("dirt")) {
                    collisionMask[i][z] = 1;
                } else {
                    collisionMask[i][z] = 0;
                }
            }
        }

    }


    /**
     * Methods
     */


    private void rooms() {

        int x;
        int y;
        int mult = 16;
        int max = 10;


        //Up to 3 to 6 rooms.
        //rooms = gen.nextInt(4) + 3;
        //Get number of rooms.
        rooms = gen.nextInt((roomMax - roomMin + 1)) + roomMin;


        //Populate grid with rooms.
        for (int i = 0; i < rooms; i++) {

            //Put into random grid location
            do {
                x = gen.nextInt(4);
                y = gen.nextInt(3);


            } while (grid[y][x] == 1);

            grid[y][x] = 1;


            //Gen room size
            //roomX = gen.nextInt(5) + 5;
            //roomY = gen.nextInt(5) + 5;
            roomX = gen.nextInt((maxRoomSizeX - minRoomSizeX)) + minRoomSizeX;
            roomY = gen.nextInt((maxRoomSizeY - minRoomSizeY)) + minRoomSizeY;

            //Populate matrix based on grid
            int padx = gen.nextInt(max - roomX) + 5;
            int pady = gen.nextInt(max - roomY) + 4;

            //Makes room
            for (int p = pady + y * mult; p <= pady + roomX + y * mult; p++) { // rows
                for (int c = padx + x * mult; c <= padx + roomY + x * mult; c++) { // columns
                    matrix[p][c].dirt();
                }
            }
        }
    }


    private void halls() {

        //center y
        int[] y = new int[16];

        //center x
        int[] z = new int[16];

        int tracker = 0;


        //Get room centers for matrix
        for (int i = 0; i < 3; i++) {

            for (int x = 0; x < 4; x++) {

                if (grid[i][x] == 1) {
                    y[tracker] = i * 16 + 7;
                    z[tracker] = x * 16 + 7;

                    tracker++;
                }
            }
        }


        //Repeat first two rooms
        y[tracker] = y[0];
        z[tracker] = z[0];
        y[tracker + 1] = y[1];
        z[tracker + 1] = z[1];


        //Connect n to n+1. Connect n to n+2.
        //TODO: Add chance to NOT connect n to n+2 ???
        /*
        //This code looks too grided
        for (int i = 0; i < tracker; i++) {

            for (int p = 1; p < 3; p++)
                draw(y[i], z[i], y[i + p], z[i + p]);

        }*/

        //This code looks more random
        for (int i = 0; i < tracker; i++) {

            drawHalls(y[i], z[i], y[i + 1], z[i + 1]);

        }


    }


    //Draws first room to second room
    private void drawHalls(int rx, int ry, int rx2, int ry2) {


        int path = 0;
        boolean flag = true;

        int offset = 7;

        int rxPlus = rx + offset;
        int rxNeg = rx - offset;

        int ryPlus = ry + offset;
        int ryNeg = ry - offset;


        int chance = 3;


        //Vertical halls

        //Down
        while (rx <= rx2) {

            //Chance to change path
            if (path != 1 && rx > rxPlus)
                path = gen.nextInt(chance);


            //Adjust path if needed
            if (path == 1) {
                if (flag) {
                    if (check(rx, ry))
                        matrix[rx][ry].dirt();
                    flag = false;
                }
                matrix[rx][ry + 1].dirt();
            } else if (check(rx, ry))
                matrix[rx][ry].dirt();

            //Next tile
            rx++;
        }


        flag = true;


        //Up
        while (rx >= rx2) {


            if (path != 1 && rx < rxNeg)
                path = gen.nextInt(chance);

            if (path == 1) {
                if (flag) {
                    if (check(rx, ry))
                        matrix[rx][ry].dirt();
                    flag = false;
                }
                matrix[rx][ry + 1].dirt();
            } else if (check(rx, ry))
                matrix[rx][ry].dirt();

            rx--;
        }


        //Horizontal halls

        flag = true;

        //Right
        while (ry <= ry2) {


            if (path != 1 && ry > ryPlus)
                path = gen.nextInt(chance);

            if (path == 1) {
                if (flag) {
                    if (check(rx, ry))
                        matrix[rx][ry].dirt();
                    flag = false;
                }

                matrix[rx + 1][ry].dirt();
            } else if (check(rx, ry))
                matrix[rx][ry].dirt();

            ry++;
        }


        flag = true;

        //Left
        while (ry >= ry2) {


            if (path != 1 && ry < ryNeg)
                path = gen.nextInt(chance);

            if (path == 1) {
                if (flag) {
                    if (check(rx, ry))
                        matrix[rx][ry].dirt();
                    flag = false;
                }
                matrix[rx + 1][ry].dirt();
            } else if (check(rx, ry))
                matrix[rx][ry].dirt();

            ry--;
        }


        //This is old code (non-randomized)
        /*
        //Down
        while(rx < rx2){
            matrix[rx][ry].dirt();
            rx++;
        }
        //Up
        while(rx > rx2){
            matrix[rx][ry].dirt();
            rx--;
        }

        //Horizontal halls


        //Right
        while(ry < ry2){
            matrix[rx][ry].dirt();
            ry++;
        }

        //Left
        while(ry > ry2){
            matrix[rx][ry].dirt();
            ry--;
        }*/
    }

    //Checks to see if path should be drawn
    private boolean check(int x, int y) {

        int c = 0;

        if (matrix[x][y].getName().equals("dirt")) {
            c++;
        }
        if (matrix[x + 1][y + 1].getName().equals("dirt")) {
            c++;
        }
        if (matrix[x + 1][y - 1].getName().equals("dirt")) {
            c++;
        }
        if (matrix[x - 1][y + 1].getName().equals("dirt")) {
            c++;
        }
        if (matrix[x - 1][y - 1].getName().equals("dirt")) {
            c++;
        }


        return c <= 2;

    }


    //Draw edges around ground
    private void advanceEdges() {

        boolean up, down, left, right;

        int counter;


        for (int i = 1; i < sizey - 1; i++) {//row
            for (int z = 1; z < sizex - 1; z++) {//column

                up = down = left = right = false;

                counter = 0;

                //Check if current tile is empty
                if (matrix[i][z].getName().equals("empty")) {

                    //top
                    if (matrix[i + 1][z].getName().equals("dirt")) {
                        counter++;
                        up = true;
                    }

                    //bottom
                    if (matrix[i - 1][z].getName().equals("dirt")) {
                        counter++;
                        down = true;
                    }

                    //left
                    if (matrix[i][z - 1].getName().equals("dirt")) {
                        counter++;
                        left = true;
                    }

                    //right
                    if (matrix[i][z + 1].getName().equals("dirt")) {
                        counter++;
                        right = true;
                    }


                    if (counter > 0) {
                        switch (counter) {
                            case 1: //Edge

                                //Type
                                if (up)
                                    matrix[i][z].topEdge();
                                else if (down)
                                    matrix[i][z].bottomEdge();
                                else if (left)
                                    matrix[i][z].rightEdge();
                                else if (right)
                                    matrix[i][z].leftEdge();

                                break;
                            case 2://Corner reversed or double edge

                                if (up && left)
                                    matrix[i][z].bottomRightRev();
                                else if (up && right)
                                    matrix[i][z].bottomLeftRev();
                                else if (down && left)
                                    matrix[i][z].topLeftRev();
                                else if (down && right)
                                    matrix[i][z].topRightRev();

                                else if (up && down) //Double edge
                                    matrix[i][z].pen();
                                else if (right && left) //Double edge
                                    matrix[i][z].pen();

                                break;
                            case 3://peninsula
                                matrix[i][z].pen();
                                if (!up)
                                    matrix[i + 1][z].single();
                                else if (!down)
                                    matrix[i - 1][z].single();
                                else if (!left)
                                    matrix[i][z - 1].single();
                                else if (!right)
                                    matrix[i][z + 1].single();
                            case 4://single
                                matrix[i][z].pen();
                                break;
                        }
                    } else {

                        up = down = left = right = false;

                        //top left
                        if (matrix[i - 1][z - 1].getName().equals("dirt")) {
                            counter++;
                            up = true;
                        }
                        //bottom left
                        if (matrix[i - 1][z + 1].getName().equals("dirt")) {
                            counter++;
                            down = true;
                        }
                        //bottom right
                        if (matrix[i + 1][z - 1].getName().equals("dirt")) {
                            counter++;
                            left = true;
                        }
                        //top right
                        if (matrix[i + 1][z + 1].getName().equals("dirt")) {
                            counter++;
                            right = true;
                        }

                        if (counter == 1) {

                            if (up)
                                matrix[i][z].bottomLeft();
                            if (down)
                                matrix[i][z].bottomRight();
                            if (left)
                                matrix[i][z].topRight();
                            if (right)
                                matrix[i][z].topLeft();
                        }
                    }

                }

            }
        }
    }


    public int[] getRandomRoom() {

        int[] result = new int[4];
        int[] loc = new int[2];
        int[] temp = new int[2];
        int[] tileMatrix = new int[2];

        //Get random room
        do {
            loc[0] = gen.nextInt(3);
            loc[1] = gen.nextInt(4);
        } while (grid[loc[0]][loc[1]] != 1);

/*
//TODO: make sure matrix location is ground
        do{
            matrix[loc[0] * 16 + 8][loc[1] * 16 + 8];
        }while();*/

        tileMatrix[0] = loc[0] * 16 + 8;
        tileMatrix[1] = loc[1] * 16 + 8;

        temp[0] = matrix[tileMatrix[0]][tileMatrix[1]].getX();
        temp[1] = matrix[tileMatrix[0]][tileMatrix[1]].getY();

        result[0] = tileMatrix[0];
        result[1] = tileMatrix[1];
        result[2] = temp[0];
        result[3] = temp[1];

        return result;
    }

}
