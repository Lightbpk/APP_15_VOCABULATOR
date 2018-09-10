package bpk.light.app_15_vocabulator;

import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;

public class TwoActivity extends AppCompatActivity {
MediaRecorder mediaRecorder;
String RecFile,LL = "LightLog";
Button btn_rec, btn_go_three;
int clickID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        btn_go_three = findViewById(R.id.btn_go_three);
        btn_rec = findViewById(R.id.btn_rec);
        clickID = 1;
        RecFile = Environment.getExternalStorageDirectory() +  "/REC1.3gpp";
        btn_go_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TwoActivity.this,ThreeActivity.class);
                startActivity(intent);
            }
        });
        btn_rec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickID++;
                if(clickID % 2 ==0) {
                    recordStart();
                    btn_go_three.setVisibility(View.INVISIBLE);
                    Log.d(LL,"REC start");
                }
                else {
                    try {
                        recordStop();
                        releaseRecorder();
                        Log.d(LL, "REC stop");
                        btn_go_three.setVisibility(View.VISIBLE);
                    }catch (Exception e){
                        e.printStackTrace();
                        Log.d(LL, "EWW REC stop");
                    }
                }
            }
        });

    }
    private void releaseRecorder() {
        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
            Log.d(LL,"Recorder Released");
        }
    }
    public void recordStart() {
        try {
            releaseRecorder();
            File outFile = new File(RecFile);
            if (outFile.exists()) {
                outFile.delete();
                Log.d(LL, "outfile deleted");
            }
            mediaRecorder = new MediaRecorder();
            /*mediaRecorder.setOnInfoListener(new MediaRecorder.OnInfoListener() {
                @Override
                public void onInfo(MediaRecorder mr, int what, int extra) {
                    Log.d(LL,"what - "+ what+" extra  - "+ extra);
                }
            });*/
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.setOutputFile(RecFile);
            //mediaRecorder.setMaxDuration(5000);
            mediaRecorder.prepare();
            mediaRecorder.start();
            Log.d(LL,"REC start");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void recordStop() {
        if (mediaRecorder != null) {
            try {
                mediaRecorder.stop();
                Log.d(LL,"Tryed REC stop");
            }catch (Exception e){
                e.printStackTrace();
                Log.d(LL,"Err catch REC stop");
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseRecorder();
    }
}
