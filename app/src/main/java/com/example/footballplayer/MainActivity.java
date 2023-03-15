package com.example.footballplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private MyDatabaseHelper myDB;
    private FloatingActionButton fabAdd;
    private RecyclerView rvPlayer;
    private AdapterFootballPlayer adapterFootballPlayer;
    private ArrayList<String> arrID, arrName, arrNumber, arrClub;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvPlayer = findViewById(R.id.rv_player);
        myDB = new MyDatabaseHelper(MainActivity.this);
        fabAdd = findViewById(R.id.fab_add);
        fabAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainActivity.this, AddActivity.class));
            }
        }
        );
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        ShowPlayer();
    }
    private void SQLiteToArrayList()
    {
        Cursor cursor = myDB.readDataPlayer();
        if (cursor.getCount() == 0)
        {
            Toast.makeText(this, "Tidak Ada Data", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while (cursor.moveToNext())
            {
                arrID.add(cursor.getString(0));
                arrName.add(cursor.getString(1));
                arrNumber.add(cursor.getString(2));
                arrClub.add(cursor.getString(3));
            }
        }
    }
    private void ShowPlayer()
    {
        arrID = new ArrayList<>();
        arrName = new ArrayList<>();
        arrNumber = new ArrayList<>();
        arrClub = new ArrayList<>();
        SQLiteToArrayList();
        adapterFootballPlayer = new AdapterFootballPlayer(MainActivity.this, arrID, arrName, arrNumber, arrClub);
        rvPlayer.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        rvPlayer.setAdapter(adapterFootballPlayer);
    }
}