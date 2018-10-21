package com.example.terz99.whiteboard;

//import com.firebase.client.Firebase;
//import com.firebase.client.FirebaseError;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DataSource {
//    private Firebase dbReference;
//    dbReference = new Firebase("https://share-n-draw.firebaseio.com/");
    public static String[] Boards = {"Board 1", "Board 2", "Board 3", "Board 1", "Board 2", "Board 3", "Board 1", "Board 2", "Board 3", "Board 1", "Board 2", "Board 3"};

    public static List<Boards> createListItems() {
        List<Boards> items = new ArrayList<>();

        for(int i=0; i<Boards.length; i++) {
            Boards board = new Boards((new Date()).getTime());

            board.setBoard(Boards[i]);
            items.add(board);
        }
        return items;
    }
}
