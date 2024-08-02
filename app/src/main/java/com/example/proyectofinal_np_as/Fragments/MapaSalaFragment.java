package com.example.proyectofinal_np_as.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectofinal_np_as.MapaSalaDibujo;
import com.example.proyectofinal_np_as.R;
import com.example.proyectofinal_np_as.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapaSalaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapaSalaFragment extends Fragment implements MapaSalaDibujo.OnCircleClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private MapaSalaDibujo mapaSalaDibujo;

    public MapaSalaFragment() {
        // Required empty public constructor
    }

    public static MapaSalaFragment newInstance(String param1, String param2) {
        MapaSalaFragment fragment = new MapaSalaFragment();
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
        View view = inflater.inflate(R.layout.fragment_mapa_sala, container, false);

        mapaSalaDibujo = view.findViewById(R.id.dibujoMapaSala);
        mapaSalaDibujo.setGalleryName(mParam1);
        mapaSalaDibujo.setOnCircleClickListener(this);

        return view;
    }

    @Override
    public void onCircleClick(int circleIndex) {
        if (getActivity() instanceof MainActivity) {
            ((MainActivity) getActivity()).showInformacionObraFragment();
        }
    }
}
