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
public class NumbersFragment extends Fragment {
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;

    private MediaPlayer.OnCompletionListener completionListener =
            new MediaPlayer.OnCompletionListener() {
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

    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.words_list, container, false);

        // Create a list of words
        final List<Word> words = new ArrayList<>();
        words.add(new Word("lutti", "one",
                R.drawable.number_one, R.raw.number_one));
        words.add(new Word("otiiko", "two",
                R.drawable.number_two, R.raw.number_two));
        words.add(new Word("tolookosu", "three",
                R.drawable.number_three, R.raw.number_three));
        words.add(new Word("oyyisa", "four",
                R.drawable.number_four, R.raw.number_four));
        words.add(new Word("massokka", "five",
                R.drawable.number_five, R.raw.number_five));
        words.add(new Word("temmokka", "six",
                R.drawable.number_six, R.raw.number_six));
        words.add(new Word("kenekaku", "seven",
                R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("kawinta", "eight",
                R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("wo'e", "nine",
                R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("na'aacha", "ten",
                R.drawable.number_ten, R.raw.number_ten));

        WordAdapter wordsAdapter = new WordAdapter(getActivity(), words, R.color.category_numbers);
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
                mediaPlayer = MediaPlayer.create(getActivity(), currentWord.getAudioResourceId());
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
