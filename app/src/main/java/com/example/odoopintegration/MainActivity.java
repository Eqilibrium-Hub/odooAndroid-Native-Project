package com.example.odoopintegration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import oogbox.api.odoo.OdooClient;
import oogbox.api.odoo.OdooUser;
import oogbox.api.odoo.client.AuthError;
import oogbox.api.odoo.client.OdooVersion;
import oogbox.api.odoo.client.helper.OdooErrorException;
import oogbox.api.odoo.client.listeners.AuthenticateListener;
import oogbox.api.odoo.client.listeners.OdooConnectListener;
import oogbox.api.odoo.client.listeners.OdooErrorListener;

public class MainActivity extends AppCompatActivity {
    final String url = "http://192.168.0.54:8069";
    private String db ="odoo_db";
    OdooClient client;
    private Button loginBtn;
    private EditText mdp ;
    private EditText login;
    private String userName ;
    private String motDePasse ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginBtn=(Button)findViewById(R.id.buttonLogin);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeIntent();
            }
        });
        login=findViewById(R.id.psw);
        mdp=findViewById(R.id.login);
        userName = login.getText().toString();
        motDePasse = mdp.getText().toString();
    }

    public void HomeIntent(){
        final AuthenticateListener loginCallback=new AuthenticateListener() {
            @Override
            public void onLoginSuccess(OdooUser user) {
                Intent homeI=new Intent(MainActivity.this,HomeActivity.class);
                homeI.putExtra("bd",db);
                homeI.putExtra("login",userName);
                homeI.putExtra("password",motDePasse);
                homeI.putExtra("url",url);
                Toast.makeText(getApplicationContext(), "login success", Toast.LENGTH_SHORT).show();
                startActivity(homeI);
            }

            @Override
            public void onLoginFail(AuthError error) {
                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                alertDialog.setTitle("Attention");
                alertDialog.setMessage("Données Erronées");
                alertDialog.setIcon(R.drawable.warning);
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alertDialog.show();
            }
        };

        login=findViewById(R.id.psw);
        mdp=findViewById(R.id.login);
        userName = login.getText().toString();
        motDePasse = mdp.getText().toString();
        client = new OdooClient.Builder(this)
                .setHost(url)
                .setErrorListener(new OdooErrorListener() {
                    @Override
                    public void onError(OdooErrorException error) {
                        Toast.makeText(MainActivity.this,"error ligne 81"+error,Toast.LENGTH_LONG).show();
                    }
                })
                .setConnectListener(new OdooConnectListener() {
                    @Override
                    public void onConnected(OdooVersion version) {
//                        Toast.makeText(MainActivity.this,"yes"+version.server_version,Toast.LENGTH_LONG).show();
                        client.authenticate(mdp.getText().toString(), login.getText().toString(), db, loginCallback);
//                        Toast.makeText(MainActivity.this,"ligne 92 ",Toast.LENGTH_LONG).show();
//                        Log.i("ligne 94 :",client.toString());
//                        Log.i("ligne 95 :",(login.getText().toString()+ mdp.getText().toString()+ db));
                    }
                }).build();

    }
//    public OdooClient getClient(){
//        return client;
//    }

    public String getDb() {
        return db;
    }

    public String getUrl() {
        return url;
    }

    public String getUserName() {
//        Log.i("login",userName);
        return userName;
    }

    public String getMotDePasse() {
        return motDePasse;
    }
}