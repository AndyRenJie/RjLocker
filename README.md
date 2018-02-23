## RjLocker ##

一款手机锁屏的Sample，可以屏蔽onBack返回键，Home键以及Menu菜单键

使用WindowManager的addView的方法可以在其他应用最上层，甚至手机桌面最上层显示窗口。也就是实现桌面悬浮窗的功能。
根据WindowManager.LayoutParams属性不同，效果也就不同。

![image](https://github.com/AndyRenJie/RjLocker/blob/master/images/ic_main_lock.png)

![image](https://github.com/AndyRenJie/RjLocker/blob/master/images/ic_lock_bg.png)

Service的onStartCommand()方法中判断Intent的Action，如果是锁屏，获取当前WindowManager，调用addView()方法将锁屏图片添加到应用最上层窗口，
addView()方法中创建ScreenSaverView自定义View和LayoutParams,添加LayoutParams的type，flags，width，height等属性。如果是解锁，调用removeView()
移除WindownManager的ScreenSaverView，并关闭自身Service
 
```
@Override
	public void onCreate() {
		super.onCreate();
		mContext = getApplicationContext();
		mWinMng = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		String action = intent.getAction();
		if(TextUtils.equals(action,LOCK_ACTION)) {
			addView();
		} else if(TextUtils.equals(action,UNLOCK_ACTION)) {
			removeView();
			stopSelf();
		}
		return super.onStartCommand(intent, flags, startId);
	}
	
	public void addView() {
		if(screenView == null) {
			screenView = new ScreenSaverView(mContext);
			LayoutParams param = new LayoutParams();
			param.type = LayoutParams.TYPE_SYSTEM_ALERT;
			param.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
			param.width = LayoutParams.MATCH_PARENT;
			param.height = LayoutParams.MATCH_PARENT;
			mWinMng.addView(screenView, param);
		}
	}
	
	public void removeView() {
		if(screenView != null) {
			mWinMng.removeView(screenView);
			screenView = null;
		}
	}
 ```
