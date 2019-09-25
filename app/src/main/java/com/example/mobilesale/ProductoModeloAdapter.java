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

public class ProductoModeloAdapter extends RecyclerView.Adapter<ProductoModeloAdapter.MyViewHolder>  implements View.OnClickListener{
    private List<Producto> lista;
    private View.OnClickListener listener;
    private Context context;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public ImageView imagen;
        public TextView marca,modelo,precio;
        public MyViewHolder(View view){
            super(view);
            imagen=view.findViewById(R.id.imvproducto);
            marca=view.findViewById(R.id.txvmarca);
            modelo=view.findViewById(R.id.txvmodelo);
            precio=view.findViewById(R.id.txvprecio2);
        }
    }
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }
    public ProductoModeloAdapter(Context context,List<Producto> productos){
        this.lista=productos;
        this.context=context;
    }
    @Override
    public ProductoModeloAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.fila_producto,parent,false);

        itemview.setOnClickListener(this);
        return new MyViewHolder(itemview);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder,int position){
        Producto producto=lista.get(position);
        ImageView img=holder.imagen;
        Picasso.with(context).load(producto.obtenerRuta()).into(img);

        //holder.imagen.setImageDrawable(producto.getImagen());
        holder.marca.setText(producto.getNo_marca());
        holder.modelo.setText(producto.getNo_modelo());
        holder.precio.setText(String.valueOf(producto.getQt_precio()));
    }
    @Override
    public int getItemCount(){
        return lista.size();
    }
}
