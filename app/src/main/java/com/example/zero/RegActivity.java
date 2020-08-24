package com.example.zero;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class RegActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText mail_id,pass,confpass,Ans,namefeild,mobile;
    Spinner ques;
    Button Register;
    String que="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        namefeild=findViewById(R.id.editText10);
        mobile=findViewById(R.id.editText4);
        mail_id=findViewById(R.id.editText);
        pass=findViewById(R.id.editText2);
        confpass=findViewById(R.id.editText3);
        Ans=findViewById(R.id.editText6);
        Register=findViewById(R.id.button3);
        ques=findViewById(R.id.spinner);
        ques.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("Select the Questions");
        categories.add("What is your Nick Name ?");
        categories.add("What is your Birth Place ? ");
        categories.add("What is your Favourite Food ?");
        categories.add("What is your Favourite Place ?");
        categories.add("What is your Hobby?");
        categories.add("Who is your Favourite Personality ?");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ques.setAdapter(dataAdapter);
    }
    public void Reg(View view){
        String username=namefeild.getText().toString();
        String mobileno=mobile.getText().toString();
        String mailid = mail_id.getText().toString();
        String password = pass.getText().toString();
        String confirm =confpass.getText().toString();
        String question =que;
        String Answer =Ans.getText().toString();
        if(username.equals("")||mobileno.equals("")||mailid.equals("")||password.equals("")||confirm.equals("")||question.equals("")||Answer.equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setMessage("Fill all the Feilds");
            builder.setPositiveButton("OK", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        else if(password.equals(confirm)&& mailid!=null && question!=null && Answer!=null){
            new Enter(this,namefeild,mobile,mail_id,pass,confpass,ques,Ans).execute(username,mobileno,mailid,password,question,Answer);}
        else{
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error");
            builder.setMessage("Enter the Valid Information");
            builder.setPositiveButton("OK", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.que = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }
}