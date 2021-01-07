package com.example.odoopintegration;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
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

public class ListClient extends AppCompatActivity {

    OdooClient client;
    private String url;
    private String login;
    private String mdp;
    private String bd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_client);
        allClient();
    }

    public void allClient(){
        MainActivity mainActivity = new MainActivity();
        url = mainActivity.getUrl();
        login = mainActivity.getUserName();
        mdp = mainActivity.getMotDePasse();
        bd = mainActivity.getDb();
//        Log.i("host",url);
//        Log.i("login_1",login);
//        Log.i("mot de passe",mdp);
//        Log.i("bd",bd);

        final AuthenticateListener loginCallback =new AuthenticateListener() {
            @Override
            public void onLoginSuccess(OdooUser user) {
                Log.i("ligne","ligne 33");
                List<Integer> ids = Arrays.asList(user.uid);
                List<String> fields = Arrays.asList("id", "name");
                client.read("product.template", ids, fields, new IOdooResponse() {
                    @Override
                    public void onResult(OdooResult result) {
                        OdooRecord[] records = result.getRecords();
                        for(OdooRecord record: records) {
                            Log.i("Name:", record.getString("name"));
                        }
                    }
                });
            }

            @Override
            public void onLoginFail(AuthError error) {
//                Log.i("host",url);
//                Log.i("login",login);
//                Log.i("motdepasse",mdp);
//                Log.i("bd",bd);
                AlertDialog alertDialog = new AlertDialog.Builder(ListClient.this).create();
                alertDialog.setTitle("Attention");
                alertDialog.setMessage("Données Erronées"+login);
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
        client = new OdooClient.Builder(this)
                .setHost(url)
                .setErrorListener(new OdooErrorListener() {
                    @Override
                    public void onError(OdooErrorException error) {
                        Toast.makeText(ListClient.this,"error ligne 81"+error,Toast.LENGTH_LONG).show();
                    }
                })
                .setConnectListener(new OdooConnectListener() {
                    @Override
                    public void onConnected(OdooVersion version) {
                        Toast.makeText(ListClient.this,"yes"+version.server_version,Toast.LENGTH_LONG).show();
//                      client.authenticate(login.getText().toString(),mdp.getText().toString(), db, loginCallback);
                        client.authenticate(login, mdp, bd, loginCallback);
                        Toast.makeText(ListClient.this,"ligne 92 ",Toast.LENGTH_LONG).show();
                        Log.i("ligne 94 :",client.toString());
                        Log.i("ligne 95 :",(login+ mdp+ bd));
                    }
                }).build();

    }

    }
