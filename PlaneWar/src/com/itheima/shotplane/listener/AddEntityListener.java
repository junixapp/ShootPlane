package com.itheima.shotplane.listener;

import com.itheima.shotplane.entity.BaseEntity;

import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

public abstract class AddEntityListener<T extends View> implements OnGlobalLayoutListener{
	protected T t;
	
	public AddEntityListener(T t) {
		super();
		this.t = t;
	}

	@Override
	public void onGlobalLayout() {
		t.getViewTreeObserver().removeGlobalOnLayoutListener(this);
		onLayoutFinish();
	}
	
	protected abstract void onLayoutFinish();

}
