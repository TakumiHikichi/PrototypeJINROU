package com.example.jinrou2.Base.ActivityBase;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.MyConstants;
import com.example.jinrou2.Base.ActivityBase.Lib.Act001PlayBGM;
import com.example.jinrou2.Base.OriginalViewBase.ImgTextButton_action;
import com.example.jinrou2.Base.OriginalViewBase.ImgTextButton_setting;
import com.example.jinrou2.Base.TextBase.AutoFontTextArea;
import com.example.jinrou2.Data.GameData;
import com.example.jinrou2.Data.PlayerData;
import com.example.jinrou2.R;

/**
 * Created by ryouta on 2015/11/08.
 */
public abstract class GameBaseActivity extends Act001PlayBGM implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_field);
        makePlayerButtons(true,false);
    }
    protected void makePlayerButtons(boolean playerButtonActive ,boolean underButtonActive){
        //ボタンフィールド
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.game_field);
        //画面下部ボタン
        Button underButton = (Button)findViewById(R.id.button1);
        underButton.setEnabled(underButtonActive);
        //ゲームフィールドを1度初期化
        layout.removeAllViews();
        GameData gd = GameData.getInstance();

        Intent intent = getIntent();
        //インテントからゲームデータ取得
        gd = (GameData)GameData.getInstance();

        //プレイヤーデータバッファ
        PlayerData pdBuf = new PlayerData();
        if(gd!=null){
            for(int i=0;i<gd.getPlayerDatas().size();i++){
                pdBuf=gd.getPlayerDatas().get(i);
                ImgTextButton_action itb = new ImgTextButton_action(pdBuf,this,pdBuf.getButtonId(),playerButtonActive);
                itb.setX(pdBuf.getButtonLeft());
                itb.setY(pdBuf.getButtonTop());
                itb.setOnClickListener(this);
                layout.addView(itb);
            }
        }
    }
    protected void makeActionResult(View v){
        //ボタンフィールド
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.game_field);
        //画面下部ボタン
        Button underButton = (Button)findViewById(R.id.button1);
        underButton.setEnabled(true);
        //ボタンフィールドを1度初期化
        layout.removeAllViews();
        LinearLayout.LayoutParams mainLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        v.setLayoutParams(mainLp);
        layout.addView(v);
    }
    protected void makeActionWait(String msg){
        //ボタンフィールド
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.game_field);
        //ボタンフィールドを1度初期化
        layout.removeAllViews();
        //画面下部ボタン
        Button underButton = (Button)findViewById(R.id.button1);
        underButton.setEnabled(false);

        AutoFontTextArea afta=new AutoFontTextArea(this);
        afta.setText(msg);
        afta.setGravity(Gravity.CENTER);
        afta.setTextColor(Color.RED);
        LinearLayout.LayoutParams mainLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        afta.setLayoutParams(mainLp);
        layout.addView(afta);
    }
    @Override
    public abstract void onClick(View v);
}
