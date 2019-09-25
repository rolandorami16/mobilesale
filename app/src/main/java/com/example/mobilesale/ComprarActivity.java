package com.example.mobilesale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ComprarActivity extends AppCompatActivity {
    private String tipo;
    private TextView tipo_recojo;
    private EditText edt;
    private TextView price;
    private EditText q;
    private int cantidad;
    private String codP,ruta;
    private double monto;
    private ImageView imagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comprar);
        Bundle parametros=getIntent().getExtras();
        if(parametros!=null){

            imagen=findViewById(R.id.imageView9);
            price=findViewById(R.id.txvprecio_p);
            tipo=parametros.getString("tipo");
            tipo_recojo=findViewById(R.id.txvtipo);
            tipo_recojo.setText(tipo);
            price.setText(parametros.getString("precio"));
            q=findViewById(R.id.edtcantidad);
            q.setText(parametros.getString("cantidad"));
            codP=parametros.getString("codp");
            Picasso.with(getBaseContext()).load(parametros.getString("rutaimg")).into(imagen);
            ruta=parametros.getString("rutaimg");
        }
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.principal2, menu);
        return true;
    }
    public void clickentrega(View view){
        Log.i("=0>","click en boton entrega");
        Intent intent=new Intent(this,EntregaActivity.class);

        startActivity(intent);
    }
    public void clickpago(View view){
        Log.i("==>","click en pago");

        Intent intent=new Intent(this,PagoActivity.class);
        edt=findViewById(R.id.edtcantidad);
        price=findViewById(R.id.txvprecio_p);
        monto=Double.parseDouble(price.getText().toString())*Double.parseDouble(edt.getText().toString());

        intent.putExtra("cantidad",edt.getText().toString());
        intent.putExtra("codigo_p",codP);
        intent.putExtra("monto_t",monto);
        intent.putExtra("url",ruta);
        System.out.println(codP +" " +monto +" ");
        startActivity(intent);
    }

}
