package com.example.miwok.UI;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.core.content.ContextCompat;

import com.example.miwok.R;
import com.example.miwok.Model.Word;

import java.util.List;

public class WordAdapter extends ArrayAdapter<Word> {
    private int colorResourceId;

    public WordAdapter(Activity context, List<Word> words, int colorResourceId) {
        super(context, 0, words);
        this.colorResourceId = colorResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        Word currentWord = getItem(position);

        TextView miwokTextView = listItemView.findViewById(R.id.miwok_text_view);
        miwokTextView.setText(currentWord.getMiwokTranslation());

        TextView defaultTextView = listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getDefaultTranslation());

        ImageView imageView = listItemView.findViewById(R.id.image);
        if(currentWord.hasImage()) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setImageResource(currentWord.getImageResourceId());
        } else {
            imageView.setVisibility(View.GONE);
        }

        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), colorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;
    }
}
