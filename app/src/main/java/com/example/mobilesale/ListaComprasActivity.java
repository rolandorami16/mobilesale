package com.example.mobilesale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListaComprasActivity extends AppCompatActivity {
    private List<CompraAuxiliar> listaproducto=new ArrayList<>();
    private RecyclerView recyclerView;
    private CompraAdapter productoAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SessionManager sessionManager;
    private String id_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_compras);
        sessionManager=new SessionManager(this);
        sessionManager.checkLoggin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        id_user=user.get(SessionManager.ID);
        obtenerDatos(Integer.parseInt(id_user));

    }
    public void obtenerDatos(int id) {
        OkHttpClient client = new OkHttpClient();
        Request request =new okhttp3.Request.Builder().url("http://mobilesale.tech/ListarCompras.php?id_user=" +id).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(!response.isSuccessful()){
                    throw new IOException("Unexpected code " + response);
                }
                else
                {
                    String cadenaJson = response.body().string(); Log.i("====>", cadenaJson);
                    Gson gson = new Gson(); Type stringStringMap = new TypeToken<ArrayList<Map<String, Object>>>() { }.getType();
                    System.out.println(cadenaJson);
                    CompraAuxiliar[] p=gson.fromJson(cadenaJson,CompraAuxiliar[].class);
                    for(CompraAuxiliar prod:p){
                        listaproducto.add(prod);
                    }
                    final RecyclerView recyclerView=findViewById(R.id.recyclerview);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {

                                layoutManager = new LinearLayoutManager(getBaseContext());
                                productoAdapter = new CompraAdapter(getBaseContext(), listaproducto);
                                recyclerView.setLayoutManager(layoutManager);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setAdapter(productoAdapter);
                                productoAdapter.notifyDataSetChanged();
                                productoAdapter.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {


                                    }

                                });
                            }catch(Exception e){
                                e.printStackTrace();
                            }


                        }
                    });
                }
            }
        });

    }
}
