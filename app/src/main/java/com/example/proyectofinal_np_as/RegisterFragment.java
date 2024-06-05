package com.example.proyectofinal_np_as;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RegisterFragment extends Fragment {

    private EditText edtCorreoR;
    private EditText edtUsuarioR;
    private EditText edtContraseñaR;
    private EditText editTextText4;
    private CheckBox checkBox;
    private Button btnRegistrarme;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        edtCorreoR = view.findViewById(R.id.edtCorreoR);
        edtUsuarioR = view.findViewById(R.id.edtUsuarioR);
        edtContraseñaR = view.findViewById(R.id.edtContraseñaR);
        editTextText4 = view.findViewById(R.id.editTextText4);
        checkBox = view.findViewById(R.id.checkBox);
        btnRegistrarme = view.findViewById(R.id.btnRegistrarme);

        btnRegistrarme.setOnClickListener(v -> {
            String correo = edtCorreoR.getText().toString();
            String usuario = edtUsuarioR.getText().toString();
            String contraseña = edtContraseñaR.getText().toString();
            String confirmarContraseña = editTextText4.getText().toString();

            if (!contraseña.equals(confirmarContraseña)) {
                Toast.makeText(getActivity(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                return;
            }

            if (checkBox.isChecked()) {
                // Aquí puedes agregar el código para registrar al usuario en tu base de datos
                Toast.makeText(getActivity(), "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
                ((LoginActivity) getActivity()).replaceFragment(new LoginFragment(), false);
            } else {
                Toast.makeText(getActivity(), "Debe aceptar los términos y condiciones", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}