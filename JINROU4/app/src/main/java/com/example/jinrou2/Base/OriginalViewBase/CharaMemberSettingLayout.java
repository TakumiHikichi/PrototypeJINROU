package com.example.jinrou2.Base.OriginalViewBase;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.CharaEnum;
import com.example.MyConstants;
import com.example.jinrou2.Base.TextBase.AutoFontTextArea;

/**
 * Created by ryouta on 2015/11/23.
 */
public class CharaMemberSettingLayout extends LinearLayout implements View.OnClickListener {
    private AutoFontTextArea aftaMember;
    private CharaEnum charaEnum;
    public CharaMemberSettingLayout(Context context,CharaEnum chara,int imageId) {
        super(context);
        charaEnum = chara;
        //定義するレイアウトの並び
        this.setOrientation(LinearLayout.HORIZONTAL);
        //this.setWeightSum(8);
        //レイアウトサイズ　横幅：適度　高さ：最大
        LinearLayout.LayoutParams viewLayout=new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        viewLayout.weight=1;

        //左部、プラスボタン定義
        Button plusButton = new Button(context);
        plusButton.setText(MyConstants.LARGE_PLUS);
        plusButton.setOnClickListener(this);
        plusButton.setLayoutParams(viewLayout);

        //右部、マイナスボタン定義
        Button minusButton = new Button(context);
        minusButton.setText(MyConstants.LARGE_MINUS);
        minusButton.setOnClickListener(this);
        minusButton.setLayoutParams(viewLayout);

        //中央上部１、テキスト定義（自動調整可能テキスト）
        AutoFontTextArea aftaName = new AutoFontTextArea(context);
        aftaName.setText(chara.getCharaName());
        aftaName.setLayoutParams(viewLayout);
        aftaName.setTextColor(Color.BLACK);

        //中央上部２、人数
        aftaMember=new AutoFontTextArea(context);
        aftaMember.setLayoutParams(viewLayout);
        aftaMember.setTextColor(Color.BLACK);
        //中央下部、イメージ定義
        ImageView iv= (ImageView)findViewById(imageId);
//        iv.setScaleType(ImageView.ScaleType.CENTER_INSIDE);

        this.addView(aftaName);
        this.addView(plusButton);
        this.addView(aftaMember);
//        this.addView(iv);
        this.addView(minusButton);

        setMemberInt(0);
    }

    public int getMemeberNum(){
        if(aftaMember.getText().toString()!=""){
            return Integer.parseInt(aftaMember.getText().toString().replaceFirst(MyConstants.MEMBER_RIGHT_DISCRIPTION,""));
        }
        return 0;
    }

    @Override
    public void onClick(View v) {
        int nowMember=0;
        if(aftaMember.getText().toString()!=""){
            nowMember = Integer.parseInt(aftaMember.getText().toString().replaceFirst(MyConstants.MEMBER_RIGHT_DISCRIPTION,""));
        }

        switch(((Button)v).getText().toString()){
            case MyConstants.LARGE_PLUS:
                setMemberInt(++nowMember);
                break;
            case MyConstants.LARGE_MINUS:
                setMemberInt(--nowMember);
                break;
            default:
                break;
        }
    }
    public void setMemberInt(int i){
        if(i>=0) aftaMember.setText(Integer.toString(i) + MyConstants.MEMBER_RIGHT_DISCRIPTION);
    }

    public CharaEnum getCharaData(){
        return charaEnum;
    }
}
