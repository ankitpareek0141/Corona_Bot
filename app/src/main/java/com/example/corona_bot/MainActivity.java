package com.example.corona_bot;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView totCases, recovered, active, critical, todayCases, todayDeaths, totalDeaths, tvCountries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        fatchData();
    }
    public void initViews(){
        totCases = findViewById(R.id.totCases);
        recovered = findViewById(R.id.recovered);
        active = findViewById(R.id.active);
        critical = findViewById(R.id.critical);
        todayCases = findViewById(R.id.todayCases);
        todayDeaths = findViewById(R.id.todayDeaths);
        totalDeaths = findViewById(R.id.totalDeaths);
        tvCountries = findViewById(R.id.tvCountries);
    }

    public void fatchData(){
        String url = "https://corona.lmao.ninja/v3/covid-19/countries/india";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response.toString());
                    totCases.setText(object.getString("cases"));
                    recovered.setText(object.getString("recovered"));
                    active.setText(object.getString("active"));
                    critical.setText(object.getString("critical"));
                    todayCases.setText(object.getString("todayCases"));
                    todayDeaths.setText(object.getString("todayDeaths"));
                    totalDeaths.setText(object.getString("deaths"));
                    tvCountries.setText(object.getString("affectedCountries"));
                }
                catch(JSONException exp){
                    exp.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Error : "+error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
}
}