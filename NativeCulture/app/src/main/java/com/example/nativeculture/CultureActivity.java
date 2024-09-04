package com.example.nativeculture;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class CultureActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.release();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("Yu Garden Lantern Festival", "豫园灯会", R.drawable.culture_yuyuandenghui, R.raw.culture_yuyuandenghui));
        words.add(new Word("Festival temple fair", "金泽庙会", R.drawable.culture_miaohui, R.raw.culture_jinzemiaohui));
        words.add(new Word("Shanghai style culture", "海派文化", R.drawable.culture_haipai, R.raw.culture_haipaiwenhua));
        words.add(new Word("Shanghai Shikumen", "石库门", R.drawable.culture_shikumen, R.raw.culture_shikumen));
        words.add(new Word("Paper Cuttings Art", "剪纸艺术", R.drawable.culture_jianzhi, R.raw.culture_jianzhi));

        ListView listView = (ListView) findViewById(R.id.list);
        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_culture);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(CultureActivity.this, words.get(i).getmAudioResourceId());
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