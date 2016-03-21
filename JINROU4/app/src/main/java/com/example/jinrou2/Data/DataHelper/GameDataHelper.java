package com.example.jinrou2.Data.DataHelper;

import com.example.CampEnum;
import com.example.CharaEnum;
import com.example.MyConstants;
import com.example.jinrou2.Data.GameData;
import com.example.jinrou2.Data.PlayerData;
import com.example.jinrou2.Data.character.Action.ActionIF;

import java.util.ArrayList;

/**
 * Created by ryouta on 2016/01/19.
 */
public class GameDataHelper {
    //ゲームデータ初期化
    public void initializationGameData(){
        GameData gd = GameData.getInstance();
        gd.setStartFlug(true);
        //全プレイヤーの状態を生存にする
        ArrayList<PlayerData> pdList = gd.getPlayerDatas();
        ArrayList<PlayerData> setList=new ArrayList();

        for(PlayerData pd:pdList){
            pd.setLivingFlug(true);
            setList.add(pd);
        }
        gd.setPlayerDatas(setList);


    }
    //毎日の一時データ初期化
    public void initializationDayData(){
        GameData gd = GameData.getInstance();
        gd.setKnightSavaId(-1);
    }
    //IDで対象プレイヤーの役職を取得
    public CharaEnum getTargetPlayerChara(GameData gd,int id){
        return getPlayer(id).getChara();
    }
    //IDで対象プレイヤーの名前を取得
    public String getTargetPlayerName(int id){
        return getPlayer(id).getName();
    }
    //IDで対象プレイヤーの生存状態を取得
    public Boolean getTargetPlayerLive(int id){
        return getPlayer(id).getLivingFlug();
    }

    //IDで対象プレイヤーの生存状態を死亡に変更
    public void setTargetPlayerDead(int id){
        GameData gd = GameData.getInstance();
        ArrayList<PlayerData> pdList = gd.getPlayerDatas();
        PlayerData pdBuf = new PlayerData();
        for(int i=0;i<pdList.size();i++){
            if(pdList.get(i).getButtonId()==id){
                pdBuf=pdList.get(i);
                pdBuf.setLivingFlug(false);
                pdList.set(i,pdBuf);
                gd.setPlayerDatas(pdList);
                break;
            }
        }
    }

    //ゲームに利用する一意な役職をゲームデータに保持
    public void setCharaKind(CharaEnum[] charaKind){
        GameData gd = GameData.getInstance();
        ArrayList<CharaEnum> charaKindBuf=new ArrayList();
        boolean addFlug;
        for(CharaEnum ceBuf:charaKind){
            addFlug=true;
            for(CharaEnum ceCheck:charaKindBuf)if(ceBuf==ceCheck)addFlug=false;
            if(addFlug)charaKindBuf.add(ceBuf);
        }
        gd.setCharaKind(charaKindBuf);
    }
    //初日に行うアクションのリストを返却
    public ArrayList<ActionIF> getFirstActionList(){
        GameData gd = GameData.getInstance();
        ArrayList<ActionIF> buf = new ArrayList();
        ArrayList<CharaEnum> charanKind = new ArrayList();
        charanKind=gd.getCharaKind();

        //初日に行うアクションをリスト化
        for(CharaEnum chara:charanKind)if(chara.getActionIntWeightFirst()!=0)buf.add(chara.getAction());
        //ソート
        buf=actionTranslationFirstActionWeight(buf);
        //初日の最後に昼用アクションを実施
        buf.add(CharaEnum.Day.getAction());
        //ソートして返却
        return buf;
    }
    //毎日行うアクションのリストを返却
    public ArrayList<ActionIF> getActionList(){
        GameData gd = GameData.getInstance();
        ArrayList<ActionIF> buf = new ArrayList();
        ArrayList<CharaEnum> charanKind = new ArrayList();
        charanKind=gd.getCharaKind();

        //行うアクションをリスト化
        for(CharaEnum chara:charanKind)if(chara.getActionIntWeight()!=0)buf.add(chara.getAction());
        //ソート
        buf=actionTranslationActionWeight(buf);
        //最後に昼用アクションを実施
        buf.add(CharaEnum.Day.getAction());
        //ソートして返却
        return buf;
    }
    //アクション実施時、プレイヤーにボタンを押させるかの判定
    public boolean getWeightToTouchCheck(CharaEnum ce){
        boolean rtn=false;
        GameData gd = GameData.getInstance();
        ArrayList<PlayerData> pdList = gd.getPlayerDatas();
        for(PlayerData pd:pdList){
            //引数の役職と一致し、
            if(pd.getChara().equals(ce)){
                //そのプレイヤーが生存している場合、true
                if(pd.getLivingFlug())rtn=true;
            }
        }
        //昼のアクションの場合は無条件で実施
        if(ce.equals(CharaEnum.Day))rtn=true;
        return rtn;
    }

