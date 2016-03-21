package com.example.jinrou2.Base.OriginalViewBase;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.MyConstants;
import com.example.jinrou2.Base.OriginalViewBase.Lib.MovingLinearLayout;
import com.example.jinrou2.Base.TextBase.AutoFontTextArea;
import com.example.jinrou2.R;

/**
 * Created by ryouta on 2015/12/29.
 */
public class ImgTextButton_setting extends MovingLinearLayout implements View.OnTouchListener{
    /** タッチ初期位置X */
    private float downX;
    /** タッチ初期位置Y */
    private float downY;
    //親レイアウト
    private RelativeLayout parentLayout;
    //呼び出し元アクティビティー
    private Activity act;
    //このビュー
    private View thisView;
    //テキストエリアのテキスト
    private String playerName;
    //テキスト
    private AutoFontTextArea bottomText;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public ImgTextButton_setting(String playerName, final RelativeLayout parentLayout, final Activity act){
        super(act.getApplicationContext(),parentLayout);
        this.act=act;
        this.playerName=playerName;
        this.parentLayout=parentLayout;
        thisView=this;

        //IDの自動設定
        this.setId(parentLayout.generateViewId());
        this.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams mainLp = new LinearLayout.LayoutParams(parentLayout.getWidth()/4,parentLayout.getHeight()/4);
        //パラメータ設定
        this.setLayoutParams(mainLp);

        //各レイアウトのパラメータ
        LinearLayout.LayoutParams imageLp=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,0,80);
        LinearLayout.LayoutParams textLp=new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,0,20);

        ImageView topImage = new ImageView(act.getApplicationContext());
        final AutoFontTextArea bottomText = new AutoFontTextArea(act.getApplicationContext());

        //イメージの定義
        topImage.setLayoutParams(imageLp);
        topImage.setImageResource(R.drawable.button_player);
        this.addView(topImage);
        //下部テキスト定義
        bottomText.setText(playerName);
        bottomText.setTextColor(Color.RED);
        bottomText.setGravity(Gravity.CENTER);
        bottomText.setLayoutParams(textLp);
        this.bottomText=bottomText;
        this.addView(bottomText);
    }

    @Override
    protected void touchAction() {
        final NameEditText editView = new NameEditText(act.getApplicationContext());
        editView.setText(playerName);
        editView.setSelection(playerName.length());

        new AlertDialog.Builder(act)
                .setTitle(MyConstants.CHECK_PLAYER_DEL_DIALOG_TITLE)
                .setView(editView)
                .setPositiveButton(MyConstants.BTN_OK, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        playerName=editView.getText().toString();
                        bottomText.setText(playerName);
                    }
                })
                .setNegativeButton(MyConstants.BTN_DELETE, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        parentLayout.removeView(thisView);
                    }
                })
                .show();
    }
    public String getText(){
        return this.playerName;
    }
}
