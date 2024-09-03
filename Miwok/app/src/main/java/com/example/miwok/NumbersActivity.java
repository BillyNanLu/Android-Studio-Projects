package com.example.miwok;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
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

        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        //        String[] words = new String[10];
        //        words[0] = "one";
        //        words[1] = "two";
        //        words[2] = "three";
        //        words[3] = "four";
        //        words[4] = "five";
        //        words[5] = "six";
        //        words[6] = "seven";
        //        words[7] = "eight";
        //        words[8] = "nine";
        //        words[9] = "ten";
        //
        //        for (int i = 0; i < words.length; i++) {
        //            Log.v("NumbersActivity", "Word at index" + i + ": " + words[i]);
        //        }


        ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("lutti", "one", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("otiiko", "two", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("tolookosu", "three", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("oyyisa", "four", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("massokka", "five", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("temmokka", "six", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("kenekaku", "seven", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("kawinta", "eight", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("wo'e", "nine", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("na'aacha", "ten", R.drawable.number_ten, R.raw.number_ten));


        //        LinearLayout rootView = (LinearLayout) findViewById(R.id.rootView);
        //        int index = 0;
        //        while (index < words.size()) {
        //            TextView textView = new TextView(this);
        //            textView.setText(words.get(index));
        //            rootView.addView(textView);
        //            index++;
        //        }

        //        for (int i = 0; i < words.size(); i++) {
        //            // Log.v("NumbersActivity", "Word at index" + i + ": " + words.get(i));
        //            TextView textView = new TextView(this);
        //            textView.setText(words.get(i));
        //            rootView.addView(textView);
        //        }


        ListView listView = (ListView) findViewById(R.id.list);
        WordAdapter itemsAdapter = new WordAdapter(this, words, R.color.category_numbers);
        listView.setAdapter(itemsAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(NumbersActivity.this, words.get(i).getmAudioResourceId());
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