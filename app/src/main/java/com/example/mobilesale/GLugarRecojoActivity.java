package com.example.mobilesale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class GLugarRecojoActivity extends AppCompatActivity {

    private Button MapLima,SaveLocal;
    private ImageButton llamar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glugar_recojo);

        MapLima = findViewById(R.id.btmLocalLima);
        SaveLocal = findViewById(R.id.btnSaveLocal);
        llamar = findViewById(R.id.contactenos);

        llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:946785963"));
                if (ActivityCompat.checkSelfPermission(GLugarRecojoActivity.this, Manifest.permission.CALL_PHONE)!=
                        PackageManager.PERMISSION_GRANTED)
                    return;
                startActivity(i);
            }
        });



        SaveLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GLugarRecojoActivity.this,ComprarActivity.class);
                startActivity(intent);
                finish();
            }
        });



        MapLima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GLugarRecojoActivity.this,GoogleMapsLimaActivity.class);
                startActivity(intent);

            }
        });

    }
}
