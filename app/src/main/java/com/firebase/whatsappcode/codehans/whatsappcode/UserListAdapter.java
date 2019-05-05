package com.firebase.whatsappcode.codehans.whatsappcode;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListViewHolder> {

    ArrayList<UserObject> userList;

    public UserListAdapter(ArrayList<UserObject> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // TODO como no usamos el ViewGroup parent, tenemos que establecer un layoutParams
        // TODO para la correcta visualizacion del RecyclerView
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_user, null, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(layoutParams);


        UserListViewHolder userListViewHolder = new UserListViewHolder(layoutView);
        return userListViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserListViewHolder holder, int position) {
        // TODO CON EL HOLDER PODEMOS INSERTAR LOS VALORES DE NUESTRO OBJETO
        // TODO POR CADA POSICION ITEM EN EL RECYCLERVIEW
        holder.tv_nombre.setText(userList.get(position).getName());
        holder.tv_phone.setText(userList.get(position).getPhone());
    }

    @Override
    public int getItemCount() {
        // TODO EL CONTEO DE ITEMS ES IGUAL AL TAMAÑO DE LA LISTA
        return userList.size();
    }

    public class UserListViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_nombre;
        public TextView tv_phone;

        public UserListViewHolder(View view) {
            super(view);
            tv_nombre = view.findViewById(R.id.tv_item_name);
            tv_phone = view.findViewById(R.id.tv_item_phone);
        }
    }


}
