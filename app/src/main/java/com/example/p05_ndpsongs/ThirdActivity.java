package com.example.p05_ndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ThirdActivity extends AppCompatActivity {
    EditText etSongID, etSongTitle, etSinger, etYear;
    RadioGroup rgThird;
    Button btnUpdate, btnDelete, btnCancel;
    Song song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        etSongID = findViewById(R.id.editTextSongID);
        etSongTitle = findViewById(R.id.editTextSongTitle);
        etSinger = findViewById(R.id.editTextSingers);
        etYear = findViewById(R.id.editTextYear);
        rgThird = findViewById(R.id.radioGroup);
        btnUpdate = findViewById(R.id.buttonUpdate);
        btnDelete = findViewById(R.id.buttonDelete);
        btnCancel = findViewById(R.id.buttonCancel);

        final Intent intent = getIntent();
        song = (Song) intent.getSerializableExtra("song");

        etSongID.setText(song.get_id() + "");
        etSongTitle.setText(song.getTitle());
        etSinger.setText(song.getSingers());
        etYear.setText(song.getYear());

        int rating = song.getStars();
        String ratingStr = Integer.toString(rating);
        for(int i = 0; i < 5; i++) {
            RadioButton rbChecked = findViewById(i);
            String rbOption = rbChecked.getText().toString();
            if(rbOption.equals(ratingStr)) {
                rbChecked.setChecked(true);
            }
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etSongTitle.getText().toString().trim();
                String singer = etSinger.getText().toString().trim();
                int year = Integer.parseInt(etYear.getText().toString().trim());

                int selectedID = rgThird.getCheckedRadioButtonId();
                RadioButton rbThird = findViewById(selectedID);
                int rating = Integer.parseInt(rbThird.getText().toString());

                int id = song.get_id();
                Song newSong = new Song(id, title, singer, year, rating);

                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.updateSong(newSong);
                Intent newIntent = new Intent(ThirdActivity.this, MainActivity.class);
                setResult(RESULT_OK, newIntent);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = song.get_id();

                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteSong(id);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
