package bpk.light.app_15_vocabulator;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class ThreeActivity extends AppCompatActivity {
Button btn_go_four, btn_gotwo_again, btn_play;
MediaPlayer mediaPlayer;
String fileName, LL="LightLog";
File playFile;
int clickID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        clickID =1;
        fileName = Environment.getExternalStorageDirectory() + "/REC1.3gpp";
        playFile = new File(fileName);
        btn_play = findViewById(R.id.btn_play);
        btn_go_four= findViewById(R.id.btn_go_four);
        btn_gotwo_again = findViewById(R.id.bnt_gotwo_again);
        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickID++;
                if (clickID % 2 ==0) playStart();
                else playStop();
            }
        });
        btn_gotwo_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThreeActivity.this,TwoActivity.class);
                startActivity(intent);
            }
        });
        btn_go_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThreeActivity.this,FourActivity.class);
                startActivity(intent);
            }
        });
    }
    public void playStart() {
        try {
            releasePlayer();
            mediaPlayer = new MediaPlayer();
            if(playFile.exists()) {
                mediaPlayer.setDataSource(fileName);
                Log.d(LL,"SetSource");
            }
            mediaPlayer.prepare();
            mediaPlayer.start();
            Log.d(LL,"Tryed Play");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(LL,"ERR Play----------------------");
            Log.e(LL,"e "+e);
            Log.e(LL,"------end print--------");
            Log.d(LL,"ERR Play");
        }
    }
    public void playStop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            Log.d(LL,"Play stop");
        }
    }
    private void releasePlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            Log.d(LL, "Released Player");
        }
    }
    @Override

    protected void onDestroy(){
        super.onDestroy();
        releasePlayer();
    }
}
