package com.example.proyectofinal_np_as.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.proyectofinal_np_as.MapaDibujo;
import com.example.proyectofinal_np_as.R;

public class MapaGaleriaFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private MapaDibujo mapaDibujo;

    public MapaGaleriaFragment() {
        // Required empty public constructor
    }

    public static MapaGaleriaFragment newInstance(String param1, String param2) {
        MapaGaleriaFragment fragment = new MapaGaleriaFragment();
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
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mapa_galeria, container, false);

        mapaDibujo = view.findViewById(R.id.dibujoMapa);
        mapaDibujo.setOnGalleryClickListener((MapaDibujo.OnGalleryClickListener) getActivity());

        return view;
    }
}
