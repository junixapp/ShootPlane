package com.itheima.shotplane.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import com.itheima.shotplane.PlaneWar;
import com.itheima.shotplane.R;

public class MainActivity extends Activity {
	private PlaneWar planeWar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        planeWar = (PlaneWar) View.inflate(this, R.layout.activity_main, null);
        setContentView(planeWar);
        
        new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				
				planeWar.start();
			}
		}, 1000);
        
    }


}
