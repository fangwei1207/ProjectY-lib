package com.zhy.weixin6.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.zhy.weixin6.ui.MainActivity;
import com.zhy.weixin6.ui.R;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class InitActivity extends Activity {
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_init);
		context = this;
		mHandler.sendEmptyMessage(1);
	}

	/**
	 * 利用消息处理机制适时更新进度条
	 */
	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == 1) {
				switch (msg.what) {
				case 1:
					try {
						// 模拟提交的耗时操作或调用别的方法
						Thread.sleep(100);
						Intent intent = new Intent(context, MainActivity.class);
						startActivity(intent);
						// //使用水波动画的方式加载
						// ActivityAnimationTool.init(new
						// WaterEffect());
						// // ActivityAnimationTool.init(new
						// BlurEffect());
						// ActivityAnimationTool.startActivity(InitActivity.this,
						// new Intent(InitActivity.this,
						// MainActivity.class));

						finish();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					;
					break;

				default:
					break;
				}

			}
		}
	};

}
