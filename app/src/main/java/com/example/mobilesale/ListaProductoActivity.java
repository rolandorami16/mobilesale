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
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListaProductoActivity extends AppCompatActivity {
    private List<Producto> listaproducto = new ArrayList<>();
    private RecyclerView recyclerView;
    private ProductoModeloAdapter productoModeloAdapter;
    private GridLayoutManager gridLayoutManager;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_producto);
        obtenerDatos();
    }

    public void obtenerDatos() {
        OkHttpClient client = new OkHttpClient();
        Request request = new okhttp3.Request.Builder().url("http://mobilesale.tech/ListarProducto.php?tipo_producto=ropa").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else {
                    String cadenaJson = response.body().string();
                    Log.i("====>", cadenaJson);
                    Gson gson = new Gson();
                    Type stringStringMap = new TypeToken<ArrayList<Map<String, Object>>>() {
                    }.getType();
                    System.out.println(cadenaJson);
                    Producto[] p = gson.fromJson(cadenaJson, Producto[].class);
                    for (Producto prod : p) {
                        listaproducto.add(prod);
                    }
                    final RecyclerView recyclerView = findViewById(R.id.rcvproducto);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                layoutManager = new LinearLayoutManager(getBaseContext());
                                productoModeloAdapter = new ProductoModeloAdapter(getBaseContext(), listaproducto);
                                recyclerView.setLayoutManager(layoutManager);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setAdapter(productoModeloAdapter);
                                productoModeloAdapter.notifyDataSetChanged();
                                productoModeloAdapter.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        String modelo = listaproducto.get(recyclerView.getChildAdapterPosition(view)).getNo_modelo();
                                        double precio = listaproducto.get(recyclerView.getChildAdapterPosition(view)).getQt_precio();
                                        String descripcion = listaproducto.get(recyclerView.getChildAdapterPosition(view)).getDescripcion();
                                        String ruta = listaproducto.get(recyclerView.getChildAdapterPosition(view)).obtenerRuta();
                                        int id_p = listaproducto.get(recyclerView.getChildAdapterPosition(view)).getCo_Producto();
                                        Intent intent1 = new Intent(getBaseContext(), Bicicleta2Activity.class);
                                        intent1.putExtra("emodelo", modelo);
                                        intent1.putExtra("eprecio", String.valueOf(precio));
                                        intent1.putExtra("edescription", descripcion);
                                        intent1.putExtra("eruta", ruta);
                                        intent1.putExtra("ecod_p", String.valueOf(id_p));
                                        startActivity(intent1);

                                    }

                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }


                        }
                    });
                }
            }
        });

    }
}
