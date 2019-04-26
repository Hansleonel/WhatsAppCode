package com.firebase.whatsappcode.codehans.whatsappcode;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class FindUserActivity extends AppCompatActivity {

    private RecyclerView rv_UserList;
    private RecyclerView.Adapter rv_UserList_Adapter;
    private RecyclerView.LayoutManager rv_UserList_LayoutManager;

    ArrayList<UserObject> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_user);

        // rv_UserList = (findViewById(R.id.Rv_UserList));

        // TODO inicializando el array del tipo UserObject
        userList = new ArrayList<>();

        // TODO Metodo para inicializar el RecyclerView
        inicializarRecyclerVIew();
    }

    private void inicializarRecyclerVIew() {
        // TODO relacionando la varible local con la variable de la interfaz del RecyclerView
        // TODO estableciendo ciertas propiedds del recyclerView
        rv_UserList = (findViewById(R.id.Rv_UserList));
        rv_UserList.setNestedScrollingEnabled(false);
        rv_UserList.setHasFixedSize(false);

        // TODO usando el layout manager para la personalizacion del Recycleview
        rv_UserList_LayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayout.VERTICAL, false);
        rv_UserList.setLayoutManager(rv_UserList_LayoutManager);

        // TODO instanciando el adaptador del RecyclerView al cual le enviamos el listado en forma de array
        // TODO ademas de agregandole el adaptador al mismo RecyclerView
        rv_UserList_Adapter = new UserListAdapter(userList);
        rv_UserList.setAdapter(rv_UserList_Adapter);

    }
}
