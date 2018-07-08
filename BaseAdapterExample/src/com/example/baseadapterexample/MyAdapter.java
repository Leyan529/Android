package com.example.baseadapterexample;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
	LayoutInflater myInflater;
	CharSequence[] list;	
	protected	static final int Jobs=0,Swan=1,Thinking=2;
	
	public MyAdapter(Context ctxt,CharSequence[] list)
	{
//		Obtains the LayoutInflater from the given context.
//		myInflater=LayoutInflater.from(ctxt);		
		myInflater=(LayoutInflater)ctxt.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.list=list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		ViewTag viewTag;
		if(convertView==null)
		{
			//Inflate a new view hierarchy from the specified xml resource.
			//Throws InflateException if there is an error.
			convertView=myInflater.inflate(R.layout.adapter,null);
			viewTag = new ViewTag(
					(ImageView)convertView.findViewById(R.id.iv),
					(TextView) convertView.findViewById(R.id.tv),
					(CheckBox) convertView.findViewById(R.id.cb)
					);
			convertView.setTag(viewTag);
		}
		else{
			viewTag = (ViewTag) convertView.getTag();
		}	
			switch(position)
			{
			case Jobs: 
				viewTag.iv.setBackgroundResource(R.drawable.jobs);
				break;
			case Swan: viewTag.iv.setBackgroundResource(R.drawable.blackswan);
				break;
			case Thinking: viewTag.iv.setBackgroundResource(R.drawable.thinking);
				break;				
			}
			
		viewTag.tv.setText(list[position]);
		return convertView;
	}

}
