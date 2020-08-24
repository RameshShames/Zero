package com.example.zero;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
public class newsFragment extends Fragment {
  private static final String ARG_PARAM1 = "param1";
  private static final String ARG_PARAM2 = "param2";
  private String mParam1;
  private String mParam2;
  RecyclerView recyclerView;
  newsadapter myAdapter;
  List<newsmodelimage> imageList;
  newsmodelimage modelImage;
  GridLayoutManager gridLayoutManager;
  public newsFragment() {
  }
  public static newsFragment newInstance(String param1, String param2) {
    newsFragment fragment = new newsFragment();
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
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_news, container, false);
    recyclerView =view.findViewById(R.id.recyclerViewnews);
    gridLayoutManager =new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false);
    recyclerView.setLayoutManager(gridLayoutManager);
    imageList = new ArrayList<>();
   myAdapter= new newsadapter(getActivity(),imageList);
    recyclerView.setAdapter(myAdapter);
    fetchImages();
    return view;
  }
  public void fetchImages(){
    StringRequest request = new StringRequest(Request.Method.POST, "https://imageapps0001.000webhostapp.com/fetchnewsimages.php",
            new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                try {
                  JSONObject jsonObject = new JSONObject(response);
                  String succes = jsonObject.getString("success");
                  JSONArray jsonArray = jsonObject.getJSONArray("data");
                  if(succes.equals("1")){
                    for(int i=0;i<jsonArray.length();i++){
                      JSONObject object = jsonArray.getJSONObject(i);
                      String id = object.getString("Img_ID");
                      String imageurl = object.getString("Image_Name");
                      String url = "https://imageapps0001.000webhostapp.com/SenseVisual/News_Images/"+imageurl;
                      modelImage = new newsmodelimage(id,url);
                      imageList.add(modelImage);
                      myAdapter.notifyDataSetChanged();
                    }
                  }
                } catch (JSONException e) {
                  e.printStackTrace();
                }
              }
            }, new Response.ErrorListener() {
      @Override
      public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
      }
    });
    RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
    requestQueue.add(request);
  }
}
