package com.example.proyectofinal_np_as;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;



public class LoginFragment extends Fragment {



    private FragmentManager fragmentManager = null;
    private RegisterFragment registerFragment = null;
    private FragmentTransaction fragmentTransaction = null;


    private EditText edtCorreo;
    private EditText edtContraseña;
    private Button btnIngresar;
    private Button btnCrearCuenta;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        edtCorreo = view.findViewById(R.id.edtCorreo);
        edtContraseña = view.findViewById(R.id.edtContraseña);
        btnIngresar = view.findViewById(R.id.btnIngresar);
        btnCrearCuenta = view.findViewById(R.id.btnCrearCuenta);



        btnIngresar.setOnClickListener(v -> {
            String correo = edtCorreo.getText().toString();
            String contraseña = edtContraseña.getText().toString();

            if (correo.equals("admin") && contraseña.equals("admin")) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            } else {
                Toast.makeText(getActivity(), "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });


        btnCrearCuenta.setOnClickListener(v -> {
            if (getActivity() instanceof LoginActivity) {
                ((LoginActivity) getActivity()).replaceFragment(new RegisterFragment(), true);
            } else {
                Toast.makeText(getActivity(), "Error: No se puede cambiar de fragmento", Toast.LENGTH_SHORT).show();
            }
        });





        return view;
    }
}


