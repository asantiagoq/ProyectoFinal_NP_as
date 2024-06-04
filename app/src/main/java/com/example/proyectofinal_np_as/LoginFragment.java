package com.example.proyectofinal_np_as;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class LoginFragment extends Fragment {

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

            if (correo.equals("user@example.com") && contraseña.equals("password")) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
            } else {
                Toast.makeText(getActivity(), "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });
        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtén el FragmentManager
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

                // Crea una instancia del RegisterFragment
                RegisterFragment registerFragment = new RegisterFragment();

                // Inicia una transacción de fragmentos
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                // Reemplaza el fragmento actual con el RegisterFragment
                transaction.replace(R.id.login_fragment_container, registerFragment);

                // Agrega la transacción al back stack
                transaction.addToBackStack(null);

                // Confirma la transacción
                transaction.commit();
            }
        });


        return view;
    }
}


