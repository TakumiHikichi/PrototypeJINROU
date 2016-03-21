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
public class WolfAction implements ActionIF {
    private static WolfAction instance;
    private void WolfSeparate(){}
    public static WolfAction getInstance(){
        if(instance==null)instance=new WolfAction();
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
        startMsg.add("じんろうのプレイヤーは、目を開けて下さい。  ");
        startMsg.add("じんろうのプレイヤーは端末を操作し、村から追放したいプレイヤーを一人選んでください。");
        return startMsg;
    }

    @Override
    public String charaActionCheckStr(int btnId) {
        GameDataHelper gdh = new GameDataHelper();
        return gdh.getTargetPlayerName(btnId) + " を村から追放します。";
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

        csr.setHeader("結果確認");
        //騎士により守られているプレイヤーで変更
        if (gd.getKnightSavaId() != btnId){
            gdh.setTargetPlayerDead(btnId);
            bodyList.add("「" + gdh.getTargetPlayerName(btnId) + "」を");
            bodyList.add("村から追放しました。");
        }else{
            bodyList.add("「" + gdh.getTargetPlayerName(btnId) + "」は");
            bodyList.add("騎士に守られました。");
        }
        csr.setBody(bodyList);
        csr.setFooter("");
        return csr;
    }

    @Override
    public ArrayList voiceGuide_EndStr() {
        ArrayList<String> endMsg = new ArrayList();
        endMsg.add("じんろうのプレイヤーは、端末を元に戻し、目を瞑ってください。   ");
        return endMsg;
    }

    @Override
    public CharaEnum getEnum() {
        return CharaEnum.WOLF;
    }

    @Override
    public boolean getActionFlug() {
        boolean rtn = false;
        GameData gd = GameData.getInstance();
        for(PlayerData pd:gd.getPlayerDatas()){
            if(pd.getChara().equals(CharaEnum.WOLF)&&pd.getLivingFlug()){
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
