package com.example.proyectofinal_np_as;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class LoginActivity extends AppCompatActivity {

    private FrameLayout loginContainer;
    private FrameLayout registerContainer;
    private FragmentManager fragmentManager = null;
    private FragmentTransaction fragmentTransaction = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginContainer = findViewById(R.id.login_fragment_container);
        registerContainer = findViewById(R.id.register_fragment_container);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.login_fragment_container, new LoginFragment())
                    .commit();
        }
    }


    public void replaceFragment(Fragment fragment, boolean showRegister){
        if (showRegister) {
            loginContainer.setVisibility(View.GONE);
            registerContainer.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.register_fragment_container, fragment)
                    .commit();
        } else {
            registerContainer.setVisibility(View.GONE);
            loginContainer.setVisibility(View.VISIBLE);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.login_fragment_container, fragment)
                    .commit();
        }
    }
}
