package com.example.android.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;


public class WordListAdapter extends
        RecyclerView.Adapter<WordListAdapter.WordViewHolder> {
    private LayoutInflater mInflater;
    private final LinkedList<String> mTitleList;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public WordListAdapter(Context context,
                           LinkedList<String> wordList) {
        mInflater = LayoutInflater.from(context);
        this.mTitleList = wordList;
    }

    @Override
    public WordListAdapter.WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.wordlist_item,
                parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(WordListAdapter.WordViewHolder holder, int position) {
        String mCurrent = mTitleList.get(position);
        holder.wordItemView.setText(mCurrent);
        holder.sentenceView.setText("This is a short description of " + mCurrent);
    }

    @Override
    public int getItemCount() {
        return mTitleList.size();
    }

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView wordItemView, sentenceView;
        final WordListAdapter mAdapter;
        private Context mContext = MainActivity.mcontext;

        public WordViewHolder(View itemView, WordListAdapter adapter) {
            super(itemView);
            wordItemView = itemView.findViewById(R.id.title);
            sentenceView = itemView.findViewById(R.id.sentence);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();
            // Use that to access the affected item in mWordList.

            String element = "This is the ingredients of " + mTitleList.get(mPosition);
            Log.e("999", element);
            Intent i = new Intent(mContext, WordActivity.class);
            i.putExtra(EXTRA_MESSAGE,element);
            mContext.startActivity(i);
        }
    }
}
