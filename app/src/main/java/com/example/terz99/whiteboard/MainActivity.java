package com.example.terz99.whiteboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.firebase.client.Firebase;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView boards;
    BoardsAdapter boardAdapter;
    ArrayList<Boards> boardsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);

        boards = (RecyclerView)findViewById(R.id.Boards);
        boards.setHasFixedSize(true);
        boards.setLayoutManager(new LinearLayoutManager(this));

        boardsList = (ArrayList<Boards>)DataSource.createListItems();
        boardAdapter = new BoardsAdapter(boardsList, new BoardsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Boards board) {
                // TODO: This
                System.out.println(board.getBoard());
            }
        });
        boards.setAdapter(boardAdapter);
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