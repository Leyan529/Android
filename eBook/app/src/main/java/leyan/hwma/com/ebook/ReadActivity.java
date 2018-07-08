package leyan.hwma.com.ebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ReadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read);
        Bundle bag = getIntent().getExtras();
        TextView chapter =(TextView)findViewById(R.id.chapter);
        chapter.setText(bag.getString("chapter"));
        TextView storyName =(TextView)findViewById(R.id.storyName);
        storyName.setText(bag.getString("Story Name"));

        Button b_back = (Button)findViewById(R.id.b_back);
        b_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
