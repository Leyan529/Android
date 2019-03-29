package com.example.android.droidcafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;


/**
 * This activity handles five checkboxes and a Show Toast button for displaying selected checkboxes
 */
public class CheckboxesActivity extends AppCompatActivity {

    private CheckBox chocolate_shrup, sprinkles, crushed_nuts, cherries, orio_cookie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkboxes);

        chocolate_shrup = (CheckBox)findViewById(R.id.box1);
        sprinkles = (CheckBox)findViewById(R.id.box2);
        crushed_nuts = (CheckBox)findViewById(R.id.box3);
        cherries = (CheckBox)findViewById(R.id.box4);
        orio_cookie = (CheckBox)findViewById(R.id.box5);



    }

    public void show_toast(View view) {
        String s = "Toppings: ";

        if(chocolate_shrup.isChecked()){
            s = s + chocolate_shrup.getText();
        }
        if(sprinkles.isChecked()){
            s = s + sprinkles.getText() ;
        }
        if(crushed_nuts.isChecked()){
            s = s + crushed_nuts.getText() ;
        }
        if(cherries.isChecked()){
            s = s + cherries.getText();
        }
        if(orio_cookie.isChecked()){
            s = s + orio_cookie.getText();
        }
        Toast.makeText(CheckboxesActivity.this, s, Toast.LENGTH_SHORT).show();

    }
}
