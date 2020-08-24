package com.example.zero;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import java.io.*;
import java.net.*;
public class Enter extends AsyncTask<String,Void,String> {
    private EditText e1,e2,e3,e4,e5,e6;
    private Spinner s;
    private Context context;
    public Enter(Context context,EditText e1,EditText e2,EditText e3,EditText e4,EditText e5,Spinner s,EditText e6) {
        this.context = context;
        this.e1=e1;
        this.e2=e2;
        this.e3=e3;
        this.e4=e4;
        this.e5=e5;
        this.s=s;
        this.e6=e6;

    }
    @Override
    protected String doInBackground(String... arg0) {
        try{
            String username = (String)arg0[0];
            String mobileno=(String)arg0[1];
            String mailid=(String)arg0[2];
            String password = (String)arg0[3];
            String question=(String)arg0[4];
            String Answer=(String)arg0[5];
            String login_url = "https://imageapps0001.000webhostapp.com/Reg.php";
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"
                    +URLEncoder.encode("mobileno","UTF-8")+"="+URLEncoder.encode(mobileno,"UTF-8")+"&"
                    +URLEncoder.encode("mailid","UTF-8")+"="+URLEncoder.encode(mailid,"UTF-8")+"&"
                    +URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"
                    +URLEncoder.encode("question","UTF-8")+"="+URLEncoder.encode(question,"UTF-8")+"&"
                    +URLEncoder.encode("Answer","UTF-8")+"="+URLEncoder.encode(Answer,"UTF-8");
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
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Registration");
        builder.setMessage(result);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
        e1.setText("");
        e2.setText("");
        e3.setText("");
        e4.setText("");
        e5.setText("");
        e6.setText("");
    }
}