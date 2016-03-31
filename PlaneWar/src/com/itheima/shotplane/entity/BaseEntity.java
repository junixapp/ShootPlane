package com.itheima.shotplane.entity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

public abstract class BaseEntity extends View {
	protected Bitmap bitmap;

	public BaseEntity(Context context) {
		super(context);
		init();
	}
	private void init() {
		bitmap = BitmapFactory.decodeResource(getResources(), getImageRes());
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(bitmap.getWidth(), bitmap.getHeight());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawBitmap(bitmap, 0, 0, null);
	}

	protected abstract int getImageRes();
	
	public void stopAnimation(){
		
	}

}
