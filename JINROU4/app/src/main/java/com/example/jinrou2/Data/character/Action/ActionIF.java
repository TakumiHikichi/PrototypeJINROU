package com.example.jinrou2.Data.character.Action;

import com.example.CharaEnum;
import com.example.jinrou2.Data.GameData;

import java.util.ArrayList;

/**
 * Created by ryouta on 2015/11/17.
 */
public interface ActionIF {
    //プレイヤーボタンの使用可否
    public boolean playerButtonEnabled();

    //画面下ボタンの使用可否
    public boolean underButtonEnabled();

    //動作前音声案内
    public ArrayList<String> voiceGuide_StartStr();

    //ボタン押下時の確認メッセージ
    public String charaActionCheckStr(int btnId);

    //ボタン押下後の確認メッセージ
    public String charaActionEndCheckStr(int btnId);

    //ボタン押下時の動作
    public ActionRtnVal charaAction(int btnId);

    //動作後音声案内
    public ArrayList<String> voiceGuide_EndStr();

    //対応するEnum
    public CharaEnum getEnum();

    //夜間アクションを行うかどうか
    public boolean getActionFlug();

    //初日アクションを行うかどうか
    public boolean getActionFirstFlug();

}
