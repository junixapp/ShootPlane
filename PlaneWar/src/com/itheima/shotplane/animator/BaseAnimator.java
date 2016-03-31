package com.itheima.shotplane.animator;

import android.view.View;
import android.view.animation.Interpolator;

import com.nineoldandroids.animation.Animator.AnimatorListener;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.animation.ValueAnimator.AnimatorUpdateListener;

public abstract class BaseAnimator {
	public ValueAnimator animator;
	protected View target;
	public BaseAnimator(View target,float startValue,float endValue){
		this.target = target;
		animator = ValueAnimator.ofFloat(startValue,endValue);
		animator.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animator) {
				float animatedValue = (Float) animator.getAnimatedValue();
				doAnim(animatedValue);
			}
		});
	}
	
	public void start(long duration){
		animator.setDuration(duration);
		animator.start();
	}
	
	
	public void setAnimationListener(AnimatorListenerAdapter listener){
		animator.addListener(listener);
	}
	
	public void setInterpolator(Interpolator value){
		animator.setInterpolator(value);
	}
	
	public void cancel(){
		animator.cancel();
	}
	
	protected abstract void doAnim(float animatedValue);
	
	
}
