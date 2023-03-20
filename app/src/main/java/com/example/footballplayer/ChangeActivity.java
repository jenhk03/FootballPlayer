package com.example.footballplayer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ChangeActivity extends AppCompatActivity
{
    private EditText etName, etNumber, etClub;
    private Button btnChange;
    MyDatabaseHelper myDB = new MyDatabaseHelper(ChangeActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        etName = findViewById(R.id.et_name);
        etNumber = findViewById(R.id.et_number);
        etClub = findViewById(R.id.et_club);
        btnChange = findViewById(R.id.btn_change);
        Intent intent = getIntent();
        String id = intent.getStringExtra("varID");
        String name = intent.getStringExtra("varName");
        String number = intent.getStringExtra("varNumber");
        String club = intent.getStringExtra("varClub");
    }
}