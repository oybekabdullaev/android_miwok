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
public class PhrasesFragment extends Fragment {
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


    public PhrasesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.words_list, container, false);

        // Create a list of words
        final List<Word> words = new ArrayList<>();
        words.add(new Word("minto wuksus", "Where are you going?",
                R.raw.phrase_where_are_you_going));
        words.add(new Word("tinnә oyaase'nә", "What is your name?",
                R.raw.phrase_what_is_your_name));
        words.add(new Word("oyaaset...", "My name is...",
                R.raw.phrase_my_name_is));
        words.add(new Word("michәksәs?", "How are you feeling?",
                R.raw.phrase_how_are_you_feeling));
        words.add(new Word("kuchi achit", "I’m feeling good.",
                R.raw.phrase_im_feeling_good));
        words.add(new Word("әәnәs'aa?", "Are you coming?",
                R.raw.phrase_are_you_coming));
        words.add(new Word("hәә’ әәnәm", "Yes, I’m coming.",
                R.raw.phrase_yes_im_coming));
        words.add(new Word("әәnәm", "I’m coming.",
                R.raw.phrase_im_coming));
        words.add(new Word("yoowutis", "Let’s go.",
                R.raw.phrase_lets_go));
        words.add(new Word("әnni'nem", "Come here.",
                R.raw.phrase_come_here));

        WordAdapter wordsAdapter = new WordAdapter(getActivity(), words, R.color.category_phrases);
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
