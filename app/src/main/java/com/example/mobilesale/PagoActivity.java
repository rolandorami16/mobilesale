package com.example.mobilesale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PagoActivity extends AppCompatActivity {
    private String cantidad;
    private String precio;
    //variables para insertar
    private String CodProducto,fecha;
    private double total;
    private String id_user,rutaimg;
    private SessionManager sessionManager;
    //ruta servicio
    private static String URL_REGIST = "http://mobilesale.tech/registro_compra.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);
        sessionManager=new SessionManager(this);
        sessionManager.checkLoggin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        id_user=user.get(SessionManager.ID);
        Bundle parametros=getIntent().getExtras();

        if(parametros!=null){
            this.cantidad=parametros.getString("cantidad");
            this.precio=parametros.getString("precio");

            //asignando valores
            Date fecha_actual=new Date();
            total=parametros.getDouble("monto_t");
            CodProducto=parametros.getString("codigo_p");
            fecha=fecha_actual.toString();
            rutaimg=parametros.getString("url");
            System.out.println(CodProducto +" " +fecha +" " +cantidad +" " +total +" " +id_user);
        }
    }
    public void clickdebito(View view){
        Log.i("===>","click en boton pago debito");
        Intent inten=new Intent(this,ComprarActivity.class);
        inten.putExtra("cantidad",cantidad);
        inten.putExtra("precio",precio);
        inten.putExtra("rutaimg",rutaimg);
        InsertarCompra();
        startActivity(inten);
    }
    public void InsertarCompra(){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    if (success.equals("1")) {
                        Toast.makeText(PagoActivity.this, "Compra realizada", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PagoActivity.this,"Registro Error" + error.toString(),Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_producto",CodProducto);
                params.put("id_user",id_user);
                params.put("fecha",fecha);
                params.put("cantidad",cantidad);
                params.put("monto",String.valueOf(total));
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}
