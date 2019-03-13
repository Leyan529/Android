package com.example.baseadapterexample;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class myList extends ListActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		getListView().setEmptyView(findViewById(R.id.empty));
		CharSequence[] list=getResources().getStringArray(R.array.books);
		setListAdapter(new MyAdapter(this,list));	
		Init();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
	
		CheckBox cb=(CheckBox)v.findViewById(R.id.cb);
		TextView tv=(TextView)v.findViewById(R.id.tv);
		if (cb.isChecked())
		{
			cb.setChecked(false);
			Toast.makeText(this, tv.getText().toString() + "已取消核取!", Toast.LENGTH_SHORT).show();
		} else
		{
			cb.setChecked(true);
			Toast.makeText(this, tv.getText().toString() + "已核取!", Toast.LENGTH_SHORT).show();
		}			
		super.onListItemClick(l, v, position, id);
	}
	
	
	
	public void Init()
	{	
		CharSequence[] list=getResources().getStringArray(R.array.books);
		setListAdapter(new MyAdapter(this,list));		
	}
	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
		return true;
	}

}
