package com.example.terz99.whiteboard;

public class Boards {
    private String board;
    private long timestamp;

    public Boards(long timestamp){
        this.timestamp = timestamp;
        board = "";
    }

    String getBoard() {
        return board;
    }

    String getName(){
        return Long.toString(timestamp) + board;
    }

    void setBoard(String board) {
        this.board = board;
    }
}
