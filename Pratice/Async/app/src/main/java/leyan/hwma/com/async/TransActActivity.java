package leyan.hwma.com.async;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.util.Log.d;


public class TransActActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trans_act);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://atm201605.appspot.com/h").build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String json = response.body().string();
                d("OKHTTP", json);

                /*parseJSON(json);
                parseJsonUseGson(json);*/
                parseJsonUseJackson(json);
            }

            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(TransActActivity.this, "告知使用者連線失敗", Toast.LENGTH_SHORT);
            }

        });
    }
    private  void setupRecyclerView(List<Transaction> list){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyler); /**取得RecyclerView元件*/
        TransactionAdapter adapter = new TransactionAdapter(list); /**取得TransactionAdapter物件*/
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void parseJSON(String s) {
        ArrayList<Transaction> trans = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(s);  //將網路上抓下來的資料轉為JSONArray物件
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsob = array.getJSONObject(i); //取得第i筆JSONObjet
                String account = jsob.getString("account"); //由JsonObject的key值取得屬性
                String date = jsob.getString("date"); //由JsonObject的key值取得屬性
                int amount = jsob.getInt("amount"); //由JsonObject的key值取得屬性
                int type = jsob.getInt("type"); //由JsonObject的key值取得屬性
                Transaction t = new Transaction(account, date, amount, type);
                trans.add(t);
                d("t", "" + t.getAccount() + "/" + t.getDate() + "/" + t.getAmount() + "/" + t.getType());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseJsonUseGson(String s) {
        Gson gson = new Gson();
      List<Transaction> list = gson.fromJson(s,
                new TypeToken<ArrayList<Transaction>>(){}.getType()); /**參數1為資料源   參數2為轉出的資料格式*/
        for (Transaction t : list) {
            d("t", "" + t.getAccount() + "/" + t.getDate() + "/" + t.getAmount() + "/" + t.getType());
        }
    }

    private void parseJsonUseJackson(String s){
        ObjectMapper objm = new ObjectMapper();
        try {
            final ArrayList<Transaction> list = objm.readValue(s, new TypeReference<List<Transaction>>() {});
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    setupRecyclerView(list);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
