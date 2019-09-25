package com.example.mobilesale;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ProductoDAO {
    private MobileSaleDbHelper mdb;
    private Producto producto;
public ProductoDAO(Context context) {
    mdb = new MobileSaleDbHelper(context);
}
public long insertarProducto(String marca,String modelo,String precio,int imagen,String tipo){
    Log.i("===>","insertar producto");
    SQLiteDatabase sql=mdb.getWritableDatabase();
    ContentValues contentValues=new ContentValues();
    contentValues.put(ProductoContract.ProductoEntry.COLUMN_NAME_MARCA,marca);
    contentValues.put(ProductoContract.ProductoEntry.COLUMN_NAME_MODELO,modelo);
    contentValues.put(ProductoContract.ProductoEntry.COLUMN_NAME_PRECIO,precio);
    contentValues.put(ProductoContract.ProductoEntry.COLUMN_NAME_IMAGEN,imagen);
    contentValues.put(ProductoContract.ProductoEntry.COLUMN_NAME_TIPO,tipo);
    long newRow=sql.insert(ProductoContract.ProductoEntry.TABLE_NAME,null,contentValues);
    return newRow;
    }
}

