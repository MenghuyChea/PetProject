package com.cheamenghuy.petproject.newsfeed;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cheamenghuy.petproject.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class NewsPost extends AppCompatActivity {
    ImageView btn_back;
    ImageView btn_home;
    TextView name_propost;
    Button btn_post;
    EditText ed_post;
    ImageView image_post;
    Button btn_addphotos;
    Bitmap bitmap;
    final int CODE_GALLERY_REQUEST = 999;
    private static final int  PERMISSION_CODE = 123;
    String url = "http://192.168.19.137:8000/api/post1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_post);

        btn_back = findViewById(R.id.btn_backpost);
        btn_home = findViewById(R.id.btn_homepost);
        ed_post =findViewById(R.id.ed_post);
        btn_addphotos = findViewById(R.id.btn_addphotos);
        image_post=findViewById(R.id.image_post);
        btn_post=findViewById(R.id.btn_post);

        name_propost= findViewById(R.id.news_namepropost);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            String value = extras.getString("uname");
            name_propost.setText(value);
        }

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = getIntent().getExtras();
                if(extras !=null) {
                    String value = extras.getString("uname");
                    Intent intent = new Intent(NewsPost.this,NewsFeed.class);
                    intent.putExtra("uname",value);
                    name_propost.setText(value);
                    startActivity(intent);
                }
            }
        });
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle extras = getIntent().getExtras();
                if(extras !=null) {
                    String value = extras.getString("uname");
                    Intent intent = new Intent(NewsPost.this,NewsFeed.class);
                    intent.putExtra("uname",value);
                    name_propost.setText(value);
                    startActivity(intent);
                }
            }
        });

        btn_addphotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check runtime Permission
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)
                {
                    if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED)
                    {
                        //Permission not granted,request it.
                        String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
                        //Show Popup for runtime permission.
                        requestPermissions(permissions,PERMISSION_CODE);
                    }else
                    {
                        //Permission granted.
                        pickImageFromGallery();
                    }
                }else
                {
                    //Permission granted.
                    pickImageFromGallery();
                }
            }
        });

        btn_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                        Log.e("checkimage",response );
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();
                        String imageData = imageToString(bitmap);
                        Bundle extras = getIntent().getExtras();
                        String description = ed_post.getText().toString();
                        if(extras !=null) {
                            String value = extras.getString("uname");
                            params.put("username",value);
                        }
                        params.put("description",description);
                        params.put("image",imageData);

                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(NewsPost.this);
                requestQueue.add(stringRequest);
            }
        });
    }

    private void pickImageFromGallery() {
        //take image from gallery by code
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,CODE_GALLERY_REQUEST);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode){
            case PERMISSION_CODE:{
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    //Permission granted.
                    pickImageFromGallery();
                }
                else{
                    //Permission was denied;
                    Toast.makeText(this,"Permission denied...!",Toast.LENGTH_SHORT).show();
                }
            }
            break;
            default:
                throw new IllegalStateException("Unexpected value: " + requestCode);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == CODE_GALLERY_REQUEST) {
            //Set image that add to view
            Uri filePath = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filePath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                image_post.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private String imageToString(Bitmap bitmap)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        byte[] imageBytes = outputStream.toByteArray();

        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
}

