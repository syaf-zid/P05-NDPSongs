package com.example.p05_ndpsongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<Song> {
    private ArrayList<Song> songArrayList;
    private Context context;
    private TextView tvSongTitle, tvSinger, tvYear;

    private ImageView iv1Star, iv2Star, iv3Star, iv4Star, iv5Star;

    public CustomAdapter(Context context, int resource, ArrayList<Song> objs) {
        super(context, resource, objs);

        songArrayList = objs;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.row, parent, false);

        tvSongTitle = rowView.findViewById(R.id.textViewSongTitle);
        tvSinger = rowView.findViewById(R.id.textViewSingers);
        tvYear = rowView.findViewById(R.id.textViewYear);

        iv1Star = rowView.findViewById(R.id.imageView1star);
        iv2Star = rowView.findViewById(R.id.imageView2star);
        iv3Star = rowView.findViewById(R.id.imageView3star);
        iv4Star = rowView.findViewById(R.id.imageView4star);
        iv5Star = rowView.findViewById(R.id.imageView5star);

        Song song = songArrayList.get(position);

        tvSongTitle.setText(song.getTitle());
        tvYear.setText(song.getYear());
        tvSinger.setText(song.getSingers());
        int rating = song.getStars();

        if(rating >= 1){
            iv1Star.setImageResource(android.R.drawable.btn_star_big_on);
            if(rating >= 2){
                iv2Star.setImageResource(android.R.drawable.btn_star_big_on);
                if(rating >= 3){
                    iv3Star.setImageResource(android.R.drawable.btn_star_big_on);
                    if(rating >= 4){
                        iv4Star.setImageResource(android.R.drawable.btn_star_big_on);
                        if(rating == 5){
                            iv5Star.setImageResource(android.R.drawable.btn_star_big_on);
                        }
                    }
                }
            }
        }

        return rowView;
    }
}
