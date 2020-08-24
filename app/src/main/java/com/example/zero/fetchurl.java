package com.example.zero;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.io.*;
import java.net.*;
import java.util.List;
public class fetchurl extends AsyncTask<String,Void,String> {
    RecyclerView recyclerView;
    public static List<PDFModel> list;
    private Context context;
    public fetchurl(Context context,List<PDFModel> list,RecyclerView recyclerView) {
        this.context = context;
        this.recyclerView=recyclerView;
        this.list=list;
    }
    @Override
    protected String doInBackground(String... arg0) {
        try{
            String name = (String)arg0[0];
            String login_url = "https://imageapps0001.000webhostapp.com/SenseVisual/fetchpdf.php";
            URL url = new URL(login_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String post_data = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8");
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
      String []urls=result.split(" ");
       // list = new ArrayList<>();
      for(int i=0;i<urls.length;i++){
          list.add(new PDFModel(String.valueOf(i),urls[i]));
      }
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(context,PDFActivity.class);
                //intent.putExtra("url",list.get(position).getPdfUrl());
                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        };
        PDFAdapter adapter = new PDFAdapter(list,context,itemClickListener);
        recyclerView.setAdapter(adapter);
    }
}