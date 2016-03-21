package com.example.jinrou2.Data.character.Action.AllAction;

import com.example.CharaEnum;
import com.example.jinrou2.Data.GameData;
import com.example.jinrou2.Data.PlayerData;
import com.example.jinrou2.Data.character.Action.ActionRtnVal;
import com.example.jinrou2.Data.character.Action.ActionIF;

import java.util.ArrayList;

/**
 * Created by ryouta on 2015/11/17.
 */
public class VillagerAction implements ActionIF {
    private static VillagerAction instance;
    private void VillagerSeparate(){}
    public static VillagerAction getInstance(){
        if(instance==null)instance=new VillagerAction();
        return instance;
    }

    @Override
    public boolean playerButtonEnabled() {
        return false;
    }

    @Override
    public boolean underButtonEnabled() {
        return false;
    }

    @Override
    public ArrayList<String> voiceGuide_StartStr() {
        return null;
    }

    @Override
    public String charaActionCheckStr(int btnId) {
        return null;
    }

    @Override
    public String charaActionEndCheckStr(int btnId) {
        return null;
    }

    @Override
    public ActionRtnVal charaAction(int btnId) {
        return null;
    }

    @Override
    public ArrayList<String> voiceGuide_EndStr() {
        return null;
    }

    @Override
    public CharaEnum getEnum() {
        return CharaEnum.VILLAGER;
    }
    @Override
    public boolean getActionFlug() {
        return false;
    }

    @Override
    public boolean getActionFirstFlug() {
        return false;
    }
}
