package com.example.zero;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
public class Admin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            if(bundle.getString("some")!=null)
            {
                Toast.makeText(getApplicationContext(), "data"+bundle.getString("some"), Toast.LENGTH_SHORT).show();
            }
        }
        //Jump to admin activity code end here

    }
    public void AddImage_Activity_Jump(View view){
        Intent i=new Intent(this,AddImageFile.class);
        startActivity(i);
    }
}
