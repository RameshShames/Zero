package com.example.zero;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
public class newsadapter extends RecyclerView.Adapter<ImageViewHOlder> {
    private Context context;
    private List<newsmodelimage> imageList;
    public newsadapter(Context context, List<newsmodelimage> imageList) {
        this.context = context;
        this.imageList = imageList;
    }
    @NonNull
    @Override
    public ImageViewHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsgrid,parent,false);
        return new ImageViewHOlder(view);
    }
    @Override
    public void onBindViewHolder(ImageViewHOlder holder, final int position) {
        Glide.with(context).load(imageList.get(position).getImageurl()).into(holder.imageView);
        Picasso.get()
                .load(imageList.get(position).getImageurl())
                .into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name=findnamefromurl(imageList.get(position).getImageurl());
                Intent i=new Intent(context,pdf1activity.class);
                i.putExtra("imgname",name);
                context.startActivity(i);
            }
        });

    }

    public String findnamefromurl(String imageurl) {
        String s=imageurl;
        StringBuilder s1=new StringBuilder(s);
        s1=s1.reverse();
        String temp=String.valueOf(s1);
        String imgname="";
        for(int i=0;i<temp.length();i++){
            if(temp.charAt(i)=='/'){
                break;
            }
            else{
                imgname+=String.valueOf(temp.charAt(i));
            }
        }
        String name=imgname.substring(4,imgname.length());
        StringBuilder fname=new StringBuilder(name);
        String ffname=String.valueOf(fname.reverse());
        return ffname;
    }
    private Uri getlocalBitmapUri(Bitmap bitmap) {
        Uri bmuri = null;
        try {
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + "image.jpg");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
            fileOutputStream.close();
            bmuri = Uri.fromFile(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        return bmuri;

    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }
}
class ImageViewHOlder extends RecyclerView.ViewHolder{
    ImageView imageView;

    public ImageViewHOlder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.selectedImage);

    }
}