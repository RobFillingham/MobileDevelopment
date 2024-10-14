package com.example.reciclable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/*Tenmemos el recyclerview, lo que hace es mostrar varias opciones para seleccionar (es un seleccionador)
solo que este seleccionadro solo hace x cantidad de vistas y las recicla, en vez de crear nuevas simplemente va reemplazando la
informacion en las que ya hizo. Este adaptador que le pasamos el recyclerView es el el que administra esto.
Este adaptador necesita implementar los tres metodos del adptador para un recycler que va a usar el recycler:

-OnCreateViewHolder : Lo llama el recycler para crear cada viewHolder con algun layout, creamos un objeto viewHolder y le pasamos un layout
-OnBindViewHolder: Lo llama cuando ya creo el ViewHolder pero ahora le va a agregar infromacion (bind), entonces
                    este metodo recibe el viewHolder que ya se le asigno un layout en OnCreate y ahora lo llena con info usando
                    el numero de viewHolder creado hasta ahora para obtener un contacto del array.
-GetItemCount: Cuantos items tiene el adaptador

Tenemos una clase que es el viewHolder, con lo que llenará el recycler su interfaz, en su constructor le pasamos el layout que
usará y obtenemos de ese layout los elementos a lso que le vamos a agregar info
    -El metodo bind contacto es para personalizar el view o layout que le pasamos al viewHolder


 */


public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.ContactoViewHolder> {

    ArrayList<Contacto> datos;

    OnItemClickListener listener;
    int stars = 0;
    public interface OnItemClickListener {
        void onItemClick(Contacto item);
    }

    public ContactoAdapter(ArrayList<Contacto> datos, OnItemClickListener listener) {
        super();
        this.datos = datos;
        this.listener = listener;
    }

    private void sortContacts() {
        Collections.sort(datos, (c1, c2) -> {
            if (c1.isFavorito() && !c2.isFavorito()) return -1;
            if (!c1.isFavorito() && c2.isFavorito()) return 1;
            return c1.getNombre().compareTo(c2.getNombre()); // Sort by name within each group
        });
    }

    class ContactoViewHolder extends RecyclerView.ViewHolder{
        TextView lblApePat;
        TextView lblApeMat;
        TextView lblNombre;
        ImageButton favorites;
        ImageView image;

        public ContactoViewHolder(View itemView){
            super(itemView);
            lblNombre = itemView.findViewById(R.id.lblNombre);
            favorites = itemView.findViewById(R.id.favorites);
            favorites.setTag("image1");
            image = itemView.findViewById(R.id.image);

            //lblApePat = itemView.findViewById(R.id.lblApePat);
            //lblApeMat = itemView.findViewById(R.id.lblApeMat);
        }




        public void bindContacto(Contacto contacto, OnItemClickListener listener){
            lblNombre.setText(contacto.getNombre());
            //lblApePat.setText(contacto.getApePat());
            //lblApePat.setText(contacto.getApePat());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {listener.onItemClick(contacto);
                }
            });

            String school = contacto.getApeMat();

            if(school.equals("University of Alabama"))
                image.setImageResource(R.drawable.alabama);
            else if(school.equals("University of Georgia"))
                image.setImageResource(R.drawable.georgia);
            else if(school.equals("University of Missouri"))
                image.setImageResource(R.drawable.missouri);
            else if(school.equals("University of Tennessee"))
                image.setImageResource(R.drawable.tennessee);
            else if(school.equals("University of Arkansas"))
                image.setImageResource(R.drawable.arkansas);
            else if(school.equals("University of South Carolina"))
                image.setImageResource(R.drawable.usc);
            else if(school.equals("University of Mississippi"))
                image.setImageResource(R.drawable.olemiss);
            else if(school.equals("Auburn University"))
                image.setImageResource(R.drawable.auburn);
            else if(school.equals("Texas A&M University"))
                image.setImageResource(R.drawable.texasam);
            else if(school.equals("University of Florida"))
                image.setImageResource(R.drawable.florida);
            else if(school.equals("SEC"))
                image.setImageResource(R.drawable.sec);
            else if (school.equals("University of Kentucky"))
                image.setImageResource(R.drawable.kentucky);
            else if (school.equals("Louisiana State University"))
                image.setImageResource(R.drawable.lsu);
            else
                image.setImageResource(R.drawable.auburn);


            favorites.setImageResource(contacto.isFavorito() ? R.drawable.star2 : R.drawable.star3);

            favorites.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    if (!contacto.isFavorito()) {
                        if (stars < 3) {
                            contacto.setFavorito(true);
                            stars++;
                            Log.d("mine", "added to favorites");
                            sortContacts();
                            notifyDataSetChanged(); //it makes the adapter rebind all of the elements
                        }
                    } else {
                        contacto.setFavorito(false);
                        stars--;
                        Log.d("mine", "removed from favorites");
                        sortContacts();
                        notifyDataSetChanged();
                    }

                }
            });


        }
    }



    @Override
    public ContactoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contacto, parent, false);
        return new ContactoViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ContactoViewHolder holder, int position) {
        Contacto contacto = datos.get(position);
        holder.bindContacto(contacto, listener);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }




}
