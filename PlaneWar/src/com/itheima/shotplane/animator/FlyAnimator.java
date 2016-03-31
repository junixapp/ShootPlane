package com.itheima.shotplane.animator;

import android.view.View;

public class FlyAnimator extends BaseAnimator {

	public FlyAnimator(View target, float startValue, float endValue) {
		super(target, startValue, endValue);
	}
	
	@Override
	protected void doAnim(float animatedValue) {
		target.setTranslationY(animatedValue);
		
		if(listener!=null){
			listener.onFly(animatedValue);
		}
	}
	private OnEnemyFlyListener listener;
	public void setOnEnemyFlyListener(OnEnemyFlyListener listener){
		this.listener = listener;
	}
	
	public interface OnEnemyFlyListener{
		void onFly(float translationY);
	}
	
}
