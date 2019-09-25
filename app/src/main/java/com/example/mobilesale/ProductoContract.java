package com.example.mobilesale;

import android.provider.BaseColumns;

public final class ProductoContract {
    private ProductoContract(){

    }
        public static class ProductoEntry implements BaseColumns{
            public static final String TABLE_NAME="PRODUCTO";
            public static final String COLUMN_NAME_MARCA="marca";
            public static final String COLUMN_NAME_MODELO="modelo";
            public static final String COLUMN_NAME_PRECIO="precio";
            public static final String COLUMN_NAME_IMAGEN="imagen";
            public static final String COLUMN_NAME_TIPO="tipo_producto";
        }
}
