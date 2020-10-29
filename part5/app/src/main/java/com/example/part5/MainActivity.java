package com.example.part5;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.weather:
                        Intent weather = new Intent(getApplicationContext(), weather.class);
                        Toast.makeText(MainActivity.this, "Weather", Toast.LENGTH_SHORT).show();
                        startActivity(weather);
                        break;
                    case R.id.map:
                        Intent map = new Intent(getApplicationContext(), map.class);
                        Toast.makeText(MainActivity.this, "Map", Toast.LENGTH_SHORT).show();
                        startActivity(map);
                        break;
                    case R.id.history:
                        Intent history = new Intent(getApplicationContext(), history.class);
                        Toast.makeText(MainActivity.this, "History", Toast.LENGTH_SHORT).show();
                        startActivity(history);
                        break;
                }
                return true;
            }
        });
    }
}