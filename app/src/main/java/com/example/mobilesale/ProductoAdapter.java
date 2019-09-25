package com.example.mobilesale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.MyViewHolder> {
    private List<Bproductos> lista;
    private Context context;

    public ProductoAdapter(List<Bproductos> Bproductos,Context context){
        this.lista = Bproductos;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_producto,parent,false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Bproductos bproductos = lista.get(position);

            ImageView img=holder.imagen;

            holder.marca.setText(bproductos.getMarca());
            holder.modelo.setText(bproductos.getModelo());
            holder.precio.setText(bproductos.getPrecio());
            Picasso.with(context).load(bproductos.obtenerRuta()).fit().into(img);

    }


    @Override
    public int getItemCount(){

        return lista.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imagen;
        TextView marca,modelo,precio;

        public MyViewHolder(View itemView){
            super(itemView);
            marca = itemView.findViewById(R.id.txvmarca);
            modelo = itemView.findViewById(R.id.txvmodelo);
            precio=itemView.findViewById(R.id.txvprecio2);
            imagen = itemView.findViewById(R.id.imvproducto);

         }
    }



}
