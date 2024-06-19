package com.example.proyectofinal_np_as.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

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

    FragmentListaObrasBinding binding;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListaObrasBinding.inflate(getLayoutInflater());
        View vista = binding.getRoot();
        return inflater.inflate(R.layout.fragment_lista_obras, container, false);
    }

    private void setupRecyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.rvObras.setLayoutManager(linearLayoutManager);
        listAdapter = new ListaAdapter(listaObras);
        binding.rvObras.setAdapter(listAdapter);

    }
}