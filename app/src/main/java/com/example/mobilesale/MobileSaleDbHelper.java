package com.example.mobilesale;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MobileSaleDbHelper extends SQLiteOpenHelper {
    public static final int DB_VERSION=2;
    public static final String  DB_NAME="mobilesale.db";
    public MobileSaleDbHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sql){
        sql.execSQL("CREATE TABLE IF NOT EXISTS " + ProductoContract.ProductoEntry.TABLE_NAME +"("
                +ProductoContract.ProductoEntry._ID  +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +ProductoContract.ProductoEntry.COLUMN_NAME_MARCA +" TEXT,"
                + ProductoContract.ProductoEntry.COLUMN_NAME_MODELO +" TEXT,"
                + ProductoContract.ProductoEntry.COLUMN_NAME_PRECIO +" TEXT,"
                + ProductoContract.ProductoEntry.COLUMN_NAME_IMAGEN +" INTEGER,"
                + ProductoContract.ProductoEntry.COLUMN_NAME_TIPO +" TEXT)");

        sql.execSQL("CREATE TABLE IF NOT EXISTS " + CompraContact.CompraEntry.TABLE_NAME +"("
                +CompraContact.CompraEntry._ID  +" INTEGER PRIMARY KEY AUTOINCREMENT,"
                +CompraContact.CompraEntry.COLUMN_NAME_ID_PRODUCTO +" INTEGER,"
                + CompraContact.CompraEntry.COLUMN_NAME_FECHA +" TEXT,"
                + CompraContact.CompraEntry.COLUMN_NAME_CANTIDAD +" INTEGER,"
                + CompraContact.CompraEntry.COLUMN_NAME_MONTO +" REAL)");

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase,int oldversion,int newversion){
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ProductoContract.ProductoEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + CompraContact.CompraEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
