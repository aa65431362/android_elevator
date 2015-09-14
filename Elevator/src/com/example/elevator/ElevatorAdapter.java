package com.example.elevator;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ElevatorAdapter extends BaseAdapter {
	
	private Context context;
	
	private int resourceId;
	
	private List<ElevatorItem> itemList = new ArrayList<ElevatorItem>();
	
	private String selectedColor;
	
	private String normalColor;
	
//	private int textHeight;
	
	public ElevatorAdapter(Context context, int resourceId, List<ElevatorItem> itemList) {
		this.context = context;
		this.resourceId = resourceId;
		this.itemList = itemList;
		selectedColor = "#EE0A3B";
		normalColor = "#333333";
	}

	@Override
	public int getCount() {
		return itemList.size();
	}

	@Override
	public ElevatorItem getItem(int position) {
		return itemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ElevatorItem item = getItem(position);
		View view;
		ViewHolder viewHolder;
		
		if (convertView == null) {
			view = LayoutInflater.from(context).inflate(resourceId, null);
			viewHolder = new ViewHolder();
			viewHolder.textView = (TextView) view.findViewById(R.id.loc_text);
			viewHolder.imageView = (ImageView) view.findViewById(R.id.loc_icon);
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.textView.setText(item.getName());
//		viewHolder.textView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, textHeight));
		if (item.getIsHighLight()) {
			viewHolder.textView.setTextColor(Color.parseColor(selectedColor));
		} else {
			viewHolder.textView.setTextColor(Color.parseColor(normalColor));
		}
		if (item.getIsImgShow()) {
			viewHolder.imageView.setVisibility(View.VISIBLE);
		} else {
			viewHolder.imageView.setVisibility(View.INVISIBLE);
		}
		return view;
	}
	
	public class ViewHolder {
		TextView textView;
		ImageView imageView;
	}
	
	public void setSelectedColor(String selectedColor) {
		this.selectedColor = selectedColor;
	}
	
	public void setNormalColor(String normalColor) {
		this.normalColor = normalColor;
	}
	
//	public void setTextHeight(int textHeight) {
//		this.textHeight = textHeight;
//	}

}
