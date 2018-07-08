package leyan.hwma.com.testfragmentcyclelife;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Context context;


    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) { /**1. Fragment被加到某個Activity畫面中時的事件，*/ //產生階段（未出現在畫面上）
        Log.d("FRAG", "onAttach");
        this.context = context;
        Toast.makeText(context, "1. onAttach (Fragment被加到某個Activity畫面中時的事件)", Toast.LENGTH_SHORT);
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) { /**Fragment被建立時的事件，可加入初始化元件或資料的程式碼*/ //產生階段（未出現在畫面上）
        super.onCreate(savedInstanceState);
        Toast.makeText(context, "2. onCreate (Fragment被建立時的事件，可加入初始化元件或資料的程式碼)", Toast.LENGTH_SHORT);
        Log.d("FRAG", "onCreate");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /**將在畫面中第一次顯示Fragment時的事件，方法必須回傳Fragment畫面的View元件，
         設計時，請使用方法中的LayoutInflater物件，在此方法中產生畫面元件並回傳*/ //產生階段（未出現在畫面上）
        Toast.makeText(context, "3. onCreateView (將在畫面中第一次顯示Fragment時的事件，方法必須回傳Fragment畫面的View元件，\n" +
                "         設計時，請使用方法中的LayoutInflater物件，在此方法中產生畫面元件並回傳)", Toast.LENGTH_SHORT);

        Log.d("FRAG", "onCreateView");
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        /**當加入本Fragment的Activity被建立時，該Activity的onCreate方法執行完成後，
         * 會自動執行此方法。執行完此方法後，Fragment才出現在畫面上*/  //產生階段（未出現在畫面上），該Activity為MainActivity
        Toast.makeText(context, "4. onActivityCreated (當加入本Fragment的Activity被建立時，該Activity的onCreate方法執行完成後，\n" +
                "         * 會自動執行此方法。執行完此方法後，Fragment才出現在畫面上)", Toast.LENGTH_SHORT);
        Log.d("FRAG", "onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() { /**當Fragment出現在畫面中時的事件*/ //準備階段（出現在畫面上）
        Toast.makeText(context, "5. onStart (當Fragment出現在畫面中時的事件)", Toast.LENGTH_SHORT);
        Log.d("FRAG", "onStart");
        super.onStart();
    }

    @Override
    public void onResume() { /**取得螢幕存取能力的事件，讓Fragment可在畫面中與使用者互動*/ //準備階段（出現在畫面上）
        Toast.makeText(context, "6. onResume (取得螢幕存取能力的事件，讓Fragment可在畫面中與使用者互動)", Toast.LENGTH_SHORT);
        Log.d("FRAG", "onResume");
        super.onResume();
    }

    @Override
    public void onPause() { /**onPause (凍結 Fragment, 然後交出與使用者互動的能力，並把需要保存的資料保存。)*/
        Toast.makeText(context, "7. onPause (凍結 Fragment, 然後交出與使用者互動的能力，並把需要保存的資料保存。)", Toast.LENGTH_SHORT);
        Log.d("FRAG", "onPause");
        super.onPause();
    }

    @Override
    public void onStop() { /** onStop(代表Fragment停止*/
        Toast.makeText(context, "8. onStop (代表Fragment停止)", Toast.LENGTH_SHORT);
        Log.d("FRAG", "onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() { /**使用者已經完全看不到Fragment*/
        Toast.makeText(context, "9. onDestroyView (使用者已經完全看不到Fragment)", Toast.LENGTH_SHORT);
        Log.d("FRAG", "onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() { /**當Fragment要被清除之前的事件*/
        Toast.makeText(context, "10. onDestroy (當Fragment要被清除之前的事件)", Toast.LENGTH_SHORT);
        Log.d("FRAG", "onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() { /**與當初被加入的Activity卸載時的事件*/
        Toast.makeText(context, "11. onAttach (與當初被加入的Activity卸載時的事件)", Toast.LENGTH_SHORT);
        Log.d("FRAG", "onDetach");
        super.onDetach();
    }
}
