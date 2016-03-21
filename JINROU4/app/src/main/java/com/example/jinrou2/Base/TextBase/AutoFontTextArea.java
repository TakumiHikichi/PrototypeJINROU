package com.example.jinrou2.Base.TextBase;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by ryouta on 2015/11/22.
 */
public class AutoFontTextArea extends TextView {
    public AutoFontTextArea(Context context) {
        super(context);
        this.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT));
//        this.setBackgroundColor(Color.BLUE);
        this.setTextSize(this.getHeight());
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
        float textSize = MIN_TEXT_SIZE;
        // Paintにテキストサイズ設定
        Paint paint = new Paint();
        paint.setTextSize(textSize);

        // テキストの縦幅取得
        Paint.FontMetrics fm = paint.getFontMetrics();
        float textHeight = (float) (Math.abs(fm.top)) + (Math.abs(fm.descent));

        // テキストの横幅取得
        float textWidth = paint.measureText(this.getText().toString());

        // 縦幅か横幅が超えるまでループ
        while (viewHeight >= textHeight && viewWidth >= textWidth)
        {
            // テキストサイズをインクリメント
            textSize++;
            // Paintにテキストサイズ設定
            paint.setTextSize(textSize);
            // テキストの縦幅を再取得
            fm = paint.getFontMetrics();
            textHeight = (float) (Math.abs(fm.top)) + (Math.abs(fm.descent));
            // テキストの横幅を再取得
            textWidth = paint.measureText(this.getText().toString());
        }
        textSize--;
        // テキストサイズ設定
        setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
    }
}
