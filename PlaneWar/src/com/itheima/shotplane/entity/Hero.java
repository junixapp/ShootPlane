package com.itheima.shotplane.entity;

import com.itheima.shotplane.R;

import android.content.Context;

public class Hero extends BaseEntity{

	public Hero(Context context) {
		super(context);
	}

	@Override
	protected int getImageRes() {
		return R.drawable.hero1;
	}

}
