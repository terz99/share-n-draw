package com.example.terz99.whiteboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {

    public static String BOARD_ID = "intent_data_board_id";
    private final String LOG_TAG = MainActivity.class.getName();
    private boolean firstTimeLoad = true;

    RecyclerView boards;
    SwipeRefreshLayout swiper;
    BoardsAdapter boardAdapter;
    ArrayList<Boards> boardsList;
    Firebase dbReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Firebase.setAndroidContext(this);
        boardsList = new ArrayList<Boards>();
        dbReference = new Firebase("https://share-n-draw.firebaseio.com/");
        boards = (RecyclerView)findViewById(R.id.Boards);
        boards.setHasFixedSize(true);
        boards.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        swiper = findViewById(R.id.swiper);
        loadAdapter();
        dbReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String boardId = dataSnapshot.getKey();
                Boards board = new Boards(Long.parseLong(boardId.substring(0, 13)));
                board.setBoard(boardId.substring(13));
                boardsList.add(board);
                boardAdapter.notifyItemInserted(boardAdapter.getItemCount());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e(LOG_TAG, "Failed to load boards.");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.action_add:
                Intent goToCreateBoard = new Intent(MainActivity.this, CreateBoard.class);
                startActivity(goToCreateBoard);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadAdapter() {
        boardAdapter = new BoardsAdapter(this, boardsList, swiper, new BoardsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Boards board) {
                Intent startCanvas = new Intent(MainActivity.this, Canvas.class);
                startCanvas.putExtra(MainActivity.BOARD_ID, board.getName());
                startActivity(startCanvas);
            }
        });
        boards.setAdapter(boardAdapter);
    }
}