package org.gditc.qrcode.utils;

import java.util.Stack;

import android.app.Activity;
/**
 * 通过堆栈管理器去管理Activity, 以实现完全退出程序
 */
public class StackManager {
	/**
	 * Stack 中对应的Activity列表  （也可以写做 Stack<Activity>）
	 */
	private static Stack<Activity> mActivityStack;
	private static StackManager mInstance;

	/**
	 * @Description 获取栈管理工具
	 * @return ActivityManager
	 */
	public static StackManager getStackManager() {
		if (mInstance == null) {
			mInstance = new StackManager();
		}
		return mInstance;
	}

	/**
	 * 推出栈顶Activity
	 */
	public void popActivity(Activity activity) {
		if (activity != null) {
			activity.finish();
			mActivityStack.remove(activity);
			activity = null;
		}
	}

	/**
	 * 获得当前栈顶Activity
	 */
	public Activity currentActivity() {
		//lastElement()获取最后个子元素，这里是栈顶的Activity
		if(mActivityStack == null || mActivityStack.size() ==0){
			return null;
		}
		Activity activity = (Activity) mActivityStack.lastElement();
		return activity;
	}

	/**
	 * 将当前Activity推入栈中
	 */
	public void pushActivity(Activity activity) {
		if (mActivityStack == null) {
			mActivityStack = new Stack<Activity>();
		}
		mActivityStack.add(activity);
	}

	/**
	 * 弹出指定的clsss所在栈顶部的中所有Activity
	 * @clsss : 指定的类 
	 */
	public void popTopActivitys(Class<?> clsss) {
		while (true) {
			Activity activity = currentActivity();
			if (activity == null) {
				break;
			}
			if (activity.getClass().equals(clsss)) {
				break;
			}
			popActivity(activity);
		}
	}
	
	/**
	 * 弹出栈中所有Activity
	 */
	public void popAllActivitys() {
		while (true) {
			Activity activity = currentActivity();
			if (activity == null) {
				break;
			}
			popActivity(activity);
		}
	}
}