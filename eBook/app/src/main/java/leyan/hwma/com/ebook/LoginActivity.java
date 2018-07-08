package leyan.hwma.com.ebook;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private Button btn_login, btn_cancel;
    private EditText uid, pswd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();

        /**    getSharedPreferences("設定檔名稱",設定模式)    取得設定檔的存取權限
                 *     MODE_PRIVATE 只允許本App內存取
                 *     MODE_MULTI_PROCESS 允許多個行程同時存取這個設定檔
                 */
        //getSharedPreferences("eBook", MODE_PRIVATE);
        SharedPreferences getPref = getSharedPreferences("eBook", MODE_PRIVATE); //取得過去登入的資料檔
        uid.setText(getPref.getString("USER",""));  //並將過去的登入資訊設置到輸入框
        pswd.setText(getPref.getString("PSWD",""));
    }

    public void findViews() {
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        uid = (EditText) findViewById(R.id.uid);
        pswd = (EditText) findViewById(R.id.pswd);
    }

    public void login(View v) {
        String userId = uid.getText().toString();
        String passWord = pswd.getText().toString();
        if (userId.equals("leyan") && passWord.equals("hwmaianxun")) {
            SharedPreferences pref = getSharedPreferences("eBook",MODE_PRIVATE );
            pref.edit().putString("USER",userId)
                    .putString("PSWD",passWord)
                    .commit(); //將登入成功的資料儲存，作為下次登入用

            //Toast.makeText(LoginActivity.this, "登入成功", Toast.LENGTH_SHORT).show();
            getIntent().putExtra("LOGIN_USERID",userId);
            getIntent().putExtra("LOGIN_PASSWD",passWord);
            setResult(RESULT_OK,getIntent());
            finish();
        }else{
            new AlertDialog.Builder(this)
                    .setTitle("eBook")
                    .setMessage("登入失敗")
                    .setPositiveButton("OK",null)
                    .show();
        }
    }

    public void cancel(View v) {
        Toast.makeText(LoginActivity.this, "取消", Toast.LENGTH_SHORT).show();
    }

}
