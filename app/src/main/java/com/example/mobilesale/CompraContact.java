package com.example.mobilesale;

import android.provider.BaseColumns;

public final class CompraContact {
    private CompraContact(){

    }
    public static class CompraEntry implements BaseColumns{
        public static final String TABLE_NAME="compra";
        public static final String COLUMN_NAME_ID_PRODUCTO="Co_producto";
        public static final String COLUMN_NAME_FECHA="fecha_compra";
        public static final String COLUMN_NAME_CANTIDAD="cantidad";
        public static final String COLUMN_NAME_MONTO="monto_total";
    }
}
