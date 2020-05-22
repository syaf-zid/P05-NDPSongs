package com.example.p05_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etSongTitle, etSinger, etYear;
    RadioGroup rgMain;
    RadioButton rbMain;
    Button btnInsert, btnShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etSongTitle = findViewById(R.id.editTextSongTitle);
        etSinger = findViewById(R.id.editTextSingers);
        etYear = findViewById(R.id.editTextYear);
        rgMain = findViewById(R.id.radioGroup);
        btnInsert = findViewById(R.id.buttonInsert);
        btnShowList = findViewById(R.id.buttonShowList);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedID = rgMain.getCheckedRadioButtonId();
                rbMain = findViewById(selectedID);

                String title = etSongTitle.getText().toString().trim();
                String singer = etSinger.getText().toString().trim();
                int year = Integer.parseInt(etYear.getText().toString().trim());
                int rating = Integer.parseInt(rbMain.getText().toString());

                DBHelper dbh = new DBHelper(MainActivity.this);
                dbh.insertSong(title, singer, year, rating);
                Toast.makeText(MainActivity.this,"Successfully inserted", Toast.LENGTH_SHORT).show();
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}
