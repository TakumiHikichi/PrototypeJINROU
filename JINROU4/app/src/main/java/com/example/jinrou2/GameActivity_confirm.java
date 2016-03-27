package com.example.jinrou2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.CharaEnum;
import com.example.MyConstants;
import com.example.jinrou2.Base.ActivityBase.Lib.Act001PlayBGM;
import com.example.jinrou2.Base.TextBase.AutoFontTextArea;
import com.example.jinrou2.Data.DataHelper.GameDataHelper;
import com.example.jinrou2.Data.GameData;
import com.example.jinrou2.Data.PlayerData;
import com.example.jinrou2.Data.SettingData;
import com.example.jinrou2.Data.character.Action.ActionIF;
import com.example.jinrou2.Lib.ToSpeech;

import java.util.ArrayList;

/**
 * Created by ryouta on 2015/12/16.
 */
public class GameActivity_confirm extends Act001PlayBGM implements View.OnClickListener{

    //ゲーム情報
    GameData gd;
    //役職説明済み人数
    int confirmCounter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //プレイヤー人数
        int members;
        int counterBuf;
        ArrayList<CharaEnum> useCharacters=new ArrayList();
        Boolean charaAddFlug = false;

        //音声再生
        startMusic(R.raw.gaconfirm);
        setContentView(R.layout.game_activity_confirm);
        //ゲームデータの受け取り
        gd=GameData.getInstance();

        //ヘッダ部にタイトル挿入
        LinearLayout llHeader = (LinearLayout)findViewById(R.id.header);
        AutoFontTextArea aftaHead = new AutoFontTextArea(this);
        aftaHead.setText(MyConstants.PLAYER_CHECK_DEFAULT_TITLE);
        aftaHead.setGravity(Gravity.CENTER);
        aftaHead.setTextColor(Color.RED);
        llHeader.addView(aftaHead);

        /**************************************************
         プリファレンスを用いて配役情報を取得する想定。各役職の人数を受け取りたい
         **************************************************/

        //ボディ部に役職一覧を挿入したい
        LinearLayout llBody = (LinearLayout)findViewById(R.id.body);
        llBody.setOrientation(LinearLayout.VERTICAL);
        members = gd.getPlayerDatas().size();
        CharaEnum characters[] = new CharaEnum[members];
        SettingData.getSettingData(members, characters, this);
        //使用役職一覧作成
        for(CharaEnum ce:characters){
            charaAddFlug=true;
            for(CharaEnum ceListVal:useCharacters){
                if(ce.equals(ceListVal))charaAddFlug=false;
            }
            if(charaAddFlug)useCharacters.add(ce);
        }

