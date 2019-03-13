package leyan.hwma.com.ebook;

import android.Manifest;
import android.app.AlertDialog;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_CONTACTS;
import static android.util.Log.d;
import static leyan.hwma.com.ebook.R.id.editOwn;
import static leyan.hwma.com.ebook.R.id.editType;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>, AdapterView.OnItemClickListener {
    private static final int REQUEST_CONTACTS = 1;
    private boolean checklog = false;
    public static final int FUNC_LOGIN = 1;
    private Spinner chapterSpin;
    private ListView list;
    private RelativeLayout aboutLayout;
    private LinearLayout abilityLayout, storyLayout;
    private CoordinatorLayout roleLayout;
    int[] icons = {R.drawable.ic_role_48dp, R.drawable.ic_about_48dp, R.drawable.ic_ability_48dp,
            R.drawable.ic_story_48dp, R.drawable.ic_exit_48dp};
    private String[] func;
    private AbilityDBHelper abilityDBHelper;
    public static ListView dbList;

    private static final int REQUEST_READ_STORAGE = 3;
    private ProgressDialog progress;
    private SimpleCursorAdapter adapter;
    private static boolean picFlag = false;

    public static RecyclerView recyclerUser;
    private FireBaseDBHelper mFireBaseDBHelper;

    private Map<String, User> userdataMap;
    public static List<Map<String, String>> userDataList,reOrderList;
    private SimpleAdapter simAadapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!checklog) {
            /**檢查登入頁面*/
            startActivityForResult(new Intent(this, LoginActivity.class), FUNC_LOGIN);
        }
