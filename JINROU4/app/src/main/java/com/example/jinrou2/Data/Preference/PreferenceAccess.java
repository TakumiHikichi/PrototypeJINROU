package com.example.jinrou2.Data.Preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.CharaEnum;
import com.example.MyConstants;

import java.util.ArrayList;

/**
 * Created by ryouta on 2015/11/30.
 */
public class PreferenceAccess {
    SharedPreferences pref;
    void PreferenceAccess(Context con){pref =con.getSharedPreferences(MyConstants.PREFERENCE_MAIN_KEY,con.MODE_PRIVATE);}

    //キーを元に、対応するキャライナム配列を返却する。設定がない場合は初期値を設定して返却
    public ArrayList<CharaEnum> getCharaData(String key){
        ArrayList<CharaEnum> rtn = new ArrayList();
        String buf = new String();
        buf= pref.getString(key, MyConstants.PREFERENCE_NO_DATA);
        //データが取得できない場合は初期値を設定し、再取得
        if(buf.equals(MyConstants.PREFERENCE_NO_DATA)) {
            setCharaDataDefault(key);
            buf= pref.getString(key, MyConstants.PREFERENCE_NO_DATA);
            if(buf==null)return null;
        }

        for(String str:buf.split(",")){
            if(str.equals(CharaEnum.VILLAGER.getCharaName())){
                rtn.add(CharaEnum.VILLAGER);
            }else if(str.equals(CharaEnum.MAD_MAN.getCharaName())){
                rtn.add(CharaEnum.MAD_MAN);
            }else if(str.equals(CharaEnum.FORTUNE_TELLER.getCharaName())){
                rtn.add(CharaEnum.FORTUNE_TELLER);
            }else if(str.equals(CharaEnum.WOLF.getCharaName())){
                rtn.add(CharaEnum.WOLF);
            }
        }
        return rtn;
    }
    public void setCharaDataDefault(String key){
        String value = new String();

        switch(key){
            /*
            case MyConstants.PREFERENCE_KEY_4MEM:
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.FORTUNE_TELLER.addName(value);
                value = CharaEnum.WOLF.addName(value);
                break;
            case MyConstants.PREFERENCE_KEY_5MEM:
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.MAD_MAN.addName(value);
                value = CharaEnum.FORTUNE_TELLER.addName(value);
                value = CharaEnum.WOLF.addName(value);
                break;
            case MyConstants.PREFERENCE_KEY_6MEM:
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.MAD_MAN.addName(value);
                value = CharaEnum.FORTUNE_TELLER.addName(value);
                value = CharaEnum.WOLF.addName(value);
                break;
            case MyConstants.PREFERENCE_KEY_7MEM:
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.MAD_MAN.addName(value);
                value = CharaEnum.FORTUNE_TELLER.addName(value);
                value = CharaEnum.WOLF.addName(value);
                break;
            case MyConstants.PREFERENCE_KEY_8MEM:
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.MAD_MAN.addName(value);
                value = CharaEnum.FORTUNE_TELLER.addName(value);
                value = CharaEnum.WOLF.addName(value);
                value = CharaEnum.WOLF.addName(value);
                break;
            case MyConstants.PREFERENCE_KEY_9MEM:
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.MAD_MAN.addName(value);
                value = CharaEnum.FORTUNE_TELLER.addName(value);
                value = CharaEnum.WOLF.addName(value);
                value = CharaEnum.WOLF.addName(value);
                break;
            case MyConstants.PREFERENCE_KEY_10MEM:
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.MAD_MAN.addName(value);
                value = CharaEnum.MAD_MAN.addName(value);
                value = CharaEnum.FORTUNE_TELLER.addName(value);
                value = CharaEnum.WOLF.addName(value);
                value = CharaEnum.WOLF.addName(value);
                break;
            case MyConstants.PREFERENCE_KEY_11MEM:
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.MAD_MAN.addName(value);
                value = CharaEnum.MAD_MAN.addName(value);
                value = CharaEnum.FORTUNE_TELLER.addName(value);
                value = CharaEnum.WOLF.addName(value);
                value = CharaEnum.WOLF.addName(value);
                break;
            case MyConstants.PREFERENCE_KEY_12MEM:
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.MAD_MAN.addName(value);
                value = CharaEnum.MAD_MAN.addName(value);
                value = CharaEnum.FORTUNE_TELLER.addName(value);
                value = CharaEnum.WOLF.addName(value);
                value = CharaEnum.WOLF.addName(value);
                value = CharaEnum.WOLF.addName(value);
                break;
            case MyConstants.PREFERENCE_KEY_13MEM:
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.MAD_MAN.addName(value);
                value = CharaEnum.MAD_MAN.addName(value);
                value = CharaEnum.FORTUNE_TELLER.addName(value);
                value = CharaEnum.WOLF.addName(value);
                value = CharaEnum.WOLF.addName(value);
                value = CharaEnum.WOLF.addName(value);
                break;
            case MyConstants.PREFERENCE_KEY_14MEM:
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.MAD_MAN.addName(value);
                value = CharaEnum.MAD_MAN.addName(value);
                value = CharaEnum.FORTUNE_TELLER.addName(value);
                value = CharaEnum.WOLF.addName(value);
                value = CharaEnum.WOLF.addName(value);
                value = CharaEnum.WOLF.addName(value);
                break;
            case MyConstants.PREFERENCE_KEY_15MEM:
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.MAD_MAN.addName(value);
                value = CharaEnum.MAD_MAN.addName(value);
                value = CharaEnum.FORTUNE_TELLER.addName(value);
                value = CharaEnum.WOLF.addName(value);
                value = CharaEnum.WOLF.addName(value);
                value = CharaEnum.WOLF.addName(value);
                break;
            case MyConstants.PREFERENCE_KEY_16MEM:
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.VILLAGER.addName(value);
                value = CharaEnum.MAD_MAN.addName(value);
                value = CharaEnum.MAD_MAN.addName(value);
                value = CharaEnum.FORTUNE_TELLER.addName(value);
                value = CharaEnum.WOLF.addName(value);
                value = CharaEnum.WOLF.addName(value);
                value = CharaEnum.WOLF.addName(value);
                value = CharaEnum.WOLF.addName(value);
                break;
            */
            default:
                break;
        }
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.commit();
    }
}
