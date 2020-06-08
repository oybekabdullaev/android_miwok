package com.example.miwok.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miwok.Fragments.FamilyFragment;
import com.example.miwok.R;

public class FamilyActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new FamilyFragment())
                .commit();

    }

}