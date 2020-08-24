package com.example.zero;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class findActivity extends AppCompatActivity {
    Button getq,getp;
    EditText fid,fans;
    TextView fq,fp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        getq=findViewById(R.id.button4);
        getp=findViewById(R.id.button5);
        fid=findViewById(R.id.editText7);
        fans=findViewById(R.id.editText8);
        fq=findViewById(R.id.textView4);
        fp=findViewById(R.id.textView5);
    }
    public void getquestion(View view){
        String id=fid.getText().toString();
        String summa="";
        if(id.equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setMessage("Enter the user Mail ID");
            builder.setPositiveButton("OK", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else{new Getting(this, 1,fq).execute(id,summa);}
    }
    public void getpass(View view){
        String id=fid.getText().toString();
        String summa=fans.getText().toString();
        if(id.equals("")||summa.equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setMessage("Enter the Answer");
            builder.setPositiveButton("OK", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else{
        new Getting(this, 2,fp).execute(id,summa);}
    }
}
