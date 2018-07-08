package leyan.hwma.com.mypicture;

import android.content.CursorLoader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class DetailActivity extends AppCompatActivity {

    private int position;
    private ImageView image;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        position = getIntent().getIntExtra("position",0);
        image = (ImageView)findViewById(R.id.imageView);
        /**依外部圖片儲存媒體庫 及參數條件 取得 CursorLoader物件*/
        CursorLoader loader = new CursorLoader(
                this,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
        cursor = loader.loadInBackground(); //以背景方式查詢，並將查到的結果存於cursor物件
        cursor.moveToPosition(position);
        update();
    }

    private void update() {
        String imagePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)); //取得原圖路徑
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        image.setImageBitmap(bitmap);
    }
}
