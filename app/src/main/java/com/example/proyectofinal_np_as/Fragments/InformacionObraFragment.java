package com.example.proyectofinal_np_as.Fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.proyectofinal_np_as.AudioplayService;
import com.example.proyectofinal_np_as.R;

public class InformacionObraFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public InformacionObraFragment() {}

    public static InformacionObraFragment newInstance(String param1, String param2) {
        InformacionObraFragment fragment = new InformacionObraFragment();
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
        View view = inflater.inflate(R.layout.fragment_informacion_obra, container, false);

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
}
