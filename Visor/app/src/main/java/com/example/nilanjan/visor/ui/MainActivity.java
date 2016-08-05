package com.example.nilanjan.visor.ui;

import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.ImageView;

import com.example.nilanjan.visor.R;
import com.example.nilanjan.visor.utils.Constants;
import com.example.nilanjan.visor.utils.Coordinate;
import com.example.nilanjan.visor.utils.OptionsAdapter;
import com.example.nilanjan.visor.utils.OptionsData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = "MainActivity";
    @Bind(R.id.backdrop)
    ImageView cityBackdrop;
    @Bind(R.id.recycler_view)
    RecyclerView options;
    private Coordinate coordinate;
    private String baseURL = "https://maps.googleapis.com/maps/api/place/photo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        coordinate = (Coordinate) getIntent().getSerializableExtra("coordinate");
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(coordinate.getName());
        String backdropUrl = baseURL + "?maxwidth=" + Constants.imageResolution
                + "&photoreference=" + coordinate.getPhotoReference()
                + "&key=" + Constants.API_KEY;
        Picasso.with(this)
                .load(backdropUrl)
                .placeholder(getDrawable(R.drawable.placeholder))
                .into(cityBackdrop);
        int columnCount = getResources().getInteger(R.integer.list_column_count);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                columnCount,
                StaggeredGridLayoutManager.VERTICAL
        );
        List<OptionsData> data = getData();
        OptionsAdapter adapter = new OptionsAdapter(data, new OptionsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(OptionsData data) {
                Log.d(TAG, "onItemClick: " + data.getOptionName());
            }
        });
        options.setLayoutManager(layoutManager);
        options.setAdapter(adapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    public List<OptionsData> getData() {
        List<OptionsData> data = new ArrayList<>();
        data.add(new OptionsData(getResources().getString(R.string.places),
                getResources().getDrawable(R.drawable.placeholder)));
        data.add(new OptionsData(getResources().getString(R.string.restaurant),
                getResources().getDrawable(R.drawable.placeholder)));
        data.add(new OptionsData(getResources().getString(R.string.hotels),
                getResources().getDrawable(R.drawable.placeholder)));
        data.add(new OptionsData(getResources().getString(R.string.memories),
                getResources().getDrawable(R.drawable.placeholder)));
        return data;
    }
}
