package com.example.mobilesale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CompraAdapter extends RecyclerView.Adapter<CompraAdapter.MyViewHolder> implements View.OnClickListener {
    private List<CompraAuxiliar> lista;
    private View.OnClickListener listener;
    private Context context;
    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView modelo,precio,fecha;
        public MyViewHolder(View view){
            super(view);
            img=view.findViewById(R.id.imageid);
            modelo=view.findViewById(R.id.txvnombre);
            precio=view.findViewById(R.id.txvprecio);
            fecha=view.findViewById(R.id.txvfecha);
        }

    }
    public CompraAdapter(Context context,List<CompraAuxiliar> compras){
        this.lista=compras;
        this.context=context;
    }
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }
    @Override
    public CompraAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_layout,parent,false);
        itemview.setOnClickListener(this);
        return new CompraAdapter.MyViewHolder(itemview);
    }
    @Override
    public void onBindViewHolder(CompraAdapter.MyViewHolder holder, int position){
        CompraAuxiliar compra=lista.get(position);
        //Producto p=new Producto();
        ImageView img=holder.img;
        Picasso.with(context).load(compra.obtenerRuta()).into(img);
        holder.modelo.setText(compra.getNo_modelo());
        holder.fecha.setText(compra.getFecha());
        holder.precio.setText(String.valueOf(compra.getMonto()));
    }
    @Override
    public int getItemCount(){
        return lista.size();
    }

}

