package com.example.footballplayer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity
{
    private EditText etName, etNumber, etClub;
    private Button btnAdd;
    MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        etName = findViewById(R.id.et_name);
        etNumber = findViewById(R.id.et_number);
        etClub = findViewById(R.id.et_club);
        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name, number, club;
                name = etName.getText().toString();
                number = etNumber.getText().toString();
                club = etClub.getText().toString();
                if (name.trim().equals(""))
                {
                    etName.setError("Nama tidak boleh kosong");
                }
                else if (number.trim().equals(""))
                {
                    etNumber.setError("Nomor tidak boleh kosong");
                }
                else if (club.trim().equals(""))
                {
                    etClub.setError("Klub tidak boleh kosong");
                }
                else
                {
                    long ex = myDB.AddPlayer(name, number, club);
                    if (ex == -1)
                    {
                        Toast.makeText(AddActivity.this, "Gagal Menambah Data", Toast.LENGTH_SHORT).show();
                        etName.requestFocus();
                    }
                    else
                    {
                        Toast.makeText(AddActivity.this, "Berhasil Menambah Data", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        }
        );
    }
}