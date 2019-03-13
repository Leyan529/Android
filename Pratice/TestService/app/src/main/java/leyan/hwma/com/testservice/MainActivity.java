package leyan.hwma.com.testservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    ChatService mService;
    private final ServiceConnection serviceConnection = new ServiceConnection() {  /**綁定特定服務的連線介面*/  //採非同步回報機制
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        mService = ((ChatService.ChatBinder) service).getService();
        Log.d("ChatActivity", "CharService binded");
    }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            Log.d("ChatActivity", "CharService unbinded");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void send(View v){
        EditText ed = (EditText)findViewById(R.id.ed_message);
        String msg = ed.getText().toString();
        if (mService!=null){
            mService.sendMessage(msg);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = new Intent(this, ChatService.class);
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(serviceConnection);
    }
}
