package com.example.proyectofinal_np_as.Fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proyectofinal_np_as.AudioplayService;
import com.example.proyectofinal_np_as.Entyti.Obra;
import com.example.proyectofinal_np_as.R;

public class InformacionObraFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_OBRA = "obra";

    private Obra obra;

    public InformacionObraFragment() {}

    public static InformacionObraFragment newInstance(String param1, String param2) {
        InformacionObraFragment fragment = new InformacionObraFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public static InformacionObraFragment newInstance(Obra obra) {
        InformacionObraFragment fragment = new InformacionObraFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_OBRA, obra);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            obra = (Obra) getArguments().getSerializable(ARG_OBRA);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_informacion_obra, container, false);

        TextView txtNombreObra = view.findViewById(R.id.txtNombreObra);
        ImageView imgObra = view.findViewById(R.id.imgObra);
        TextView txtAutor = view.findViewById(R.id.txtAutor);
        TextView txtDescripcionTitulo = view.findViewById(R.id.txtDescripcionTitulo);
        //TextView txtHistoriaTitulo = view.findViewById(R.id.txtHistoriaTitulo);

        if (obra != null) {
            txtNombreObra.setText(obra.getName());
            imgObra.setImageResource(obra.getImagen()); // Assumes drawable resource ID
            txtAutor.setText(getAutorNameById(obra.getAutorId())); // Implement this method to fetch author name
            txtDescripcionTitulo.setText(obra.getDescripcion());
            //txtHistoriaTitulo.setText(getHistoriaById(obra.getGaleriaId())); // Implement this method to fetch gallery history
        }

        ImageButton btnPlay = view.findViewById(R.id.btnPlay);
        ImageButton btnStop = view.findViewById(R.id.btnStop);

        btnPlay.setOnClickListener(onClickListenerPlay());
        btnStop.setOnClickListener(onClickListenerStop());

        return view;
    }

    private View.OnClickListener onClickListenerPlay() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent audioplayServiceIntent = new Intent(getActivity(), AudioplayService.class);
                audioplayServiceIntent.putExtra(AudioplayService.FILENAME, "La Mona Lisa.mp3");
                audioplayServiceIntent.putExtra(AudioplayService.COMMAND, AudioplayService.PLAY);
                getActivity().startService(audioplayServiceIntent);
            }
        };
    }

    private View.OnClickListener onClickListenerStop() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent audioplayServiceIntent = new Intent(getActivity(), AudioplayService.class);
                audioplayServiceIntent.putExtra(AudioplayService.COMMAND, AudioplayService.STOP);
                getActivity().startService(audioplayServiceIntent);
            }
        };
    }

    private String getAutorNameById(int autorId) {
        // Implement this method to fetch the author's name from the database or other source
        return "Autor Desconocido"; // Placeholder
    }

    private String getHistoriaById(int galeriaId) {
        // Implement this method to fetch the gallery's history from the database or other source
        return "Historia Desconocida"; // Placeholder
    }
}
