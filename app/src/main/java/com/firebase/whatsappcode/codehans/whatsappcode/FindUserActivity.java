package com.firebase.whatsappcode.codehans.whatsappcode;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class FindUserActivity extends AppCompatActivity {

    private RecyclerView rv_UserList;
    // TODO como vemos creamos un Recycler.Adapter que parece no ser del tipo
    // TODO UserListAdpater, es en el metodo inicializarRecyclerVIew donde lo instanciamos como tal
    private RecyclerView.Adapter rv_UserList_Adapter;
    private RecyclerView.LayoutManager rv_UserList_LayoutManager;

    ArrayList<UserObject> contactosList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_user);

        // rv_UserList = (findViewById(R.id.Rv_UserList));

        // TODO inicializando el array del tipo UserObject
        contactosList = new ArrayList<>();

        // TODO Metodo para inicializar el RecyclerView
        inicializarRecyclerVIew();
        obtenerListaDeContactos();
    }

    // TODO metodo que nos permite obtener los contactos para mostrarlos en el RecyclerView
    private void obtenerListaDeContactos() {
        // TODO como vemos los datos de los contactos se pueden recorrer y obtener con un cursor
        Cursor phonesDatos = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phonesDatos.moveToNext()) {
            // TODO separando cada dato de phonesData para poder insertarlo en la lista
            String nombreContact = phonesDatos.getString(phonesDatos.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phoneContact = phonesDatos.getString(phonesDatos.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            // TODO agregando a la lista cada nombre y telephone obtenido del cursor
            UserObject contacto = new UserObject(nombreContact, phoneContact);
            contactosList.add(contacto);
            // TODO por ahora notificamos al adaptador dentro del while que se
            // TODO agregaron objetos al array contactosList por consiguiente se agregaron items
            // TODO al recyclerView
            // TODO aunque podria darse dicha actualziacion fuera de este
            rv_UserList_Adapter.notifyDataSetChanged();
        }
        //rv_UserList_Adapter.notifyDataSetChanged();
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
        rv_UserList_Adapter = new UserListAdapter(contactosList);
        rv_UserList.setAdapter(rv_UserList_Adapter);

    }
}
