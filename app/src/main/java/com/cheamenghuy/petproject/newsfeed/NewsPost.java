package com.cheamenghuy.petproject.newsfeed;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.cheamenghuy.petproject.R;

public class NewsPost extends AppCompatActivity {
    ImageView btn_back;
    ImageView btn_home;
    TextView name_propost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_post);

        btn_back = findViewById(R.id.btn_backpost);
        btn_home = findViewById(R.id.btn_homepost);
        name_propost= findViewById(R.id.news_namepropost);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            String value = extras.getString("uname");
            name_propost.setText(value);
        }

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewsPost.this,NewsFeed.class);
                startActivity(intent);
            }
        });
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewsPost.this,NewsFeed.class);
                startActivity(intent);
            }
        });
    }

}
