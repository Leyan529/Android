package leyan.hwma.com.ebook;

import android.util.Log;

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

import static android.util.Log.d;

public class JSONParser {
    String data;

    public JSONParser(String data) {
        this.data = data;
    }

    private void parseJSON(String s) {
        ArrayList<Role> trans = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(s);  //將網路上抓下來的資料轉為JSONArray物件
            for (int i = 0; i < array.length(); i++) {
                JSONObject jsob = array.getJSONObject(i); //取得第i筆JSONObjet
                String account = jsob.getString("account"); //由JsonObject的key值取得屬性
                String date = jsob.getString("date"); //由JsonObject的key值取得屬性
                int amount = jsob.getInt("amount"); //由JsonObject的key值取得屬性
                int type = jsob.getInt("type"); //由JsonObject的key值取得屬性
                Role t = new Role(account, date, amount, type);
                trans.add(t);
                Log.d("t", "" + t.getAccount() + "/" + t.getDate() + "/" + t.getAmount() + "/" + t.getType());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseJsonUseGson(String s) {
        Gson gson = new Gson();
        List<Role> list = gson.fromJson(s,
                new TypeToken<ArrayList<Role>>() {
                }.getType()); /**參數1為資料源   參數2為轉出的資料格式*/
        for (Role t : list) {
            d("t", "" + t.getAccount() + "/" + t.getDate() + "/" + t.getAmount() + "/" + t.getType());
        }
    }

    public ArrayList<Role> parseJsonUseJackson() {
        ObjectMapper objm = new ObjectMapper();
        ArrayList<Role> list = null;
        try {
            list = objm.readValue(data, new TypeReference<List<Role>>() {
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


}
