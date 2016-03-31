package com.itheima.shotplane.listener;


import android.view.View;
import android.view.ViewGroup;

import com.itheima.shotplane.entity.Hero;


public class AddHeroListener extends AddEntityListener<Hero>{

	public AddHeroListener(Hero t) {
		super(t);
	}

	@Override
	protected void onLayoutFinish() {
		Hero hero = t;
		
		//将heao设置到底部中心
		int parentWidth = ((ViewGroup)hero.getParent()).getWidth();
		int parentHeitht = ((ViewGroup)hero.getParent()).getHeight();
		float translationX = parentWidth/2 - hero.getWidth()/2;
		hero.setTranslationX(translationX);
		hero.setTranslationY(parentHeitht-hero.getHeight());
		
		hero.setVisibility(View.VISIBLE);
	}

}
