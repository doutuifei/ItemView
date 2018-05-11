package com.muzi.itemview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.LinearInterpolator;

/**
 * Created by muzi on 2018/5/11.
 * 727784430@qq.com
 */
public class ItemView extends View {

    //大小
    private float viewHeight;
    private float viewOpenWidth;
    private float viewCloseWidth;
    private float curreWidth;

    //图片
    private int imgId = R.drawable.img2;
    private Bitmap bitmap;
    private float bitmapWidth;
    private RectF bitmapRectF;

    //框
    private Rect rect;
    private int vPadding = 8;

    //文字
    private Rect textRect;
    private String text = "人气";
    private float textHeight, textWidth;

    private Paint paint;

    private boolean isShow = false;

    public ItemView(Context context, int imgId, String text) {
        super(context, null);
        this.imgId = imgId;
        this.text = text;
        init();
    }

    public ItemView(Context context, boolean isShow, int imgId, String text) {
        super(context, null);
        this.isShow = isShow;
        this.imgId = imgId;
        this.text = text;
        init();
    }

    public ItemView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        initSize();
        initBitMap();
        initText();
    }

    public void toggle() {
        if (isShow) {
            closeAnimator();
        } else {
            openAnimator();
        }
    }

    public boolean isShow() {
        return isShow;
    }

    private void initSize() {
        WindowManager manager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(metrics);
        int screesWidth = metrics.widthPixels;
        viewCloseWidth = screesWidth / 7f;
        viewHeight = viewOpenWidth = viewCloseWidth * 3f;

        if (isShow) {
            curreWidth = viewOpenWidth;
        } else {
            curreWidth = viewCloseWidth;
        }

        paint = new Paint();
        paint.setTextSize(25);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
    }

    private void initBitMap() {
        bitmap = BitmapFactory.decodeResource(getResources(), imgId);
        bitmapWidth = bitmap.getWidth();

        bitmapRectF = new RectF();
        bitmapRectF.top = 0;
        bitmapRectF.bottom = viewHeight;
        bitmapRectF.left = 0;
        bitmapRectF.right = bitmapWidth;
    }

    private void initText() {
        textRect = new Rect();
        paint.setTextSize(25);
        paint.getTextBounds(text, 0, text.length(), textRect);
        textWidth = textRect.right - textRect.left;
        textHeight = textRect.bottom - textRect.top;

        rect = new Rect();
        rect.top = (int) (viewHeight / 2 - textHeight / 2 - vPadding);
        rect.bottom = (int) (viewHeight / 2 + textHeight / 2 + vPadding);

        rect.left = (int) (curreWidth / 2 - viewCloseWidth / 2);
        rect.right = (int) (curreWidth / 2 + viewCloseWidth / 2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, null, bitmapRectF, paint);

        //框
        paint.setColor(Color.parseColor("#80000000"));
        rect.left = (int) (curreWidth / 2 - viewCloseWidth / 2);
        rect.right = (int) (curreWidth / 2 + viewCloseWidth / 2);
        canvas.drawRect(rect, paint);

        //文字
        paint.setColor(Color.WHITE);
        canvas.drawText(text, curreWidth / 2 - textWidth / 2, viewHeight / 2 + textHeight / 2, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension((int) curreWidth, (int) viewHeight);
    }

    private void openAnimator() {
        if (isShow) {
            return;
        }
        isShow = true;
        ValueAnimator openAnimator = ValueAnimator.ofFloat(viewCloseWidth, viewOpenWidth);
        openAnimator.setDuration(500);
        openAnimator.setInterpolator(new LinearInterpolator());
        openAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                curreWidth = (float) animation.getAnimatedValue();
                requestLayout();
            }
        });
        openAnimator.start();
    }

    private void closeAnimator() {
        if (!isShow) {
            return;
        }
        isShow = false;
        ValueAnimator closeAnimator = ValueAnimator.ofFloat(viewOpenWidth, viewCloseWidth);
        closeAnimator.setDuration(500);
        closeAnimator.setInterpolator(new LinearInterpolator());
        closeAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                curreWidth = (float) animation.getAnimatedValue();
                requestLayout();
            }
        });
        closeAnimator.start();
    }

}
