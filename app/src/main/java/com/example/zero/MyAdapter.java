package com.example.zero;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import com.squareup.picasso.Picasso;
public class MyAdapter extends PagerAdapter {
    private Context context;
    private String[] imageUrls;
    public int imgposition=0;
    MyAdapter(Context context, String[] imageUrls) {
        this.context = context;
        this.imageUrls = imageUrls;
    }
    @Override
    public int getCount() {
        return imageUrls.length;
    }
    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        this.imgposition=position;
        View view = LayoutInflater.from(context).inflate(R.layout.pager,null);
        final ImageView imageView =view.findViewById(R.id.pager);
                Picasso.get()
                .load(imageUrls[position])
                .into(imageView);
        container.addView(imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent x=new Intent(context,FullScreen.class);
                x.setData(Uri.parse(imageUrls[position]));
                context.startActivity(x);
            }
        });
        return imageView;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}