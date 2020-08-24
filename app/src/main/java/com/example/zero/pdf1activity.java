package com.example.zero;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
public class pdf1activity extends AppCompatActivity {
    RecyclerView recyclerView;
    public static List<PDFModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf1activity);
        recyclerView = findViewById(R.id.RV);
        list = new ArrayList<>();
        Intent i=getIntent();
        String name=i.getStringExtra("imgname");
        new fetchurl(this,list, recyclerView).execute(name);
    }
}
