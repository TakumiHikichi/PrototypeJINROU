package com.example.jinrou2;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.CharaEnum;
import com.example.MyConstants;
import com.example.jinrou2.Base.ActivityBase.Lib.Act001PlayBGM;
import com.example.jinrou2.Base.OriginalViewBase.CharaMemberSettingLayout;
import com.example.jinrou2.Base.TextBase.AutoFontTextArea;
import com.example.jinrou2.Data.SettingData;

/**
 * Created by ryouta on 2015/11/08.
 */
public class SettingActivity extends Act001PlayBGM implements View.OnClickListener{
    private CharaEnum settingData[];
    private int memberNum;
    private Spinner selectSpinner;
    private CharaMemberSettingLayout cmsVillager;
    private CharaMemberSettingLayout cmsWolf;
    private CharaMemberSettingLayout cmsMadman;
    private CharaMemberSettingLayout cmsFortuneTellor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_activity);
        setHeaderText(MyConstants.SETTING_HEADER_DEFAULT);

        Button enterButton = (Button)findViewById(R.id.EnterButton);
        enterButton.setOnClickListener(this);

        LinearLayout llValues = (LinearLayout)findViewById(R.id.LinearLayout1_2_Right);
        LinearLayout.LayoutParams onceLP = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        onceLP.weight=1;

       cmsVillager = new CharaMemberSettingLayout(this, CharaEnum.VILLAGER,R.drawable.villedger);
       cmsWolf = new CharaMemberSettingLayout(this, CharaEnum.WOLF,R.drawable.wolf);
       cmsMadman = new CharaMemberSettingLayout(this, CharaEnum.MAD_MAN,R.drawable.madman);
       cmsFortuneTellor = new CharaMemberSettingLayout(this, CharaEnum.FORTUNE_TELLER,R.drawable.fortunetellor);

        cmsVillager.setLayoutParams(onceLP);
        cmsWolf.setLayoutParams(onceLP);
        cmsMadman.setLayoutParams(onceLP);
        cmsFortuneTellor.setLayoutParams(onceLP);

        llValues.addView(cmsVillager);
        llValues.addView(cmsWolf);
        llValues.addView(cmsMadman);
        llValues.addView(cmsFortuneTellor);

        LinearLayout brankLine;
        brankLine = new LinearLayout(this);
        brankLine.setLayoutParams(onceLP);
        llValues.addView(brankLine);
        brankLine = new LinearLayout(this);
        brankLine.setLayoutParams(onceLP);
        llValues.addView(brankLine);
        brankLine = new LinearLayout(this);
        brankLine.setLayoutParams(onceLP);
        llValues.addView(brankLine);
        brankLine = new LinearLayout(this);
        brankLine.setLayoutParams(onceLP);
        llValues.addView(brankLine);
        brankLine = new LinearLayout(this);
        brankLine.setLayoutParams(onceLP);
        llValues.addView(brankLine);
        brankLine = new LinearLayout(this);
        brankLine.setLayoutParams(onceLP);
        llValues.addView(brankLine);

        //コンボボックス設定
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item);
        adapter.add("4人用");
        adapter.add("5人用");
        adapter.add("6人用");
        adapter.add("7人用");
        adapter.add("8人用");
        adapter.add("9人用");
        adapter.add("10人用");
        adapter.add("11人用");
        adapter.add("12人用");
        adapter.add("13人用");
        adapter.add("14人用");
        adapter.add("15人用");
        adapter.add("16人用");
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectSpinner=(Spinner)findViewById(R.id.mem_spinner);
        selectSpinner.setAdapter(adapter);
        selectSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected (AdapterView < ? > parent, View view,int position, long id){
                memberNum = position + 4;
                updateViewData(memberNum);
            }

            @Override
            public void onNothingSelected (AdapterView < ? > parent){
            }
        });

        updateViewData(4);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.EnterButton:
                saveSettingData(memberNum);
                break;
            default:
                break;
        }
    }
    public void setHeaderText(String s){
        LinearLayout llHeader= (LinearLayout)findViewById(R.id.header);
        Button enterButton = (Button)findViewById(R.id.EnterButton);
        AutoFontTextArea headerText= new AutoFontTextArea(this);
        headerText.setText(s);
        llHeader.removeAllViews();
        llHeader.addView(enterButton);
        llHeader.addView(headerText);
    }
    private void saveSettingData(int num){
        //現在の配役情報に更新
        CharaEnum tempData[] = new CharaEnum[16];
        int index = 0;
        for(int i = 0;i < cmsVillager.getMemeberNum(); i++){
            tempData[index] = cmsVillager.getCharaData();
            index++;
        }
        for(int i = 0;i < cmsWolf.getMemeberNum(); i++){
            tempData[index] = cmsWolf.getCharaData();
            index++;
        }
        for(int i = 0;i < cmsMadman.getMemeberNum(); i++){
            tempData[index] = cmsMadman.getCharaData();
            index++;
        }
        for(int i = 0;i < cmsFortuneTellor.getMemeberNum(); i++){
            tempData[index] = cmsFortuneTellor.getCharaData();
            index++;
        }

        if(index != memberNum){
            //パラメータエラーメッセージ
            new AlertDialog.Builder(this)
                    .setTitle("設定人数エラー")
                    .setMessage("設定人数の合計を" + String.valueOf(memberNum) + "人に設定してください。")
                    .setPositiveButton( "OK", null)
                    .show();
            return;
        }

        //配役情報を保存
        settingData = tempData;
        SettingData.saveSettingData(num, settingData, this);
    }

    private void updateViewData(int num) {
        //保存されている配役情報を取得
        memberNum = num;
        settingData = new CharaEnum[memberNum];
        SettingData.getSettingData(memberNum, settingData, this);

        //役職の人数表示をセット
        cmsVillager.setMemberInt(countData(settingData,cmsVillager.getCharaData()));
        cmsWolf.setMemberInt(countData(settingData,cmsWolf.getCharaData()));
        cmsMadman.setMemberInt(countData(settingData,cmsMadman.getCharaData()));
        cmsFortuneTellor.setMemberInt(countData(settingData,cmsFortuneTellor.getCharaData()));
    }

    //データ数カウント
    private int countData(CharaEnum dataArray[],CharaEnum countData){
        int count = 0;
        for(CharaEnum data:dataArray)
        {
            if(data == countData){
                count++;
            }
        }
        return count;
    }
}
