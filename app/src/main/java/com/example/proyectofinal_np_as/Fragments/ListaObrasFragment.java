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
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private SearchView searchView;
    private List<Obra> bd;
    private List<Obra> listaObras = new ArrayList<>();
    private ListaAdapter listAdapter;
    private String mParam1;
    private String mParam2;

    public ListaObrasFragment() {
        // Required empty public constructor
    }

    public static ListaObrasFragment newInstance(String param1, String param2) {
        ListaObrasFragment fragment = new ListaObrasFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        bd = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentListaObrasBinding.inflate(inflater, container, false);
        setupRecyclerView();
        addObras(bd); // Mueve esta llamada aquí
        setupSearchView();
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.rvObras.setLayoutManager(linearLayoutManager);
        listAdapter = new ListaAdapter(listaObras);
        binding.rvObras.setAdapter(listAdapter);

        listAdapter.setOnItemClickListener(obra -> {
            InformacionObraFragment fragment = InformacionObraFragment.newInstance("param1", "param2");
            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.main_fragment_container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        });
    }

    private void setupSearchView() {
        searchView = binding.txtBuscar;
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
        for (Obra item : bd) { // usa bd en lugar de listaObras para filtrar
            if (item.getName().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        listAdapter.filterList(filteredList);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void addObras(List<Obra> bd) {
        // Obtén una instancia de la base de datos
        AppDatabase db = Room.databaseBuilder(getContext().getApplicationContext(),
                AppDatabase.class, "galeria-db").allowMainThreadQueries().build();

        // Carga las obras en un hilo separado
        new Thread(() -> {
            List<Obra> obras = db.obraDao().getAll(); // Obtén todas las obras desde la base de datos

            getActivity().runOnUiThread(() -> {
                listaObras.clear();
                listaObras.addAll(obras);
                if (listAdapter != null) {
                    listAdapter.notifyDataSetChanged(); // Notifica al adaptador que los datos han cambiado
                } else {
                    Log.e("ListaObrasFragment", "listaAdapter es null!");
                }
            });
        }).start();
}
}