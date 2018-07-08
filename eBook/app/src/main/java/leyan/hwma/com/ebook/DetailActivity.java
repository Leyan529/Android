package leyan.hwma.com.ebook;

import android.content.CursorLoader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    /**GestureDetector.OnGestureListener : 手勢偵測監聽者介面*/

    private int position;
    private ImageView image;
    private Cursor cursor;
    private GestureDetector dector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dector = new GestureDetector(this, this);    //取得手勢監聽物件

        position = getIntent().getIntExtra("position", 0);
        image = (ImageView) findViewById(R.id.imageView);
        /**依外部圖片儲存媒體庫 及參數條件 取得 CursorLoader物件*/
        CursorLoader loader = new CursorLoader(
                this,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        cursor = loader.loadInBackground(); //以背景方式查詢，並將查到的結果存於cursor物件
        cursor.moveToPosition(position);
        imageUpdate();
    }

    private void imageUpdate() {
        String imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)); //取得原圖路徑
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        image.setImageBitmap(bitmap);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /**覆寫Activity的onTouchEvent，交由手勢監聽器的onTouchEvent去做控制*/
        return dector.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        /**在畫面中按下*/
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        /**輕觸螢幕未放開時*/
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        /**輕觸螢幕放開時*/
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        /**使用者按下後移動時*/
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        /**使用者長按螢幕時，約2秒鐘*/
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float distance = e2.getX() - e1.getX();
        if (distance > 100) {
            if (!cursor.moveToPrevious()) {   //判斷是否有前一張圖片，否則cursor指向最後一張圖
                cursor.moveToLast();
            }
            imageUpdate();
        } else if (distance < -100) {
            if (!cursor.moveToNext()) {   //判斷是否有下一張圖片，否則cursor指向第一張圖
                cursor.moveToFirst();
            }
            imageUpdate();
        }
        return false;
    }
}
