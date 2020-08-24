package com.example.zero;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;
import java.util.Random;
public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIME_OUT=4000;
    TextView quot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        quot=findViewById(R.id.textView7);
        Random r=new Random();
        int id=r.nextInt(10);
        new quotes(this,quot).execute(String.valueOf(id));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(MainActivity.this,First.class);
                startActivity(i);
                finish();     }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}