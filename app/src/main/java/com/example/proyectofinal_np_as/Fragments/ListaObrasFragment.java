package com.example.proyectofinal_np_as.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectofinal_np_as.Entyti.Obra;
import com.example.proyectofinal_np_as.ListaAdapter;
import com.example.proyectofinal_np_as.R;
import com.example.proyectofinal_np_as.databinding.FragmentListaObrasBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListaObrasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListaObrasFragment extends Fragment {

    private FragmentListaObrasBinding binding;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private List<Obra> bd;
    private List<Obra> listaObras = new ArrayList<Obra>();
    private ListaAdapter listAdapter = new ListaAdapter(listaObras);
    private String mParam1;
    private String mParam2;

    public ListaObrasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListaObrasFragment.
     */
    // TODO: Rename and change types and number of parameters
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

        bd = new ArrayList<Obra>(); //Esta parte se modificará cuando se agregue la Base de datos
        addObras(bd); //Esta parte se modificará cuando se agregue la Base de datos
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentListaObrasBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupRecyclerView();
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.rvObras.setLayoutManager(linearLayoutManager);
        listAdapter = new ListaAdapter(listaObras);
        binding.rvObras.setAdapter(listAdapter);
    }
    @SuppressLint("NotifyDataSetChanged")
    public void addObras(List<Obra> list){
        list.add(new Obra("Monalisa", R.drawable.imagen));
        list.add(new Obra("Pintura", R.drawable.imagen));
        list.add(new Obra("Escultura", R.drawable.imagen));
        list.add(new Obra("Sangrienta", R.drawable.imagen));
        list.add(new Obra("Básica", R.drawable.imagen));

        listaObras.addAll(list);
        listAdapter.notifyDataSetChanged();
    }
}