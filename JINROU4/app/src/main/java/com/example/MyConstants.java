package com.example;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by ryouta on 2015/11/03.
 */
public class MyConstants {
    private void MyConstants() {}
    //ゲーム中の読み上げ前待機時間（最大値）
    public static final int MAX_WAIT_TIME = 3;

    //ボタンのプラスマイナス
    public static final String LARGE_PLUS = "＋";
    public static final String LARGE_MINUS="－";
    //確認ダイアログのタイトル
    public static final String CHECK_PLAYER_DEL_DIALOG_TITLE ="変更";
    //インテントのキー
    public static final String INTENT_KEY="JINROU_INTENT";

    //プリファレンスを用いたキー情報
    public static final String PREFERENCE_MAIN_KEY="RKHT_JINROU_PREFERENCE_KEY";
    public static final String PREFERENCE_NO_DATA="NOTHING";

    public static final String BTN_OK = "OK";
    public static final String BTN_DELETE="削除";
    public static final String BTN_CANCEL = "CANCEL";
    public static final String DIALOG_TITLE_MAKE_PLAYER="参加者名を入力してください";
    public static final String SETTING_HEADER_DEFAULT="設定変更したい人数を\n変更してください。";

    public static final String MEMBER_RIGHT_DISCRIPTION = "人";


    public static final String PLAYER_CHECK_PLAYER="プレイヤー";
    public static final String PLAYER_CHECK_DEFAULT_TITLE="役職一覧";
    public static final String PLAYER_CHECK_TITLE ="プレイヤー確認";
    public static final String PLAYER_CHECK_NEXT_ONE1="端末を、プレイヤー「";
    public static final String PLAYER_CHECK_NEXT_ONE2="」に渡してください。";
    public static final String PLAYER_CHECK1="プレイヤー「";
    public static final String PLAYER_CHECK2="」ですか？";
    public static final String PLAYER_CHECK_VICTORY_CONDITIONS="【勝利条件】";
    public static final String PLAYER_CHECK_ACTION="【夜の行動】";
    public static final String PLAYER_CHECK_YOUR1="の役職は";
    public static final String PLAYER_CHECK_YOUR2="です。";
    public static final String PLAYER_CHECK_NEXT_ONE="次のプレイヤーへ";
    public static final String PLAYER_CHECK_NEXT_PAGE="ゲームスタート";

    public static final String START_GAME ="ゲームを開始します。 ";
    public static final String START_ANNOUNCE ="最初の夜です。各プレイヤーは目を閉じてください   ";

    public static final String CHECK="確認";
    public static final String STOP_TOUCH="操作しないでください。";


    //勝敗確認用
    public static final int TIE=0;
    public static final int WIN_WOLF=1;
    public static final int WIN_VILLAGER=2;

    //勝利画面表示用
    public static final String WIN ="【勝利】";
    public static final String LOSE ="【敗北】";
    public static final String CONFIRM_NEXT_GAME="次のゲームへ進みます";
}
