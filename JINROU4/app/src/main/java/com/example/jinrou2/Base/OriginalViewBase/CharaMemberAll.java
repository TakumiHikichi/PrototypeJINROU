package com.example.jinrou2.Base.OriginalViewBase;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.widget.LinearLayout;

import com.example.CharaEnum;
import com.example.jinrou2.R;
import com.example.jinrou2.SettingActivity;

/**
 * Created by ryouta on 2015/11/23.
 */
public class CharaMemberAll extends LinearLayout{
    public CharaMemberAll(Activity parentActivity) {
        super(parentActivity.getApplicationContext());

        Context context = parentActivity.getApplicationContext();

        this.setLayoutParams(new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
        this.setOrientation(LinearLayout.VERTICAL);
        //1行目の情報
//        this.setWeightSum(8);
        LinearLayout.LayoutParams onceLP=new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        onceLP.weight=1;

        CharaMemberSettingLayout cmsVillager = new CharaMemberSettingLayout(context, CharaEnum.VILLAGER,R.drawable.villedger);
        CharaMemberSettingLayout cmsWolf = new CharaMemberSettingLayout(context, CharaEnum.WOLF,R.drawable.wolf);
        CharaMemberSettingLayout cmsMadman = new CharaMemberSettingLayout(context, CharaEnum.MAD_MAN,R.drawable.madman);
        CharaMemberSettingLayout cmsFortuneTellor = new CharaMemberSettingLayout(context, CharaEnum.FORTUNE_TELLER,R.drawable.fortunetellor);

        cmsVillager.setLayoutParams(onceLP);
        cmsWolf.setLayoutParams(onceLP);
        cmsMadman.setLayoutParams(onceLP);
        cmsFortuneTellor.setLayoutParams(onceLP);

        this.addView(cmsVillager);
        this.addView(cmsWolf);
        this.addView(cmsMadman);
        this.addView(cmsFortuneTellor);

        //this.setWeightSum(8)が上手く機能しないためブランクラインを作成して対処
        LinearLayout brankLine;
        brankLine = new LinearLayout(context);
        brankLine.setLayoutParams(onceLP);
        this.addView(brankLine);
        brankLine = new LinearLayout(context);
        brankLine.setLayoutParams(onceLP);
        this.addView(brankLine);
        brankLine = new LinearLayout(context);
        brankLine.setLayoutParams(onceLP);
        this.addView(brankLine);
        brankLine = new LinearLayout(context);
        brankLine.setLayoutParams(onceLP);
        this.addView(brankLine);
        brankLine = new LinearLayout(context);
        brankLine.setLayoutParams(onceLP);
        this.addView(brankLine);
        brankLine = new LinearLayout(context);
        brankLine.setLayoutParams(onceLP);
        this.addView(brankLine);

    }
}