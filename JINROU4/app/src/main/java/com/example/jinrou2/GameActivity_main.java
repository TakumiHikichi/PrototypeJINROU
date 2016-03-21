package com.example.jinrou2;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.CampEnum;
import com.example.CharaEnum;
import com.example.MyConstants;
import com.example.jinrou2.Base.ActivityBase.GameBaseActivity;
import com.example.jinrou2.Data.DataHelper.GameDataHelper;
import com.example.jinrou2.Data.GameData;
import com.example.jinrou2.Data.PlayerData;
import com.example.jinrou2.Data.character.Action.ActionIF;
import com.example.jinrou2.Data.character.Action.ActionRtnVal;
import com.example.jinrou2.Data.character.Action.AllAction.DayAction;
import com.example.jinrou2.Lib.MakePosiNegaDialog;
import com.example.jinrou2.Lib.ToSpeech;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ryouta on 2015/12/06.
 */
public class GameActivity_main extends GameBaseActivity implements View.OnClickListener, DialogInterface.OnClickListener {
    //初日処理フラグ
    private boolean firstFlug;
    //実施するアクション一覧
    private ArrayList<ActionIF> actionKind = new ArrayList();
    //実施アクション番号
    private int actionCounter =0;
    //音声発信用
    private ToSpeech ts;
    //共通ボタン
    private Button bottomBUtton;
    //アクションか、確認かの判定フラグ
    private boolean playActionFlug;
    //アクション対象のID
    private int actionTargetId;
    //終了用スピーチの内容
    ArrayList<String> endSpeechList;
    //開始用スピーチの内容
    ArrayList<String> startSpeechList;
    //動作アクションバッファ
    ActionIF action;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //下部ボタン定義
        bottomBUtton = (Button)findViewById(R.id.button1);
        bottomBUtton.setOnClickListener(this);

