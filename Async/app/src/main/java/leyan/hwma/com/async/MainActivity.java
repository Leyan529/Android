package leyan.hwma.com.async;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void go1(View v) {
        new Job1Task().execute();
    }

    public void go2(View v) {
        new Job2Task().execute(3);
    }

    public void go3(View v) {
        new Job3Task().execute(10);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }

    class Job1Task extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            TextView info = (TextView) findViewById(R.id.info);
            info.setText("DONE Job1");
        }
    }

    class Job2Task extends AsyncTask<Integer, Void, Void> {
        @Override
        protected Void doInBackground(Integer... params) {
            try {
                Thread.sleep(params[0] * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            TextView info = (TextView) findViewById(R.id.info);
            info.setText("DONE Job2");
        }
    }

    class Job3Task extends AsyncTask<Integer, Integer, Void> {
        private final TextView info;
        private final EditText editTextOfSec, editTextOfMinute, editTextOfHour;
        private int totalTime;

        public Job3Task() {
            info = (TextView) findViewById(R.id.info);
            editTextOfHour = (EditText) findViewById(R.id.EditTextOfHour);
            editTextOfMinute = (EditText) findViewById(R.id.EditTextOfMinute);
            editTextOfSec = (EditText) findViewById(R.id.EditTextOfSec);
            int timeHour = Integer.parseInt(String.valueOf(editTextOfHour.getText())) * 60 * 60;
            int timeMinute = Integer.parseInt(String.valueOf(editTextOfMinute.getText())) * 60;
            int timeSec = Integer.parseInt(String.valueOf(editTextOfSec.getText()));
            totalTime = timeHour + timeMinute + timeSec;
        }

        @Override
        protected Void doInBackground(Integer... params) {
            for (int i = totalTime; i > 0; i--) {
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            info.setText(String.valueOf(values[0]));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            info.setText("DONE Job3");
        }
    }
}
