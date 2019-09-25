package com.example.mobilesale;

public class CompraAuxiliar {
    private int id_user;
    private String No_modelo;
    private double monto;
    private String fecha;
    private String ruta_imagen;
    final public String rutaWeb="http://mobilesale.tech/img_ropa/";
    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNo_modelo() {
        return No_modelo;
    }

    public void setNo_modelo(String no_modelo) {
        No_modelo = no_modelo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getRuta() {
        return ruta_imagen;
    }

    public void setRuta(String ruta) {
        this.ruta_imagen = ruta;
    }
    public String  obtenerRuta(){
        return rutaWeb +ruta_imagen;
    }
}
