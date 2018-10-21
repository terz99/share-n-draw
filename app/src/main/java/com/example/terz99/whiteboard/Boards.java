package com.example.terz99.whiteboard;

/**
 * Represents a board object which contains a name (given by the user) and a timestamp.
 * The timestamp is used to make unique IDs for the board.
 */
public class Boards {
    private String board;
    private long timestamp;

    /**
     * @param timestamp
     */
    public Boards(long timestamp){
        this.timestamp = timestamp;
        board = "";
    }

    /**
     * @return  String  The board's name
     */
    String getBoard() {
        return board;
    }

    /**
     * @return  String  The board's name in Firebase Realtime Database.
     */
    String getName(){
        return Long.toString(timestamp) + board;
    }

    /**
     * @param board
     */
    void setBoard(String board) {
        this.board = board;
    }
}
