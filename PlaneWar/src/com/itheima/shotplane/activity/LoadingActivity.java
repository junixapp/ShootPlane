package com.itheima.shotplane.activity;

import com.itheima.shotplane.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class LoadingActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loading);
		View view = findViewById(R.id.view);
		
		AnimationDrawable drawable = (AnimationDrawable) view.getBackground();
		drawable.start();
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				startActivity(new Intent(LoadingActivity.this,MainActivity.class));
				finish();
			}
		}, 2701);
	}
}
