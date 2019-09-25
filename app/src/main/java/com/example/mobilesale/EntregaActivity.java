package com.example.mobilesale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class EntregaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrega);
    }
    public void clickEnvio(View view){
        Log.i("==>","click boton envio");
        Intent intent=new Intent(this,EnvioActivity.class);
        startActivity(intent);
    }
    public void clickrecojo(View view){
        Log.i("===>","click en boton recojo en tienda");
        Intent inten=new Intent(this,GLugarRecojoActivity.class);
        inten.putExtra("tipo","recojo en tienda");
        startActivity(inten);
    }
}
