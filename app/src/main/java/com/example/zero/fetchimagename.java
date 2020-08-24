package com.example.zero;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.DeadObjectException;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import androidx.viewpager.widget.ViewPager;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class fetchimagename  extends AsyncTask<String,Void,String>{
    private Context context;
    private ViewPager viewPager;
    public static String one;
   // private ImageButton down,share;
    public fetchimagename(Context context, ViewPager viewPager) {
        this.context = context;
        this.viewPager=viewPager;

    }

    @Override
    protected String doInBackground(String... arg0) {
        try{
            String login_url = "http://imageapps0001.000webhostapp.com/fetchimagename.php";
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = "";
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
        String []re=result.split(" ");
        String temp="";
        for(int i=0;i<re.length;i++){
            temp=temp+"https://imageapps0001.000webhostapp.com/SenseVisual/Quotes_Images/"+re[i]+" ";
        }
        this.one=temp;
        final String[] imageurl=temp.split(" ");
        MyAdapter adapter = new MyAdapter(context,imageurl);
        viewPager.setAdapter(adapter);
    }
}
