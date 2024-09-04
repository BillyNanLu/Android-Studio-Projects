package com.example.nativeculture;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ProvincialismActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Oriental Pearl TV Tower", "东方明珠电视塔"));
        words.add(new Word("the Yu Garden", "豫园"));
        words.add(new Word("Shanghai Bund", "外滩"));
        words.add(new Word("Disney", "上海迪士尼乐园"));
        words.add(new Word("Nanjing Road Walkway", "南京路步行街"));

        ListView listView = (ListView) findViewById(R.id.list);
        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_provincialism);
        listView.setAdapter(itemsAdapter);
    }
}