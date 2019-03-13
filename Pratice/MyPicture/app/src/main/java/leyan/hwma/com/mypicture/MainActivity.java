package leyan.hwma.com.mypicture;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;

/**
 * Android 3.0以上 import Android 3.0以下 import
 */
/**Android 3.0以下 import */
/*import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;*/

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener {
    /**GestureDetector.OnGestureListener : 手勢偵測監聽者介面*/
    private static final int REQUEST_READ_STORAGE = 3;
    private SimpleCursorAdapter adapter;

    /**
     * 使用者要求讀取外部儲存資源的辨識值
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkExternalPermission();
    }

    public void checkExternalPermission() {
        int permission = ActivityCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {  //未取得權限，向使用者要求權限
            /**requestPermissions  參數1 :Context     參數2 :要求權限的字串列    參數3 : 權限請求辨識編號  (不重複的值)  */
            ActivityCompat.requestPermissions(this, new String[]{READ_EXTERNAL_STORAGE}, REQUEST_READ_STORAGE);
        } else {    //已有權限，可進行檔案存取
            //Toast.makeText(this, "已取得權限，可進行檔案存取", Toast.LENGTH_SHORT).show();
            readThumbnails();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_READ_STORAGE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readThumbnails();
                } else {
                    new AlertDialog.Builder(this)
                            .setMessage("必須允許讀取外部儲存權限才能顯示圖檔")
                            .setPositiveButton("OK", null)
                            .show();
                }
                break;
        }
    }

    private void readThumbnails() {
        GridView grid = (GridView) findViewById(R.id.grid);
        String[] from = {MediaStore.Images.Thumbnails.DATA, MediaStore.Images.Media.DISPLAY_NAME};/**from : 欲顯示欄位*/
        int[] to = new int[]{R.id.thumb_image, R.id.thumb_text}; /**to : 映射layout元件*/
        adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.thumb_item, null, from, to, 0);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(this);
        getLoaderManager().initLoader(0, null, this);
        /**Android3.0以上  使用  getLoaderManager() 取得LoaderManager 並實作android.app.LoaderManager.LoaderCallbacks<Cursor>*/
        /**Android3.0以下  使用  getSupportLoaderManager() 取得LoaderManager 並實作 android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor>*/
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        /**剛建立Loader時會自動執行此方法，完成後以非同步方式叫用onLoadFinished*/
         Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI; //儲存先前查詢的資料位置
        return new CursorLoader(this,uri,null,null,null,null);  //回傳資料讀取器元件
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        /**當資料讀取器讀取完成時呼叫*/
        adapter.swapCursor(data);   //替換adapter內的cursor物件
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,DetailActivity.class);
        intent.putExtra("position",position);
        startActivity(intent);
    }


}
