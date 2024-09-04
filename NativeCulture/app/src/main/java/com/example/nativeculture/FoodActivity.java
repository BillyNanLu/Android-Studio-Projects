package com.example.nativeculture;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {
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
        words.add(new Word("Fried buns", "生煎包", R.drawable.food_shengjian, R.raw.food_sehngjian));
        words.add(new Word("Shao-Mai", "烧麦", R.drawable.food_shaomai, R.raw.food_shaomai));
        words.add(new Word("Spiced bean", "五香豆", R.drawable.food_wuxiangdou, R.raw.food_wuxiangdou));
        words.add(new Word("Youdunzi", "油墩子", R.drawable.food_youdunzi, R.raw.food_youdunzi));
        words.add(new Word("Scallion oil mixed noodles", "葱油拌面", R.drawable.food_congyoumian, R.raw.food_congyoubanmian));

        ListView listView = (ListView) findViewById(R.id.list);
        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_food);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(FoodActivity.this, words.get(i).getmAudioResourceId());
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