    //勝敗確認
    public CampEnum checkVictory_Defeat(){
        CampEnum rtn= null;
        ArrayList<PlayerData> pd=GameData.getInstance().getPlayerDatas();
        int villagerCount=0;
        int wolfCount=0;
        //プレイヤー全員分確認
        for(PlayerData pdBuf:pd){
            //生存している場合、ポイントカウント
            if(pdBuf.getLivingFlug()) {
                //人狼の場合
                if (pdBuf.getChara().equals(CharaEnum.WOLF)) {
                    wolfCount++;
                } else {
                    //人狼以外の場合は村人側にカウント
                    villagerCount++;
                }
            }
        }
        //それぞれのポイントをカウントし、勝敗を確認する
        //人狼の生存が０の場合、村人勝利
        if(wolfCount==0)rtn=CampEnum.VILLAGER_CAMP;
        //人狼>=村人の場合、人狼勝利
        if(wolfCount>=villagerCount)rtn=CampEnum.WOLF_CAMP;
        //どちらでもない場合はnullを返却
        return rtn;
    }
    //勝者のリストを返却
    public ArrayList<PlayerData> getWinnerList(){
        ArrayList<PlayerData> rtn = new ArrayList();
        GameData gd = GameData.getInstance();
        CampEnum victoryCamp =  checkVictory_Defeat();
        for(PlayerData pd:gd.getPlayerDatas()){
            if(pd.getChara().getCamp().equals(victoryCamp.getCamp()))rtn.add(pd);
        }
        return rtn;
    }
    //敗者のリストを返却
    public ArrayList<PlayerData> getLoserList(){
        ArrayList<PlayerData> rtn = new ArrayList();
        GameData gd = GameData.getInstance();
        CampEnum victoryCamp =  checkVictory_Defeat();
        for(PlayerData pd:gd.getPlayerDatas()){
            if(!pd.getChara().getCamp().equals(victoryCamp.getCamp()))rtn.add(pd);
        }
        return rtn;
    }

    //IDで対応するプレイヤー情報を返却、ない場合はnull
    private PlayerData getPlayer(int id){
        PlayerData pd = new PlayerData();
        GameData gd = GameData.getInstance();
        ArrayList<PlayerData> players = gd.getPlayerDatas();
        for(int i=0;i<players.size();i++){
            if(players.get(i).getButtonId()==id)pd = players.get(i);
        }
        return pd;
    }

    //ActionIFのリストをアクションの重みで並べ替えてリストで返却
    private ArrayList<ActionIF> actionTranslationActionWeight(ArrayList<ActionIF> list){
        ArrayList<ActionIF> rtn = new ArrayList();
        ActionIF[] ifBuf = new ActionIF[list.size()];
        //バッファに初期値を設定
        ActionIF actionBuf=null;
        //2件以上存在する場合は並べ替え
        if(list.size()>=2) {
            for(int i=0;i<list.size();i++)ifBuf[i]=list.get(i);
            for (int i = 0; i < ifBuf.length-1; i++) {
                for (int j = i + 1; j < ifBuf.length; j++) {
                    if(ifBuf[i].getEnum().getActionIntWeight()>ifBuf[j].getEnum().getActionIntWeight()){
                        actionBuf=ifBuf[i];
                        ifBuf[i]=ifBuf[j];
                        ifBuf[j]=actionBuf;
                    }
                }
            }
            for(ActionIF aif:ifBuf)rtn.add(aif);
        }else{
            //1件の場合は直接返却
            rtn = list;
        }
        return rtn;
    }
    //ActionIFのリストを初日の重みで並べ替えてリストで返却
    private ArrayList<ActionIF> actionTranslationFirstActionWeight(ArrayList<ActionIF> list){
        ArrayList<ActionIF> rtn = new ArrayList();
        ActionIF[] ifBuf = new ActionIF[list.size()];
        //バッファに初期値を設定
        ActionIF actionBuf=null;
        //2件以上存在する場合は並べ替え
        if(list.size()>=2) {
            for(int i=0;i<list.size();i++)ifBuf[i]=list.get(i);
            for (int i = 0; i < ifBuf.length-1; i++) {
                for (int j = i + 1; j < ifBuf.length; j++) {
                    if(ifBuf[i].getEnum().getActionIntWeightFirst()>ifBuf[j].getEnum().getActionIntWeightFirst()){
                        actionBuf=ifBuf[i];
                        ifBuf[i]=ifBuf[j];
                        ifBuf[j]=actionBuf;
                    }
                }
            }
            for(ActionIF aif:ifBuf)rtn.add(aif);
        }else{
            //1件の場合は直接返却
            rtn = list;
        }
        return rtn;
    }
}
