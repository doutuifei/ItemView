package com.muzi.itemview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by muzi on 2018/5/11.
 * 727784430@qq.com
 */
public class ItemGroup extends LinearLayout implements View.OnClickListener {

    private int currePosition = 2;
    private int nextPosition = -1;
    private int margin = 2;

    public ItemGroup(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        ItemView itemView0 = new ItemView(getContext(), R.drawable.img1, "潮品");
        MarginLayoutParams params0 = new MarginLayoutParams(MarginLayoutParams.MATCH_PARENT, MarginLayoutParams.MATCH_PARENT);
        params0.rightMargin = margin;
        itemView0.setLayoutParams(params0);
        itemView0.setId(R.id.view0);
        itemView0.setOnClickListener(this);
        addView(itemView0);

        ItemView itemView1 = new ItemView(getContext(), R.drawable.img2, "搭配");
        MarginLayoutParams params1 = new MarginLayoutParams(MarginLayoutParams.MATCH_PARENT, MarginLayoutParams.MATCH_PARENT);
        params1.leftMargin = margin;
        params1.rightMargin = margin;
        itemView1.setLayoutParams(params1);
        itemView1.setId(R.id.view1);
        itemView1.setOnClickListener(this);
        addView(itemView1);

        ItemView itemView2 = new ItemView(getContext(), true, R.drawable.img3, "人气");
        MarginLayoutParams params2 = new MarginLayoutParams(MarginLayoutParams.MATCH_PARENT, MarginLayoutParams.MATCH_PARENT);
        params2.leftMargin = margin;
        params2.rightMargin = margin;
        itemView2.setLayoutParams(params2);
        itemView2.setId(R.id.view2);
        itemView2.setOnClickListener(this);
        addView(itemView2);

        ItemView itemView3 = new ItemView(getContext(), R.drawable.img4, "晒单");
        MarginLayoutParams params3 = new MarginLayoutParams(MarginLayoutParams.MATCH_PARENT, MarginLayoutParams.MATCH_PARENT);
        params3.leftMargin = margin;
        params3.rightMargin = margin;
        itemView3.setLayoutParams(params3);
        itemView3.setId(R.id.view3);
        itemView3.setOnClickListener(this);
        addView(itemView3);

        ItemView itemView4 = new ItemView(getContext(), R.drawable.img5, "BuyTV");
        MarginLayoutParams params4 = new MarginLayoutParams(MarginLayoutParams.MATCH_PARENT, MarginLayoutParams.MATCH_PARENT);
        params4.leftMargin = margin;
        itemView4.setLayoutParams(params4);
        itemView4.setId(R.id.view4);
        itemView4.setOnClickListener(this);
        addView(itemView4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view0:
                nextPosition = 0;
                break;
            case R.id.view1:
                nextPosition = 1;
                break;
            case R.id.view2:
                nextPosition = 2;
                break;
            case R.id.view3:
                nextPosition = 3;
                break;
            case R.id.view4:
                nextPosition = 4;
                break;
        }
        if (nextPosition == currePosition) {
            if (onSelect != null) {
                onSelect.onSelect(currePosition);
            }
            return;
        } else {
            ItemView nextView = (ItemView) getChildAt(nextPosition);
            nextView.toggle();
            ItemView curreView = (ItemView) getChildAt(currePosition);
            curreView.toggle();
            currePosition = nextPosition;
        }
    }

    private OnSelect onSelect;

    public void setOnSelect(OnSelect onSelect) {
        this.onSelect = onSelect;
    }

    public interface OnSelect {
        void onSelect(int position);
    }

}
