package com.example.proyectofinal_np_as.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import com.example.proyectofinal_np_as.AppDatabase;
import com.example.proyectofinal_np_as.Entyti.Obra;
import com.example.proyectofinal_np_as.ListaAdapter;
import com.example.proyectofinal_np_as.R;
import com.example.proyectofinal_np_as.databinding.FragmentListaObrasBinding;

import java.util.ArrayList;
import java.util.List;

public class ListaObrasFragment extends Fragment {

    private FragmentListaObrasBinding binding;
    private List<Obra> bd; // Lista completa de obras
    private List<Obra> listaObras; // Lista mostrada en el RecyclerView
    private ListaAdapter listAdapter;
    private AppDatabase db;

    public ListaObrasFragment() {
        // Required empty public constructor
    }

    public static ListaObrasFragment newInstance(String param1, String param2) {
        ListaObrasFragment fragment = new ListaObrasFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inicializar la base de datos
        db = Room.databaseBuilder(requireContext().getApplicationContext(),
                AppDatabase.class, "galeria-db").allowMainThreadQueries().build();

        bd = new ArrayList<>();
        listaObras = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentListaObrasBinding.inflate(inflater, container, false);
        setupRecyclerView();
        setupSearchView();
        addObras(); // Mueve esta llamada aquí
        return binding.getRoot();
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.rvObras.setLayoutManager(linearLayoutManager);
        listAdapter = new ListaAdapter(listaObras);
        binding.rvObras.setAdapter(listAdapter);

        listAdapter.setOnItemClickListener(obra -> {
            InformacionObraFragment fragment = InformacionObraFragment.newInstance(obra);
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.main_fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });

    }

    private void setupSearchView() {
        SearchView searchView = binding.txtBuscar;
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
    }

    @SuppressLint("NotifyDataSetChanged")
    private void filter(String text) {
        List<Obra> filteredList = new ArrayList<>();
        if (text == null || text.isEmpty()) {
            filteredList.addAll(bd); // Si no hay texto, mostrar todas las obras
        } else {
            for (Obra item : bd) {
                if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }
        listaObras.clear();
        listaObras.addAll(filteredList);
        listAdapter.notifyDataSetChanged(); //Notifica al adaptador que los datos han cambiado
    }

    @SuppressLint("NotifyDataSetChanged")
    private void addObras() {
        new Thread(() -> {
            List<Obra> obras = db.obraDao().getAll(); // Obtén todas las obras desde la base de datos

            if (getActivity() != null) {
                getActivity().runOnUiThread(() -> {
                    bd.clear();
                    bd.addAll(obras);
                    filter(""); // Actualiza la lista visible después de cargar todas las obras
                });
            } else {
                Log.e("ListaObrasFragment", "Activity is null");
            }
        }).start();
    }

}
