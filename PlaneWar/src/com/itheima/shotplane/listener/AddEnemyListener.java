package com.itheima.shotplane.listener;

import java.util.Random;

import com.itheima.shotplane.PlaneWar;
import com.itheima.shotplane.entity.Enemy;

public class AddEnemyListener extends AddEntityListener<Enemy> {

	public AddEnemyListener(Enemy t) {
		super(t);
	}

	@Override
	protected void onLayoutFinish() {
		Enemy enemy = t;
		 
		// 开始飞行
		PlaneWar war = (PlaneWar) enemy.getParent();
		if (war != null) {
			// 隐藏敌人
			Random random = new Random();
			enemy.setTranslationY(-1 * enemy.getHeight());
			float targetX = random.nextInt((war.getWidth() - enemy.getWidth()));
			enemy.setTranslationX(targetX);
			// Log.e("tag", "targetX:"+targetX);

			enemy.setDuration(4000 + new Random().nextInt(2000));
			// 飞行目标
			enemy.setTargetY(war.getHeight() + enemy.getHeight());

			enemy.fly();
		}
	}

}