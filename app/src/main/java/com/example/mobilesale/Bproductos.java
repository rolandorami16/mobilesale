package com.example.mobilesale;

import com.google.gson.annotations.SerializedName;

public class Bproductos {
    final public String rutaWeb="http://mobilesale.tech/img_ropa/";
    @SerializedName("id") private int id;
    @SerializedName("marca") private String marca;
    @SerializedName("modelo") private String modelo;
    @SerializedName("precio") private String precio;
    @SerializedName("No_tipo") private String No_tipo;
    @SerializedName("ruta_imagen") private String ruta_imagen;

    public String getNo_tipo() {
        return No_tipo;
    }
    public void setNo_tipo(String no_tipo) {
        No_tipo = no_tipo;
    }


    public String getRuta_imagen() {
        return ruta_imagen;
    }

    public void setRuta_imagen(String ruta_imagen) {
        this.ruta_imagen = ruta_imagen;
    }


    private int imagen;
    private String tp_producto;
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getMarca() {
        return marca;
    }

    public void setMarca(String nombre) {
        this.marca = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getTp_producto() {
        return tp_producto;
    }

    public void setTp_producto(String tp_producto) {
        this.tp_producto = tp_producto;
    }

    public String  obtenerRuta(){
        return rutaWeb +ruta_imagen;
    }

    public Bproductos(int id, String marca, String modelo, String precio, String tipo, String ruta){
        id = id;
        this.marca=marca;
        this.modelo=modelo;
        this.precio=precio;
        tp_producto=tipo;
        No_tipo=tipo;
        ruta_imagen=ruta;
    }
}
