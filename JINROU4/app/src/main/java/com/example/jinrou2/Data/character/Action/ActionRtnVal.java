package com.example.jinrou2.Data.character.Action;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;

import com.example.jinrou2.Base.TextBase.AutoFontTextArea;

import java.util.ArrayList;

/**
 * Created by ryouta on 2016/01/17.
 */
public class ActionRtnVal {
    private String header;
    private ArrayList<String> body;
    private String footer;

    public LinearLayout getFieldLayout(Context con){
        LinearLayout fieldLayout=new LinearLayout(con);
        LinearLayout.LayoutParams mainLp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        AutoFontTextArea aftaHead = new AutoFontTextArea(con);
        LinearLayout.LayoutParams headerLp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,20);
        AutoFontTextArea aftaFoot = new AutoFontTextArea(con);
        LinearLayout.LayoutParams footerLp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,20);

        ArrayList<AutoFontTextArea> aftaBody=new ArrayList();
        ArrayList<LinearLayout.LayoutParams> bodyLp=new ArrayList();
        AutoFontTextArea aftaBodyBuf = new AutoFontTextArea(con);
        LinearLayout.LayoutParams bodyLpBuf=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,10);

        fieldLayout.setOrientation(LinearLayout.VERTICAL);
        fieldLayout.setLayoutParams(mainLp);

        //ヘッダ部作成
        aftaHead.setText(this.header);
        aftaHead.setTextColor(Color.RED);
        aftaHead.setGravity(Gravity.CENTER);
        aftaHead.setLayoutParams(headerLp);
        fieldLayout.addView(aftaHead);
        //ボディ部作成
        for(int i = 0;i<this.body.size();i++){
            bodyLpBuf=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,10);
            bodyLp.add(bodyLpBuf);
            aftaBodyBuf= new AutoFontTextArea(con);
            aftaBodyBuf.setText(body.get(i));
            aftaBodyBuf.setTextColor(Color.RED);
            aftaBodyBuf.setGravity(Gravity.CENTER);
            aftaBodyBuf.setLayoutParams(bodyLp.get(i));
            aftaBody.add(aftaBodyBuf);
            fieldLayout.addView(aftaBody.get(i));
        }

        //フッタ部作成
        aftaFoot.setText(this.footer);
        aftaFoot.setTextColor(Color.RED);
        aftaFoot.setGravity(Gravity.CENTER);
        aftaFoot.setLayoutParams(footerLp);
        fieldLayout.addView(aftaFoot);


        return fieldLayout;
    }

    public void setHeader(String header) {
        this.header = header;
    }
    public void setBody(ArrayList<String> body) {
        if(body.size()>6){
            body=new ArrayList();
            body.add("SYSTEM ERROR");
            body.add("行が多すぎます。");
        }
        this.body = body;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }


}
