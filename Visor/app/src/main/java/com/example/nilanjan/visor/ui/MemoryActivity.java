package com.example.nilanjan.visor.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.nilanjan.visor.R;
import com.example.nilanjan.visor.utils.Coordinate;
import com.example.nilanjan.visor.utils.MemoryAdapter;
import com.example.nilanjan.visor.utils.MemoryColumns;
import com.example.nilanjan.visor.utils.MemoryData;
import com.example.nilanjan.visor.utils.MemoryProvider;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MemoryActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final String TAG = "MemoryActivity";
    private static final int URL_LOADER = 0;
    @Bind(R.id.memory_recycler_view)
    RecyclerView recyclerView;
    @Bind(R.id.fab_memory)
    FloatingActionButton memoryAdd;
    Coordinate coordinate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        ButterKnife.bind(this);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.memory_collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getString(R.string.memory_title));
        ImageView backdrop = (ImageView) findViewById(R.id.memory_backdrop);
        Picasso.with(this)
                .load(R.drawable.memory)
                .into(backdrop);
        final int columnCount = getResources().getInteger(R.integer.option_column_count);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                columnCount,
                StaggeredGridLayoutManager.VERTICAL
        );
        recyclerView.setLayoutManager(layoutManager);
        Intent intent = getIntent();
        coordinate = (Coordinate) intent.getSerializableExtra("coordinate");

        memoryAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MemoryDetailsActivity.class);
                intent.putExtra("mode", 1);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMemories();
    }

    private void getMemories() {

        getSupportLoaderManager().initLoader(URL_LOADER, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, MemoryProvider.Memories.CONTENT_URI, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        cursor.moveToFirst();
        ArrayList<MemoryData> memoryList = new ArrayList<>();
        while (!cursor.isAfterLast()) {
            Log.d(TAG, "getMemories: " + Arrays.toString(cursor.getColumnNames()));
            MemoryData data = new MemoryData(
                    cursor.getString(cursor.getColumnIndex(MemoryColumns.ID)),
                    cursor.getString(cursor.getColumnIndex(MemoryColumns.DATE)),
                    cursor.getString(cursor.getColumnIndex(MemoryColumns.HEADER)),
                    cursor.getString(cursor.getColumnIndex(MemoryColumns.BODY)),
                    cursor.getString(cursor.getColumnIndex(MemoryColumns.LATITUDE)),
                    cursor.getString(cursor.getColumnIndex(MemoryColumns.LONGITUDE))
            );
            memoryList.add(data);
            cursor.moveToNext();
        }
        MemoryAdapter adapter = new MemoryAdapter(memoryList, new MemoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MemoryData data) {
                Log.d(TAG, "onItemClick: " + data.getHeader());
                Log.d(TAG, "onItemClick: " + data.getBody() + " " + data.getId());
                Intent intent = new Intent(getBaseContext(), MemoryDetailsActivity.class);
                intent.putExtra("mode", 2);
                intent.putExtra("data", data);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
