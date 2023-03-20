package com.example.footballplayer;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        etName.setText(name);
        etNumber.setText(number);
        etClub.setText(club);
        btnChange.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String getName, getNumber, getClub;
                getName = etName.getText().toString();
                getNumber = etNumber.getText().toString();
                getClub = etClub.getText().toString();
                if (getName.trim().equals(""))
                {
                    etName.setError("Nama Player tidak boleh kosong!!");
                }
                else if (getNumber.trim().equals(""))
                {
                    etNumber.setError("Nomor Punggung tidak boleh kosong!!");
                }
                else if (getClub.trim().equals(""))
                {
                    etClub.setError("Klub tidak boleh kosong!!");
                }
                else
                {
                    long exec = myDB.changePlayer(id, getName, getNumber, getClub);
                    if (exec == -1)
                    {
                        Toast.makeText(ChangeActivity.this, "Gagal mengubah data", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(ChangeActivity.this, "Sukses mengubah data", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        }
        );
    }
}