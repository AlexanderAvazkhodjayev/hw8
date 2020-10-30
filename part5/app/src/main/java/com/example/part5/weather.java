package com.example.part5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import android.widget.Toast;



import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;


public class weather extends AppCompatActivity implements OnMapReadyCallback{
    TextToSpeech t1;
    Button b1;
    Button b2;
    private GoogleMap mMap;
    private ListView weatherProp;
    private ListView weatherDa;
    public Double a;
    public String lat;
    public String long_value;
    public ArrayList<String> list2 = new ArrayList<>();

    public static WeatherData WeatherData;
    public static WeatherData getWeatherData() {
        return WeatherData;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        weatherProp = (ListView) findViewById(R.id.weatherProperty);
        weatherDa = (ListView) findViewById(R.id.weatherData);
        b1=(Button)findViewById(R.id.button);
        b2=(Button)findViewById(R.id.button2);

        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });
        //Setting first list view
        final ArrayList<String> list = new ArrayList<>();
        list.add("Property");
        list.add("City Name");
        list.add("Wind Speed");
        list.add("Wind Direction");
        list.add("Humidity");
        list.add("Temperature(K)");
        list.add("Feels Like(K)");
        list.add("Weather");
        list.add("Pressure");
        list.add("Country");
        list.add("Coordinates");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        weatherProp.setAdapter(arrayAdapter);




        final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        // Intent Data Being Sent
        Intent intent = getIntent();
        String message = intent.getStringExtra("lat");
        String message2 = intent.getStringExtra("long");
        String input_data = intent.getStringExtra("actual_data");

        final RequestQueue queue = Volley.newRequestQueue(this);
        String url= "";

        if (!TextUtils.isEmpty(message)) {
            url="https://api.openweathermap.org/data/2.5/weather?lat=" + message + "&lon=" + message2 + "&appid=2397d4abcaf90749690c871029817c98";
        }

        if (!TextUtils.isEmpty(input_data)) {
            if(input_data.matches("\\d+(?:\\.\\d+)?")) {
                url = "https://api.openweathermap.org/data/2.5/weather?zip="+input_data+",us&appid=2397d4abcaf90749690c871029817c98";
            } else {
                url = "https://api.openweathermap.org/data/2.5/weather?q=" + input_data + "&appid=2397d4abcaf90749690c871029817c98";
            }


        }


        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            WeatherData = new WeatherData();
                            JSONObject json = new JSONObject(response);
                            list2 = new ArrayList<>();
                            list2.add("Description");
                            list2.add(json.getString("name"));
                            list2.add(json.getJSONObject("wind").getString("speed"));
                            list2.add(json.getJSONObject("wind").getString("deg"));
                            list2.add(json.getJSONObject("main").getString("humidity"));
                            a = Double.parseDouble(json.getJSONObject("main").getString("temp"));
                            Double b = a * 9/5 - 459.67;
                            String r = String.valueOf(b);
                            list2.add(String.valueOf(r));


                            Double b2 = Double.parseDouble(String.valueOf(json.getJSONObject("main").getString("feels_like")));
                            Double c2 = b2 * 9/5 - 459.67;
                            String r2 = String.valueOf(c2);
                            list2.add(String.valueOf(r2));

                            list2.add(json.getJSONArray("weather").getJSONObject(0).getString("description"));
                            list2.add(json.getJSONObject("main").getString("pressure"));
                            list2.add(json.getJSONObject("sys").getString("country"));
                            list2.add(json.getJSONObject("coord").getString("lon") + " , "  + json.getJSONObject("coord").getString("lat"));
                            WeatherData.Lat = String.valueOf(json.getJSONObject("coord").getString("lat"));
                            WeatherData.Long = String.valueOf(json.getJSONObject("coord").getString("lon"));
                             System.out.println(lat);

                            arrayAdapter2.clear();
                            arrayAdapter2.addAll(list2);
                            weatherDa.setAdapter(arrayAdapter2);




                        }catch(Exception e){

                            }
                        }

                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("JSON request didn't work");
            }
        });

        queue.add(stringRequest);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = list.toString();
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null);

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak2 = list2.toString();
                Toast.makeText(getApplicationContext(), toSpeak2,Toast.LENGTH_SHORT).show();
                t1.speak(toSpeak2, TextToSpeech.QUEUE_FLUSH, null);
            }
        });



        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent home = new Intent(getApplicationContext(), MainActivity.class);
                        Toast.makeText(weather.this, "Home", Toast.LENGTH_SHORT).show();
                        startActivity(home);
                        break;
                    case R.id.weather:
                        Toast.makeText(weather.this, "Weather", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.map:
                        Intent map = new Intent(getApplicationContext(), map.class);
                        Toast.makeText(weather.this, "Map", Toast.LENGTH_SHORT).show();
                        startActivity(map);
                        break;
                    case R.id.history:
                        Intent history = new Intent(getApplicationContext(), history.class);
                        Toast.makeText(weather.this, "History", Toast.LENGTH_SHORT).show();
                        startActivity(history);
                        break;
                }
                return true;
            }


        });



    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        double lat_value = 0;
        double long_val = 0;
        // Add a marker in Sydney and move the camera
        if(WeatherData != null) {
            lat_value = Double.parseDouble(WeatherData.Lat);
            long_val = Double.parseDouble(WeatherData.Long);
        }else {

        }

        LatLng TutorialsPoint = new LatLng(lat_value, long_val);
        mMap.addMarker(new MarkerOptions().position(TutorialsPoint).title("Tutorialspoint.com"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(TutorialsPoint));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(TutorialsPoint,14));

    }
    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }
}