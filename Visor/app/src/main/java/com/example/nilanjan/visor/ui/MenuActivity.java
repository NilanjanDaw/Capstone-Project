package com.example.nilanjan.visor.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.nilanjan.visor.R;
import com.example.nilanjan.visor.utils.Constants;
import com.example.nilanjan.visor.utils.Coordinate;
import com.example.nilanjan.visor.utils.HttpAsyncTask;
import com.example.nilanjan.visor.utils.MenuAdapter;
import com.example.nilanjan.visor.utils.MenuData;
import com.example.nilanjan.visor.widgets.HeaderTextView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MenuActivity extends AppCompatActivity {

    private static String TAG = "MenuActivity";
    @Bind(R.id.title_menu)
    HeaderTextView title;
    @Bind(R.id.progressBar)
    ProgressBar progressBar;
    @Bind(R.id.recycler_view_menu)
    RecyclerView recyclerView;
    private String mode;
    private Coordinate coordinate;
    private StaggeredGridLayoutManager layoutManager;
    private List<MenuData> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        mode = intent.getStringExtra("mode");
        coordinate = (Coordinate) intent.getSerializableExtra("coordinate");
        title.setText(mode);
        Log.d(TAG, "onCreate: " + coordinate.getPlaceID());
        progressBar.setVisibility(View.VISIBLE);
        boolean status = getPlaces();
        final int columnCount = getResources().getInteger(R.integer.option_column_count);
        layoutManager = new StaggeredGridLayoutManager(
                columnCount,
                StaggeredGridLayoutManager.VERTICAL
        );
        recyclerView.setLayoutManager(layoutManager);

    }

    public boolean getPlaces() {

        String params = "";
        for (String parameter : Constants.PLACES_OF_INTEREST) {
            params += parameter + "|";
        }
        params = params.substring(0, params.length() - 1);
        StringBuilder query = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        query.append("location=")
                .append(coordinate.getLatitude())
                .append(",")
                .append(coordinate.getLongitude());
        query.append("&type=" + params);
        query.append("&rankby=prominence");
        query.append("&radius=10000");
        query.append("&key=" + Constants.API_KEY);
        Log.d(TAG, "getPlaces: " + query);
        final HttpAsyncTask asyncTask = new HttpAsyncTask(new HttpAsyncTask.OnFinish() {
            @Override
            public void processData(JSONArray array) {
                List<MenuData> data = parseData(array);
                MenuAdapter adapter = new MenuAdapter(data);
                progressBar.setVisibility(View.GONE);
                recyclerView.setAdapter(adapter);
            }
        });
        asyncTask.execute(query.toString());
        return false;
    }

    private List<MenuData> parseData(JSONArray array) {
        List<MenuData> parsedList = new ArrayList<>();
        return parsedList;
    }
}
