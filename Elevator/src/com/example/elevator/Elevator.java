package com.example.elevator;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class Elevator extends LinearLayout {
	
	private Context context;
	
	private LinearLayout item_linearLayout;
	
	private LinearLayout item_linearBG;
	
	private int barWidth;
	
	private int allItemWidth;
	
	private HorizontalScrollView horizontalScrollView;
	
	private GridView item_gridView;
	
	private ElevatorAdapter adapter;
	
	private FrameLayout downPullImg;
	
	private TextView itemBarTv;
	
	private ElevatorOnClicklistener mListener;
	
	private String selectedColor;
	
	private String normalColor;
	
	int remainWidth = 0;

	int scrollWidth = 0;
	
//	private int textHeight;
	
	private List<ElevatorText> elevatorTextList = new ArrayList<ElevatorText>();
	private List<ElevatorItem> itemList = new ArrayList<ElevatorItem>();
	
	public Elevator(Context context) {
		this(context, null);
	}
	
	public Elevator(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.context = context;
		selectedColor = "#EE0A3B";
		normalColor = "#333333";
		final Animation firstAnimation = AnimationUtils.loadAnimation(context, R.anim.huichang_elevator_first_rotate);
		final Animation backAnimation = AnimationUtils.loadAnimation(context, R.anim.huichang_elevator_back_rotate);
		LinearInterpolator lin = new LinearInterpolator();
		firstAnimation.setInterpolator(lin);
		backAnimation.setInterpolator(lin);
		firstAnimation.setFillAfter(true);
		backAnimation.setFillAfter(true);
		LayoutInflater.from(context).inflate(R.layout.huichang_elevator_layout, this, true);
		item_linearLayout = (LinearLayout) findViewById(R.id.linear);
		item_linearLayout.setGravity(Gravity.CENTER_VERTICAL);
		item_linearBG = (LinearLayout) findViewById(R.id.linear_bg);
		horizontalScrollView = (HorizontalScrollView) findViewById(R.id.horizontalscroll);
		item_gridView = (GridView) findViewById(R.id.gridView);
		downPullImg = (FrameLayout) findViewById(R.id.image);
		itemBarTv = (TextView) findViewById(R.id.downText);
		WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		barWidth = wm.getDefaultDisplay().getWidth();
		adapter = new ElevatorAdapter(context, R.layout.huichang_tbelevatortext_layout, itemList);
//		adapter.setTextHeight(textHeight);
		final View upPopView = LayoutInflater.from(context).inflate(R.layout.uppop_window, null);
		final View downPopView = LayoutInflater.from(context).inflate(R.layout.downpop_window, null);
		final PopupWindow downPopupWindow = new PopupWindow(downPopView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		final PopupWindow upPopupWindow = new PopupWindow(upPopView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		downPopupWindow.setFocusable(true);
		LinearLayout downMongolia = (LinearLayout) downPopView.findViewById(R.id.downMongolia);
		LinearLayout upMongolia = (LinearLayout) upPopView.findViewById(R.id.upMongolia);
		downMongolia.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				downPopupWindow.dismiss();
				upPopupWindow.dismiss();
				itemBarTv.setVisibility(View.INVISIBLE);
				item_linearLayout.setVisibility(View.VISIBLE);
				downPullImg.startAnimation(backAnimation);
			}
		});
		upMongolia.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				downPopupWindow.dismiss();
				upPopupWindow.dismiss();
				itemBarTv.setVisibility(View.INVISIBLE);
				item_linearLayout.setVisibility(View.VISIBLE);
				downPullImg.startAnimation(backAnimation);
			}
		});
		item_gridView = (GridView) downPopView.findViewById(R.id.gridView);
		item_gridView.setAdapter(adapter);
		item_gridView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
				for (ElevatorItem currentItem : itemList) {
					currentItem.setIsHighLight(false);
					currentItem.setIsImgShow(false);
				}
				itemList.get(position).setIsHighLight(true);
				itemList.get(position).setIsImgShow(true);
				adapter.notifyDataSetChanged();
				for (ElevatorText elevatortext : elevatorTextList) {
					elevatortext.hide();
				}
				elevatorTextList.get(position).show();
				itemBarTv.setVisibility(View.INVISIBLE);
				item_linearLayout.setVisibility(View.VISIBLE);
				downPullImg.startAnimation(backAnimation);
				remainWidth = 0;
				scrollWidth = 0;
				for (int i = (int) id; i < itemList.size(); i++) {
					remainWidth += itemList.get(i).getWidth();
				}
				if (remainWidth >= barWidth / 2) {
					for (int i = 0; i < id; i++) {
						scrollWidth += itemList.get(i).getWidth();
					}
					horizontalScrollView.smoothScrollTo(scrollWidth - barWidth / 2 + itemList.get((int) id).getWidth() / 2, 0);
				} else {
					horizontalScrollView.smoothScrollTo(allItemWidth, 0);
				}
				if (mListener != null) {
					mListener.OnClick(itemList.get(position));
				}
				downPopupWindow.dismiss();
				upPopupWindow.dismiss();
			}
		});
		final int[] location = new int[2];
