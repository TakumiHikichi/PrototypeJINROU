package com.example.jinrou2.Base.ButtonBase.Lib;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by ryouta on 2015/11/03.
 */
public class Btn001LongTouchMove extends Button{
    public Btn001LongTouchMove(Context con){
        super(con);
        this.setOnTouchListener(new OnTouchListener() {
            private float downX;
            private float downY;
            private int downLeftMargin;
            private int downTopMargin;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ViewGroup.MarginLayoutParams param =
                        (ViewGroup.MarginLayoutParams) v.getLayoutParams();
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    downX = event.getRawX();
                    downY = event.getRawY();
                    downLeftMargin = param.leftMargin;
                    downTopMargin = param.topMargin;
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    param.leftMargin = downLeftMargin + (int) (event.getRawX() - downX);
                    param.topMargin = downTopMargin + (int) (event.getRawY() - downY);
                    v.layout(param.leftMargin,
                            param.topMargin,
                            param.leftMargin + v.getWidth(),
                            param.topMargin + v.getHeight());
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getEventTime() - event.getDownTime() < 100) ;
                    //ここでタッチ処理
                }
                return false;
            }
        });

    }
}
