package com.example.jinrou2;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.CharaEnum;
import com.example.MyConstants;
import com.example.jinrou2.Base.ActivityBase.Lib.Act001PlayBGM;
import com.example.jinrou2.Base.OriginalViewBase.ImgTextButton_setting;
import com.example.jinrou2.Base.OriginalViewBase.NameEditText;
import com.example.jinrou2.Data.DataHelper.GameDataHelper;
import com.example.jinrou2.Data.GameData;
import com.example.jinrou2.Data.PlayerData;
import com.example.jinrou2.Data.SettingData;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ryouta on 2015/11/08.
 */
public class GameActivity_setting extends Act001PlayBGM implements View.OnClickListener {

    Button btn1;
    Button btn2;
    RelativeLayout layout;
    GameData gd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startMusic(R.raw.gasetting);
        setContentView(R.layout.game_settint_field);
        //プレイヤーデータバッファ
        layout = (RelativeLayout)findViewById(R.id.game_field);
        btn1=(Button)findViewById(R.id.button1);
        btn2=(Button)findViewById(R.id.button2);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        //ゲームデータ取得
        gd = GameData.getInstance();
        //プレイヤー情報が存在する場合
        if(gd.getPlayerDatas()!=null){
            ImgTextButton_setting itb_s[]=new ImgTextButton_setting[gd.getPlayerDatas().size()];
            for(int i=0;i<gd.getPlayerDatas().size();i++){
                itb_s[i]=new ImgTextButton_setting(gd.getPlayerDatas().get(i).getName(),layout,this,gd.getIconHeight(),gd.getIconHeight());
Log.d("ボタンに入れる値X",""+gd.getPlayerDatas().get(i).getButtonLeft());
                itb_s[i].setX(gd.getPlayerDatas().get(i).getButtonLeft());
Log.d("ボタンに入れた値X", "" + itb_s[i].getX());
                itb_s[i].setY(gd.getPlayerDatas().get(i).getButtonTop());
                layout.addView(itb_s[i]);
            }
        }else{
            gd=GameData.getInstance();
        }
    }

    @Override
    public void onClick(View v) {
        GameDataHelper gdh = new GameDataHelper();
        //押されたボタン
        switch(v.getId()){
            case R.id.button1:
                final GameActivity_setting this_GA = this;
                final NameEditText editView = new NameEditText(this);
                new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setTitle(MyConstants.DIALOG_TITLE_MAKE_PLAYER)
                    .setView(editView)
                    .setPositiveButton(MyConstants.BTN_OK, new DialogInterface.OnClickListener() {
                        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
                        public void onClick(DialogInterface dialog, int whichButton) {
                            if (editView.getText().toString() != "") {
                                RelativeLayout layout = (RelativeLayout) findViewById(R.id.game_field);
                                ImgTextButton_setting itb = new ImgTextButton_setting(editView.getText().toString(),(RelativeLayout)findViewById(R.id.game_field),this_GA);
                                layout.addView(itb);
                            }
                        }
                    }).show();
                break;
            case R.id.button2:
                //役職配役用
                Random rnd = new Random();
                //参加人数
                int members=0;
                //プレイヤー情報作成
                ArrayList<PlayerData> playerList = new ArrayList();
                //ボタン情報取得
                ImgTextButton_setting psBtn;
                //作成されたボタンの数をカウント
                members=layout.getChildCount();

                //プレイヤー人数毎に設定される配役情報を取得
                CharaEnum characters[] = new CharaEnum[members];
                SettingData.getSettingData(members, characters,this);
                //GameDataに一意な役職を設定
                gdh.setCharaKind(characters);
                //配役用配列
                boolean[] casts = new boolean[members];
                PlayerData pdBuf;
                //ランダム数値生成用バッファ
                int rndBuf = 0;

                //参加人数分繰り返し
                for(int i=0;i<members;i++) {
                    pdBuf=new PlayerData();
                    //子要素のボタンを取得
                    psBtn=(ImgTextButton_setting) layout.getChildAt(i);
                    //ボタンの情報からプレイヤーデータを作成
                    pdBuf.setName(psBtn.getText());
//ボタンの情報
Log.d("ボタン"+i,"TOP「"+psBtn.getTop()+"」");
                    pdBuf.setButtonLeft(psBtn.getX());
                    pdBuf.setButtonTop( psBtn.getY());
                    pdBuf.setButtonId(psBtn.getId());
                    pdBuf.setLivingFlug(true);
                    //ここから配役
                    do{
                        rndBuf=rnd.nextInt(members);
                    }while(casts[rndBuf]);
                    casts[rndBuf]=true;
                    //役職決定
                    pdBuf.setChara(characters[rndBuf]);
                    playerList.add(pdBuf);
                }
                gd.setPlayerDatas(playerList);
                gd.setIconWidth(((RelativeLayout) findViewById(R.id.game_field)).getWidth() / 4);
                gd.setIconHeight(((RelativeLayout) findViewById(R.id.game_field)).getHeight() / 4);
                Intent intent = new Intent(GameActivity_setting.this, GameActivity_confirm.class);
                intent.putExtra(MyConstants.INTENT_KEY, GameData.getInstance());
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
