package com.example;

import com.example.jinrou2.Data.character.Action.ActionIF;
import com.example.jinrou2.Data.character.Action.AllAction.DayAction;
import com.example.jinrou2.Data.character.Action.AllAction.FortuneTellerAction;
import com.example.jinrou2.Data.character.Action.AllAction.MadManAction;
import com.example.jinrou2.Data.character.Action.AllAction.VillagerAction;
import com.example.jinrou2.Data.character.Action.AllAction.WolfAction;
import com.example.jinrou2.R;

/**
 * Created by ryouta on 2015/11/17.
 */
public enum CharaEnum{
    VILLAGER("村人","むらびと",CampEnum.VILLAGER_CAMP,"特別な行動はありません", VillagerAction.getInstance(), R.drawable.villedger,"村人",0,0),
    FORTUNE_TELLER("占い師","うらないし",CampEnum.VILLAGER_CAMP,"夜の時間に誰か一人を占い、狼かどうか確認できます。", FortuneTellerAction.getInstance(),R.drawable.fortunetellor,"村人",1,1),
    WOLF("人狼","じんろう",CampEnum.WOLF_CAMP,"夜の時間に誰か一人を襲撃し、殺害します。", WolfAction.getInstance(),R.drawable.wolf,"人狼",2,0),
    MAD_MAN("狂人","きょうじん",CampEnum.WOLF_CAMP,"特別な行動はありません。", MadManAction.getInstance(),R.drawable.madman,"村人",0,0),
    Day("","",null,"", DayAction.getInstance(),0,"",0,2);

    private String charaName;                  //名前
    private String charaPronounce;            //読み方
    private CampEnum camp;                  //陣営情報
    private String charaConfirm;        //挙動説明
    private ActionIF separate;         //動作
    private int graphicId;              //画像ID
    private String divResult;                //占い結果
    private int actionIntWeight;        //アクションの重みづけ
    private int actionIntWeightFirst;   //初日アクションの重みづけ

    //挙動説明の文字数（半角換算）
    private final int CONFIRM_LENGTH_FORMAT = 60;

    private CharaEnum(String charaName,String charaPronounce,CampEnum camp,String charaConfirm,ActionIF separate,int graphicId,String divResult,int actionIntWeight,int actionIntWeightFirst){
        this.charaName=charaName;
        this.charaPronounce = charaPronounce;
        this.camp = camp;
        this.charaConfirm=charaConfirmFormat(charaConfirm);
        this.separate=separate;
        this.graphicId=graphicId;
        this.divResult=divResult;
        this.actionIntWeight=actionIntWeight;
        this.actionIntWeightFirst=actionIntWeightFirst;
    }
    public String getCharaName(){
        return charaName;
    }
    public String getPronounce(){
        return charaPronounce;
    }
    public ActionIF getAction(){
        return separate;
    }
    public String getCharaConfirm(){
        return charaConfirm;
    }
    public String getDivResult(){return divResult;}
    public String getCamp(){
        return camp.getCamp();
    }
    public String getVictoryConditions(){
        return camp.getVictoryConditions();
    }
    public int getGraphicId(){
        return graphicId;
    }
    public int getActionIntWeight(){
        return actionIntWeight;
    }
    public int getActionIntWeightFirst(){
        return actionIntWeightFirst;
    }
    //文字列を半角換算でCONFIRM_LENGTH_FORMATになるまで空白埋め
    private String charaConfirmFormat(String str){
        int nowLength = getByteLength(str);
        for(int i=nowLength;i<CONFIRM_LENGTH_FORMAT;i++)str=str+" ";
        return str;
    }
    /*
 * 文字列のバイト長を返すメソッド
 */
    private static int getByteLength(String value) {
        int length = 0;
        for (int i = 0; i < value.length(); i++) {
            char c = value.charAt(i);
            if (c >= 0x20 && c <= 0x7E) {
                // JISローマ字(ASCII)
                length++;
            } else if (c >= 0xFF61 && c <= 0xFF9F) {
                // JISカナ(半角カナ)
                length++;
            } else {
                // その他(全角)
                length += 2;
            }
        }
        return length;
    }
}
