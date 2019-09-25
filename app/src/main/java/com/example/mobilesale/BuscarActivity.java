package com.example.mobilesale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;


import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BuscarActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Bproductos> bproductos;
    private ProductoAdapter productoAdapter;
    private ApiInterface apiInterface;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.schRecycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        fetchProducto("");
    }

    public void fetchProducto(String key){
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        retrofit2.Call<List<Bproductos>> call = apiInterface.getProductos(key);

        call.enqueue(new Callback<List<Bproductos>>() {
            @Override
            public void onResponse(Call<List<Bproductos>> call, Response<List<Bproductos>> response) {
                progressBar.setVisibility(View.GONE);
                bproductos = response.body();
                productoAdapter = new ProductoAdapter(bproductos,BuscarActivity.this);
                recyclerView.setAdapter(productoAdapter);
                productoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Bproductos>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(BuscarActivity.this,"Error on:"+t.toString(),Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );

        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                fetchProducto(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                fetchProducto(newText);
                return true;
            }
        });

        return true;
    }
}

