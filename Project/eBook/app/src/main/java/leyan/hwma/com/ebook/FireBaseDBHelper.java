package leyan.hwma.com.ebook;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.Map;

public class FireBaseDBHelper {
    //    private static String url = "https://ebook-24547.firebaseio.com/User"; /**取得剛申請的Firebase url*/
    private DatabaseReference refUser;
    //    private ArrayAdapter<String> fireBaseAdapter;
    public static FireBaseDBHelper instance = null;

    public FireBaseDBHelper(DatabaseReference databaseReference) {
//        this.fireBaseAdapter = fireBaseAdapter;
        this.refUser = databaseReference;

    }

    public static FireBaseDBHelper getInstance(DatabaseReference databaseReference) {
        if (instance == null)
            instance = new FireBaseDBHelper(databaseReference);
        return instance;
    }
    public void insert(int position,String account,String pswd){
        refUser.child(String.valueOf(position)).child("account").setValue(account);
        refUser.child(String.valueOf(position)).child("password").setValue(pswd);
    }
    public void update(int position,String account,String pswd){
        Map<String,Object> data = new HashMap<>();
        data.put(account,pswd);
        refUser.child(String.valueOf(position)).child("account").setValue(account);
        refUser.child(String.valueOf(position)).child("password").setValue(pswd);
    }
    public void delete(int position,String account,String pswd){
        refUser.child(String.valueOf(position)).setValue(null);
    }
    public void sortKey(DataSnapshot dataSnapshot){
        //TODO
    }
}
