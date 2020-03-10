package com.cheamenghuy.petproject.newsfeed;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cheamenghuy.petproject.R;
import com.cheamenghuy.petproject.adapter.NewsAdapter;
import com.cheamenghuy.petproject.model.NewsModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class NewsFeed extends AppCompatActivity {
    FloatingActionButton btn_post;
    RecyclerView recyclerView;
    ArrayList<NewsModel> arrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed);
        recyclerView=findViewById(R.id.recycleView1);

        btn_post = findViewById(R.id.btn_add);


        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = getIntent().getExtras();
                if(extras !=null) {
                    String value = extras.getString("uname");
                    Intent intent = new Intent(NewsFeed.this,NewsPost.class);
                    intent.putExtra("uname",value);
                    startActivity(intent);
                }
            }
        });

        arrayList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        NewsAdapter adapter = new NewsAdapter(getApplicationContext(),arrayList);
        recyclerView.setAdapter(adapter);

    }

    private RequestQueue mRequestQueue;
    @Override
    protected void onResume() {
        super.onResume();
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        jsonRequest("https://reqres.in/api/users");
    }
    public void jsonRequest(String url){
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONArray array = object.getJSONArray("data");

                    Log.e("model",array.toString());

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject object1 = array.getJSONObject(i);
                        NewsModel model = new NewsModel();
                        model.setImg_pro(object1.getString("avatar"));
                        model.setName_pro(object1.getString("first_name"));
                        model.setDesc(object1.getString("email"));
                        model.setImg_post(object1.getString("avatar"));
                        arrayList.add(model);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                NewsAdapter adapter = new NewsAdapter(getApplicationContext(),arrayList);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("TESTING", "Error: " + error.getMessage());
            }
        });
        mRequestQueue.add(request);
    }

}
