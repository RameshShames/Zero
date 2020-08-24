package com.example.zero;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
public class SigninActivity  extends AsyncTask<String,Void,String>{
    private Context context;
    private EditText user;
    private EditText password;
    SharedPreferences pref;
    ProgressBar loadingbar;
    String u;
    String p;
    public SigninActivity(Context context,EditText user,EditText password,ProgressBar loadingbar) {
        this.context = context;
        this.user=user;
        this.password=password;
        this.loadingbar=loadingbar;
    }
    @Override
    protected String doInBackground(String... arg0) {
        try{
            loadingbar.setVisibility(View.VISIBLE);
            String username = (String)arg0[0];
            String password = (String)arg0[1];
            this.u=username;
            this.p=password;
            String login_url = "http://imageapps0001.000webhostapp.com/dbconn.php";
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                    +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
            bufferedWriter.write(post_data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
            String result="";
            String line="";
            while((line = bufferedReader.readLine())!= null) {
                result += line;
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return result;
        } catch(Exception e){
            return new String("Exception: " + e.getMessage());
        }
    }
    @Override
    protected void onPostExecute(String result){
        loadingbar.setVisibility(View.GONE);
        if(result.equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Login");
            builder.setMessage("Wrong user id or Password");
            builder.setPositiveButton("OK", null);
            AlertDialog dialog = builder.create();
            dialog.show();
            this.user.setText("");
            this.password.setText("");
        }
        else{
            Intent intent = new Intent(context, bottomnavmain_MainActivity.class);
            //String []str=result.split(" ");
           // String likes="";
           // for(int i=1;i<str.length;i++){
             //   likes=likes+str[i]+" ";
            //}
             intent.putExtra("Name", result);
         // Global obj=new Global(likes);
           // obj.setLikes(likes);
             pref=context.getSharedPreferences("user_details",context.MODE_PRIVATE);
             SharedPreferences.Editor editor = pref.edit();
             editor.putString("username",u);
             editor.putString("password",p);
             editor.putString("Name",result);
             editor.commit();
           // intent.putExtra("Mail", str[1]);
            //intent.putExtra("Mobile", str[2]);
           // this.user.setText("");
            //this.password.setText("");
            context.startActivity(intent);

        }
    }
}
