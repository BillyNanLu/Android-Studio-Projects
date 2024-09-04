package com.example.nativeculture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView self = (TextView) findViewById(R.id.self);
        self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SelfActivity.class);
                startActivity(intent);
            }
        });

        TextView scenic = (TextView) findViewById(R.id.scenic);
        scenic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ScenicActivity.class);
                startActivity(intent);
            }
        });

        TextView food = (TextView) findViewById(R.id.food);
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FoodActivity.class);
                startActivity(intent);
            }
        });

//        TextView provincialism = (TextView) findViewById(R.id.provincialism);
//        provincialism.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(MainActivity.this, ProvincialismActivity.class);
//                startActivity(intent);
//            }
//        });

        TextView culture = (TextView) findViewById(R.id.culture);
        culture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CultureActivity.class);
                startActivity(intent);
            }
        });
    }
}