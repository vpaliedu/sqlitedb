package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sqlite.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DataBaseCommands dbCom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.button.setOnClickListener(v->{
            dbCom = new DataBaseCommands();
            dbCom.saveData(binding.editTextText.getText().toString(),binding.editTextTextEmailAddress.getText().toString(), getApplicationContext());
            Intent intent = new Intent(this, ListActivity.class);
            startActivity(intent);
        });

    }

}