//        checkDangerPermission();
        initLayout(); //Layout元件初始化
        initFunctionMode(); //選單功能設置
        storyFunc(); //故事功能設置
        abilityFunc(); //技能資料庫設置
        aboutFunc(); //關於(圖片功能設置)
        roleFunc(); //角色JSON解析

    }

    public void roleFunc() {
        final ListView listUser = (ListView) findViewById(R.id.listUser);
        userDataList = new LinkedList<>();
        reOrderList= new LinkedList<>();
        simAadapter = new SimpleAdapter(
                MainActivity.this,
                userDataList,
                android.R.layout.simple_list_item_2,
                new String[]{"id", "pswd"},
                new int[]{android.R.id.text1, android.R.id.text2});
        listUser.setAdapter(simAadapter);

        final DatabaseReference refUser = FirebaseDatabase.getInstance().getReference("User");
        refUser.addValueEventListener(new ValueEventListener() {
            /**建立RealTime DataBase監聽事件*/
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {  //當有資料新增會進行呼叫
                userDataList.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if (ds.child("password").getValue() != null){
                        User u  = new User(ds.child("account").getValue().toString(), ds.child("password").getValue().toString());
                        Map<String, String> uMap = new HashMap<>();
                        uMap.put("id", u.getAccount());
                        uMap.put("pswd", u.getPassword());
                        userDataList.add(uMap);
                    }
                }
                listUser.setAdapter(simAadapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        FloatingActionButton roleAddFab = (FloatingActionButton) findViewById(R.id.roleAddFab);
        mFireBaseDBHelper = FireBaseDBHelper.getInstance(refUser);
        roleAddFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getLayoutInflater();
                View viewOfEditUser = inflater.inflate(R.layout.user_dailog, null);
                final EditText editId = (EditText) viewOfEditUser.findViewById(R.id.userId);
                final EditText editPswd = (EditText) viewOfEditUser.findViewById(R.id.userPswd);
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("新增角色")
                        .setView(viewOfEditUser)
                        .setPositiveButton(getString(R.string.OK), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mFireBaseDBHelper.insert(userDataList.size(), editId.getText().toString(), editPswd.getText().toString());
                            }
                        })
                        .setNegativeButton(getString(R.string.cancel), null)
                        .show();
            }
        });

        listUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Map<String, String> item = ((Map<String, String>) (simAadapter.getItem(position)));
                LayoutInflater inflater = getLayoutInflater();
                final View viewOfEditUser = inflater.inflate(R.layout.user_dailog, null);
                final EditText editId = (EditText) viewOfEditUser.findViewById(R.id.userId);
                final EditText editPswd = (EditText) viewOfEditUser.findViewById(R.id.userPswd);
                final String uid = item.get("id");
                final String pswd = item.get("pswd");
                new AlertDialog.Builder(MainActivity.this)
                        .setItems(new String[]{"查看", "修改", "刪除"},
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        LayoutInflater inflater = getLayoutInflater();

                                        switch (which) {
                                            case 0:
                                                View showOfUser = inflater.inflate(R.layout.user_dailog_show, null);
                                                final TextView showId = (TextView) showOfUser.findViewById(R.id.userId);
                                                final TextView showPswd = (TextView) showOfUser.findViewById(R.id.userPswd);
                                                showId.setText(uid);
                                                showPswd.setText(pswd);
                                                new AlertDialog.Builder(MainActivity.this)
                                                        .setTitle("查看使用者")
                                                        .setView(showOfUser)
                                                        .setPositiveButton(getString(R.string.OK), null)
                                                        .show();
                                                break;
                                            case 1:
                                                editId.setText(uid);
                                                editPswd.setText(pswd);
                                                new AlertDialog.Builder(MainActivity.this)
                                                        .setTitle("修改角色")
                                                        .setView(viewOfEditUser)
                                                        .setPositiveButton(getString(R.string.OK), new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                mFireBaseDBHelper.update(position, editId.getText().toString(), editPswd.getText().toString());
                                                            }
                                                        })
                                                        .setNegativeButton(getString(R.string.cancel), null)
                                                        .show();
                                                break;
                                            case 2:
                                                new AlertDialog.Builder(MainActivity.this)
                                                        .setTitle("刪除角色")
                                                        .setMessage("確認要刪除該筆資料")
                                                        .setPositiveButton(getString(R.string.OK), new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                mFireBaseDBHelper.delete(position, editId.getText().toString(), editPswd.getText().toString());
                                                            }
                                                        })
                                                        .setNegativeButton(getString(R.string.cancel), null)
                                                        .show();
                                                break;
                                        }
                                    }
                                })
                        .show();
            }
        });

        /*
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://atm201605.appspot.com/h").build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                d("OKHTTP", json);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<Role> list = new JSONParser(json).parseJsonUseJackson();
                        UserAdapter adapter = new UserAdapter(list); *//**取得RecyclerViewAdapter物件*//*
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(MainActivity.this, "告知使用者連線失敗", Toast.LENGTH_SHORT);
            }
        });*/


    }

    public void aboutFunc() {
        if (!picFlag) checkExternalPermission();
    }

    public void checkDangerPermission() {
        /**Android 6.0以上需檢查危險權限    (READ_CONTACTS : 讀取聯絡人   WRITE_CONTACTS : 寫入聯絡人)*/
        int permission = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS);
        if (permission != PackageManager.PERMISSION_GRANTED) {  //未取得權限，向使用者要求權限
            /**requestPermissions  參數1 :Context     參數2 :要求權限的字串列    參數3 : 權限請求辨識編號  (不重複的值)  */
            ActivityCompat.requestPermissions(this, new String[]{READ_CONTACTS, WRITE_CONTACTS}, REQUEST_CONTACTS);

        } else {    //已有權限，可進行檔案存取
            Toast.makeText(this, "已取得權限，可進行檔案存取", Toast.LENGTH_SHORT).show();
            readContacts();
        }
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

    private void readThumbnails() {
        progress = new ProgressDialog(this);
        progress.setProgressStyle(0);
        progress.setMessage("圖片載入中");
        progress.setCancelable(false);
        progress.show();
        GridView grid = (GridView) findViewById(R.id.grid);
        String[] from = {MediaStore.Images.Thumbnails.DATA, MediaStore.Images.Media.DISPLAY_NAME};/**from : 欲顯示欄位*/
        int[] to = new int[]{R.id.thumb_image, R.id.thumb_text}; /**to : 映射layout元件*/
        /*String[] from = {MediaStore.Images.Thumbnails.DATA};*//**from : 欲顯示欄位*//*
        int[] to = new int[]{R.id.thumb_image}; *//**to : 映射layout元件*/
        adapter = new SimpleCursorAdapter(getBaseContext(), R.layout.thumb_item, null, from, to, 0);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(this);
        getLoaderManager().initLoader(0, null, this);
        picFlag = true;
        /**Android3.0以上  使用  getLoaderManager() 取得LoaderManager 並實作android.app.LoaderManager.LoaderCallbacks<Cursor>*/
        /**Android3.0以下  使用  getSupportLoaderManager() 取得LoaderManager 並實作 android.support.v4.app.LoaderManager.LoaderCallbacks<Cursor>*/
    }

    private void readContacts() {
        TreeMap<Integer, HashMap<String, String>> ContactMap = new TreeMap<>();
        ContentResolver reslover = getContentResolver();    /**取得ContentReslover內容查找器物件**/
        Cursor cursor = reslover.query(Contacts.CONTENT_URI, null, null, null, null, null);/**取得所有聯絡人的結果資料指標*/
        while (cursor.moveToNext()) { /**依序拜訪所有聯絡人資料*/
            int contactId = cursor.getInt(cursor.getColumnIndex(Contacts._ID));
            String contactName = cursor.getString(cursor.getColumnIndex(Contacts.DISPLAY_NAME));
            int phoneCount = cursor.getInt(cursor.getColumnIndex(Contacts.HAS_PHONE_NUMBER));
            if (phoneCount > 0) {   /**假如聯絡人電話數>0 ，依序讀取聯絡人電話號碼*/
                Cursor curPhones = getContentResolver().query(Phone.CONTENT_URI, null, Phone.CONTACT_ID + " = " + contactId, null, null);
                while (curPhones.moveToNext()) {
                    String phoneNumber = curPhones.getString(curPhones.getColumnIndex(Phone.NUMBER));
                    HashMap<String, String> tempMap = new HashMap<>();
                    tempMap.put(contactName, phoneNumber);
                    ContactMap.put(contactId, tempMap);
                    //Log.d("Record", contactId + "/" + contactName + "/"+phoneCount);
                }

            }
        }

        TestMap(ContactMap);
    }

    public void TestMap(TreeMap<Integer, HashMap<String, String>> contactMap) {
        Iterator contactIter = contactMap.entrySet().iterator();
        while (contactIter.hasNext()) {
            TreeMap.Entry entry = (TreeMap.Entry) contactIter.next();
            Integer key = (Integer) entry.getKey();
            HashMap<String, String> value = (HashMap<String, String>) entry.getValue();

            Iterator contactInnnerIter = value.entrySet().iterator();
            while (contactInnnerIter.hasNext()) {
                HashMap.Entry innerEntry = (HashMap.Entry) contactInnnerIter.next();
                String innerKey = (String) innerEntry.getKey();
                String innerValue = (String) innerEntry.getValue();
                d("Record", key + "/" + innerKey + "/" + innerValue);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        /**參數1 :已取得權限的名稱     參數2 :要求權限的字串列    參數3 : 使用者的回覆結果  */
        switch (requestCode) {
            case REQUEST_CONTACTS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "已取得權限，可進行檔案存取", Toast.LENGTH_SHORT).show();
                    readContacts();
                } else {
                    //使用者拒絕權限，顯示對話框告知
                    new android.support.v7.app.AlertDialog.Builder(this)
                            .setMessage("必須允許聯絡人權限才能顯示資料")
                            .setPositiveButton("OK", null)
                            .show();
                }
                break;
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

    public void abilityFunc() {
        dbList = (ListView) findViewById(R.id.dbList);
        abilityDBHelper = AbilityDBHelper.getInstance(this);
        abilityDBHelper.setDbList();
        dbList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final int pos = position;
                final String type = abilityDBHelper.cursor.getString(1).toString();
                final String owner = abilityDBHelper.cursor.getString(2).toString();
                final String dscr = abilityDBHelper.cursor.getString(3).toString();

                new AlertDialog.Builder(MainActivity.this)
                        .setItems(new String[]{getString(R.string.show_ability), getString(R.string.update_ability), getString(R.string.delete_ability)},
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        LayoutInflater inflater = getLayoutInflater();

                                        switch (which) {
                                            case 0:
                                                View viewOfShowAbility = inflater.inflate(R.layout.ability_dialog_show, null);
                                                final TextView showType = (TextView) viewOfShowAbility.findViewById(editType);
                                                final TextView showOwn = (TextView) viewOfShowAbility.findViewById(editOwn);
                                                final TextView showDscr = (TextView) viewOfShowAbility.findViewById(R.id.editAbility);
                                                showType.setText(type);
                                                showOwn.setText(owner);
                                                showDscr.setText(dscr);
                                                new AlertDialog.Builder(MainActivity.this)
                                                        .setTitle(getString(R.string.show_ability))
                                                        .setView(viewOfShowAbility)
                                                        .setPositiveButton(getString(R.string.OK), null)
                                                        .show();
                                                break;
                                            case 1:
                                                View viewOfEditAbility = inflater.inflate(R.layout.ability_dialog, null);
                                                final EditText editType = (EditText) viewOfEditAbility.findViewById(R.id.editType);
                                                final EditText editOwn = (EditText) viewOfEditAbility.findViewById(R.id.editOwn);
                                                final EditText editDscr = (EditText) viewOfEditAbility.findViewById(R.id.editAbility);
                                                editType.setText(type);
                                                editOwn.setText(owner);
                                                editDscr.setText(dscr);
                                                new AlertDialog.Builder(MainActivity.this)
                                                        .setTitle(R.string.edit_ability)
                                                        .setView(viewOfEditAbility)
                                                        .setPositiveButton(getString(R.string.OK), new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                abilityDBHelper.cursor.moveToPosition(pos);
                                                                abilityDBHelper.update(abilityDBHelper.cursor.getInt(0), editType.getText().toString(), editOwn.getText().toString(), editDscr.getText().toString());
                                                                abilityDBHelper.setDbList();
                                                            }
                                                        })
                                                        .setNegativeButton(getString(R.string.cancel), null)
                                                        .show();
                                                break;
                                            case 2:
                                                new AlertDialog.Builder(MainActivity.this)
                                                        .setTitle(R.string.delete_ability)
                                                        .setMessage("確認要刪除該筆資料")
                                                        .setPositiveButton(getString(R.string.OK), new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                abilityDBHelper.cursor.moveToPosition(pos);
                                                                abilityDBHelper.delete(abilityDBHelper.cursor.getInt(0));
                                                                abilityDBHelper.setDbList();
                                                            }
                                                        })
                                                        .setNegativeButton(getString(R.string.cancel), null)
                                                        .show();
                                                break;
                                        }
                                    }
                                })
                        .show();
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater inflater = getLayoutInflater();
                View viewOfEditAbility = inflater.inflate(R.layout.ability_dialog, null);
                final EditText editType = (EditText) viewOfEditAbility.findViewById(R.id.editType);
                final EditText editOwn = (EditText) viewOfEditAbility.findViewById(R.id.editOwn);
                final EditText editDscr = (EditText) viewOfEditAbility.findViewById(R.id.editAbility);
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(R.string.insert_ability)
                        .setView(viewOfEditAbility)
                        .setPositiveButton(getString(R.string.OK), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                abilityDBHelper.insert(editType.getText().toString(), editOwn.getText().toString(), editDscr.getText().toString());
                            }
                        })
                        .setNegativeButton(getString(R.string.cancel), null)
                        .show();
                //startActivity(new Intent(MainActivity.this, AddActivity.class));
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

    public void storyFunc() {
        chapterSpin = (Spinner) findViewById(R.id.chapterSpin);
        chapterSpin.setAdapter(ArrayAdapter.createFromResource(this, R.array.chapterNum, android.R.layout.simple_spinner_dropdown_item));
        chapterSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MainActivity.this, "" + chapterSpin.getSelectedItem(), Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        list.setAdapter(ArrayAdapter.createFromResource(MainActivity.this, R.array.bookCate1, android.R.layout.simple_list_item_1));
                        break;
                    case 1:
                        list.setAdapter(ArrayAdapter.createFromResource(MainActivity.this, R.array.bookCate2, android.R.layout.simple_list_item_1));
                        break;
                    case 2:
                        list.setAdapter(ArrayAdapter.createFromResource(MainActivity.this, R.array.bookCate3, android.R.layout.simple_list_item_1));
                        break;
                    case 3:
                        list.setAdapter(ArrayAdapter.createFromResource(MainActivity.this, R.array.bookCate4, android.R.layout.simple_list_item_1));
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        list = (ListView) findViewById(R.id.list);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
//                Toast.makeText(MainActivity.this, "position：" + position + "   Story Name：" + listView.getItemAtPosition(position).toString(),
//                        Toast.LENGTH_SHORT).show();
                Bundle bag = new Bundle();
                bag.putString("Story Name", listView.getItemAtPosition(position).toString());
                bag.putString("chapter", chapterSpin.getSelectedItem().toString());
                startActivity(new Intent(MainActivity.this, ReadActivity.class).putExtras(bag));

                //bag.putString("chapter",position);

            }
        });
    }

    public void initFunctionMode() {
        func = getResources().getStringArray(R.array.func);
        GridView functionView = (GridView) findViewById(R.id.functionView);
        functionView.setAdapter(new IconAdapter());
        //functionView.setAdapter(ArrayAdapter.createFromResource(this, R.array.func, android.R.layout.simple_spinner_dropdown_item));
        functionView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /**     GridView gridView = (GridView) parent;
                 Toast.makeText(MainActivity.this, "position：" + position + " 選單文字：" + gridView.getItemAtPosition(position).toString(),
                 Toast.LENGTH_SHORT).show();
                 */
                switch ((int) id) {
                    case R.drawable.ic_role_48dp:
                        aboutLayout.setVisibility(View.GONE);
                        roleLayout.setVisibility(View.VISIBLE);
                        abilityLayout.setVisibility(View.GONE);
                        storyLayout.setVisibility(View.GONE);
                        break;
                    case R.drawable.ic_about_48dp:
                        aboutLayout.setVisibility(View.VISIBLE);
                        roleLayout.setVisibility(View.GONE);
                        abilityLayout.setVisibility(View.GONE);
                        storyLayout.setVisibility(View.GONE);
                        break;
                    case R.drawable.ic_ability_48dp:
                        aboutLayout.setVisibility(View.GONE);
                        roleLayout.setVisibility(View.GONE);
                        abilityLayout.setVisibility(View.VISIBLE);
                        storyLayout.setVisibility(View.GONE);
                        break;
                    case R.drawable.ic_story_48dp:
                        aboutLayout.setVisibility(View.GONE);
                        roleLayout.setVisibility(View.GONE);
                        abilityLayout.setVisibility(View.GONE);
                        storyLayout.setVisibility(View.VISIBLE);
                        break;
                    case R.drawable.ic_exit_48dp:
                        finish();
                        break;
                }
            }
        });
    }

    public void initLayout() {
        roleLayout = (CoordinatorLayout) findViewById(R.id.RoleLayout);
        aboutLayout = (RelativeLayout) findViewById(R.id.AboutLayout);
        abilityLayout = (LinearLayout) findViewById(R.id.AbilityLayout);
        storyLayout = (LinearLayout) findViewById(R.id.StoryLayout);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //覆寫葉面跳轉認證方法
        super.onActivityResult(requestCode, resultCode, data);
        //requestCode = FUNC_LOGIN , resultCode = RESULT_OK , data = getIntent物件
        if (requestCode == FUNC_LOGIN) { //1.請求測試
            if (resultCode == RESULT_OK) {  //2.回傳結果測試
                String uid = data.getStringExtra("LOGIN_USERID");  //3.認證成功
                String pswd = data.getStringExtra("LOGIN_PASSWD");
                //Toast.makeText(this, "認證成功", Toast.LENGTH_SHORT).show();
                d("RESULT", uid + "/" + pswd);
            } else {
                finish();  //4.認證失敗
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu); //取得menu設定物件
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //選單按鈕事件
        int id = item.getItemId();
        switch (id) {
            case R.id.action_setting:
                Toast.makeText(MainActivity.this, "設定", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_help:
                Toast.makeText(MainActivity.this, "幫助", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        /**剛建立Loader時會自動執行此方法，完成後以非同步方式叫用onLoadFinished*/
        Uri uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI; //儲存先前查詢的資料位置
        return new CursorLoader(this, uri, null, null, null, null);  //回傳資料讀取器元件
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        /**當資料讀取器讀取完成時呼叫*/
        adapter.swapCursor(data);   //替換adapter內的cursor物件
        progress.dismiss();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
    }

    class IconAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            /**回傳功能項目個數*/
            return func.length;
        }

        @Override
        public Object getItem(int position) {
            /**回傳position所對應的功能文字資源*/
            return func[position];
        }

        @Override
        public long getItemId(int position) {
            /**回傳position所對應的icon資源*/
            return icons[position];
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            if (row == null) {
                row = getLayoutInflater().inflate(R.layout.item_row, null); //取得功能row的Adapter配置版面
                ImageView image = (ImageView) row.findViewById(R.id.item_image);
                image.setImageResource(icons[position]);  //取得單元icon

                TextView text = (TextView) row.findViewById(R.id.item_text);
                text.setText(func[position]);  //取得單元func_name
            }
            return row;  //回傳設置好的功能row
        }


    }
}
