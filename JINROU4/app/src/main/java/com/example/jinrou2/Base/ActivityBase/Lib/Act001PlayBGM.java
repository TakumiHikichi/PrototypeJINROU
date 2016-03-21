package com.example.jinrou2.Base.ActivityBase.Lib;

import android.app.Activity;
import android.media.MediaPlayer;

import com.example.jinrou2.Lib.ToSpeech;

/**
 * Created by ryouta on 2015/11/01.
 */
public class Act001PlayBGM extends Activity {
    private MediaPlayer mPlayer;
    /**
     * IDを受け取り、その音楽ファイルを再生する
     * 別の音楽が再生中の場合はそれを停止する
     *
     * @param int
     * @return -
     */
    protected void startMusic(int id){
        //別のBGMが再生中の場合、それを停止
        if(mPlayer!=null && mPlayer.isPlaying())mPlayer.stop();
        mPlayer = MediaPlayer.create(getApplicationContext(), id);
        //繰り返し再生
        mPlayer.setLooping(true);
        try{
            //再生
            //[debug]mPlayer.start();
            mPlayer.start();
        }catch(Exception e){}
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        //BGMが停止されている場合再生
        if (mPlayer != null) {
            if (!mPlayer.isPlaying()) mPlayer.start();
        }
    }
        @Override
    protected void onPause() {
        super.onPause();
        //一時停止
        if(mPlayer!=null)mPlayer.pause();
        ToSpeech ts = ToSpeech.getInstance(this);
        //if(ts.isSpeaking())ts.stopSpeech();
    }

    @Override
    protected void onStart(){
        super.onStart();
    }
    @Override
    protected void onResume(){super.onResume();}
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        super.onStop();
        if(mPlayer!=null) {
            //停止・解放
            mPlayer.stop();
            mPlayer.release();
        }
        ToSpeech ts = ToSpeech.getInstance(this);
        if(ts.isSpeaking())ts.stopSpeech();
    }
}