        ts=ToSpeech.getInstance(this);
        GameDataHelper gdh = new GameDataHelper();
        //初日かどうかのフラグ
        firstFlug=true;
        //最初に押されるボタン＝プレイヤーボタン（アクション実施）
        playActionFlug=true;
        //初日の夜で使用する役職（一意）
        actionKind=gdh.getFirstActionList();
        //初日の夜に実施する役職が存在しない場合
        if(actionKind.size()==0){
            //初日フラグを降ろす
            firstFlug=false;
            //使用する役職を、通常の夜のものに変更
            actionKind = gdh.getActionList();
        }
        //最初に実行するアクションをバッファに格納
        action=actionKind.get(actionCounter);
    }
    /*
     * 画面状のボタンが押された際の処理
     */
    @Override
    public void onClick(View v) {
        //押されたボタンのID
        actionTargetId=v.getId();
        //YES NOが存在するダイアログを生成
        MakePosiNegaDialog dialog = new MakePosiNegaDialog(this);
        //ボタンが押されるたびに実行するアクションをバッファに格納
        action=actionKind.get(actionCounter);

        //アクションを実施する場合、確認ダイアログを表示
        dialog.setHeader(MyConstants.CHECK);
        dialog.setPositiveButtonText(MyConstants.BTN_OK);
        dialog.setNegativeButtonText(MyConstants.BTN_CANCEL);
        //押されたボタンがプレイヤーのボタンか、下部のボタンか確認
        if(playActionFlug){
            //プレイヤーのボタンの場合、ダイアログのメッセージをアクション実施確認メッセージに
            dialog.setBody(action.charaActionCheckStr(actionTargetId));
        }else{
            //結果確認の場合、ダイアログのメッセージを終了確認メッセージに
            dialog.setBody(action.charaActionEndCheckStr(actionTargetId));
        }
        //ダイアログを表示
        dialog.showDialog(this);
    }

    /*
     * ダイアログのボタンが押された際の処理
     */
    @Override
    public void onClick(DialogInterface dialog, int which) {
        //目を閉じて端末を戻せ～次の人までのアナウンス文字列用
        endSpeechList=new ArrayList();
        //目を開けて端末を操作しろ～の文字列用
        startSpeechList=new ArrayList();
        //ゲームデータから情報を取得用
        GameDataHelper gdh = new GameDataHelper();
        //ランダム生成
        Random rnd;
        //待機時間（×0.5秒）
        int waitTimeHalf=0;
        //待機用文字列バッファ
        String waitStr=new String();
        //勝敗確認用Enum
        CampEnum winnerCamp=null;
        //ダイアログで押されたボタンがPOSITIVEかNEGATIVEか
        switch(which) {
            //POSITIVEボタンの場合
            case DialogInterface.BUTTON_POSITIVE:
                //アクション結果を確認した場合、次のアクションへ行くためのアナウンス
                if(!playActionFlug) {
/* ＊＊＊＊＊＊＊＊＊ここから、アクションの終了メッセージ（次の人開始まで）生成処理＊＊＊＊＊＊＊＊＊*/
                    //アクションの終了メッセ―ジ格納
                    endSpeechList = action.voiceGuide_EndStr();
                    //実施アクション番号を次に
                    actionCounter++;
                    //アクション番号が種類を超えた場合
                    if (actionCounter >= actionKind.size()) {
                        //初日の場合
                        if (firstFlug) {
                            //初日フラグを降ろす
                            firstFlug = false;
                            //通常のアクション一覧を格納
                            actionKind = gdh.getActionList();
                        }
                        //アクション番号を初期に戻す
                        actionCounter = 0;
                    }

                    //アクション対象プレイヤーが死亡している場合
                    while(!gdh.getWeightToTouchCheck( actionKind.get(actionCounter).getEnum())) {
                        //待機時間（ランダム）生成
                        rnd=new Random();
                        waitStr=new String();
                        //0.5秒単位のため、最大時間の２倍から待機時間を取得
                        waitTimeHalf=rnd.nextInt(MyConstants.MAX_WAIT_TIME * 2);
                        //waitTimeHalfの数値文半角スペースを埋め込む（半角スペース読み上げは読み上げ待機（500ms）
                        for(int i=0;i<waitTimeHalf;i++)waitStr=waitStr+" ";
                        //バッファにアクション番号のアクションを格納
                        action = actionKind.get(actionCounter);
                        //アクション対象者が死亡しているため、アクション終了用文字列に次のアクション開始メッセージを格納
                        for (String s : action.voiceGuide_StartStr()) endSpeechList.add(s);
                        //アクション対象者が死亡しているため、一定時間読み上げ待機メッセージを格納
                        endSpeechList.add(waitStr);
                        //アクション対象者が死亡しているため、悪書う終了用文字列に次のアクション終了メッセージを格納
                        for(String s:action.voiceGuide_EndStr())endSpeechList.add(s);
                        //アクション番号を次に
                        actionCounter++;
                        //アクション番号チェック
                        if (actionCounter >= actionKind.size()) {
                            if (firstFlug) {
                                firstFlug = false;
                                actionKind = gdh.getActionList();
                            }
                            actionCounter = 0;
                        }
                    }
/* ＊＊＊＊＊＊＊＊＊ここまで、アクションの終了メッセージ（次の人開始まで）生成処理＊＊＊＊＊＊＊＊＊*/
                    //アクション終了メッセージ読み上げ用スレッド
                    final Thread endSpeechThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Random rnd = new Random();
                            //待ち時間（１につき0.5秒）
                            int waitTimeHalf = rnd.nextInt(MyConstants.MAX_WAIT_TIME * 2);
                            //読み上げ中の場合、読み上げ中のフラグ立てる
                            boolean speakingFlug = false;
                            if(ts.isSpeaking())speakingFlug=true;
                            //読み上げ中でボタンを押された場合、読み終わるまで待機、読み上げ終わってても0.3sec待機
                            do{
                                try{
                                    Thread.sleep(300);
                                }catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }while(ts.isSpeaking());
                            //スピーチ中にボタンを押された場合、終了まで待ちさらにランダムの時間待機
                            if(speakingFlug){
                                try{
                                    Thread.sleep(waitTimeHalf*500);
                                }catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            //終了用読み上げ開始
                            ts.speech(endSpeechList);
                            //終了用読み上げが終了するまで待機
                            while(ts.isSpeaking()){
                                try {
                                    Thread.sleep(300);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }}
                    });
                    endSpeechThread.setName("END_SPEECH");
                    endSpeechThread.start();
                    /*
                    //終了用メッセージの読み上げが終わり次第次のアクション開始メッセージを読み上げるスレッド
                    final Thread startSpeechThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                //終了メッセージ読み上げ待ち
                                endSpeechThread.join();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            //次のアクション開始メッセージ読み上げ
                            ts.speech(startSpeechList);
                        }
                    });
                    startSpeechThread.setName("START_SPEECH");
                    startSpeechThread.start();
                    */
                    //別スレッドから画面切り替える用ハンドラー
                    final Handler handler=new Handler();
                    //終了用メッセージの読み上げが終わり次第画面を切り替える用スレッド
                    final Thread updScreen = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                //終了用メッセージ読み上げ待ち
                                endSpeechThread.join();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            //終了用メッセージの読み上げが終わり次第、ハンドラーへ画面更新をポスト
                            handler.post(new Runnable() {
                                //以下はメインスレッドで実施
                                @Override
                                public void run() {
                                    //実施アクション設定
                                    action = actionKind.get(actionCounter);
                                    //アクションの開始文章取得
                                    for (String s : action.voiceGuide_StartStr()) startSpeechList.add(s);
                                    //アクションの開始文を読み上げ
                                    ts.speech(startSpeechList);
                                    //プレイヤーボタン生成画面に更新
                                    makePlayerButtons(action.playerButtonEnabled(),action.underButtonEnabled());
                                }
                            });
                        }
                    });
                    updScreen.setName("UPD_SCREEN");
                    updScreen.start();
                    //メインスレッドでは操作させない画面を表示
                    makeActionWait(MyConstants.STOP_TOUCH);
                    playActionFlug = true;
                }else{
                //アクションを実施する場合
                    if(gdh.getTargetPlayerLive(actionTargetId)){
                        makeActionResult(action.charaAction(actionTargetId).getFieldLayout(this));
                        bottomBUtton.setText(MyConstants.BTN_OK);
                        playActionFlug=false;
                    }
                    //昼の時間のアクションが実行後、勝敗確認
                    winnerCamp=gdh.checkVictory_Defeat();
                    if(winnerCamp!=null){
                        //勝利陣営の勝利メッセージを出力
                        ts.speech(winnerCamp.getVictorySpeech());
                        //勝敗表示画面へ遷移
                        Intent intent = new Intent(GameActivity_main.this, GameActivity_end.class);
                        //intent.putExtra(MyConstants.INTENT_KEY, gd);
                        startActivity(intent);
                    }
                }
                break;
            default:
                break;
        }
    }
}