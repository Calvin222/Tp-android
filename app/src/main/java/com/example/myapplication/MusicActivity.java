package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import java.util.Timer;
import java.util.TimerTask;

public class MusicActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        this.seekBar = (SeekBar) findViewById(R.id.sound_bar);
        this.mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.spirits);

        //Fonction qui permet de lire un son depuis une url
        //String url = "https://www.youtube.com/watch?v=F9kXstb9FF4&ab_channel=TheStrumbellasVEVO";
        //this.mediaPlayer = new MediaPlayer();
        //try {
        //    mediaPlayer.setDataSource(url);
        //    mediaPlayer.prepare();
        //} catch (IOException e) {
        //    e.printStackTrace();
        //}

        //Progress bar de la "musique"
        Timer timer = new  Timer();
        timer.schedule(new TimerTask(){
            @Override
            public void run(){
                seekBar.setProgress(mediaPlayer.getCurrentPosition() / 1000);
            }
        }, 1000, 1000);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                if(b)
                mediaPlayer.seekTo(seekBar.getProgress() * 1000);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    //Fonction qui impose une condition pour mettre en pause ou rejouer la musique.
    public void playSound(View view) {

        Button button = (Button) view;

        if(mediaPlayer.isPlaying()){
            mediaPlayer.pause();
            button.setText(getString(R.string.play_music_btr));
        }
        else {
            mediaPlayer.start();
            button.setText(getString(R.string.pause_music_btr));
        }
    }
}
