package leyan.hwma.com.ebook;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> { /**日後以自定義JSON從網路下載資料格式為主*/
    private List<User> Users; /**展示之資料集合*/
    public UserAdapter(List<User> Users) {
        this.Users = Users;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { /**建立可展示該列紀錄的View元件*/
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_users, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User User = Users.get(position);
        holder.textOfAccount.setText(User.getAccount());
        holder.textOfPswd.setText(User.getPassword());
    }

    @Override
    public int getItemCount() {
        return Users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textOfAccount;
        private final TextView textOfPswd;


        public ViewHolder(View itemView) {
            super(itemView);
            textOfAccount = (TextView)itemView.findViewById(R.id.textOfAccount);
            textOfPswd = (TextView) itemView.findViewById(R.id.textOfPswd);
        }
    }
}
