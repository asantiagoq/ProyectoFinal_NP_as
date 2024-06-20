package com.example.proyectofinal_np_as;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal_np_as.Entyti.Obra;

import java.util.List;

public class
















































































ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder> {

    List<Obra> listaObras;

    public ListaAdapter(List<Obra> listaObras){
        this.listaObras  = listaObras;
    }

    @NonNull
    @Override
    public ListaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_obras, parent,false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaAdapter.ViewHolder holder, int position) {
        Obra obra = listaObras.get(position);
        holder.imgObra.setImageResource(obra.getImagen());
        holder.nombreObra.setText(obra.getName().toUpperCase());
    }

    @Override
    public int getItemCount() {
        return listaObras.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreObra;
        ImageView imgObra;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreObra = itemView.findViewById(R.id.nombreObra);
            imgObra = itemView.findViewById(R.id.imgObra);
        }
    }
}
