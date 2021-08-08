package com.example.musicplayersimple;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    MediaPlayer music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting the song file to player
        music = MediaPlayer.create(this, R.raw.faded);

        //for music to start again if song is finished
        music.setLooping(true);

        //creating listener for the play pause button
        ImageButton playButton = (ImageButton) findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(music.isPlaying())
                {
                    //if music is playing then pause it and change image
                    music.pause();
                    playButton.setImageResource(R.drawable.ic_baseline_play_arrow_64);
                }
                else
                {
                    //if music is not playing then play it and change image
                    music.start();
                    playButton.setImageResource(R.drawable.ic_baseline_pause_64);
                }
            }
        });
    }
}