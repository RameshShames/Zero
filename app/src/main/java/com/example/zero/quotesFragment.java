package com.example.zero;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import java.util.HashMap;

public class quotesFragment extends Fragment {
    private static final int PERMISSION_REQUEST_CODE = 100;
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";
  private String mParam1;
  private String mParam2;
    ViewPager viewPager;
    ImageButton like,unlike,down,share;
    String check="unfilled";
    TextView li;
    int c=0;
    HashMap<Integer,String>map=new HashMap<>();
    int length1;
    int image_total;
    int likecount;
    String temp;
    private InterstitialAd interstitial;
    public quotesFragment() {
  }
  public static quotesFragment newInstance(String param1, String param2) {
    quotesFragment fragment = new quotesFragment();
    Bundle args = new Bundle();
    args.putString(ARG_PARAM1, param1);
    args.putString(ARG_PARAM2, param2);
    fragment.setArguments(args);
    return fragment;
  }
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mParam1 = getArguments().getString(ARG_PARAM1);
      mParam2 = getArguments().getString(ARG_PARAM2);
    }
  }
  //If Admin click on button will jump to Admin Panel activity code start here
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
      View view = inflater.inflate(R.layout.fragment_quotes, container, false);
      viewPager = view.findViewById(R.id.view_pager);
      like=view.findViewById(R.id.like);
      unlike=view.findViewById(R.id.unlike);
      down=view.findViewById(R.id.down);
      li=view.findViewById(R.id.textView8);
      share=view.findViewById(R.id.share);
      MobileAds.initialize(getContext(), " ca-app-pub-3940256099942544~3347511713");
      AdRequest adIRequest = new AdRequest.Builder().build();
      interstitial = new InterstitialAd(getContext());
      interstitial.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
      interstitial.loadAd(adIRequest);
      temp = Global.getLikes();
      String []likes=temp.split(" ");
      length1=likes.length;
      image_total=length1;
      for(int i=0;i<=image_total;i++){
      map.put(i,"GONE");
      }
      final int []no_likes=new int[length1+1];
      for(int i=0;i<length1;i++){
          no_likes[i]=Integer.parseInt(likes[i]);
      }
      //super.getActivity().onBackPressed();


      viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
     @Override
     public void onPageScrolled(final int position, float positionOffset, int positionOffsetPixels) {

         li.setText(String.valueOf(no_likes[position]));
          if(map.get(position).equals("GONE")){
              like.setVisibility(View.GONE);
              unlike.setVisibility(View.VISIBLE);
              check = "unfilled";
         }
         else{
              like.setVisibility(View.VISIBLE);
              unlike.setVisibility(View.GONE);
              check = "filled";
         }
         // Prepare an Interstitial Ad Listener

     }
     @Override
     public void onPageSelected(int position) {
         c++;
         if(c%2==0 & c!=0) {
             interstitial.show();
         }
     }
     @Override
     public void onPageScrollStateChanged(int state) {
     }
   });
    down.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
          if (checkPermission()) {
              int po = viewPager.getCurrentItem();
              String temp1 = fetchimagename.one;
              String[] temp = temp1.split(" ");
              String downurl = temp[po];
              Toast.makeText(getContext(), "Image Saved Successfull..!", Toast.LENGTH_LONG).show();
              new Details().execute(downurl);
          }
          else{
              requestPermission();
          }
          }
    });
    share.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com");
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
      }
    });
    like.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (check.equals("filled")) {
          like.setVisibility(View.GONE);
          unlike.setVisibility(View.VISIBLE);
        }
          int currentlikeno=no_likes[viewPager.getCurrentItem()];
          currentlikeno--;
          no_likes[viewPager.getCurrentItem()]=currentlikeno;
          li.setText(String.valueOf(no_likes[viewPager.getCurrentItem()]));
         int temp=viewPager.getCurrentItem();
          map.put(temp,"GONE");
        check = "unfilled";
          new likeupdates().execute(String.valueOf(currentlikeno),String.valueOf(viewPager.getCurrentItem()));
    }
    });
    unlike.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if (check.equals("unfilled")) {
          like.setVisibility(View.VISIBLE);
          unlike.setVisibility(View.GONE);
        }
          int currentlikeno=no_likes[viewPager.getCurrentItem()];
          currentlikeno++;
          no_likes[viewPager.getCurrentItem()]=currentlikeno;
          li.setText(String.valueOf(no_likes[viewPager.getCurrentItem()]));
      int temp=viewPager.getCurrentItem();
          map.put(temp,"VISIBLE");
        check = "filled";
        new likeupdates().execute(String.valueOf(currentlikeno),String.valueOf(viewPager.getCurrentItem()));
      }
    });
    fetchImages();
   
    return view;
  }

  public void fetchImages(){
      new fetchimagename(getContext(),viewPager).execute();
    }
    private boolean checkPermission() {
        int result = ContextCompat.checkSelfPermission(getContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }
    private void requestPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            Toast.makeText(getContext(), "Write External Storage permission allows us to save files. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(getActivity(), new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);
        }
    }



}
