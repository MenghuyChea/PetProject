package com.cheamenghuy.petproject.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.cheamenghuy.petproject.R;
import com.cheamenghuy.petproject.adapter.CareAdapter;
import com.cheamenghuy.petproject.adapter.NewsAdapter;
import com.cheamenghuy.petproject.model.CareModel;
import com.cheamenghuy.petproject.model.NewsModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    NewsAdapter adapter;
    ArrayList<NewsModel> careModels;
    FloatingActionButton floatingActionButton;
    View view;
    String username;

    public HomeFragment(String username) {
        this.username = username;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=view.findViewById(R.id.recycleView_home);

        floatingActionButton = view.findViewById(R.id.btn_add);
        careModels = new ArrayList<>();
        for(int i = 0 ; i<4; i++){
            NewsModel model = new NewsModel();
            model.setDesc("Hello my friends.");
            model.setImg_pro("https://images.pexels.com/photos/744667/pexels-photo-744667.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
            model.setImg_post("https://images.pexels.com/photos/744667/pexels-photo-744667.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
            model.setName_pro(username);
            careModels.add(model);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new NewsAdapter(view.getContext(),careModels);
        recyclerView.setAdapter(adapter);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame_layout,new PostFragment(username)).commit();
            }
        });
        return view;
    }

    private RequestQueue mrequestQueue;
    @Override
    public void onResume() {
        super.onResume();

        mrequestQueue = Volley.newRequestQueue(view.getContext());
        jsonRequest("http://192.168.200.87:8000/api/newpet/show");
    }

    void jsonRequest(String url) {

        JsonArrayRequest req = new JsonArrayRequest(
                url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try{
                    careModels = new ArrayList<>();
                    for(int i=0;i<response.length();i++){
                        JSONObject jsonObject = response.getJSONObject(i);

                        NewsModel model = new NewsModel();
                        model.setDesc(jsonObject.getString("description"));
                        model.setImg_pro("https://images.pexels.com/photos/744667/pexels-photo-744667.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
                        model.setName_pro(jsonObject.getString("username"));
                        model.setImg_post(jsonObject.getString("url"));

                        //
                        careModels.add(model);
                        Log.e("Response data",response.getString(i));
                    }

                }catch (Exception ex){
                    Log.e("Error exception",ex.toString());
                }

                adapter = new NewsAdapter(view.getContext(),careModels);
                recyclerView.setAdapter(adapter);

                Log.d("Testing", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Te0ting","Error"+error.getMessage());
            }
        });
        mrequestQueue.add(req);
    }
}
