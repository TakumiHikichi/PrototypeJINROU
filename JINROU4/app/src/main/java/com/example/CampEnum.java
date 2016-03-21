package com.example;

/**
 * Created by ryouta on 2015/12/20.
 */
public enum CampEnum {
    VILLAGER_CAMP("【村人陣営】","人狼の全滅","この瞬間、村から狼はいなくなりました。  村人陣営の勝利です。"),
    WOLF_CAMP("【狼陣営】","村人を人狼と同数以下まで減らす","この瞬間、狼の数が村人を超えました。  狼陣営の勝利です。");

    private String camp;                     //陣営
    private String victoryConditions;       //勝利条件
    private String victorySpeech;           //勝利時読み上げ文章

    private CampEnum(String camp,String victoryConditions,String victorySpeech){
        this.camp = camp;
        this.victoryConditions=victoryConditions;
        this.victorySpeech=victorySpeech;
    }
    public String getCamp(){
        return camp;
    }
    public String getVictoryConditions(){
        return victoryConditions;
    }
    public String getVictorySpeech(){return victorySpeech;}
}
