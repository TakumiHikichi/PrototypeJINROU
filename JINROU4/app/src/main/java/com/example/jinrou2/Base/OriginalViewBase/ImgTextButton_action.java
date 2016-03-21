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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.MyConstants;
import com.example.jinrou2.Base.OriginalViewBase.Lib.MovingLinearLayout;
import com.example.jinrou2.Base.TextBase.AutoFontTextArea;
import com.example.jinrou2.Data.GameData;
import com.example.jinrou2.Data.PlayerData;
import com.example.jinrou2.R;

/**
 * Created by ryouta on 2015/12/29.
 */
public class ImgTextButton_action extends LinearLayout{
    //テキストエリアのテキスト
    private String playerName;
    //テキスト
    private AutoFontTextArea bottomText;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public ImgTextButton_action(PlayerData pd,final Activity act,int layoutId,boolean buttonActive){
        super(act.getApplicationContext());
        GameData gd = GameData.getInstance();
        int imageId;
        this.playerName=pd.getName();
        //プレイヤーが生きている場合
        if(pd.getLivingFlug()){
            //ボタンがアクティブの場合
            if(buttonActive) {
                imageId = R.drawable.button_player;
            }else{
                imageId=R.drawable.button_player_negative;
            }
        }else{
            imageId=R.drawable.negativebutton_player;
        }
        //IDの自動設定
        this.setId(layoutId);
        this.setOrientation(LinearLayout.VERTICAL);
        LayoutParams mainLp = new LayoutParams(gd.getIconWidth(),gd.getIconHeight());
        //パラメータ設定
        this.setLayoutParams(mainLp);

        //各レイアウトのパラメータ
        LayoutParams imageLp=new LayoutParams(LayoutParams.MATCH_PARENT,0,80);
        LayoutParams textLp=new LayoutParams(LayoutParams.MATCH_PARENT,0,20);

        ImageView topImage = new ImageView(act.getApplicationContext());
        final AutoFontTextArea bottomText = new AutoFontTextArea(act.getApplicationContext());

        //イメージの定義
        topImage.setLayoutParams(imageLp);
        topImage.setImageResource(imageId);
        this.addView(topImage);
        //下部テキスト定義
        bottomText.setText(playerName);
        bottomText.setTextColor(Color.RED);
        bottomText.setGravity(Gravity.CENTER);
        bottomText.setLayoutParams(textLp);
        this.bottomText=bottomText;
        this.addView(bottomText);
    }
    public String getText(){
        return this.playerName;
    }

}
