package com.example.mobilesale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    //rutina Logout
    SessionManager sessionManager;
    //

    private List<Categoria> categorias=new ArrayList<>();
    private RecyclerView recyclerView;
    private CategoriaAdapter categoriaAdapter;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //rutina Logout
        sessionManager = new SessionManager(this);
        sessionManager.checkLoggin();
        //

        recyclerView=findViewById(R.id.rcvcategoria);
        categoriaAdapter=new CategoriaAdapter(getBaseContext(),categorias);
        categoriaAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Seleccion" +categorias.get(recyclerView.getChildAdapterPosition(view)).getNombre(),Toast.LENGTH_SHORT).show();
                switch (categorias.get(recyclerView.getChildAdapterPosition(view)).getNombre()){
                    case"ROPA":
                        Log.i("===>","lista de ropas");
                        Intent intent1=new Intent(getBaseContext(),ListaProductoActivity.class);
                        startActivity(intent1);
                }
            }
        });
        gridLayoutManager=new GridLayoutManager(HomeActivity.this,1);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayout.VERTICAL));
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(categoriaAdapter);

        datos_categoria();

    }
    public void datos_categoria(){
        Categoria categoria=new Categoria("ROPA",R.drawable.ropa);
        Categoria categoria2=new Categoria("MUEBLES",R.drawable.categoria_mueble);
        categorias.add(categoria);
        categorias.add(categoria2);
        categoriaAdapter.notifyDataSetChanged();
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

            //rutina Logout
            case R.id.salir:
                Log.i("====>", "Click en salir!!");
                sessionManager.logout();
                return true;
            //
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void click(View view){
        Log.i("==>","imagen bicicleta");
        Intent intent=new Intent(this,Bicicleta2Activity.class);
        startActivity(intent);
    }
}
