package com.example.lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.example.lab4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity  {

    ActivityMainBinding  binding;
    ArrayAdapter<String> adapter;
    String[] nameList ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //I write names in res/values/strings.xml in there I create array and input name in here and then call
        Resources resources = getResources();
        nameList = resources.getStringArray(R.array.name_list);



        //simple_list_item_multiple_choice -- this is ready code, this code create us checkbox view
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice,nameList);
        binding.listview.setAdapter(adapter);

        //Click listview and getPosition show on Toast message
        binding.printSelectedItems.setOnClickListener(view -> {
            StringBuilder itemSelected = new StringBuilder("Selected items are: \n");
            for (int i = 0; i < binding.listview.getCount(); i++) {
                if (binding.listview.isItemChecked(i)) {
                    itemSelected.append(binding.listview.getItemAtPosition(i)).append("\n");
                }
            }
            Toast.makeText(getApplicationContext(), itemSelected.toString(), Toast.LENGTH_SHORT).show();
        });

        //search name list in listview
        binding.searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                if(adapter.getCount()<=0){
                    binding.noRecords.setVisibility(View.VISIBLE);
                    binding.listview.setVisibility(View.INVISIBLE);
                }
                else {
                    binding.listview.setVisibility(View.VISIBLE);
                    binding.noRecords.setVisibility(View.INVISIBLE);
                }
                return false;
            }


        });
    }
}

