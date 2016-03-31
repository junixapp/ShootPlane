package com.itheima.shotplane.dialog;

import com.itheima.shotplane.R;
import com.itheima.shotplane.activity.MainActivity;
import com.itheima.shotplane.util.SharePrefUtil;
import com.itheima.shotplane.util.SharePrefUtil.KEY;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class GameDialog extends Dialog implements OnClickListener {

	public GameDialog(Context context) {
		this(context, R.style.GameDialog);
	}

	public GameDialog(Context context, int theme) {
		super(context, theme);
	}

	private Button btn_restart, btn_finish;
	private TextView text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dialog_game);

		btn_restart = (Button) findViewById(R.id.btn_restart);
		btn_finish = (Button) findViewById(R.id.btn_finish);
		text = (TextView) findViewById(R.id.text);

		// 设置点击事件
		btn_finish.setOnClickListener(this);
		btn_restart.setOnClickListener(this);
		
		//设置分数
		int score = SharePrefUtil.getInt(getContext(), KEY.SCORE, 0);
		text.setText("目前最高得分："+score);
	}

	@Override
	public void onClick(final View v) {
		dismiss();
		new Handler().postDelayed(new Runnable() {
			public void run() {
				switch (v.getId()) {
				case R.id.btn_restart:
					if (listener != null) {
						listener.onRestartClick();
					}
					break;
				case R.id.btn_finish:
					if (listener != null) {
						listener.onFinishClick();
					}
					break;
				}
			}
		}, 1100);
		
	}

	private OnRestartClickListener listener;

	public void setOnRestartClickListener(OnRestartClickListener listener) {
		this.listener = listener;
	}

	public interface OnRestartClickListener {
		void onRestartClick();
		void onFinishClick();
	}

}
