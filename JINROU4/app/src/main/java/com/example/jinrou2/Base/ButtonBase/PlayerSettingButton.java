package com.example.jinrou2.Base.ButtonBase;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.MyConstants;
import com.example.jinrou2.GameActivity_setting;
import com.example.jinrou2.R;

import java.util.Random;

/**
 * Created by ryouta on 2015/11/08.
 */
public class PlayerSettingButton extends Button {
    /**
     *
     * @param playerName ボタンテキスト
     * @param parentLayout 親レイアウト
     * @param ga 表示アクティビティ
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public PlayerSettingButton(String playerName, final RelativeLayout parentLayout,final Activity ga) {
        super(ga.getApplicationContext());
        //親のレイアウトからIDを動的に取得し設定
        this.setId(parentLayout.generateViewId());
        this.setBackgroundColor(Color.argb(0, 0, 0, 0));
        this.setText(playerName);
        this.setTypeface(Typeface.MONOSPACE);
        this.setTextColor(Color.RED);
        //画像ランダム表示
        //changeCharaIcon(ga);

        this.setWidth(parentLayout.getWidth()/4);
        this.setHeight(parentLayout.getHeight() / 4);
        //this.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.button_player, 0, 0);

        Drawable graphics = getResources().getDrawable(R.drawable.button_player);
        graphics.setBounds(0,0,parentLayout.getWidth()/4,parentLayout.getHeight() / 4-60);
        this.setCompoundDrawables(null,graphics,null,null);

        this.setOnTouchListener(new OnTouchListener() {
            private float downX;
            private float downY;
            private int downLeftMargin;
            private int downTopMargin;

            @Override
            public boolean onTouch(final View v, MotionEvent event) {
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
                }else if(event.getAction()==MotionEvent.ACTION_UP){
                    if(event.getEventTime() - event.getDownTime() < 130) {
                        //ここでタッチ処理
                        final EditText editView = new EditText(ga.getApplicationContext());
                        editView.setBackgroundColor(Color.WHITE);
                        editView.setTextColor(Color.BLACK);
                        editView.setText(((Button)v).getText().toString());
                        editView.setSelection(((Button) v).getText().toString().length());
                        new AlertDialog.Builder(ga)
                            .setView(editView)
                            .setTitle(MyConstants.CHECK_PLAYER_DEL_DIALOG_TITLE)
                            .setPositiveButton(MyConstants.BTN_OK, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    ((Button) v).setText(editView.getText().toString());
                                }
                            })
                            .setNegativeButton(MyConstants.BTN_DELETE, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    parentLayout.removeView(v);
                                }
                            })
                            .show();
                    }
                }
                return false;
            }
        });
    }
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom)
    {
        super.onLayout(changed, left, top, right, bottom);
        textResize();
    }
    //未テスト
    private void textResize()
    {
        /** 最小のテキストサイズ */
        final float MIN_TEXT_SIZE = 6f;
        int viewHeight = this.getHeight();	// Viewの縦幅
        int viewWidth = this.getWidth();	// Viewの横幅
        // テキストサイズ
        float textSize = getTextSize();
        // Paintにテキストサイズ設定
        Paint paint = new Paint();
        paint.setTextSize(textSize);

        // テキストの縦幅取得
        Paint.FontMetrics fm = paint.getFontMetrics();
        float textHeight = (float) (Math.abs(fm.top)) + (Math.abs(fm.descent));

        // テキストの横幅取得
        float textWidth = paint.measureText(this.getText().toString());

        // 縦幅と、横幅が収まるまでループ
        while (viewHeight < textHeight | viewWidth < textWidth)
        {
            // 調整しているテキストサイズが、定義している最小サイズ以下か。
            if (MIN_TEXT_SIZE >= textSize)
            {
                // 最小サイズ以下になる場合は最小サイズ
                textSize = MIN_TEXT_SIZE;
                break;
            }
            // テキストサイズをデクリメント
            textSize--;
            // Paintにテキストサイズ設定
            paint.setTextSize(textSize);
            // テキストの縦幅を再取得
            fm = paint.getFontMetrics();
            textHeight = (float) (Math.abs(fm.top)) + (Math.abs(fm.descent));
            // テキストの横幅を再取得
            textWidth = paint.measureText(this.getText().toString());
        }
        // テキストサイズ設定
        setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }
    public void changeCharaIcon(Activity act){
        int id=0;
        int buf=0;
        Random rnd = new Random();
        buf =1+ rnd.nextInt(30);
        id = getResources().getIdentifier("man"+buf, "drawable", act.getPackageName());
        this.setCompoundDrawablesWithIntrinsicBounds(0, id, 0, 0);
    }
}
