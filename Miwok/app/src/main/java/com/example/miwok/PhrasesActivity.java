package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {
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
        words.add(new Word("minto wuksus", "Where are you going?", R.raw.phrase_where_are_you_going));
        words.add(new Word("tinnə oyaase'nə", "What is your name?", R.raw.phrase_what_is_your_name));
        words.add(new Word("oyaaset...", "My name is...", R.raw.phrase_my_name_is));
        words.add(new Word("michəksəs?", "How are you feeling?", R.raw.phrase_where_are_you_going));
        words.add(new Word("kuchi achit", "I'm feeling good.", R.raw.phrase_im_feeling_good));
        words.add(new Word("əənəs'aa?", "Are you coming?", R.raw.phrase_are_you_coming));
        words.add(new Word("həə’ əənəm", "Yes, I'm coming.", R.raw.phrase_yes_im_coming));
        words.add(new Word("əənəm", "I'm coming.", R.raw.phrase_im_coming));
        words.add(new Word("yoowutis", "Let's go.", R.raw.phrase_lets_go));
        words.add(new Word("ənni'nem", "Come here.", R.raw.phrase_come_here));

        ListView listView = (ListView) findViewById(R.id.list);
        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_phrases);
        listView.setAdapter(itemsAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                MediaPlayer mediaPlayer = MediaPlayer.create(PhrasesActivity.this, words.get(i).getmAudioResourceId());
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