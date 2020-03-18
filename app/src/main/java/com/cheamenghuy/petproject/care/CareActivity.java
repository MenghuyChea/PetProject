package com.cheamenghuy.petproject.care;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.cheamenghuy.petproject.home.HomeFragment;
import com.cheamenghuy.petproject.notification.NotificationFragment;
import com.cheamenghuy.petproject.person.PersonFragment;
import com.cheamenghuy.petproject.R;
import com.cheamenghuy.petproject.search.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class CareActivity extends AppCompatActivity {

   BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care);

        bottomNavigationView = findViewById(R.id.btn_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(navigation);

        //default fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,new HomeFragment()).commit();
      }
      private BottomNavigationView.OnNavigationItemSelectedListener navigation = new
              BottomNavigationView.OnNavigationItemSelectedListener() {
                  @Override
                  public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                      Fragment fragment = null;
                      switch (item.getItemId()){
                          case R.id.btn_home:
                              fragment = new HomeFragment();
                              break;
                          case R.id.btn_search:
                              fragment = new SearchFragment();
                              break;
                          case R.id.btn_care:
                              fragment = new CareFragment();
                              break;
                          case R.id.btn_notification:
                              fragment = new NotificationFragment();
                              break;
                          case R.id.btn_person:
                              fragment = new PersonFragment();
                              break;
                      }
                      getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout,fragment).commit();
                      return true;
                  }
              };
}
