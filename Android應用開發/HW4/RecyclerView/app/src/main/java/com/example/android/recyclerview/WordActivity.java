package com.example.android.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

public class WordActivity extends AppCompatActivity {
    private TextView showWord;
    private Button up;
    public static final int CHOOSE_FOOD_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word);
        showWord = (TextView)findViewById(R.id.showWord);

        Intent intent = getIntent();
        String s = intent.getStringExtra(WordListAdapter.EXTRA_MESSAGE);
        showWord.setText(s);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }
}
