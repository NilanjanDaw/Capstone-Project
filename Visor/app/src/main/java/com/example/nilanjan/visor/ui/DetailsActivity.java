package com.example.nilanjan.visor.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.example.nilanjan.visor.R;
import com.example.nilanjan.visor.utils.Constants;
import com.example.nilanjan.visor.utils.Coordinate;
import com.example.nilanjan.visor.utils.MenuData;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private static final String TAG = "DetailsActivity";
    Coordinate coordinate;
    MenuData destinationData;
    GoogleMap googleMap;

    @Bind(R.id.place_title)
    TextView placeTitle;
    @Bind(R.id.address)
    TextView address;
    @Bind(R.id.open)
    TextView open;
    @Bind(R.id.navigate)
    ImageButton navigate;
    @Bind(R.id.img)
    ImageView img;
    @Bind(R.id.ratingBar)
    RatingBar ratings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);

        Intent intent = getIntent();
        coordinate = (Coordinate) intent.getSerializableExtra("coordinate");
        destinationData = (MenuData) intent.getSerializableExtra("data");
        placeTitle.setText(destinationData.getTitle());
        address.setText(destinationData.getAddress());
        ratings.setMax(5);
        ratings.setRating((float) destinationData.getRating());
        if (destinationData.isOpen()) {
            open.setText(R.string.open);
            open.setTextColor(Color.GREEN);
        } else {
            open.setText(R.string.closed);
            open.setTextColor(Color.RED);
        }
        collapsingToolbarLayout.setTitle(getString(R.string.details_head));
        collapsingToolbarLayout.setExpandedTitleColor(Color.alpha(0));
        String backdropUrl = Constants.photoBaseURL + "?maxwidth=" + Constants.imageResolution
                + "&photoreference=" + destinationData.getThumbnailReference()
                + "&key=" + Constants.API_KEY;
        Picasso.with(this)
                .load(backdropUrl)
                .into(img);
        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr=" + coordinate.getLatitude() + "," + coordinate.getLongitude()
                                + "&daddr=" + destinationData.getLatitude() + "," + destinationData.getLongitude()));
                startActivity(intent);
            }
        });
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        this.googleMap = googleMap;
        LatLng position = new LatLng(coordinate.getLatitude(), coordinate.getLongitude());
        LatLng destination = new LatLng(destinationData.getLatitude(), destinationData.getLongitude());
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position, 14.0f));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            googleMap.setMyLocationEnabled(true);
        }
        googleMap.addMarker(new MarkerOptions().position(destination).title(destinationData.getTitle()));
        GoogleDirection.withServerKey(getResources().getString(R.string.google_maps_API))
                .from(position)
                .to(destination)
                .execute(new DirectionCallback() {
                    @Override
                    public void onDirectionSuccess(Direction direction, String rawBody) {
                        Log.d(TAG, "onDirectionSuccess: " + rawBody);
                        if (direction.isOK()) {
                            ArrayList<LatLng> directionPositionList = direction.getRouteList().get(0).getLegList().get(0).getDirectionPoint();
                            googleMap.addPolyline(DirectionConverter.createPolyline(getBaseContext(), directionPositionList, 5, getResources().getColor(R.color.accent)));

                        } else {
                            if (getCurrentFocus() != null)
                                Snackbar.make(getCurrentFocus(), getResources().getString(R.string.error), Snackbar.LENGTH_LONG)
                                        .show();
                        }
                    }

                    @Override
                    public void onDirectionFailure(Throwable t) {

                    }
                });
    }
}
