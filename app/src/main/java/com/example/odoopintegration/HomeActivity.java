package com.example.odoopintegration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;
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

public class HomeActivity extends AppCompatActivity {
    OdooClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
//        HomeIntent();
        findViewById(R.id.listClient).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listClient=new Intent(getApplicationContext(),ListClient.class);
                startActivity(listClient);
            }
        });

        findViewById(R.id.listProduct).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent listProuit=new Intent(getApplicationContext(),ListProduit.class);
                startActivity(listProuit);
            }
        });

        findViewById(R.id.listLivraison).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
//    public void HomeIntent(){
//        final AuthenticateListener loginCallback=new AuthenticateListener() {
//            @Override
//            public void onLoginSuccess(OdooUser user) {
//                List<Integer> ids = Arrays.asList(user.uid);
//                List<String> fields = Arrays.asList("id", "name");
//                Log.i("onloginDucces:", "login succes");
//                client.read("res.users", ids, fields, new IOdooResponse() {
//                    @Override
//                    public void onResult(OdooResult result) {
//                        OdooRecord[] records = result.getRecords();
//                        Toast.makeText(HomeActivity.this," "+result.lastKey(),Toast.LENGTH_LONG).show();
//                        for(OdooRecord record: records) {
//                            Log.i("Name:", record.getString("name"));
//                            Log.i("Name:", "1");
//                        }
//                    }
//                });
//            }
//
//            @Override
//            public void onLoginFail(AuthError error) {
//                AlertDialog alertDialog = new AlertDialog.Builder(HomeActivity.this).create();
//                alertDialog.setTitle("Attention");
//                alertDialog.setMessage("Données Erronées");
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
//
//
//        client = new OdooClient.Builder(this)
//                .setHost(getIntent().getStringExtra("url"))
//                .setErrorListener(new OdooErrorListener() {
//                    @Override
//                    public void onError(OdooErrorException error) {
//                        Toast.makeText(HomeActivity.this,"error ligne 81"+error,Toast.LENGTH_LONG).show();
//                    }
//                })
//                .setConnectListener(new OdooConnectListener() {
//                    @Override
//                    public void onConnected(OdooVersion version) {
////                      Toast.makeText(HomeActivity.this,"yes"+version.server_version,Toast.LENGTH_LONG).show();
////                      client.authenticate(login.getText().toString(),mdp.getText().toString(), db, loginCallback);
//                        client.authenticate(getIntent().getStringExtra("password"), getIntent().getStringExtra("login"), getIntent().getStringExtra("bd"), loginCallback);
//                        Toast.makeText(HomeActivity.this,"ligne 92 ",Toast.LENGTH_LONG).show();
//                        Log.i("ligne 94 :",client.toString());
//                        Log.i("ligne 95 :",(getIntent().getStringExtra("password")+ getIntent().getStringExtra("login")+ getIntent().getStringExtra("bd")));
//                    }
//                }).build();
//
//    }
}