package leyan.hwma.com.async;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder> {
    private List<Transaction> trans; /**展示之資料集合*/

    public TransactionAdapter(List<Transaction> trans) {
        this.trans = trans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { /**建立可展示該列紀錄的View元件*/
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_trans, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Transaction tran = trans.get(position);
        holder.textOfAccount.setText(tran.getAccount());
        holder.textOfDate.setText(tran.getDate());
        holder.textOfAmount.setText(String.valueOf(tran.getAmount()));  /**非字串須轉型成字串，否則會有ResourceException*/
        holder.textOfType.setText(String.valueOf(tran.getType()));
    }

    @Override
    public int getItemCount() {
        return trans.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textOfAccount;
        private final TextView textOfDate;
        private final TextView textOfAmount;
        private final TextView textOfType;

        public ViewHolder(View itemView) {
            super(itemView);
            textOfAccount = (TextView)itemView.findViewById(R.id.textOfAccount);
            textOfDate = (TextView) itemView.findViewById(R.id.textOfDate);
            textOfAmount = (TextView) itemView.findViewById(R.id.textOfAmount);
            textOfType = (TextView) itemView.findViewById(R.id.textOfType);
        }
    }
}
