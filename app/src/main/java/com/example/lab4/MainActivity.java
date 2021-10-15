package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.lab4.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity  {

    ActivityMainBinding  binding;
    ArrayAdapter<String> adapter;
    String[] nameList ;
    String[] positionList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //I write names in res/values/strings.xml in there I create array and input name in here and then call
        Resources resources = getResources();
        nameList = resources.getStringArray(R.array.name_list);
        positionList = resources.getStringArray(R.array.position);



        //simple_list_item_multiple_choice -- this is ready code, this code create us checkbox view
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice,nameList);
        binding.listview.setAdapter(adapter);

        //Click listview and getPosition show on Toast message
        binding.printSelectedItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringBuilder itemSelected = new StringBuilder("Selected items are: \n");
                for (int i = 0; i < binding.listview.getCount(); i++) {
                    if (binding.listview.isItemChecked(i)) {
                        itemSelected.append(binding.listview.getItemAtPosition(i)).append("\n");
                    }
                }
                Toast.makeText(getApplicationContext(), itemSelected.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        //search name list in listview
        binding.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(adapter.isEmpty()){
                    binding.listview.setVisibility(View.INVISIBLE);
                    binding.noRecords.setVisibility(View.VISIBLE);
                }
                else {
                    binding.listview.setVisibility(View.VISIBLE);
                    binding.noRecords.setVisibility(View.INVISIBLE);
                }
                adapter.getFilter().filter(newText);

                return true;
            }
        });

    }
}

