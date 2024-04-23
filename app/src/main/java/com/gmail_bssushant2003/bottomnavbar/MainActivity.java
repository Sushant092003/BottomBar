package com.gmail_bssushant2003.bottomnavbar;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PlaceAdapter adapter;
    private List<Places> placeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(android.R.color.white, getTheme()));
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the adapter with an empty list
        adapter = new PlaceAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // Set up bottom navigation item click listener
        SmoothBottomBar bottomBar = findViewById(R.id.bottombar);
        bottomBar.setOnItemSelectedListener((OnItemSelectedListener) i -> {
            switch (i) {
                case 0: // First fragment
                    // Read data from places_data.csv
                    adapter.setData(readCsvData("places_data.csv"));
                    break;
                case 1: // Second fragment
                    // Read data from food_span_data.csv
                    adapter.setData(readCsvData("food_span_data.csv"));
                    break;
                case 2: // Third fragment
                    // Read data from shopping_data.csv
                    adapter.setData(readCsvData("places_data.csv"));
                    break;
            }
            return false;
        });

        // Initially, load data for the first fragment
        bottomBar.setItemActiveIndex(0);

        adapter.setData(readCsvData("places_data.csv"));

    }

    private List<Places> readCsvData(String filename) {
        List<Places> placesList = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(getAssets().open(filename))
            );
            CSVReader reader = new CSVReader(br);

            reader.readNext();

            String[] line;
            while ((line = reader.readNext()) != null) {
                String name = line[0];
                String imageUrl = line[1];
                String bestTimeToVisit = line[2];
                Places place = new Places(name, imageUrl, bestTimeToVisit);
                placesList.add(place);
            }

            reader.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return placesList;
    }
}