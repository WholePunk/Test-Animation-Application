package com.wholepunk.android.testanimationapplication;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TestAdapter extends BaseAdapter {

	private ArrayList<String> data;
	private LayoutInflater layoutInflater;
	private int layout;
		
	public TestAdapter (Context context, int l)
	{
		layoutInflater = LayoutInflater.from(context);
		layout = l;
	}
	
	public void addData(ArrayList<String> array)
	{
		data = array;
	}
	
	@Override
	public int getCount() 
	{
		return data.size();
	}

	@Override
	public Object getItem(int position) 
	{
		return data.get(position);
	}

	@Override
	public long getItemId(int position) 
	{
		return position;
	}

	@Override
	public View getView(int position, View reuseView, ViewGroup parent) 
	{
		if (reuseView == null)
		{
			reuseView = layoutInflater.inflate(layout, null);
		}
		
		String item = (String) data.get(position);
		
		TextView titleTextView = (TextView) reuseView.findViewById(R.id.list_item);
		titleTextView.setText(item);
		
		return reuseView;
	}

}
