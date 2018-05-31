package com.luis.strategy.map;

public abstract class MapObject{
	
	protected MapObject map;
	protected boolean selected;
	protected boolean select;
	
	public MapObject(MapObject map) {
		super();
		this.map = map;
	}
	
	public boolean isSelected() {
		return selected;
	}
	
	public void setSelect(boolean select) {
		this.select = select;
	}

	public boolean isSelect() {
		return select;
	}
	
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
