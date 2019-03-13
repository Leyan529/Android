package leyan.hwma.com.testservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class ChatService extends Service {
    ChatBinder mBinder = new ChatBinder();
    public class ChatBinder extends Binder {
        public ChatService getService(){
            return ChatService.this;
        }
    }
    public ChatService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public void sendMessage(String message){
        Log.d("ChatService", "sendMessage:"+message);
    }
    public void deleteMessage(){
        Log.d("ChatService", "deleteMessage");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("ChatService", "onCreate");
    }

    @Override
    public void onDestroy() {
        Log.d("ChatService", "onDestroy");
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("ChatService", "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }
}
