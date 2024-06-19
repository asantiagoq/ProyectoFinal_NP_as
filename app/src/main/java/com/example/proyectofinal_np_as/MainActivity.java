package com.example.proyectofinal_np_as;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.proyectofinal_np_as.Fragments.HomeFragment;
import com.example.proyectofinal_np_as.Fragments.InformacionObraFragment;
import com.example.proyectofinal_np_as.Fragments.ListaObrasFragment;
import com.example.proyectofinal_np_as.Fragments.MapaGaleriaFragment;
import com.example.proyectofinal_np_as.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private FragmentManager fragmentManager = null;
    private FragmentTransaction fragmentTransaction = null;
    private MapaGaleriaFragment mapaGaleriaFragment = null;
    private ListaObrasFragment listaObrasFragment = null;
    private HomeFragment homeFragment = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_fragment_container, new LoginFragment())
                    .commit();
        }

        fragmentManager = getSupportFragmentManager();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.menu_home);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.menu_home){
                    homeFragment = HomeFragment.newInstance("","");
                    loadFragment(homeFragment);
                    return true;
                }else if (menuItem.getItemId() == R.id.menu_cuadros){
                    listaObrasFragment = ListaObrasFragment.newInstance("","");
                    loadFragment(listaObrasFragment);
                    return true;
                }else if( menuItem.getItemId() == R.id.menu_mapa){
                    mapaGaleriaFragment = MapaGaleriaFragment.newInstance("","");
                    loadFragment(mapaGaleriaFragment);
                    return true;
                }else
                return false;
            }
        });
    }
    private void loadFragment(Fragment fragment){
        if (fragmentManager != null){
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_fragment_container, fragment);
            fragmentTransaction.commit();
        }
    }
}
