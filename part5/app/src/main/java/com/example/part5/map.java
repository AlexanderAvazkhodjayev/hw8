package com.example.part5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.TileProvider;
import com.google.android.gms.maps.model.UrlTileProvider;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.net.MalformedURLException;
import java.net.URL;

public class map extends AppCompatActivity implements OnMapReadyCallback {

    public static WeatherData WeatherData;

    private GoogleMap mMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map2);
        mapFragment.getMapAsync(this);



        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent home = new Intent(getApplicationContext(), MainActivity.class);
                        Toast.makeText(map.this, "Home", Toast.LENGTH_SHORT).show();
                        startActivity(home);
                        break;
                    case R.id.weather:
                        Intent weather = new Intent(getApplicationContext(), weather.class);
                        Toast.makeText(map.this, "Weather", Toast.LENGTH_SHORT).show();
                        startActivity(weather);
                        break;
                    case R.id.map:
                        Toast.makeText(map.this, "Map", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.history:
                        Intent history = new Intent(getApplicationContext(), history.class);
                        Toast.makeText(map.this, "History", Toast.LENGTH_SHORT).show();
                        startActivity(history);
                        break;
                }
                return true;
            }
        });
    }



    public void onMapReady(GoogleMap googleMap) {
        TileProvider tileProvider = new UrlTileProvider(256, 256) {        @Override
        public URL getTileUrl(int x, int y, int zoom) {            /* Define the URL pattern for the tile images */
            String s = String.format("https://tile.openweathermap.org/map/temp_new/1/1/1.png?appid=2397d4abcaf90749690c871029817c98", zoom, x, y);
            if (!checkTileExists(x, y, zoom)) {
                return null;
            }            try {
                return new URL(s);
            } catch (MalformedURLException e) {
                throw new AssertionError(e);
            }
        }        /*
         * Check that the tile server supports the requested x, y and zoom.
         * Complete this stub according to the tile range you support.
         * If you support a limited range of tiles at different zoom levels, then you
         * need to define the supported x, y range at each zoom level.
         */
            private boolean checkTileExists(int x, int y, int zoom) {
                int minZoom = 12;
                int maxZoom = 16;            return (zoom >= minZoom && zoom <= maxZoom);
            }
        };
        WeatherData w = weather.getWeatherData();
        Double lat = Double.parseDouble(w.Lat);
        Double lon = Double.parseDouble(w.Long);
        LatLng loc = new LatLng(lat,lon);
        googleMap.addMarker(new MarkerOptions().position(loc).title("You are here!"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(loc));
        googleMap.moveCamera(CameraUpdateFactory.zoomTo(12));
        TileOverlay tileOverlay = googleMap.addTileOverlay(new TileOverlayOptions()
                .tileProvider(tileProvider));
    }
}