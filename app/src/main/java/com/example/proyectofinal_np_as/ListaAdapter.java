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

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolder> {

    private List<Obra> listaObras;
    private OnItemClickListener onItemClickListener;

    public void filterList(List<Obra> filteredList) {
        listaObras = filteredList;
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(Obra obra);
    }

    public ListaAdapter(List<Obra> listaObras) {
        this.listaObras = listaObras;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv_obras, parent, false);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Obra obra = listaObras.get(position);
        holder.imgObra.setImageResource(obra.getImagen());
        holder.nombreObra.setText(obra.getName().toUpperCase());

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(obra);
            }
        });
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
