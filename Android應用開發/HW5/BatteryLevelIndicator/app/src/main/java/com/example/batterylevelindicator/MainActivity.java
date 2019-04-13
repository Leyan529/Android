package com.example.batterylevelindicator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private int level;
    int max = 5, min = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increaseLevel(View view) {
        level++;
        if (level > max) level = max;
        ImageView image = (ImageView) findViewById(R.id.battery);
        image.setImageResource(R.drawable.battery_level);
        image.setImageLevel(level);
    }

    public void decreaseLevel(View view) {
        level--;
        if (level < min) level = min;
        ImageView image = (ImageView) findViewById(R.id.battery);
        image.setImageResource(R.drawable.battery_level);
        image.setImageLevel(level);
    }
}
