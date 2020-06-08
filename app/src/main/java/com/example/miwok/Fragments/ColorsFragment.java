package com.example.miwok.Fragments;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.miwok.R;
import com.example.miwok.Model.Word;
import com.example.miwok.UI.WordAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    private MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };

    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT
                            || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        mediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        releaseMediaPlayer();
                    }
                }
            };


    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.words_list, container, false);

        // Create a list of words
        final List<Word> words = new ArrayList<>();
        words.add(new Word("weṭeṭṭi", "red",
                R.drawable.color_red, R.raw.color_red));
        words.add(new Word("chokokki", "green",
                R.drawable.color_green, R.raw.color_green));
        words.add(new Word("ṭakaakki", "brown",
                R.drawable.color_brown, R.raw.color_brown));
        words.add(new Word("ṭopoppi", "gray",
                R.drawable.color_gray, R.raw.color_gray));
        words.add(new Word("kululli", "black",
                R.drawable.color_black, R.raw.color_black));
        words.add(new Word("kelelli", "white",
                R.drawable.color_white, R.raw.color_white));
        words.add(new Word("ṭopiisә", "dusty yellow",
                R.drawable.color_dusty_yellow, R.raw.color_dusty_yellow));
        words.add(new Word("chiwiiṭә", "mustard yellow",
                R.drawable.color_mustard_yellow, R.raw.color_mustard_yellow));

        WordAdapter wordsAdapter = new WordAdapter(getActivity(), words, R.color.category_colors);
        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(wordsAdapter);

        audioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Word currentWord = words.get(position);

                audioManager.requestAudioFocus(audioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                releaseMediaPlayer();
                mediaPlayer = MediaPlayer.create(getActivity(),
                        currentWord.getAudioResourceId());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(completionListener);
            }
        });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();

        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            audioManager.abandonAudioFocus(audioFocusChangeListener);
        }
    }
}