        TextView afta[] = new TextView[useCharacters.size()*2];
        LinearLayout line[] = new LinearLayout[useCharacters.size()];
        LinearLayout.LayoutParams[] textLp = new LinearLayout.LayoutParams[useCharacters.size()*2];
        LinearLayout.LayoutParams[] lineLp = new LinearLayout.LayoutParams[useCharacters.size()];
        for(int i=0;i<useCharacters.size();i++){
            //各役職毎に人数を確認
            counterBuf=0;
            for(CharaEnum ce:characters)if(useCharacters.get(i).equals(ce))counterBuf++;

            //１行を定義
            line[i]=new LinearLayout(this);
            lineLp[i]=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1);
            line[i].setOrientation(LinearLayout.HORIZONTAL);
            line[i].setLayoutParams(lineLp[i]);
            //１行の左部
            textLp[i*2]=new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1);
            afta[i*2]=new AutoFontTextArea(this);
            afta[i*2].setText(useCharacters.get(i).getCharaName());
            afta[i*2].setTextColor(Color.RED);
            afta[i*2].setLayoutParams(textLp[i]);
            line[i].addView(afta[i*2]);

            //１行の右部
            textLp[i*2+1]=new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,1);
            afta[i*2+1]=new AutoFontTextArea(this);
            afta[i*2+1].setText(" ："+counterBuf+MyConstants.MEMBER_RIGHT_DISCRIPTION);
            afta[i*2+1].setTextColor(Color.RED);
            afta[i*2+1].setLayoutParams(textLp[i]);
            line[i].addView(afta[i*2+1]);


            llBody.addView(line[i]);
        }


        /**************************************************
         プリファレンスを用いて配役情報を取得する想定。各役職の人数を受け取りたい
         **************************************************/

        Button nextButton = (Button)findViewById(R.id.nextButton);
        nextButton.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        ToSpeech ts = ToSpeech.getInstance(this);

        final Activity act = this;
        GameDataHelper gdh = new GameDataHelper();
        String nextButtonStr = new String();
        Button nextButton = (Button)findViewById(R.id.nextButton);
        //ゲーム開始時のアナウンス
        ArrayList<String> announce = new ArrayList();
        //初日にアクションを行うリスト
        ArrayList<ActionIF> firstActions;

        //ページレイアウト挿入エリア
        final LinearLayout llHeader = (LinearLayout)findViewById(R.id.header);
        final LinearLayout llBody = (LinearLayout)findViewById(R.id.body);
        final LinearLayout llVictoryConditionAreaHead = (LinearLayout)findViewById(R.id.VictoryConditionAreaHead);
        final LinearLayout llVictoryConditionAreaBody = (LinearLayout)findViewById(R.id.VictoryConditionAreaBody);
        final LinearLayout llActionAreaHead = (LinearLayout)findViewById(R.id.ActionAreaHead);
        final LinearLayout llActionAreaBody = (LinearLayout)findViewById(R.id.ActionAreaBody);

        //各エリアを初期化
        llHeader.removeAllViews();
        llBody.removeAllViews();
        llVictoryConditionAreaBody.removeAllViews();
        llVictoryConditionAreaHead.removeAllViews();
        llActionAreaBody.removeAllViews();
        llActionAreaHead.removeAllViews();

        //全員が確認していない場合
        if(gd.getPlayerDatas().size()>confirmCounter) {
            //役職確認用対象プレイヤー
            final PlayerData targetPlayerData = gd.getPlayerDatas().get(confirmCounter);
            //次のプレイヤー名をコール
            ts.speech(MyConstants.PLAYER_CHECK_NEXT_ONE1+targetPlayerData.getName()+MyConstants.PLAYER_CHECK_NEXT_ONE2);

            //次へ回すダイアログ表示
            new AlertDialog.Builder(act)
                    .setTitle(MyConstants.PLAYER_CHECK_TITLE)
                    .setCancelable(false)
                    .setMessage(MyConstants.PLAYER_CHECK_NEXT_ONE1 + targetPlayerData.getName() + MyConstants.PLAYER_CHECK_NEXT_ONE2)
                    .setPositiveButton(MyConstants.BTN_OK, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            //確認ダイアログ
                            new AlertDialog.Builder(act)
                                    .setTitle(MyConstants.PLAYER_CHECK_TITLE)
                                    .setCancelable(false)
                                    .setMessage(MyConstants.PLAYER_CHECK1 + targetPlayerData.getName() + MyConstants.PLAYER_CHECK2)
                                    .setPositiveButton(MyConstants.BTN_OK, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            CharaEnum ceBuf = targetPlayerData.getChara();
                                            //ヘッダ部用テキストエリア役職記入
                                            AutoFontTextArea aftaHead = new AutoFontTextArea(act);
                                            aftaHead.setText(MyConstants.PLAYER_CHECK_PLAYER+targetPlayerData.getName()+MyConstants.PLAYER_CHECK_YOUR1 + "\n" + ceBuf.getCamp() + ceBuf.getCharaName() + MyConstants.PLAYER_CHECK_YOUR2);
                                            aftaHead.setGravity(Gravity.CENTER);
                                            aftaHead.setTextColor(Color.RED);
                                            //【勝利条件】
                                            AutoFontTextArea aftaVictoryConditionsHead = new AutoFontTextArea(act);
                                            aftaVictoryConditionsHead.setText(MyConstants.PLAYER_CHECK_VICTORY_CONDITIONS);
                                            aftaVictoryConditionsHead.setGravity(Gravity.LEFT);
                                            aftaVictoryConditionsHead.setTextColor(Color.RED);
                                            //勝利条件内容
                                            AutoFontTextArea aftaVictoryConditionsBody = new AutoFontTextArea(act);
                                            aftaVictoryConditionsBody.setText(ceBuf.getVictoryConditions());
                                            aftaVictoryConditionsBody.setGravity(Gravity.LEFT);
                                            aftaVictoryConditionsBody.setTextColor(Color.RED);
                                            //【アクション】
                                            AutoFontTextArea aftaActionHead = new AutoFontTextArea(act);
                                            aftaActionHead.setText(MyConstants.PLAYER_CHECK_ACTION);
                                            aftaActionHead.setGravity(Gravity.LEFT);
                                            aftaActionHead.setTextColor(Color.RED);
                                            //アクション内容
                                            AutoFontTextArea aftaActionBody = new AutoFontTextArea(act);
                                            aftaActionBody.setText(ceBuf.getCharaConfirm());
                                            aftaActionBody.setGravity(Gravity.LEFT);
                                            aftaActionBody.setTextColor(Color.RED);
                                            //挿入用画面
                                            ImageView iv = new ImageView(act);
                                            iv.setLayoutParams(new LinearLayout.LayoutParams(
                                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                                    LinearLayout.LayoutParams.MATCH_PARENT));
                                            iv.setImageResource(ceBuf.getGraphicId());
                                            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

                                            //それぞれをViewに設定
                                            llHeader.addView(aftaHead);
                                            llBody.addView(iv);
                                            llVictoryConditionAreaHead.addView(aftaVictoryConditionsHead);
                                            llVictoryConditionAreaBody.addView(aftaVictoryConditionsBody);
                                            llActionAreaHead.addView(aftaActionHead);
                                            llActionAreaBody.addView(aftaActionBody);
                                        }
                                    }).show();
                        }
                    }).show();
            //次のプレイヤーへ
            confirmCounter++;
            if (gd.getPlayerDatas().size() > confirmCounter) {
                nextButtonStr = MyConstants.PLAYER_CHECK_NEXT_ONE;
            } else {
                nextButtonStr = MyConstants.PLAYER_CHECK_NEXT_PAGE;
            }
            nextButton.setText(nextButtonStr);
        }else{
            //初日に行うアクションリストを取得
            firstActions=gdh.getFirstActionList();
            //ゲーム開始のアナウンスを設定
            announce.add(MyConstants.START_GAME);
            //夜からの場合目を閉じるアナウンス
            if(!firstActions.get(0).getEnum().equals(CharaEnum.Day))announce.add(MyConstants.START_ANNOUNCE);
            //最初に行うアクションのアナウンスを追加
            for(String buf:firstActions.get(0).voiceGuide_StartStr())announce.add(buf);
            //アナウンス開始
            ts.speech(announce);
            //初期フラグを立てる
            gd.setStartFlug(true);
            //人数分の確認が終わった場合、ゲームメインへ遷移
            Intent intent = new Intent(GameActivity_confirm.this, GameActivity_main.class);
            intent.putExtra(MyConstants.INTENT_KEY, GameData.getInstance());
            startActivity(intent);
        }
    }
}
