package com.firebase.whatsappcode.codehans.whatsappcode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainPageActivity extends AppCompatActivity {

    private Button btn_lgt, btn_findUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        btn_lgt = findViewById(R.id.btn_lgt);
        btn_findUser = findViewById(R.id.btn_findUser);

        btn_lgt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO metodo Firebase para poder hacer logot desde la autentificacion
                FirebaseAuth.getInstance().signOut();
                // TODO

                // TODO creacion de Intent para volver al mainActivity
                // TODO ademas agregamos FLAGS para temrinar alguna otra actividad
                // TODO puesto que se desea que el usuario inicie todo el proceso de verificacion otra vez
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
                return;
                // TODO
            }
        });

        btn_findUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FindUserActivity.class));

            }
        });
    }
}
