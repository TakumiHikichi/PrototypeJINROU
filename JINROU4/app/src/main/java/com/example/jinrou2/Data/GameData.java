package com.example.jinrou2.Data;

import com.example.CharaEnum;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ryouta on 2015/11/15.
 */
public class GameData implements Serializable {
    //******************************シングルトン化******************************
    private static GameData instance = null;
    private GameData(){}
    public static GameData getInstance(){
        if(instance==null)instance=new GameData();
        return instance;
    }
    //******************************シングルトン化ここまで******************************
    //ゲーム開始前フラグ
    private Boolean firstFlug;
    //プレイヤー全員分の情報
    private ArrayList<PlayerData> playerDatas = new ArrayList();
    //一意な役職の一覧
    private ArrayList<CharaEnum> charaKind= new ArrayList();
    //アイコンの高さ
    private int iconHeight;
    //アイコンの幅
    private int iconWidth;
    //騎士が守っているID（毎日リセット）
    private int KnightSavaId;

    public ArrayList<PlayerData> getPlayerDatas() {
        return playerDatas;
    }
    public void setPlayerDatas(ArrayList<PlayerData> playerDatas) {
        this.playerDatas = playerDatas;
    }
    //icon(LinearLayoutの高さ
    public int getIconHeight() {
        return iconHeight;
    }
    public void setIconHeight(int iconHeight) {
        this.iconHeight = iconHeight;
    }
    //icon(LinearLayoutの幅
    public int getIconWidth() {
        return iconWidth;
    }
    public void setIconWidth(int icohWidth) {
        this.iconWidth = icohWidth;
    }

    //役職の一意化
    public void setCharaKind(ArrayList<CharaEnum> charaKind){
        this.charaKind=charaKind;
    }
    public ArrayList<CharaEnum> getCharaKind(){
        return charaKind;
    }

    public Boolean getStartFlug() {
        return firstFlug;
    }
    public void setStartFlug(Boolean firstFlug) {
        this.firstFlug = firstFlug;
    }

    public int getKnightSavaId() {
        return KnightSavaId;
    }
    public void setKnightSavaId(int knightSavaId) {
        this.KnightSavaId = knightSavaId;
    }
}
