package com.example.p05_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    Button btnShow5Stars;
    ListView lv;

    ArrayAdapter aa;
    ArrayList<Song> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        lv = findViewById(R.id.listView);
        btnShow5Stars = findViewById(R.id.buttonShowSongs);

        DBHelper dbh = new DBHelper(SecondActivity.this);
        al.clear();
        al.addAll(dbh.getSongs(""));
        dbh.close();

        aa = new CustomAdapter(SecondActivity.this, R.layout.row, al);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song = al.get(position);

                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("song", song);
                startActivityForResult(intent, 9);
            }
        });

        btnShow5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                al.clear();
                al.addAll(dbh.getSongs("5"));
                dbh.close();

                aa = new CustomAdapter(SecondActivity.this, R.layout.row, al);
                lv.setAdapter(aa);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            if(requestCode == 9) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                al.clear();
                al.addAll(dbh.getSongs(""));
                dbh.close();

                aa = new CustomAdapter(SecondActivity.this, R.layout.row, al);
                lv.setAdapter(aa);
            }
        }
    }
}
