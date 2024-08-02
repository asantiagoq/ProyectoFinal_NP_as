package com.example.proyectofinal_np_as;


import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import com.example.proyectofinal_np_as.Entyti.Autor;
import com.example.proyectofinal_np_as.Entyti.Galeria;
import com.example.proyectofinal_np_as.Entyti.Obra;
import com.example.proyectofinal_np_as.Fragments.HomeFragment;
import com.example.proyectofinal_np_as.Fragments.ListaObrasFragment;
import com.example.proyectofinal_np_as.Fragments.LoginFragment;
import com.example.proyectofinal_np_as.Fragments.MapaGaleriaFragment;
import com.example.proyectofinal_np_as.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private FragmentManager fragmentManager = null;
    private FragmentTransaction fragmentTransaction = null;
    private MapaGaleriaFragment mapaGaleriaFragment = null;
    private ListaObrasFragment listaObrasFragment = null;
    private HomeFragment homeFragment = null;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "galeria-db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
        populateDatabaseFromTxt(db);

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
                } else if (menuItem.getItemId() == R.id.menu_cuadros){
                    listaObrasFragment = ListaObrasFragment.newInstance("","");
                    loadFragment(listaObrasFragment);
                    return true;
                } else if( menuItem.getItemId() == R.id.menu_mapa){
                    mapaGaleriaFragment = MapaGaleriaFragment.newInstance("","");
                    loadFragment(mapaGaleriaFragment);
                    return true;
                }
                return false;
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        if (fragmentManager != null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.main_fragment_container, fragment);
            fragmentTransaction.commit();
        }
    }

    private void populateDatabaseFromTxt(AppDatabase db) {
        new Thread(() -> {
            try {
                Log.d("populateDatabaseFromTxt", "Opening Pictures.txt");
                InputStream inputStream = getAssets().open("Pictures.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    Log.d("populateDatabaseFromTxt", "Reading line: " + line);
                    String[] parts = line.split("\\|");
                    if (parts.length >= 6) {
                        int roomId = Integer.parseInt(parts[0]);
                        String autorNombre = parts[1];
                        String obraNombre = parts[2];
                        String imagenStr = parts[3];
                        String audioUrl = parts.length > 4 ? parts[4] : null;
                        String descripcion = parts[5];

                        Log.d("populateDatabaseFromTxt", "Processing obra: " + obraNombre);

                        Autor autor = db.autorDao().findByName(autorNombre);
                        if (autor == null) {
                            autor = new Autor(autorNombre);
                            long autorId = db.autorDao().insert(autor);
                            autor.setId((int) autorId);
                            Log.d("populateDatabaseFromTxt", "Inserted autor: " + autorNombre);
                        }

                        Galeria galeria = db.galeriaDao().findByName("Room " + roomId);
                        if (galeria == null) {
                            galeria = new Galeria("Room " + roomId);
                            long galeriaId = db.galeriaDao().insert(galeria);
                            galeria.setId((int) galeriaId);
                            Log.d("populateDatabaseFromTxt", "Inserted galeria: Room " + roomId);
                        }

                        // Convertir imagenStr a un recurso drawable identificable en el proyecto
                        String imagenName = imagenStr.substring(0, imagenStr.lastIndexOf('.'));
                        int imagenResId = getResources().getIdentifier(imagenName, "drawable", getPackageName());

                        if (imagenResId == 0) {
                            Log.e("populateDatabaseFromTxt", "Resource not found for image: " + imagenName);
                            continue;
                        }

                        // Crear instancia de Obra con imagenResId
                        Obra obra = new Obra(obraNombre, imagenResId, descripcion, audioUrl, autor.getId(), galeria.getId());
                        db.obraDao().insert(obra);
                        Log.d("populateDatabaseFromTxt", "Inserted obra: " + obraNombre);
                    }
                }
                reader.close();
                Log.d("populateDatabaseFromTxt", "Finished reading file.");
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("populateDatabaseFromTxt", "Error reading file: " + e.getMessage());
            }
        }).start();
    }

}
