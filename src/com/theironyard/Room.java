package com.theironyard;

/**
 * Created by zach on 7/13/16.
 */
public class Room {
    int row;
    int col;
    boolean wasVisited = false;
    boolean hasBottom = true;
    boolean hasRight = true;

    public Room(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
