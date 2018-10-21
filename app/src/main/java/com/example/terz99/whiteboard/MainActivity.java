package com.example.terz99.whiteboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = MainActivity.class.getName();

    RecyclerView boards;
    BoardsAdapter boardAdapter;
    ArrayList<Boards> boardsList;
    Firebase dbReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        boardsList = new ArrayList<Boards>();
        dbReference = new Firebase("https://share-n-draw.firebaseio.com/");
        dbReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> boardIds = new ArrayList<String>();
                for(DataSnapshot boardSnapshot : dataSnapshot.getChildren()){
                    boardIds.add(boardSnapshot.getKey());
                }
                for(String boardId : boardIds){
                    Boards tmp = new Boards(Long.parseLong(boardId.substring(0, 12)));
                    tmp.setBoard(boardId.substring(13));
                    boardsList.add(tmp);
                }
                boards = (RecyclerView)findViewById(R.id.Boards);
                boards.setHasFixedSize(true);
                boards.setLayoutManager(new LinearLayoutManager(MainActivity.this));

                boardAdapter = new BoardsAdapter(boardsList, new BoardsAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Boards board) {
                        // TODO: Redirect to canvas
                        System.out.println(board.getBoard());
                    }
                });
                boards.setAdapter(boardAdapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e(LOG_TAG, "Error loading board IDs");
            }
        });
        setContentView(R.layout.activity_main);
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
}