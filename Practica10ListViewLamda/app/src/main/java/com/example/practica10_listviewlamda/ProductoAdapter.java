package com.example.practica10_listviewlamda;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductoAdapter extends ArrayAdapter {

    Activity context;
    Producto []datos;

    ProductoAdapter(Activity context, Producto []datos){
        super(context, R.layout.producto_layout, datos);
        this.context = context;
        this.datos = datos;
    }

    public View getView(int position, View convertView, ViewGroup parent){
      View item = convertView;
      if(item == null){
          LayoutInflater inflater = context.getLayoutInflater();
          item = inflater.inflate(R.layout.producto_layout, null);
      }
      TextView txtNombre = item.findViewById(R.id.nombre);
      txtNombre.setText(datos[position].getNombre());
      TextView lblSubtitulo = item.findViewById(R.id.precio);
      lblSubtitulo.setText(datos[position].getPrecio()+"");
      ImageView image = item.findViewById(R.id.imagen);
      switch(position){
          case 0:
                image.setImageResource(R.drawable.auburn);
              break;
          case 1:
              image.setImageResource(R.drawable.alabama);
              break;
          case 2:
              image.setImageResource(R.drawable.georgia);
              break;
          case 3:
              image.setImageResource(R.drawable.lsu);
              break;
          case 4:
              image.setImageResource(R.drawable.florida);
              break;
          case 5:
              image.setImageResource(R.drawable.tennessee);
              break;
      }

      return (item);
    };

}
