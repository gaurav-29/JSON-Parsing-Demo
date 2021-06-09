package com.example.jsonparsingdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recfood;
    private ArrayList<Food> TitleFoodList;
    private RequestQueue requestQueue;
    private Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recfood = findViewById(R.id.recfood);

        TitleFoodList = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);

        parseJSON();
    }

    private void parseJSON() {

        String url = "https://wifibasket.com/sabzi19/index.php?view=category";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("category_list_new");

                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject titleObject = jsonArray.getJSONObject(i);
                        String titleName = titleObject.getString("name");
                        String imageUrl = titleObject.getString("icon");

                        Food food = new Food(imageUrl,titleName);
                        TitleFoodList.add(food);
                    }

                    FoodAdapter foodAdapter = new FoodAdapter(TitleFoodList,MainActivity.this);
                    recfood.setHasFixedSize(true);
                    recfood.setLayoutManager(new LinearLayoutManager(ctx));
                    recfood.setAdapter(foodAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        });

        requestQueue.add(request);
    }
}
