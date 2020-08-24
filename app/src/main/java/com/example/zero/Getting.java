package com.example.zero;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;

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
public class Getting extends AsyncTask<String,Void,String> {
    private Context context;
    private int flag;
    private TextView fq;
    public Getting(Context context, int flag,TextView fq) {
        this.context = context;
        this.flag = flag;
        this.fq=fq;
    }

    @Override
    protected String doInBackground(String... arg0) {
        try{
            String username = (String)arg0[0];
            String summa =(String)arg0[1];
            String flag=String.valueOf(this.flag);
            String login_url = "http://imageapps0001.000webhostapp.com/getpass.php";
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                    +URLEncoder.encode("flag","UTF-8")+"="+URLEncoder.encode(flag,"UTF-8")+"&"
                    +URLEncoder.encode("ans","UTF-8")+"="+URLEncoder.encode(summa,"UTF-8");
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
        if(flag==1){
            if(result.equals("")){
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Error");
                builder.setMessage("Enter the Valid User Id");
                builder.setPositiveButton("OK", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }else{
                fq.setText(result);}
        }
        else if(flag==2){
            if(result.equals("")){
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Error");
                builder.setMessage("Answer is Wrong :(");
                builder.setPositiveButton("OK", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }else{
                fq.setText(result);}
        }

    }

}