package com.example.mobilesale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Bicicleta2Activity extends AppCompatActivity {
    private TextView precio_p;
    private TextView modelo_p;
    private TextView descripcion_p;
    private ImageView image_p;
    private String codProducto,ruta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bicicleta2);
        Bundle datos=getIntent().getExtras();
        if(datos!=null){
            modelo_p=findViewById(R.id.txvmodelo_);
            precio_p=findViewById(R.id.txvprecio_producto);
            descripcion_p=findViewById(R.id.txvdescription);
            image_p=findViewById(R.id.imageView7);
            modelo_p.setText(datos.getString("emodelo"));
            precio_p.setText(datos.getString("eprecio"));
            descripcion_p.setText(datos.getString("edescription"));
            ruta=datos.getString("eruta").toString();
            Picasso.with(getBaseContext()).load(datos.getString("eruta")).into(image_p);
            codProducto=datos.getString("ecod_p");

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.home:
                Log.i("====>", "Click en Inicio!!");
                Intent intent = new Intent(this,HomeActivity.class);
                startActivity(intent);
                return true;
            case R.id.buscar:
                Log.i("====>", "Click en Buscar!!");
                Intent buscar = new Intent(this,BuscarActivity.class);
                startActivity(buscar);
                return true;
            case R.id.compras:
                Log.i("====>", "Click en compras!!");
                Intent compras=new Intent(this,ListaComprasActivity.class);
                startActivity(compras);

                return true;
            case R.id.perfil:
                Log.i("====>", "Click en perfil!!");
                Intent perfil = new Intent(this,EditarPerfilActivity.class);
                startActivity(perfil);
                return true;
            case R.id.salir:
                Log.i("====>", "Click en salir!!");
                Intent salir = new Intent(this,MainActivity.class);
                startActivity(salir);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClick2(View view){
        Log.i("===>","Click en boton comprar");
        Intent intent=new Intent(this,ComprarActivity.class);
        intent.putExtra("precio",precio_p.getText().toString());
        intent.putExtra("codp",codProducto);
        intent.putExtra("rutaimg",ruta);
        startActivity(intent);
    }

}
