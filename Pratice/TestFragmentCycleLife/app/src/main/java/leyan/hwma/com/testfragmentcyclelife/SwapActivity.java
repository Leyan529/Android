package leyan.hwma.com.testfragmentcyclelife;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class SwapActivity extends AppCompatActivity {

    private MasterFragment master;
    private DetailFragment detail;
    private FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swap);
        master = MasterFragment.newInstance("a", "b");
        detail = DetailFragment.newInstance("a", "b");
        fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.swap_container, master);
        ft.commit();
    }

    public void swap(View v) {
        FragmentTransaction ft = fm.beginTransaction();
        if (detail != null && detail.isVisible()) {
            ft.replace(R.id.swap_container, master);
            //ft.addToBackStack(null); //將上一個fragment加入堆疊中
            ft.commit();
        }else{
            ft.replace(R.id.swap_container, detail);
            //ft.addToBackStack(null);
            ft.commit();
        }
    }
}
