package com.example.mobilesale;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoriaAdapter extends RecyclerView.Adapter<CategoriaAdapter.MyViewHolder> implements View.OnClickListener {
    private List<Categoria> lista_categoria;
    private Context context;
    private View.OnClickListener listener;

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    @Override
    public void onClick(View view) {
            if(listener!=null){
                listener.onClick(view);
            }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView categoria;
        public ImageView imagen;

        public MyViewHolder(View view){
            super(view);
            categoria=view.findViewById(R.id.txvnombre_categoria);
            imagen=view.findViewById(R.id.imvimagen_categoria);

        }
    }
    public CategoriaAdapter(Context context,List<Categoria> categorias){
        lista_categoria=categorias;
        this.context=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_categoria_producto,parent,false);
        itemview.setOnClickListener(this);
        MyViewHolder mh=new MyViewHolder(itemview);
        return mh;
    }
    @Override
    public void onBindViewHolder(CategoriaAdapter.MyViewHolder holder, int position){
        Categoria categoria=lista_categoria.get(position);
        holder.categoria.setText(categoria.getNombre());
        holder.imagen.setImageResource(categoria.getImagen());

    }

    @Override
    public int getItemCount(){
        return lista_categoria.size();
    }



}
