package com.cheamenghuy.petproject.search;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.cheamenghuy.petproject.R;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    SearchView searchView;
    ListView listView;

    ArrayAdapter<String> adapter;
    ArrayList<String> name;
    View root;

    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_search, container, false);

        searchView = root.findViewById(R.id.btn_search_search);
        listView = root.findViewById(R.id.list_view);

        name = new ArrayList<>();
        name.add("Soy Sin");
        name.add("Sa Nit");
        name.add("Meng Visal");
        name.add("Votha");
        name.add("Von Seyha");

       adapter = new ArrayAdapter<>(root.getContext(),android.R.layout.simple_list_item_1,name);
       listView.setAdapter(adapter);

       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               return false;
           }

           @Override
           public boolean onQueryTextChange(String newText) {

               adapter.getFilter().filter(newText);
               return false;
           }
       });
        return root;
    }
}
