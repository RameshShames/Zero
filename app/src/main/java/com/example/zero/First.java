package com.example.zero;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class First extends AppCompatActivity {
    private EditText usernameField, passwordField;
    private TextView forg;
    private long backPressedTime;
    private Toast backToast;
    SharedPreferences pref;
    ProgressBar loadingbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        usernameField = (EditText) findViewById(R.id.editText5);
        passwordField = (EditText) findViewById(R.id.editText9);
        forg = (TextView) findViewById(R.id.textView);
        loadingbar=findViewById(R.id.register_progress);
        pref=getSharedPreferences("user_details",MODE_PRIVATE);
        if(pref.contains("username") && pref.contains("password")){
            //String username=pref.getString("username","");
            //String password=pref.getString("password","");
            String Name=pref.getString("Name","");
            Intent intent = new Intent(this, bottomnavmain_MainActivity.class);
            intent.putExtra("Name", Name);
            startActivity(intent);
        }
    }
    public void loginPost(View view) {
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();
        if(username.equals("")||password.equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setMessage("Enter User ID or Password");
            builder.setPositiveButton("OK", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }

        else{
            loadingbar.setVisibility(View.VISIBLE);
            new SigninActivity(this,usernameField,passwordField,loadingbar).execute(username, password);}
    }
    public void registerPage(View view){
        startActivity(new Intent(this,RegActivity.class));
    }
    public void forget(View view){
        startActivity(new Intent(this,findActivity.class));
    }
    @Override
    public void onBackPressed(){
        if(backPressedTime+2000>System.currentTimeMillis()){
            backToast.cancel();
            super.onBackPressed();
            return;
        }
        else{
            backToast=Toast.makeText(getBaseContext(),"Press Back again to Exit",Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime=System.currentTimeMillis();
    }
}