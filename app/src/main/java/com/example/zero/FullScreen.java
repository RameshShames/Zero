package com.example.zero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class FullScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen);
        ImageView imageView=(ImageView)findViewById(R.id.imageView2);
        Intent CallingActivityIntent=getIntent();
        if(CallingActivityIntent!=null){
            Uri imageuri =CallingActivityIntent.getData();
            if(imageuri!=null && imageView!=null){
                Glide.with(this).load(imageuri).into(imageView);
            }
        }
    }
}