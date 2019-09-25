package com.example.mobilesale;

import com.google.gson.annotations.SerializedName;

public class Producto {

    final public String rutaWeb="http://mobilesale.tech/img_ropa/";
    @SerializedName("Co_Producto") private int Co_Producto;
    @SerializedName("No_marca") private String No_marca;
    @SerializedName("No_modelo") private String No_modelo;
    @SerializedName("Qt_precio") private double Qt_precio;

    @SerializedName("No_tipo") private String No_tipo;
    @SerializedName("ruta_imagen") private String ruta_imagen;
    @SerializedName("descripcion") private String descripcion;
    //private Drawable imagen;


    public String getRutaWeb() {
        return rutaWeb;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCo_Producto() {
        return Co_Producto;
    }

    public void setCo_Producto(int co_Producto) {
        Co_Producto = co_Producto;
    }

    public String getNo_marca() {
        return No_marca;
    }

    public void setNo_marca(String no_marca) {
        No_marca = no_marca;
    }

    public String getNo_modelo() {
        return No_modelo;
    }

    public void setNo_modelo(String no_modelo) {
        No_modelo = no_modelo;
    }

    public double getQt_precio() {
        return Qt_precio;
    }

    public void setQt_precio(double qt_precio) {
        Qt_precio = qt_precio;
    }

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

    public String  obtenerRuta(){
        return rutaWeb +ruta_imagen;
    }

    public Producto(int id,String marca, String modelo, double precio, String tipo, String ruta){
        Co_Producto=id;
        this.No_marca=marca;
        this.No_modelo=modelo;
        this.Qt_precio=precio;
        No_tipo=tipo;
        ruta_imagen=ruta;
    }
}
