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
import com.example.nilanjan.visor.utils.Utils;
import com.example.nilanjan.visor.widgets.HeaderTextView;

import org.json.JSONArray;

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
    private Coordinate coordinate;
    private List<MenuData> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String mode = intent.getStringExtra("mode");
        Log.d(TAG, "onCreate: " + mode);
        coordinate = (Coordinate) intent.getSerializableExtra("coordinate");
        title.setText(mode);
        Log.d(TAG, "onCreate: " + coordinate.getPlaceID());
        progressBar.setVisibility(View.VISIBLE);
        boolean status = getPlaces(mode);
        Log.d(TAG, "onCreate: " + status);
        final int columnCount = getResources().getInteger(R.integer.option_column_count);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                columnCount,
                StaggeredGridLayoutManager.VERTICAL
        );
        recyclerView.setLayoutManager(layoutManager);

    }

    public boolean getPlaces(String mode) {

        String params = "";
        if (mode.equalsIgnoreCase(getResources().getString(R.string.places))) {
            for (String parameter : Constants.PLACES_OF_INTEREST) {
                params += parameter + "|";
            }
        } else if (mode.equalsIgnoreCase(getResources().getString(R.string.restaurant))) {
            params = "restaurant|cafe";
        } else if (mode.equalsIgnoreCase(getResources().getString(R.string.hotels))) {
            params = "lodging";
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
                List<MenuData> data = Utils.parseData(array);
                Log.d(TAG, "processData: " + data.size());
                MenuAdapter adapter = new MenuAdapter(data, new MenuAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(MenuData data) {
                        Intent intent = new Intent(getBaseContext(), DetailsActivity.class);
                        intent.putExtra("data", data);
                        intent.putExtra("coordinate", coordinate);
                        startActivity(intent);
                    }
                });
                progressBar.setVisibility(View.GONE);
                recyclerView.setAdapter(adapter);
            }
        });
        asyncTask.execute(query.toString());
        return false;
    }


}
