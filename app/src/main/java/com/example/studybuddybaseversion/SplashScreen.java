package com.example.studybuddybaseversion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    Button ContinueButton;
    EditText ET;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        sharedPreferences = getSharedPreferences("StudyBuddy", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (sharedPreferences.contains("Username")){
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        ET = findViewById(R.id.name_edit_text);
        String name = ET.getText().toString();
        editor.putString("Username", name);
        editor.apply();

        // Clicked Login button of Introductory Activity
        ContinueButton = findViewById(R.id.continue_button);
        ContinueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}