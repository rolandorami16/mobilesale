package com.example.mobilesale;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class CompraDAO {
    private MobileSaleDbHelper DbHelper;
    private Compra compra;
    public CompraDAO(Context context){
    DbHelper = new MobileSaleDbHelper(context);

    }
    public long insertarCompra(int co_producto,String fecha,int cantidad,double monto){
        Log.i("===>","insertar compra");
        SQLiteDatabase sql=DbHelper.getWritableDatabase();
        ContentValues content=new ContentValues();
        content.put(CompraContact.CompraEntry.COLUMN_NAME_ID_PRODUCTO,co_producto);
        content.put(CompraContact.CompraEntry.COLUMN_NAME_FECHA,fecha);
        content.put(CompraContact.CompraEntry.COLUMN_NAME_CANTIDAD,cantidad);
        content.put(CompraContact.CompraEntry.COLUMN_NAME_MONTO,monto);
        long newRow=sql.insert(CompraContact.CompraEntry.TABLE_NAME,null,content);
        return newRow;
    }

}
