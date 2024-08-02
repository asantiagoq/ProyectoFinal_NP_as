package com.example.proyectofinal_np_as;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.proyectofinal_np_as.Fragments.MapaSalaFragment;

public class GalleryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        String galleryName = getIntent().getStringExtra("GALLERY_NAME");

        // Cargar el fragmento MapaSalaFragment con el nombre de la galería
        MapaSalaFragment mapaSalaFragment = MapaSalaFragment.newInstance(galleryName, "");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.gallery_fragment_container, mapaSalaFragment);
        fragmentTransaction.commit();
    }
}
