package com.cheamenghuy.petproject.home;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.TextView;

import com.cheamenghuy.petproject.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class PostFragment extends Fragment {

    Button button_choose;
    TextView textView_username;
    ImageView button_back;
    View root;
    Uri imageUri;
    final int CODE_GALLERY_REQUEST= 999;
    final int IMAGE_CODE = 123;
    ImageView imageView;
    String username;


    public PostFragment(String username) {
        this.username = username;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_post, container, false);
        button_choose = root.findViewById(R.id.btn_addphotos);
        imageView = root.findViewById(R.id.image_post);
        button_back = root.findViewById(R.id.btn_backpost);
        textView_username = root.findViewById(R.id.news_namepropost);
        textView_username.setText(username);

        button_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }


        });
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame_layout,new HomeFragment(username)).commit();
            }
        });

        return root;
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,IMAGE_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CODE_GALLERY_REQUEST && resultCode == Activity.RESULT_OK){
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
        else if(requestCode == IMAGE_CODE && resultCode == Activity.RESULT_OK){
            imageUri = data.getData();
            imageView.setImageURI(imageUri);
        }
    }
}
