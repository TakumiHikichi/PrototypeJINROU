package com.example.jinrou2.Data;

import android.app.Activity;
import android.content.SharedPreferences;
import com.example.CharaEnum;

/**
 * Created by Takumi_2 on 2015/12/27.
 */
public class SettingData {
    public static boolean getSettingData(int memberNum,CharaEnum settingData[],Activity activity){

        //設定人数の配役情報を取得
        String defaultSetting = getDefaultSetting(memberNum);
        String keyname = "KeyName_" + String.valueOf(memberNum);
        SharedPreferences prefs = activity.getSharedPreferences("JINROU_SETTING_KEY", Activity.MODE_PRIVATE);
        String settingStr = prefs.getString(keyname, defaultSetting);

        //配役情報を配列にセット
        String tempData[] = settingStr.split(",");
        for(int i = 0; i < memberNum; i++){
            settingData[i]=CharaEnum.valueOf(tempData[i]);
        }

        return true;
    }

    public static boolean saveSettingData(int memberNum,CharaEnum settingData[],Activity activity){

        //配役情報配列を文字列に変換
        StringBuffer bufStr = new StringBuffer();
        for(int i=0; i < memberNum; i++)
        {
            bufStr.append(settingData[i].toString());
            bufStr.append(",");
        }
        String settingStr = bufStr.toString();
        settingStr.substring(0,settingStr.length()-1);    //末尾の","を削除

        //設定人数の配役情報を保存
        String keyname = "KeyName_" + String.valueOf(memberNum);
        SharedPreferences prefs = activity.getSharedPreferences("JINROU_SETTING_KEY", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(keyname, settingStr);
        editor.commit();

        return true;
    }

    private static String getDefaultSetting(int memberNum){
        switch(memberNum)
        {
            case 4:
                return "WOLF,FORTUNE_TELLER,VILLAGER,VILLAGER";
            case 5:
                return  "WOLF,FORTUNE_TELLER,MAD_MAN,VILLAGER,VILLAGER";
            case 6:
                return "WOLF,WOLF,FORTUNE_TELLER,VILLAGER,VILLAGER,VILLAGER";
            case 7:
                return "WOLF,WOLF,FORTUNE_TELLER,VILLAGER,VILLAGER,VILLAGER,VILLAGER";
            case 8:
                return "WOLF,WOLF,WOLF,FORTUNE_TELLER,MAD_MAN,VILLAGER,VILLAGER,VILLAGER";
            case 9:
                return "WOLF,WOLF,FORTUNE_TELLER,MAD_MAN,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER";
            case 10:
                return "WOLF,WOLF,FORTUNE_TELLER,MAD_MAN,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER";
            case 11:
                return "WOLF,WOLF,FORTUNE_TELLER,MAD_MAN,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER";
            case 12:
                return "WOLF,WOLF,FORTUNE_TELLER,MAD_MAN,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER";
            case 13:
                return "WOLF,WOLF,FORTUNE_TELLER,MAD_MAN,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER";
            case 14:
                return "WOLF,WOLF,FORTUNE_TELLER,MAD_MAN,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER";
            case 15:
                return "WOLF,WOLF,FORTUNE_TELLER,MAD_MAN,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER";
            case 16:
                return "WOLF,WOLF,FORTUNE_TELLER,MAD_MAN,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER,VILLAGER";
            default:
                break;
        }
        return "";
    }
}