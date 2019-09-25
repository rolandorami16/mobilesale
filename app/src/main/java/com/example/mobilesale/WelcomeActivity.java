package com.example.mobilesale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;

public class WelcomeActivity extends AppCompatActivity {

    private TextView name;
    private Button btnEntendido;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        sessionManager = new SessionManager(this);
        sessionManager.checkLoggin();

        name = findViewById(R.id.nombreUsuario);
        btnEntendido = findViewById(R.id.btnEntendido);

        HashMap<String, String> user = sessionManager.getUserDetail();
        String nName = user.get(SessionManager.NAME);

        name.setText(nName);

        btnEntendido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WelcomeActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });


    }

}
