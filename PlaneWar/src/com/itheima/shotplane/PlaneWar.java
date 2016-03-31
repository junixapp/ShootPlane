package com.itheima.shotplane;

import com.itheima.shotplane.activity.MainActivity;
import com.itheima.shotplane.entity.BaseEntity;
import com.itheima.shotplane.entity.Bullet;
import com.itheima.shotplane.entity.Enemy;
import com.itheima.shotplane.entity.Hero;
import com.itheima.shotplane.listener.AddBoomListener;
import com.itheima.shotplane.listener.AddBulletListener;
import com.itheima.shotplane.listener.AddEnemyListener;
import com.itheima.shotplane.listener.AddHeroListener;
import com.itheima.shotplane.util.SharePrefUtil;
import com.itheima.shotplane.util.SharePrefUtil.KEY;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * 场景控制类
 * 
 * @author Administrator
 * 
 */
public class PlaneWar extends FrameLayout {
	private String TAG = "PlaneWar";

	private Paint paint = null;
	private String str = "当前得分：%s";
	private int score = 0;// 得分
	private int textHeight = 0;

	private final int CREATE_ENEMY_DURATION = 1000;// 制造敌人间隔时间
	private final int CREATE_BULLET_DURATION = 100;// 生产子弹间隔时间
	private final int MSG_ENEMY = 1;
	private final int MSG_BULLET = 2;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case MSG_ENEMY:
				generateEnemy();
				break;
			case MSG_BULLET:
				generateBullet();
				break;
			}
		};
	};

	public PlaneWar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	public PlaneWar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public PlaneWar(Context context) {
		super(context);
		init();
	}

	private void init() {
		paint = new Paint();
		paint.setTextSize(16);
		paint.setColor(Color.WHITE);
		paint.setTextSkewX(-0.15f);

		textHeight = getTextHeight(str);
	}

	/**
	 * 开始游戏
	 */
	public void start() {
		// 生产敌人
		generateEnemy();

		// 生产Hero
		generateHero();

		// 射出子弹
		generateBullet();
	}

	private Hero mHero;

	public Hero getHero() {
		return mHero;
	}

	/**
	 * 生成英雄
	 */
	private void generateHero() {
		mHero = new Hero(getContext());
		mHero.setVisibility(View.INVISIBLE);
		mHero.getViewTreeObserver().addOnGlobalLayoutListener(
				new AddHeroListener(mHero));
		addView(mHero);
	}

	/**
	 * 制造敌人
	 */
	private void generateEnemy() {
		Enemy enemy = new Enemy(getContext());
		enemy.setPlaneWar(this);
		enemy.getViewTreeObserver().addOnGlobalLayoutListener(
				new AddEnemyListener(enemy));
		addView(enemy);

		// 循环制造敌人
		handler.sendEmptyMessageDelayed(MSG_ENEMY, CREATE_ENEMY_DURATION);
	}

	/**
	 * 生产子弹
	 */
	private void generateBullet() {
		Bullet bullet = new Bullet(getContext());
		bullet.setHero(mHero);
		bullet.getViewTreeObserver().addOnGlobalLayoutListener(
				new AddBulletListener(bullet));
		addView(bullet);
		// 循环制造敌人
		handler.sendEmptyMessageDelayed(MSG_BULLET, CREATE_BULLET_DURATION);
	}

	private float downX, downY;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (mHero.getVisibility() != View.VISIBLE) {
			return false;
		}
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downX = event.getX();
			downY = event.getY();
			moveHeroTo(downX, downY);
			break;
		case MotionEvent.ACTION_MOVE:
			float moveX = event.getX();
			float moveY = event.getY();
			float deltaX = moveX - downX;
			float deltaY = moveY - downY;

			moveHeroBy(deltaX, deltaY);

			downX = moveX;
			downY = moveY;
			break;
		case MotionEvent.ACTION_UP:

			break;
		}
		return true;
	}

	/**
	 * 移动英雄到某个点
	 */
	private void moveHeroTo(float x, float y) {
		// 计算translation坐标和场景相对坐标的偏移量
		float xOffset = x - mHero.getTranslationX() - mHero.getWidth() / 2;
		float yOffset = y - mHero.getTranslationY() - mHero.getHeight() / 2;

		moveHeroBy(xOffset, yOffset);
	}

	/**
	 * 移动英雄
	 * 
	 * @param xOffset
	 * @param yOffset
	 */
	private void moveHeroBy(float xOffset, float yOffset) {
		float newTranslationX = mHero.getTranslationX() + xOffset;
		float newTranslationY = mHero.getTranslationY() + yOffset;

		// 控制英雄的四边不能超出场景范围
		if (newTranslationX < 0)
			newTranslationX = 0;
		if (newTranslationX > (getWidth() - mHero.getWidth())) {
			newTranslationX = getWidth() - mHero.getWidth();
		}
		if (newTranslationY < 0)
			newTranslationY = 0;
		if (newTranslationY > (getHeight() - mHero.getHeight())) {
			newTranslationY = getHeight() - mHero.getHeight();
		}

		mHero.setTranslationX(newTranslationX);
		mHero.setTranslationY(newTranslationY);
	}

	/**
	 * 终止游戏
	 */
	public void end() {
		clearAll();

		MainActivity mainActivity = (MainActivity) getContext();
		mainActivity.finish();
	}

	/**
	 * 重新开始游戏
	 */
	public void clearAll() {
		handler.removeMessages(MSG_ENEMY);
		handler.removeMessages(MSG_BULLET);
		// 清除所有游戏实体
		int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			View view = getChildAt(i);
			if (view instanceof Enemy) {
				Enemy enemy = (Enemy) view;
				enemy.stopAnimation();
			} else if (view instanceof Bullet) {
				Bullet bullet = (Bullet) view;
				bullet.stopAnimation();
			}
		}

		removeAllViews();

		// 重置分数
		resetScore();
	}

	/**
	 * 引爆敌人，此处不能用WindowManager实现，因为同时可能引爆多个
	 * 
	 * @param translationY
	 * @param translationX
	 * @param translationRect
	 */
	public void boomEnemy(float translationX, float translationY) {
		// 创建用于临时爆炸的敌人
		View v = new View(getContext());
		Drawable drawable = getResources().getDrawable(R.drawable.enemy_boom);
		v.setLayoutParams(new LayoutParams(drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight()));
		v.setBackgroundDrawable(drawable);
		v.setTranslationX(translationX);
		v.setTranslationY(translationY);

		v.getViewTreeObserver().addOnGlobalLayoutListener(
				new AddBoomListener(v));
		addView(v);
	}

	/**
	 * 引爆英雄
	 */
	public void boomHero() {
		// 创建用于临时爆炸的英雄
		View v = new View(getContext());

		Drawable drawable = getResources().getDrawable(R.drawable.hero_boom);
		v.setLayoutParams(new LayoutParams(drawable.getIntrinsicWidth(),
				drawable.getIntrinsicHeight()));
		v.setBackgroundDrawable(drawable);
		v.setTranslationX(mHero.getTranslationX());
		v.setTranslationY(mHero.getTranslationY());

		v.getViewTreeObserver().addOnGlobalLayoutListener(
				new AddBoomListener(v));
		addView(v);
	}

	/**
	 * 移除子弹
	 */
	public void removeBullet() {
		handler.removeMessages(MSG_BULLET);
		int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			View view = getChildAt(i);
			if (view instanceof Bullet) {
				Bullet bullet = (Bullet) view;
				bullet.stopAnimation();
				removeView(bullet);
			}
		}
	}

	public void hideHero() {
		mHero.setVisibility(View.INVISIBLE);
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		canvas.drawText(String.format(str, score), 1, textHeight + 1, paint);
	}

	public int getTextHeight(String text) {
		Rect bounds = new Rect();
		paint.getTextBounds(text, 0, text.length(), bounds);
		return bounds.height();
	}

	public void increaseScore() {
		score += 100;
		postInvalidate();
	}

	private void resetScore() {
		score = 0;
		postInvalidate();
	}

	public void saveScore() {
		int localScore = SharePrefUtil.getInt(getContext(), KEY.SCORE, 0);
		SharePrefUtil.saveInt(getContext(), KEY.SCORE,
				Math.max(localScore, score));
	}

}
