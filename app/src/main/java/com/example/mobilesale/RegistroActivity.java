package com.example.mobilesale;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegistroActivity extends AppCompatActivity {
    private EditText email, pass, name,apellidos;
    private Button btnRegRegistrar, btnRegCancelar;
    private static String URL_REGIST = "http://mobilesale.tech/registro.php";
//http://mobilesale.tech/registro.php
    //private static String URL_REGIST = "https://mobilsale.000webhostapp.com/registro.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);


        email = findViewById(R.id.regEmail);
        pass = findViewById(R.id.regPassword);
        name = findViewById(R.id.regNombre);
        apellidos = findViewById(R.id.regApellidos);
        btnRegRegistrar = findViewById(R.id.btnRegRegistrar);
        btnRegCancelar = findViewById(R.id.btnRegCancelar);

        btnRegCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistroActivity.this,LoginActivity.class);
                RegistroActivity.this.startActivity(intent);
                finish();
            }
        });


        btnRegRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Regist();
            }
        });


    }

    private void Regist(){

        final String email = this.email.getText().toString().trim();
        final String pass = this.pass.getText().toString().trim();
        final String name = this.name.getText().toString().trim();
        final String apellidos = this.apellidos.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            System.out.println(success);
                            if (success.equals("1")){
                                Toast.makeText(RegistroActivity.this,"Registro Completo",Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(RegistroActivity.this,LoginActivity.class);
                                RegistroActivity.this.startActivity(intent);
                                finish();

                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(RegistroActivity.this,"Registro Error" + e.toString(),Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegistroActivity.this,"Registro conection Error" + error.toString(),Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email",email);
                params.put("pass",pass);
                params.put("name",name);
                params.put("apellidos",apellidos);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
