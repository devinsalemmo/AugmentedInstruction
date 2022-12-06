package com.example.finalarapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class SettingsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_settings_page);




        //Creates Button to leave homepage
        ImageButton settingsButton = (ImageButton) findViewById((R.id.back_button));
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SettingsPage.this, TopicsPage.class));
            }
        });

        Spinner spinner_languages=findViewById(R.id.spinner_languages);

        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.spinner_languages, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner_languages.setAdapter(adapter);
    }
}