package com.example.elevator;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ElevatorText extends LinearLayout {
	
//	private Context context;
	
	private ImageView imageView;
	
	private TextView textView;
	
	private String selectedColor;
	
	private String normalColor;

	public ElevatorText(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		LayoutInflater.from(context).inflate(R.layout.huichang_tbelevatortext_layout, this, true);
		imageView = (ImageView) findViewById(R.id.loc_icon);
		textView = (TextView) findViewById(R.id.loc_text);
	}
	
	public ElevatorText(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}
	
	public ElevatorText(Context context) {
		this(context, null);
	}
	
	public void setText(String str) {
		textView.setText(str);
	}
	
	public void show() {
		imageView.setVisibility(View.VISIBLE);
		textView.setTextColor(Color.parseColor(selectedColor));
	}
	
	public void hide() {
		imageView.setVisibility(View.INVISIBLE);
		textView.setTextColor(Color.parseColor(normalColor));
	}
	
	public void setSelectedColor(String selectedColor) {
		this.selectedColor = selectedColor;
	}
	
	public void setNormalColor(String normalColor) {
		this.normalColor = normalColor;
	}
	
	public void setTextSize(int textSize) {
		this.textView.setTextSize(textSize);
	}

}