//		upPopView.measure(w, h);
		downPullImg.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				v.getLocationOnScreen(location);
				upPopupWindow.setHeight(location[1]);
				if (downPopupWindow.isShowing() == true) {
					downPopupWindow.dismiss();
					upPopupWindow.dismiss();
					itemBarTv.setVisibility(View.INVISIBLE);
					item_linearLayout.setVisibility(View.VISIBLE);
					downPullImg.startAnimation(backAnimation);
				} else if (downPopupWindow.isShowing() == false) {
					downPopupWindow.showAsDropDown(v);
					upPopupWindow.showAtLocation(item_linearBG, Gravity.NO_GRAVITY, location[0], -location[1]);
					itemBarTv.setVisibility(View.VISIBLE);
					item_linearLayout.setVisibility(View.INVISIBLE);
					downPullImg.startAnimation(firstAnimation);
				}
			}
		});
	}
	
	public void setList(final List<ElevatorItem> list) {
		int id = 0;
		itemList.clear();
		itemList.addAll(list);
		item_linearLayout.removeAllViews();
		elevatorTextList.clear();
		adapter.notifyDataSetChanged();
		int w = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);  
		int h = View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
		for (final ElevatorItem item : itemList) {
			final ElevatorText elevatorText = new ElevatorText(context);
			elevatorText.setText(item.getName());
//			elevatorText.setTextSize(20);
			elevatorText.setSelectedColor(selectedColor);
			elevatorText.setNormalColor(normalColor);
			elevatorText.measure(w, h);
			item.setWidth(elevatorText.getMeasuredWidth());
			System.out.println("width" + elevatorText.getMeasuredWidth());
			item.setId(id);
//			elevatorText.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, textHeight>0 ? textHeight : LayoutParams.MATCH_PARENT));
			elevatorTextList.add(elevatorText);
			elevatorText.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View view) {
					for (ElevatorText elevatortext : elevatorTextList) {
						elevatortext.hide();
					}
					elevatorText.show();
					for (ElevatorItem currentItem : itemList) {
						currentItem.setIsHighLight(false);
						currentItem.setIsImgShow(false);
					}
					item.setIsHighLight(true);
					item.setIsImgShow(true);
					remainWidth = 0;
					scrollWidth = 0;
					for (int i = item.getId(); i < itemList.size(); i++) {
						remainWidth += item.getWidth();
					}
					if (remainWidth > barWidth / 2) {
						for (int i = 0; i < item.getId(); i++) {
							scrollWidth += itemList.get(i).getWidth();
						}
						horizontalScrollView.smoothScrollTo(scrollWidth - barWidth / 2 + item.getWidth() / 2, 0);
					} else {
						horizontalScrollView.smoothScrollTo(allItemWidth, 0);
					}
					adapter.notifyDataSetChanged();
					if (mListener != null) {
						mListener.OnClick(item);
					}					
				}
			});
			item_linearLayout.addView(elevatorText);
			id++;
		}
		for (ElevatorText elevatorText : elevatorTextList) {
			elevatorText.hide();
		}
		elevatorTextList.get(0).show();
		allItemWidth = 0;
		for (ElevatorItem currentItem : itemList) {
			currentItem.setIsHighLight(false);
			currentItem.setIsImgShow(false);
			allItemWidth += currentItem.getWidth();
		}
		itemList.get(0).setIsHighLight(true);
		itemList.get(0).setIsImgShow(true);
		adapter.notifyDataSetChanged();
	}
	
	public interface ElevatorOnClicklistener {
		public void OnClick(ElevatorItem item);
	}
	
	public void setElevatorOnClickListener(ElevatorOnClicklistener listener) {
		this.mListener = listener;
	}
	
	public void setSelectedColor(String selectedColor) {
		this.selectedColor = selectedColor;
		this.adapter.setSelectedColor(selectedColor);
	}
	
	public void setNormalColor(String normalColor) {
		this.normalColor = normalColor;
		this.adapter.setNormalColor(normalColor);
	}
	
	public void setBackgroundColor(String bgColor) {
		this.item_linearBG.setBackgroundColor(Color.parseColor(bgColor));
	}
	
	public void setTabAlpha(float alpha){
		this.item_linearBG.setAlpha(alpha);
	}
	
	public void setLocation(int id) {
		for (ElevatorText elevatortext : elevatorTextList) {
			elevatortext.hide();
		}
		elevatorTextList.get(id).show();
		for (ElevatorItem currentItem : itemList) {
			currentItem.setIsHighLight(false);
			currentItem.setIsImgShow(false);
		}
		itemList.get(id).setIsHighLight(true);
		itemList.get(id).setIsImgShow(true);
		adapter.notifyDataSetChanged();
		remainWidth = 0;
		scrollWidth = 0;
		for (int i = (int) id; i < itemList.size(); i++) {
			remainWidth += itemList.get((int) id).getWidth();
		}
		if (remainWidth > barWidth) {
			for (int i = 0; i < id; i++) {
				scrollWidth += itemList.get(i).getWidth();
			}
			horizontalScrollView.smoothScrollTo(scrollWidth - barWidth * 2, 0);
		} else {
			horizontalScrollView.smoothScrollTo(allItemWidth, 0);
		}
	}

//	public void setTextHeight(int height){
//		textHeight = height;
//	}
	
}
