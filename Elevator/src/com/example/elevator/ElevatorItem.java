package com.example.elevator;

public class ElevatorItem {
	
	private String name;
	
	private boolean isHighLight;
	
	private int id;
	
	private boolean isImgShow;
	
	private int width;
	
	private int viewHeight;
	
	public int getViewHeight() {
		return viewHeight;
	}

	public void setViewHeight(int viewHeight) {
		this.viewHeight = viewHeight;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
	
	public ElevatorItem(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsHighLight() {
		return isHighLight;
	}

	public void setIsHighLight(boolean isHighLight) {
		this.isHighLight = isHighLight;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getIsImgShow() {
		return isImgShow;
	}

	public void setIsImgShow(boolean isImgShow) {
		this.isImgShow = isImgShow;
	}
	
}
