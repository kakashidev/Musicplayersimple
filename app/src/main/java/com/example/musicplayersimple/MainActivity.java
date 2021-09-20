package com.example.musicplayersimple;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int index=0;
    MediaPlayer music;


    //2D Array of songs and getting resource from its name
    String[][] soundResources = {{"faded", "bad_liar", "paris",},
            {"faded_cover_image", "bad_liar_cover_image", "paris_cover_image"},
            {"Faded", "Bad Liar", "Paris"},
            {"Alan Walker", "Imagine Dragons", "The Chainsmokers"}};

//    String[] mySounds = {"faded", "bad_liar", "paris"};
//    String[] cover_images = {"faded_cover_image", "bad_liar_cover_image", "paris_cover_image"};
//    String[] songNames = {"Faded", "Bad_Liar", "paris"};
//    String[] artistNames = {"Alan Walker", "Imagine Dragons", "The Chainsmokers"};




    //function for creating media player for playing music
    public void playMusic(int index){
        int mp3Resource = getResources().getIdentifier(soundResources[0][index], "raw" , getPackageName());
        int coverArtResource = getResources().getIdentifier(soundResources[1][index], "drawable", getPackageName());

        //setting the song file to player
        music = MediaPlayer.create(this, mp3Resource);
        music.start();
        ImageButton playButton = (ImageButton) findViewById(R.id.playButton);
        playButton.setImageResource(R.drawable.ic_baseline_pause_64);

        //Setting song name & artist name
        TextView songName = (TextView) findViewById(R.id.songNameTextView);
        TextView singerName = (TextView) findViewById(R.id.singerNameTextView);
        songName.setText(soundResources[2][index]);
        singerName.setText(soundResources[3][index]);

        //for music to start again if song is finished

        //to start next song after current song is finished
        music.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                goToNextSong();
            }
        });

        ImageView cover_art = (ImageView) findViewById(R.id.coverArt);
        cover_art.setImageResource(coverArtResource);
    }


    //function for going to next song
    public void goToNextSong()
    {
        music.release();
        music=null;
        if(index != 2)
        {
            index++;
        }
        else
        {
            index = 0;
        }
        playMusic(index);
    }


    //function for going to previous song
    public void goToPreviousSong()
    {
        music.release();
        music=null;
        if(index!=0)
        {
            index--;
        }
        else
        {
            index=2;
        }
        playMusic(index);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //calling playMusic to start first song at start of application
        playMusic(0);


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


        //creating listener for next song button
        ImageButton nextButton = (ImageButton) findViewById(R.id.playNextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNextSong();
            }
        });


        //creating listener for previous song
        ImageButton previousButton = (ImageButton) findViewById(R.id.playPreviousButton);
        previousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPreviousSong();
            }
        });
    }
}