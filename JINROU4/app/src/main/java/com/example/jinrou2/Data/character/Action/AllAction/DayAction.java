package com.example.jinrou2.Data.character.Action.AllAction;

import com.example.CharaEnum;
import com.example.jinrou2.Data.DataHelper.GameDataHelper;
import com.example.jinrou2.Data.GameData;
import com.example.jinrou2.Data.character.Action.ActionIF;
import com.example.jinrou2.Data.character.Action.ActionRtnVal;

import java.util.ArrayList;

/**
 * Created by ryouta on 2015/11/17.
 */
public class DayAction implements ActionIF {
    private static DayAction instance;
    private void FortuneTellerSeparate(){}
    public static DayAction getInstance(){
        if(instance==null)instance=new DayAction();
        return instance;
    }

    @Override
    public boolean playerButtonEnabled() {
        return true;
    }

    @Override
    public boolean underButtonEnabled() {
        return false;
    }

    @Override
    public ArrayList voiceGuide_StartStr() {
        GameData gdTest = GameData.getInstance();
        GameDataHelper gdhTest = new GameDataHelper();

        ArrayList<String> startMsg = new ArrayList();
        startMsg.add("朝の時間がやってきました。");
        startMsg.add("各プレイヤーは目を開けて下さい。 ");
        startMsg.add("全員で話し合いを行い、誰か一人を村から追放できます。");
        startMsg.add("追放したいプレイヤーを選択してください。");

        return startMsg;
    }

    @Override
    public String charaActionCheckStr(int btnId) {
        GameDataHelper gdh = new GameDataHelper();
        return gdh.getTargetPlayerName(btnId) + " を村から追放します。";
    }

    @Override
    public String charaActionEndCheckStr(int btnId) {
        return "夜の時間を開始します。";
    }

    @Override
    public ActionRtnVal charaAction(int btnId) {
        ActionRtnVal csr = new ActionRtnVal();
        GameDataHelper gdh = new GameDataHelper();
        ArrayList<String> bodyList = new ArrayList();
        //プレイヤーの状態を死亡に変更
        gdh.setTargetPlayerDead(btnId);
        csr.setHeader("結果確認");
        bodyList.add("「" + gdh.getTargetPlayerName(btnId) + "」を");
        bodyList.add("村から追放しました。");
        csr.setBody(bodyList);
        csr.setFooter("");
        return csr;
    }

    @Override
    public ArrayList voiceGuide_EndStr() {
        ArrayList<String> endMsg = new ArrayList();
        endMsg.add("恐ろしい夜がやってきます。 ");
        endMsg.add("各プレイヤーは目を閉じてください。  ");
        return endMsg;
    }

    @Override
    public CharaEnum getEnum() {
        return CharaEnum.Day;
    }

    @Override
    public boolean getActionFlug() {
        return true;
    }

    @Override
    public boolean getActionFirstFlug() {
        return true;
    }
}
