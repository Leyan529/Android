package com.example.counterhomework;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private int mcount = 0;
    private TextView tCount;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tCount = (TextView)findViewById(R.id.textCount);
        editText = (EditText)findViewById(R.id.editText);
        if (savedInstanceState != null) {
            String save_text =
                    savedInstanceState.getString("save_text");
            editText.setText(save_text);

            int save_count =
                    savedInstanceState.getInt("save_count");
            mcount = save_count;
            tCount.setText(""+mcount);
        }
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (editText.getVisibility() == View.VISIBLE) {
            outState.putString("save_text",editText.getText().toString());
            outState.putInt("save_count",mcount);
        }
    }

    public void countUp(View v ){
        mcount++;
        if (tCount != null)
            tCount.setText(""+mcount);
    }

}
