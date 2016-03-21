package com.example.jinrou2.Data.character.Action.AllAction;

import com.example.CharaEnum;
import com.example.jinrou2.Data.DataHelper.GameDataHelper;
import com.example.jinrou2.Data.GameData;
import com.example.jinrou2.Data.PlayerData;
import com.example.jinrou2.Data.character.Action.ActionRtnVal;
import com.example.jinrou2.Data.character.Action.ActionIF;

import java.util.ArrayList;

/**
 * Created by ryouta on 2015/11/17.
 */
public class FortuneTellerAction implements ActionIF {
    private static FortuneTellerAction instance;
    private void FortuneTellerSeparate(){}
    public static FortuneTellerAction getInstance(){
        if(instance==null)instance=new FortuneTellerAction();
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
        ArrayList<String> startMsg = new ArrayList();
        startMsg.add("占い師のプレイヤーは、目を開けて下さい。   ");
        startMsg.add("占い師のプレイヤーは、狼かどうか確認したいプレイヤーを一人選んでください。");
        return startMsg;
    }

    @Override
    public String charaActionCheckStr(int btnId) {
        GameDataHelper gdh = new GameDataHelper();
        return gdh.getTargetPlayerName(btnId) + " に対して占いを行います。";
    }

    @Override
    public String charaActionEndCheckStr(int btnId) {
        return "「OK」を押下し、端末を元に戻し、目を閉じてください。";
    }

    @Override
    public ActionRtnVal charaAction(int btnId) {
        GameData gd = GameData.getInstance();
        ActionRtnVal csr = new ActionRtnVal();
        GameDataHelper gdh = new GameDataHelper();
        ArrayList<String> bodyList = new ArrayList();

        csr.setHeader("占い結果");
        bodyList.add("プレイヤー「" + gdh.getTargetPlayerName(btnId) + "」は");
        bodyList.add("【" + gdh.getTargetPlayerChara(gd,btnId).getDivResult() + "】のようです。");
        csr.setBody(bodyList);
        csr.setFooter("");
        return csr;
    }

    @Override
    public ArrayList voiceGuide_EndStr() {
        ArrayList<String> endMsg = new ArrayList();
        endMsg.add("占い師のプレイヤーは、端末を元に戻し、目を瞑ってください。   ");
        return endMsg;
    }

    @Override
    public CharaEnum getEnum() {
        return CharaEnum.FORTUNE_TELLER;
    }

    @Override
    public boolean getActionFlug() {
        boolean rtn = false;
        GameData gd = GameData.getInstance();
        for(PlayerData pd:gd.getPlayerDatas()){
            if(pd.getChara().equals(CharaEnum.FORTUNE_TELLER)&&pd.getLivingFlug()){
                rtn=true;
            }
        }
        return rtn;
    }

    @Override
    public boolean getActionFirstFlug() {
        return false;
    }
}
