package com.cheamenghuy.petproject.care;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cheamenghuy.petproject.R;
import com.cheamenghuy.petproject.adapter.TypePetsAdapter;
import com.cheamenghuy.petproject.model.TypePetsModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TypeofEachPetsFragment extends Fragment {
    int position;

    String[][]  dogs={
            {"Hasky","https://www.pets4homes.co.uk/images/articles/2035/large/the-siberian-husky-and-its-history-with-the-chukchi-people-53fc5b5fa1215.jpg"},
            {"Golden Retriever","https://images.localist.com/photos/32176080498196/original/982edd3c6bd6bb91197d611bbc53f24bf76210c0.jpg"},
            {"German Shepherd","https://germanshepherdcountry.com/wp-content/uploads/2016/10/GSD-Standing-GSC-600x425.jpg"},
            {"Pomeranian Spitz","https://i.pinimg.com/originals/19/76/fd/1976fda7680d2dbd43b972c855b91f6a.png"},
            {"Jiffpom","https://i.pinimg.com/originals/cf/ea/b3/cfeab35cbef3ed4f808e248e6e211d63.jpg"}
    };
    String[][] fishs={
            {"Red mosaic guppy","https://cdn10.bigcommerce.com/s-qqoyf9q9/products/1718/images/4061/mos__69630.1555690370.1280.1280.jpg?c=2"},
            {"Wakin goldfish","https://live.staticflickr.com/7209/6896978864_9dffa2e143_b.jpg"},
            {"calico goldfish","https://cdn2.tstatic.net/tribunnews/foto/bank/images/ikan-mas-koki_20180824_212155.jpg"},
            {"Angle fish","https://www.fishbazaar.sg/wp-content/uploads/2019/04/Koi-Angelfish.jpg"},
            {"Betta fish","https://cache.desktopnexus.com/thumbseg/2467/2467128-bigthumbnail.jpg"}
    };
    String[][] cats = {
            {"British Shorthair","https://newcastlebeach.org/images/british-shorthair-4.jpg"},
            {"Exotic Shorthair","https://vetstreet.brightspotcdn.com/dims4/default/4af17a9/2147483647/thumbnail/645x380/quality/90/?url=https%3A%2F%2Fvetstreet-brightspot.s3.amazonaws.com%2Fda%2F3f%2Faaaf93ab43f1aa42df7fc0c38e28%2FExotic-Shorthair-AP-1CEWS1-645sm3614.jpg"},
            {"Maine coon","https://i0.wp.com/cdn-prod.medicalnewstoday.com/content/images/articles/327/327448/maine-coon-cat.jpg?w=1155&h=1537"},
            {"Persian","https://www.arizonapetvet.com/blog/wp-content/uploads/2015/12/shutterstock_69423520.jpg"},
            {"Manx","https://vetstreet.brightspotcdn.com/dims4/default/3b339b6/2147483647/thumbnail/645x380/quality/90/?url=https%3A%2F%2Fvetstreet-brightspot.s3.amazonaws.com%2F90%2Fc20890a33e11e087a80050568d634f%2Ffile%2FManx-5-645mk062211.jpg"}
    };
    String[][]  parrots={
            {"Cockatoo","https://www.wildrepublic.com/wp-content/uploads/2018/12/Sulphur-Created-Cockatoo-xl-600x404.jpg"},
            {"Blue-Yellow macaw","https://besthqwallpapers.com/Uploads/17-9-2019/104572/thumb2-blue-and-yellow-macaw-beautiful-parrot-blue-yellow-parrot-beautiful-birds-blue-and-gold-macaw.jpg"},
            {"Budgerigar","https://www.animalesexoticos.org/wp-content/uploads/2019/01/periquito-australiano.jpg"},
            {"American grey parrot","https://i.ytimg.com/vi/RfXxh0Eff_w/maxresdefault.jpg"},
            {"Rosella","https://live.staticflickr.com/4243/34528181253_449e3c087c_b.jpg"}
    };
    RecyclerView recyclerView;
    TypePetsAdapter adapter;
    ArrayList<TypePetsModel> listModels;

    View root;

    private String[][] check(int position){
        String[][] arrpets={};
        switch (position){
            case 0:
                arrpets =dogs;
                break;
            case 1:
                arrpets = cats;
                break;
            case 2:
                arrpets = fishs;
                break;
            case 3:
                arrpets = parrots;
                break;
        }
        return arrpets;
    }
    public TypeofEachPetsFragment(int position) {
        this.position = position;
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_type_each_pet, container, false);
        recyclerView = root.findViewById(R.id.recycleView_pets);
        listModels = new ArrayList<>();
        String[][] pets = check(position);
        for(int i=0 ; i<pets.length ; i++){
            TypePetsModel model = new TypePetsModel();
            model.setImg(pets[i][1]);
            model.setName(pets[i][0]);
            listModels.add(model);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new TypePetsAdapter(root.getContext(),listModels);
        recyclerView.setAdapter(adapter);
        return root;
    }
}
