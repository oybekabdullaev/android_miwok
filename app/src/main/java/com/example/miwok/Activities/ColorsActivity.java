package com.example.miwok.Activities;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miwok.Fragments.ColorsFragment;
import com.example.miwok.Fragments.NumbersFragment;
import com.example.miwok.R;

import java.util.ArrayList;
import java.util.List;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new ColorsFragment())
                .commit();

    }

}