package com.theironyard;

public class Main {

    static final int SIZE = 10;

    static Room[][] createRooms() {
        Room[][] rooms = new Room[SIZE][SIZE];
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                rooms[row][col] = new Room(row, col);
            }
        }
        return rooms;
    }

    public static void main(String[] args) {
	    Room[][] rooms = createRooms();
        for (Room[] row : rooms) {
            System.out.print(" _");
        }
        System.out.println();
        for (Room[] row : rooms) {
            System.out.print("|");
            for (Room room : row) {
                System.out.print("_|");
            }
            System.out.println();
        }
    }
}
