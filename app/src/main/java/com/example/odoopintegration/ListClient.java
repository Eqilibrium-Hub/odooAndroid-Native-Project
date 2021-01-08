package com.example.odoopintegration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import oogbox.api.odoo.OdooClient;
import oogbox.api.odoo.OdooUser;
import oogbox.api.odoo.client.AuthError;
import oogbox.api.odoo.client.OdooVersion;
import oogbox.api.odoo.client.helper.OdooErrorException;
import oogbox.api.odoo.client.helper.data.OdooRecord;
import oogbox.api.odoo.client.helper.data.OdooResult;
import oogbox.api.odoo.client.listeners.AuthenticateListener;
import oogbox.api.odoo.client.listeners.IOdooResponse;
import oogbox.api.odoo.client.listeners.OdooConnectListener;
import oogbox.api.odoo.client.listeners.OdooErrorListener;

public class ListClient extends AppCompatActivity {

//    OdooClient client;
    private String url;
    private String login;
    private String mdp;
    private String bd;
    private String url_client ="http://192.168.0.54/odoo_2/readAllPartner.php";
    ArrayList<Client> data=new ArrayList<>();
    private RecyclerView.Adapter customAdapter;
    private RecyclerView myrecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_client);
        initRecyclerView();
    }

    private void initRecyclerView(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url_client, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONArray array = null;
                try {
                    array =new JSONArray(response);
//                    JSONObject json = new JSONObject(response);
//                    JSONArray country_id = json.getJSONArray("country_id");
                    for (int i=0;i<array.length();i++){
                        JSONObject obj=array.getJSONObject(i);
                        Log.i("country_id" , obj.getString("country_id").substring(6,19));
                        Client client=new Client(obj.getString("name"),obj.getString("country_id").substring(1,4),obj.getString("country_id").substring(6,19));
                        data.add(client);
                    }
                    customAdapter=new CustomAdapterClient(data);
                    myrecycler = findViewById(R.id.recyclerv_view);
                    myrecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    myrecycler.setAdapter(customAdapter);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),
                            "merci de verifier votre connection internet", Toast.LENGTH_LONG ).show();
                    Client user=new Client("erreur 404 ","object not found","merci de verifier votre connection");

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

//    public void allClient(){
//        MainActivity mainActivity = new MainActivity();
//        url = mainActivity.getUrl();
//        login = mainActivity.getUserName();
//        mdp = mainActivity.getMotDePasse();
//        bd = mainActivity.getDb();
////        Log.i("host",url);
////        Log.i("login_1",login);
////        Log.i("mot de passe",mdp);
////        Log.i("bd",bd);
//
//        final AuthenticateListener loginCallback =new AuthenticateListener() {
//            @Override
//            public void onLoginSuccess(OdooUser user) {
//                Log.i("ligne","ligne 33");
//                List<Integer> ids = Arrays.asList(user.uid);
//                List<String> fields = Arrays.asList("id", "name");
//                client.read("product.template", ids, fields, new IOdooResponse() {
//                    @Override
//                    public void onResult(OdooResult result) {
//                        OdooRecord[] records = result.getRecords();
//                        for(OdooRecord record: records) {
//                            Log.i("Name:", record.getString("name"));
//                        }
//                    }
//                });
//            }
//
//            @Override
//            public void onLoginFail(AuthError error) {
////                Log.i("host",url);
////                Log.i("login",login);
////                Log.i("motdepasse",mdp);
////                Log.i("bd",bd);
//                AlertDialog alertDialog = new AlertDialog.Builder(ListClient.this).create();
//                alertDialog.setTitle("Attention");
//                alertDialog.setMessage("Données Erronées"+login);
//                alertDialog.setIcon(R.drawable.warning);
//                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                    }
//                });
//                alertDialog.show();
//            }
//        };
//        client = new OdooClient.Builder(this)
//                .setHost(url)
//                .setErrorListener(new OdooErrorListener() {
//                    @Override
//                    public void onError(OdooErrorException error) {
//                        Toast.makeText(ListClient.this,"error ligne 81"+error,Toast.LENGTH_LONG).show();
//                    }
//                })
//                .setConnectListener(new OdooConnectListener() {
//                    @Override
//                    public void onConnected(OdooVersion version) {
//                        Toast.makeText(ListClient.this,"yes"+version.server_version,Toast.LENGTH_LONG).show();
////                      client.authenticate(login.getText().toString(),mdp.getText().toString(), db, loginCallback);
//                        client.authenticate(login, mdp, bd, loginCallback);
//                        Toast.makeText(ListClient.this,"ligne 92 ",Toast.LENGTH_LONG).show();
//                        Log.i("ligne 94 :",client.toString());
//                        Log.i("ligne 95 :",(login+ mdp+ bd));
//                    }
//                }).build();
//
//    }

    }
