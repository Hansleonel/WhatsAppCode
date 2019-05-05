package com.firebase.whatsappcode.codehans.whatsappcode;

import android.Manifest;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
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

        // TODO cuando le demos click a este button nos dirigira a la activity FindUserActivity
        // TODO en dicha actividad obtendremos todos los contactos y los mostraremos
        btn_findUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FindUserActivity.class));

            }
        });

        // TODO Metodo para obtener permisos del telephone
        obtenerPermisos();
    }

    // TODO este metodo nos permite obtener permisos para la lectura y escritura de la lista de
    // TODO contactos del telephone
    private void obtenerPermisos() {
        // TODO esta condicion se da para verificar la version del telephone en caso sea mayor a la version
        // TODO Mashmellow se necesitaran pedir los permisos expresamente al usuario
        // TODO Mashmellow en caso sea M or above no se necesitaran expresamente
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS, Manifest.permission.READ_CONTACTS}, 1);
        }
    }
}
