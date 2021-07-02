package com.teguh.sejarahislam.ui;


import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.teguh.sejarahislam.R;

public class BoardView extends LinearLayout {

	public BoardView(Context context) {
		this(context, null);
	}


	public BoardView(Context context, AttributeSet attributeSet) {
		super(context, attributeSet);
		setOrientation(LinearLayout.VERTICAL);
		setGravity(Gravity.CENTER);
//		int margin = getResources().getDimensionPixelSize(R.dimen.margine_top);
//		int padding = getResources().getDimensionPixelSize(R.dimen.board_padding);
//		mScreenHeight = getResources().getDisplayMetrics().heightPixels - margin - padding*2;
//		mScreenWidth = getResources().getDisplayMetrics().widthPixels - padding*2 - Utils.px(20);
//		mViewReference = new HashMap<Integer, TileView>();
		setClipToPadding(false);
	}

	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
	}

	public static BoardView fromXml(Context context, ViewGroup parent) {
		return (BoardView) LayoutInflater.from(context).inflate(R.layout.board_view, parent, false);
	}

}
