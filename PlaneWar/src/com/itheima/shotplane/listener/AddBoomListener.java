package com.itheima.shotplane.listener;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;

public class AddBoomListener extends AddEntityListener<View>{

	public AddBoomListener(View t) {
		super(t);
	}

	@Override
	protected void onLayoutFinish() {
		Drawable background = t.getBackground();
		if(background instanceof AnimationDrawable){
			AnimationDrawable bg = (AnimationDrawable) background;
			bg.start();
			
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					((ViewGroup)t.getParent()).removeView(t);
				}
			}, 601);
		}
	}

}
