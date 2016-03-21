package com.example.jinrou2.Base.OriginalViewBase.Lib;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.MyConstants;

/**
 * Created by ryouta on 2015/12/30.
 */
public abstract class MovingLinearLayout extends LinearLayout implements View.OnTouchListener {
    /** タッチ初期位置X */
    private float downX;
    /** タッチ初期位置Y */
    private float downY;
    private int downLeftMargin;
    private int downTopMargin;

    //親ビュー
    private View parentView;

    public MovingLinearLayout(Context context,View parentView) {
        super(context);
        this.parentView = parentView;
        this.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        ViewGroup.MarginLayoutParams param =
                (ViewGroup.MarginLayoutParams) v.getLayoutParams();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            downX = event.getRawX();
            downY = event.getRawY();
            downLeftMargin = param.leftMargin;
            downTopMargin = param.topMargin;
            return true;
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            param.leftMargin = downLeftMargin + (int) (event.getRawX() - downX);
            param.topMargin = downTopMargin + (int) (event.getRawY() - downY);
            v.layout(param.leftMargin,
                    param.topMargin,
                    param.leftMargin + v.getWidth(),
                    param.topMargin + v.getHeight());
            return false;
        }else if (event.getAction() == MotionEvent.ACTION_UP) {
            if (event.getEventTime() - event.getDownTime() < 130) {
                touchAction();
            }
        }
        return false;
    }
    //タッチ時の処理
    protected abstract void touchAction();
}
