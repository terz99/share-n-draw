package com.example.terz99.whiteboard;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;

import java.util.Date;

public class CreateBoard extends AppCompatActivity {

    private Button createButton;
    private EditText nameEditText;
    Firebase dbReference;
    private Boards board;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_a_board);

        dbReference = new Firebase("https://share-n-draw.firebaseio.com/");
        createButton = findViewById(R.id.create_button);
        nameEditText = findViewById(R.id.name_edit_text);
        board = new Boards((new Date()).getTime());

        createButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                board.setBoard(nameEditText.getText().toString());
                dbReference.child(board.getName()).child("dummy").child("x").setValue(-1);
                dbReference.child(board.getName()).child("dummy").child("y").setValue(-1);
                dbReference.child(board.getName()).child("dummy").child("id").setValue(-1);
                dbReference.child(board.getName()).child("dummy").child("action").setValue(-1);
                Intent startMainActivity = new Intent(CreateBoard.this, MainActivity.class);
                startActivity(startMainActivity);
            }
        });
    }
}
