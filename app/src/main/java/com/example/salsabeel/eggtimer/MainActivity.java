package com.example.salsabeel.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    CountDownTimer eggTimer;
    int curr_time = 30000;
    int s = 30;
    int m = 0;

    boolean click = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        final TextView seconds = (TextView) findViewById(R.id.seconds);

        final SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);

        int max_time = 600000;


        seekBar.setMax(max_time);
        seekBar.setProgress(30000);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                curr_time = i;
                //Log.i("Current time",Integer.toString(curr_time));
                if ( i < 60000) {
                    s = i / 1000;
                    m = 0;
                }
                else {
                    s = (i/1000) % 60;
                    m = i/60000;
                }

                seconds.setText(Integer.toString(m)+":"+Integer.toString(s));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });




    }

    public void startTicking(View view)
    {
        final SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        final MediaPlayer sound = MediaPlayer.create(this,R.raw.cow_moo);
        final TextView seconds = (TextView) findViewById(R.id.seconds);
        final Button button = (Button) findViewById(R.id.button);

        //Toast.makeText(getApplicationContext(), Integer.toString(curr_time),Toast.LENGTH_SHORT).show();



        if ( click == false ) {
            eggTimer = new CountDownTimer(curr_time,1000) {
                @Override
                public void onTick(long l) {
                    seekBar.setProgress((int) l);
                    //Log.i("SeekBar value : ",String.valueOf(l/1000));
                }

                @Override
                public void onFinish() {
                    sound.start();
                    curr_time = 30000;
                    s = 30;
                    m = 0;
                    seconds.setText(Integer.toString(m)+":"+Integer.toString(s));
                    seekBar.setProgress(30000);
                    seekBar.setEnabled(true);
                    button.setText("GO");
                    click =false;
                }
            };
            eggTimer.start();
            seekBar.setEnabled(false);
            button.setText("STOP");
            click = true;
        }
        else
        {
            eggTimer.cancel();
            eggTimer = null;
            curr_time = 30000;
            s = 30;
            m = 0;
            seconds.setText(Integer.toString(m)+":"+Integer.toString(s));
            seekBar.setProgress(30000);
            seekBar.setEnabled(true);
            button.setText("GO");
            click =false;
        }

    }



}
