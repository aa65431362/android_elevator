package com.example.elevator;

import java.util.ArrayList;
import java.util.List;

import com.example.elevator.Elevator.ElevatorOnClicklistener;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private Elevator elevator;
	
	private List<ElevatorItem> itemList;
	
	private ScrollView scrollView;
	
	private LinearLayout viewContainer;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        viewContainer = (LinearLayout) findViewById(R.id.viewContainer);
        elevator = (Elevator) findViewById(R.id.elevator);
        
        initData();
        
        elevator.setList(itemList);
        elevator.setElevatorOnClickListener(new ElevatorOnClicklistener() {

			@Override
			public void OnClick(ElevatorItem item) {
				Toast.makeText(getApplicationContext(), item.getName() + " " + item.getId(), Toast.LENGTH_SHORT).show();
				int scrollHeight = 0;
				for (int i = 0; i < itemList.indexOf(item); i++) {
					scrollHeight += itemList.get(i).getViewHeight();
				}
				viewContainer.scrollTo(0, scrollHeight);
		}});
        
    }
    
    private void initData() {
    	itemList = new ArrayList<ElevatorItem>();
    	ElevatorItem item1 = new ElevatorItem("yaochen");
    	itemList.add(item1);
    	ElevatorItem item2 = new ElevatorItem("wanglun");
    	itemList.add(item2);
    	ElevatorItem item3 = new ElevatorItem("yaochen");
    	itemList.add(item3);
    	ElevatorItem item4 = new ElevatorItem("wanglun");
    	itemList.add(item4);
    	ElevatorItem item5 = new ElevatorItem("yaochen");
    	itemList.add(item5);
    	ElevatorItem item6 = new ElevatorItem("wanglun");
    	itemList.add(item6);
    	ElevatorItem item7 = new ElevatorItem("yaochen");
    	itemList.add(item7);
    	ElevatorItem item8 = new ElevatorItem("wanglun");
    	itemList.add(item8);
    	ElevatorItem item9 = new ElevatorItem("yaochen");
    	itemList.add(item9);
    	ElevatorItem item10 = new ElevatorItem("wanglun");
    	itemList.add(item10);
    	ElevatorItem item11 = new ElevatorItem("yaochen");
    	itemList.add(item11);
    	ElevatorItem item12 = new ElevatorItem("wanglun");
    	itemList.add(item12);
    	
    	// 测量宽高
    	int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
    	for (ElevatorItem currentItem : itemList) {
    		
    		TextView textView = new TextView(this);
    		textView.setText(currentItem.getName());
//    		textView.setTextSize(50);
    		textView.setGravity(Gravity.CENTER);
    		textView.measure(w, h);
    		currentItem.setViewHeight(textView.getMeasuredHeight());
    		viewContainer.addView(textView);
    	}
    }
}
