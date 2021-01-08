package com.example.odoopintegration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListProduit extends AppCompatActivity {

    private String url_product ="http://192.168.0.54/odoo_2/readAllProduct.php";
    ArrayList<Produit> data=new ArrayList<>();
    private RecyclerView.Adapter customAdapter;
    private RecyclerView myrecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_produit);
        initRecyclerView();
    }

    private void initRecyclerView(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url_product, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray array = null;
                try {
                    array =new JSONArray(response);
                    for (int i=0;i<array.length();i++){
                        JSONObject obj=array.getJSONObject(i);
                        Log.i("product Name" , obj.getString("name"));
                        Produit prod=new Produit(obj.getString("name"),obj.getString("list_price"),obj.getString("id"));
                        data.add(prod);
                    }
                    customAdapter=new CustomAdapterProduit(data);
                    myrecycler = findViewById(R.id.recyclerv_view_product);
                    myrecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    myrecycler.setAdapter(customAdapter);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),
                            "merci de verifier votre connection internet", Toast.LENGTH_LONG ).show();
                    Produit user=new Produit("erreur 404 ","object not found","merci de verifier votre connection");

                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        "merci de verifier votre connection internet", Toast.LENGTH_LONG ).show();
            }
        }
        );
        queue.add(stringRequest);


    }

}