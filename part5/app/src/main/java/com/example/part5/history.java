package com.example.part5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONObject;

import java.util.ArrayList;

public class history extends AppCompatActivity {

    public static WeatherData WeatherData;
    public double long_val = 0;
    public double lat_value = 0;
    public Double a;

    public ArrayList<String> list1 = new ArrayList<>();
    public ArrayList<String> list2 = new ArrayList<>();
    public ArrayList<String> list3 = new ArrayList<>();
    public ArrayList<String> list4 = new ArrayList<>();
    public ArrayList<String> list5 = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        final ListView listOne = (ListView)findViewById(R.id.ListOne);
        final ListView listTwo =(ListView) findViewById(R.id.ListTwo);
        final ListView listThree = (ListView)findViewById(R.id.ListThree);
        final ListView listFour = (ListView)findViewById(R.id.ListFour);
        final ListView listFive = (ListView)findViewById(R.id.ListFive);


        long oneDay = 24 * 60 * 60;
        long currentDay = System.currentTimeMillis() / 1000L;
        long fourthDay = currentDay - oneDay;
        long thirdDay = currentDay - 2 * oneDay;
        long secondDay = currentDay - 3 * oneDay;
        long firstDay = currentDay - 4 * oneDay;
        WeatherData w = weather.getWeatherData();
        if(w != null) {
            lat_value = Double.parseDouble(w.Lat);
            long_val = Double.parseDouble(w.Long);
        }else {

        }
        final ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        RequestQueue queue = Volley.newRequestQueue(this);

        String urlOne = "https://api.openweathermap.org/data/2.5/onecall/timemachine?lat=" + lat_value +"&lon=" + long_val + "&dt="+currentDay+"&appid=2397d4abcaf90749690c871029817c98";
        System.out.println(urlOne);
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, urlOne,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            list1 = new ArrayList<>();
                            list1.add("Current Day");
                            list1.add(json.getJSONObject("current").getString("humidity"));
                            list1.add(json.getJSONObject("current").getString("pressure"));
                            arrayAdapter1.addAll(list1);
                            listOne.setAdapter(arrayAdapter1);
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



        queue = Volley.newRequestQueue(this);
        final ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);


        String urlTwo = "https://api.openweathermap.org/data/2.5/onecall/timemachine?lat=" + lat_value +"&lon=" + long_val + "&dt="+fourthDay+"&appid=2397d4abcaf90749690c871029817c98";
        final StringRequest stringRequest2 = new StringRequest(Request.Method.GET, urlTwo,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            list2 = new ArrayList<>();
                            list2.add("1 Day Ago");
                            list2.add(json.getJSONObject("current").getString("humidity"));
                            list2.add(json.getJSONObject("current").getString("pressure"));
                            arrayAdapter2.addAll(list2);
                            listTwo.setAdapter(arrayAdapter2);
                        }catch(Exception e){
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("JSON request didn't work");
            }
        });


        queue.add(stringRequest2);

        queue = Volley.newRequestQueue(this);
        final ArrayAdapter<String> arrayAdapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        String urlThree = "https://api.openweathermap.org/data/2.5/onecall/timemachine?lat=" + lat_value +"&lon=" + long_val + "&dt="+thirdDay+"&appid=2397d4abcaf90749690c871029817c98";
        final StringRequest stringRequest3 = new StringRequest(Request.Method.GET, urlThree,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            list3 = new ArrayList<>();
                            list3.add("2 Day Ago");
                            list3.add(json.getJSONObject("current").getString("humidity"));
                            list3.add(json.getJSONObject("current").getString("pressure"));
                            arrayAdapter3.addAll(list3);
                            listThree.setAdapter(arrayAdapter3);
                        }catch(Exception e){
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("JSON request didn't work");
            }
        });
        queue.add(stringRequest3);





        queue = Volley.newRequestQueue(this);
        final ArrayAdapter<String> arrayAdapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        String urlFour = "https://api.openweathermap.org/data/2.5/onecall/timemachine?lat=" + lat_value +"&lon=" + long_val + "&dt="+secondDay+"&appid=2397d4abcaf90749690c871029817c98";
        final StringRequest stringRequest4 = new StringRequest(Request.Method.GET, urlFour,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            list4 = new ArrayList<>();
                            list4.add("3 Day Ago");
                            list4.add(json.getJSONObject("current").getString("humidity"));
                            list4.add(json.getJSONObject("current").getString("pressure"));
                            arrayAdapter4.addAll(list4);
                            listFour.setAdapter(arrayAdapter4);
                        }catch(Exception e){
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("JSON request didn't work");
            }
        });
        queue.add(stringRequest4);



        queue = Volley.newRequestQueue(this);
        final ArrayAdapter<String> arrayAdapter5 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);


        String urlFive = "https://api.openweathermap.org/data/2.5/onecall/timemachine?lat=" + lat_value +"&lon=" + long_val + "&dt="+firstDay+"&appid=2397d4abcaf90749690c871029817c98";
        final StringRequest stringRequest5 = new StringRequest(Request.Method.GET, urlFive,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            list5 = new ArrayList<>();
                            list5.add("4 Day Ago");
                            list5.add(json.getJSONObject("current").getString("humidity"));
                            list5.add(json.getJSONObject("current").getString("pressure"));
                            arrayAdapter5.addAll(list5);
                            listFive.setAdapter(arrayAdapter5);
                        }catch(Exception e){
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("JSON request didn't work");
            }
        });


        queue.add(stringRequest5);



        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        Intent home = new Intent(getApplicationContext(), MainActivity.class);
                        Toast.makeText(history.this, "Home", Toast.LENGTH_SHORT).show();
                        startActivity(home);
                        break;
                    case R.id.weather:
                        Intent weather = new Intent(getApplicationContext(), weather.class);
                        Toast.makeText(history.this, "Weather", Toast.LENGTH_SHORT).show();
                        startActivity(weather);
                        break;
                    case R.id.map:
                        Intent map = new Intent(getApplicationContext(), map.class);
                        Toast.makeText(history.this, "Map", Toast.LENGTH_SHORT).show();
                        startActivity(map);
                        break;
                    case R.id.history:
                        Toast.makeText(history.this, "History", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
}