package com.nfu.oldwork.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;

import com.nfu.oldwork.R;
import com.nfu.oldwork.utils.DensityUtil;


/**
 * 从底部弹出或滑出选择菜单或窗口  
 */
public class ActionSheetWindow extends PopupWindow {

	/**
	 * 图库选择
	 */
	private Button takePhotoBtn;

	/**
	 * 取消
	 */
	private Button cancelBtn;
	private View mMenuView;

	@SuppressLint("InflateParams")
	public ActionSheetWindow(Context context, OnClickListener itemsOnClick) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.nfu_actionwindow, null);
		takePhotoBtn = (Button) mMenuView.findViewById(R.id.takePhotoBtn);
		cancelBtn = (Button) mMenuView.findViewById(R.id.cancelBtn);
		// 设置按钮监听
		cancelBtn.setOnClickListener(itemsOnClick);
		takePhotoBtn.setOnClickListener(itemsOnClick);
		
		// 设置SelectPicPopupWindow的View
		this.setContentView(mMenuView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(DensityUtil.dip2px(context,150f));
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.contactMsgAnim);
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0x80000000);
		// 设置SelectPicPopupWindow弹出窗体的背景
		this.setBackgroundDrawable(dw);
		// mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
		mMenuView.setOnTouchListener(new OnTouchListener() {

			@Override
			@SuppressLint("ClickableViewAccessibility")
			public boolean onTouch(View v, MotionEvent event) {

				int height = mMenuView.findViewById(R.id.pop_layout).getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						dismiss();
					}
				}
				return true;
			}
		});

	}

	public ActionSheetWindow setText(String str){
		takePhotoBtn.setText(str);
		return this;
	}

}
