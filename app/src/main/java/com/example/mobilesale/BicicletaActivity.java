package com.example.mobilesale;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class BicicletaActivity extends AppCompatActivity {

    private Button mAlerBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bicicleta);

        mAlerBtn = (Button) findViewById(R.id.button5);
        mAlerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(BicicletaActivity.this);
                alerta.setMessage("Para comprar debe estar registrado o debe haber iniciado sesión.")
                        .setCancelable(false)
                        .setPositiveButton("Continuar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                Intent dialogIntent = new Intent(getApplicationContext(), LoginActivity.class);
                                getApplication().startActivity(dialogIntent);

                            }
                        })
                        .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog titulo = alerta.create();
                titulo.setTitle("Alerta");
                titulo.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.home:
                Log.i("====>", "Click en Inicio!!");
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.buscar:
                Log.i("====>", "Click en Buscar!!");
                Intent buscar = new Intent(this,BuscarActivity.class);
                startActivity(buscar);
                return true;
            case R.id.login:
                Log.i("====>", "Click en Login!!");
                Intent login = new Intent(this,LoginActivity.class);
                startActivity(login);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.home:
                Log.i("====>", "Click en Inicio!!");
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.buscar:
                Log.i("====>", "Click en Buscar!!");
                Intent buscar = new Intent(this,BuscarActivity.class);
                startActivity(buscar);
                return true;
            case R.id.login:
                Log.i("====>", "Click en Login!!");
                Intent login = new Intent(this,LoginActivity.class);
                startActivity(login);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClick(View view){
        Log.i("===>","Click en boton comprar");
        Intent intent=new Intent(this,ComprarActivity.class);
        startActivity(intent);
    }
    public void clickcomprar(View view){
        Log.i("==>","click boton pay");
        Intent intent=new Intent(this,ComprarActivity.class);
        startActivity(intent);
    }*/

