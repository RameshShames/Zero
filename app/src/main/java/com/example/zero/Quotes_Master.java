package com.example.zero;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import static java.lang.Boolean.TRUE;
public class Quotes_Master extends AsyncTask<String,Void,String> {
    private Context context;
    private EditText about;
    private Spinner lang;
    public Quotes_Master(Context context, EditText about,Spinner lang) {
        this.context = context;
        this.about = about;
        this.lang=lang;
    }

    @Override
    protected String doInBackground(String... arg0) {
        try{
            String abo = (String)arg0[0];
            String language =(String)arg0[1];
            String Image_Name=(String)arg0[2];
            String login_url = "http://imageapps0001.000webhostapp.com/Quotes_Master.php";
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("about","UTF-8")+"="+URLEncoder.encode(abo,"UTF-8")+"&"
                    +URLEncoder.encode("language","UTF-8")+"="+URLEncoder.encode(language,"UTF-8")+"&"+
                    URLEncoder.encode("Image_Name","UTF-8")+"="+URLEncoder.encode(Image_Name,"UTF-8");
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
        about.setText("");
        Toast.makeText(context, result, Toast.LENGTH_SHORT).show();

    }

}