package com.example.nativeculture;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ScenicActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.release();
        }
    };

    private AudioManager mAudioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Oriental Pearl TV Tower", "东方明珠电视塔", R.drawable.scenic_orientalpearltvtower, R.raw.scenic_dong));
        words.add(new Word("the Yu Garden", "豫园", R.drawable.scenic_yugarden, R.raw.scenic_yuyuan));
        words.add(new Word("Shanghai Bund", "外滩", R.drawable.scenic_waitan, R.raw.scenic_waitan));
        words.add(new Word("Disney", "上海迪士尼乐园", R.drawable.scenic_disney, R.raw.scenic_disney));
        words.add(new Word("Nanjing Road Walkway", "南京路步行街", R.drawable.scenic_nanjing, R.raw.scenic_nanjingroad));

        ListView listView = (ListView) findViewById(R.id.list);
        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_scenic);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(ScenicActivity.this, words.get(i).getmAudioResourceId());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(onCompletionListener);
            }
        });
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}