package leyan.hwma.com.ebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.SimpleCursorAdapter;

import static leyan.hwma.com.ebook.MainActivity.dbList;

/**
 * 初次建立需實作方法，並建立constructor
 */

public class AbilityDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "expense.db";
    public static final String TABLE_NAME = "ability";
    private static final String FIELD_ID = "_id";
    private static final String FIELD_TYPE = "Type";
    private static final String FIELD_OWNER = "Owner";
    private static final String FIELD_DSCR = "Dscr";

    private Context context;
    public static Cursor cursor;
    private SQLiteDatabase db;
    private SimpleCursorAdapter dbAdapter;
    private static AbilityDBHelper instance = null;

    public static AbilityDBHelper getInstance(Context context) {  //單一資料庫物件取得
        if (instance == null) instance = new AbilityDBHelper(context, DATABASE_NAME, null, 1);
        return instance;
    }

    public AbilityDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        /**      參數1:  Context       參數2:  資料庫檔案名稱     參數3:  資料庫處理模式     參數4:  資料庫版本    * */
        super(context, name, factory, version);
        this.context = context;
        cursor = this.getReadableDatabase().query(TABLE_NAME, null, null, null, null, null, null);
        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /**  建立資料庫表格     */
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                FIELD_ID + " INTEGER PRIMARY KEY NOT NULL , " +
                FIELD_TYPE + " VARCHAR NOT NULL , " +
                FIELD_OWNER + " VARCHAR , " +
                FIELD_DSCR + " VARCHAR) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);// 刪除資料庫
        onCreate(db);// 重新建立資料庫
    }

    public void insert(String type, String owner, String dscr) {
        /**   新增資料      參數1:  資料表名稱       參數2:  欲插入null值的欄位名稱     參數3:  插入資料資料集合  型態 :ContentValues   */
        ContentValues insertData = new ContentValues();
        insertData.put("type", type);
        insertData.put("owner", owner);
        insertData.put("dscr", dscr);
        long testInsert = this.getWritableDatabase().insert(TABLE_NAME, null, insertData);
        Log.d("ADD", testInsert + "");
        cursor.requery();
    }

    public void delete(int id) {
        /**   刪除資料      參數1:  資料表名稱       參數2:  Where條件     參數3:  條件陣列的?值(new String[]{?,?,?,...})   */
        db.delete(TABLE_NAME, FIELD_ID + "=" + Integer.toString(id), null);
        cursor.requery();
    }

    public void update(int id, String type, String owner, String dscr) {
        /**   刪除資料      參數1:  資料表名稱      參數2: 欲修改資料集合  型態 :ContentValues    參數3:  Where條件     參數4:  條件陣列的?值(new String[]{?,?,?,...})   */
        ContentValues updateData = new ContentValues();
        updateData.put(FIELD_TYPE, type);
        updateData.put(FIELD_OWNER, owner);
        updateData.put(FIELD_DSCR, dscr);
        db.update(TABLE_NAME, updateData, FIELD_ID + "=" + Integer.toString(id), null);
        cursor.requery();
    }

    public void setDbList() {
        dbAdapter = new SimpleCursorAdapter(context, android.R.layout.simple_expandable_list_item_2, cursor, new String[]{FIELD_TYPE, FIELD_OWNER}, new int[]{android.R.id.text1, android.R.id.text2}, 1);
        dbList.setAdapter(dbAdapter);
    }


}
