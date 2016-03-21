package com.example.jinrou2;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.CampEnum;
import com.example.CharaEnum;
import com.example.MyConstants;
import com.example.jinrou2.Base.ActivityBase.Lib.Act001PlayBGM;
import com.example.jinrou2.Base.TextBase.AutoFontTextArea;
import com.example.jinrou2.Data.DataHelper.GameDataHelper;
import com.example.jinrou2.Data.GameData;
import com.example.jinrou2.Data.PlayerData;
import com.example.jinrou2.Data.SettingData;

import java.util.ArrayList;

/**
 * Created by ryouta on 2016/03/05.
 */
public class GameActivity_end extends Act001PlayBGM implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //参加者
        int members=0;
        //参加者カウント用
        int memberCount=0;

        //音声再生
        startMusic(R.raw.gaconfirm);
        //画面生成
        setContentView(R.layout.game_activity_confirm);
        //データ
        GameData gd = GameData.getInstance();
        GameDataHelper gdh = new GameDataHelper();
        //勝利陣営取得
        CampEnum winnerCamp = gdh.checkVictory_Defeat();

        //ヘッダ部にタイトル挿入
        LinearLayout llHeader = (LinearLayout)findViewById(R.id.header);
        AutoFontTextArea aftaHead = new AutoFontTextArea(this);
        aftaHead.setText(MyConstants.WIN+winnerCamp.getCamp());
        aftaHead.setGravity(Gravity.CENTER);
        aftaHead.setTextColor(Color.WHITE);
        llHeader.addView(aftaHead);

        //ボディ部にプレイヤー一覧を挿入したい
        LinearLayout llBody = (LinearLayout)findViewById(R.id.body);
        llBody.setOrientation(LinearLayout.VERTICAL);
        members = gd.getPlayerDatas().size();
        LinearLayout line[] = new LinearLayout[members];
        AutoFontTextArea aftaLeft[] = new AutoFontTextArea[members];
        AutoFontTextArea aftaCenter[] = new AutoFontTextArea[members];
        AutoFontTextArea aftaRight[] = new AutoFontTextArea[members];
        LinearLayout.LayoutParams[] lineLp = new LinearLayout.LayoutParams[members];
        LinearLayout.LayoutParams[] textLpLeft = new LinearLayout.LayoutParams[members];
        LinearLayout.LayoutParams[] textLpCenter = new LinearLayout.LayoutParams[members];
        LinearLayout.LayoutParams[] textLpRight = new LinearLayout.LayoutParams[members];

        //使用役職一覧の行情報定義
        for(int i=0;i<members;i++){
            //１行を定義
            line[i]=new LinearLayout(this);
            lineLp[i]=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1);
            line[i].setOrientation(LinearLayout.HORIZONTAL);
            line[i].setLayoutParams(lineLp[i]);
            //１行の左部定義
            textLpLeft[i]=new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,20);
            textLpLeft[i].gravity=Gravity.CENTER;
            aftaLeft[i]=new AutoFontTextArea(this);
            aftaLeft[i].setLayoutParams(textLpLeft[i]);
            //１行の中央部定義
            textLpCenter[i]=new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,30);
            textLpCenter[i].gravity=Gravity.CENTER;
            aftaCenter[i]=new AutoFontTextArea(this);
            aftaCenter[i].setLayoutParams(textLpCenter[i]);
            //１行の右部定義
            textLpRight[i]=new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,50);
            textLpRight[i].gravity=Gravity.LEFT;
            aftaRight[i]=new AutoFontTextArea(this);
            aftaRight[i].setLayoutParams(textLpRight[i]);
        }
        memberCount=0;
        for(PlayerData pdBuf:gdh.getWinnerList()){
            aftaLeft[memberCount].setText(MyConstants.WIN);
            aftaLeft[memberCount].setTextColor(Color.WHITE);
            aftaLeft[memberCount].setLayoutParams(textLpLeft[memberCount]);
            aftaCenter[memberCount].setText(pdBuf.getChara().getCharaName());
            aftaCenter[memberCount].setTextColor(Color.WHITE);
            aftaCenter[memberCount].setLayoutParams(textLpCenter[memberCount]);
            aftaRight[memberCount].setText(pdBuf.getName());
            aftaRight[memberCount].setTextColor(Color.WHITE);
            aftaRight[memberCount].setLayoutParams(textLpRight[memberCount]);
            line[memberCount].addView(aftaLeft[memberCount]);
            line[memberCount].addView(aftaCenter[memberCount]);
            line[memberCount].addView(aftaRight[memberCount]);
            llBody.addView(line[memberCount]);
            memberCount++;
        }
        for(PlayerData pdBuf:gdh.getLoserList()){
            aftaLeft[memberCount].setText(MyConstants.LOSE);
            aftaLeft[memberCount].setTextColor(Color.RED);
            aftaLeft[memberCount].setLayoutParams(textLpLeft[memberCount]);
            aftaCenter[memberCount].setText(pdBuf.getChara().getCharaName());
            aftaCenter[memberCount].setTextColor(Color.RED);
            aftaCenter[memberCount].setLayoutParams(textLpCenter[memberCount]);
            aftaRight[memberCount].setText(pdBuf.getName());
            aftaRight[memberCount].setTextColor(Color.RED);
            aftaRight[memberCount].setLayoutParams(textLpRight[memberCount]);
            line[memberCount].addView(aftaLeft[memberCount]);
            line[memberCount].addView(aftaCenter[memberCount]);
            line[memberCount].addView(aftaRight[memberCount]);
            llBody.addView(line[memberCount]);
            memberCount++;
        }

    }
    @Override
    public void onClick(View v) {

    }
}
