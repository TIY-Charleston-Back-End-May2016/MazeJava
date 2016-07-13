package com.theironyard;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

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

    static ArrayList<Room> possibleNeighbors(Room[][] rooms, int row, int col) {
        ArrayList<Room> neighbors = new ArrayList<>();

        // get top room
        if (row > 0) {
            neighbors.add(rooms[row-1][col]);
        }
        // get bottom room
        if (row < SIZE - 1) {
            neighbors.add(rooms[row+1][col]);
        }
        // get left room
        if (col > 0) {
            neighbors.add(rooms[row][col-1]);
        }
        // get right room
        if (col < SIZE -1) {
            neighbors.add(rooms[row][col+1]);
        }

        neighbors = neighbors.stream()
                .filter(room -> !room.wasVisited)
                .collect(Collectors.toCollection(ArrayList<Room>::new));

        return neighbors;
    }

    static Room randomNeighbor(Room[][] rooms, int row, int col) {
        ArrayList<Room> neighbors = possibleNeighbors(rooms, row, col);

        if (neighbors.size() > 0) {
            Random r = new Random();
            int index = r.nextInt(neighbors.size());
            return neighbors.get(index);
        }

        return null;
    }

    static void tearDownWall(Room oldRoom, Room newRoom) {
        // going up
        if (newRoom.row < oldRoom.row) {
            newRoom.hasBottom = false;
        }
        // going down
        else if (newRoom.row > oldRoom.row) {
            oldRoom.hasBottom = false;
        }
        // going left
        else if (newRoom.col < oldRoom.col) {
            newRoom.hasRight = false;
        }
        // going right
        else if (newRoom.col > oldRoom.col) {
            oldRoom.hasRight = false;
        }
    }

    static boolean createMaze(Room[][] rooms, Room room) {
        room.wasVisited = true;
        Room nextRoom = randomNeighbor(rooms, room.row, room.col);
        if (nextRoom == null) {
            return false;
        }
        tearDownWall(room, nextRoom);
        while (createMaze(rooms, nextRoom));
        return true;
    }

    public static void main(String[] args) {
	    Room[][] rooms = createRooms();
        createMaze(rooms, rooms[0][0]);
        for (Room[] row : rooms) {
            System.out.print(" _");
        }
        System.out.println();
        for (Room[] row : rooms) {
            System.out.print("|");
            for (Room room : row) {
                System.out.print(room.hasBottom ? "_" : " ");
                System.out.print(room.hasRight ? "|" : " ");
            }
            System.out.println();
        }
    }
}
