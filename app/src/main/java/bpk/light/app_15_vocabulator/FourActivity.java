package bpk.light.app_15_vocabulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FourActivity extends AppCompatActivity {
Button btn_try_filter, btn_next_filter, btn_apply_filter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);
        btn_try_filter = findViewById(R.id.btn_try_filter);
        btn_next_filter = findViewById(R.id.btn_next_filter);
        btn_apply_filter = findViewById(R.id.btn_apply_filter);
        btn_try_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
