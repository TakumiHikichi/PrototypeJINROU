package com.example.jinrou2.Lib;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.util.Log;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by ryouta on 2015/11/03.
 * 初期化時にコンテキストとを受け取る
 * 「onInit」で初期化の結果を確認
 *  半角スペースが末尾についてる数だけディレイをかける。
 */
public class ToSpeech implements TextToSpeech.OnInitListener,MyErrorCodeIF {
    private TextToSpeech tts;
    private final Locale LOCALE = Locale.getDefault();
    private int iReturnErrorCode =ERROR_CODE_TTS_STAY;
    private static ToSpeech instance = null;

    //＊＊＊＊＊＊＊＊＊＊＊シングルトン化＊＊＊＊＊＊＊＊＊＊＊＊＊
    private ToSpeech(Context con) {
        tts = new TextToSpeech(con, this);
    }
    public static ToSpeech getInstance(Context con){
        if(instance==null){
            instance=new ToSpeech(con);
        }
        return instance;
    }
    //＊＊＊＊＊＊＊＊＊＊＊シングルトン化ここまで＊＊＊＊＊＊＊＊＊＊＊＊＊
    public void speech(final String speech) {
        long time = 0;
        for(int i=speech.length()-1;i > 0;i--){
            if(speech.charAt(i)==' '){
                time=time+500;
            }else{
                break;
            }
        }
        if (speech == null || speech == "") return;
        if(tts.isSpeaking())tts.stop();
        Log.d("合成音声出力", speech);
        tts.speak(speech, TextToSpeech.QUEUE_FLUSH, null);
        tts.playSilence(time,TextToSpeech.QUEUE_ADD,null);
    }
    public void speech(final ArrayList<String> speech) {
        long time = 0;
        if (speech == null) return;
        if(tts.isSpeaking())tts.stop();
        for (final String str : speech) {
            time = 0;
            if(str!="") {
                Log.d("合成音声出力", str);
                //ディレイ設定
                for(int i=str.length()-1;i > 0;i--){
                    if(str.charAt(i)==' '){
                        time=time+500;
                    }else{
                        break;
                    }
                }
                tts.speak(str, TextToSpeech.QUEUE_ADD, null);
                tts.playSilence(time,TextToSpeech.QUEUE_ADD,null);
            }
        }
    }
    public boolean isSpeaking(){
        return tts.isSpeaking();
    }
    public void stopSpeech(){tts.stop();}
    public void toSpeechShutdown(){
        if (null != tts)tts.shutdown();
    }

    //初期化時の状態を返却する
    @Override
    public int getErrorCode() {
        return iReturnErrorCode;
    }

    @Override
    public void onInit(int status) {
        if(status==TextToSpeech.SUCCESS){
            if(tts.isLanguageAvailable(LOCALE)>= TextToSpeech.LANG_AVAILABLE){
                tts.setLanguage(LOCALE);
                iReturnErrorCode =ERROR_CODE_TTS_NO_PROBREM;
            }else{
                iReturnErrorCode =ERROR_CODE_TTS_LOCALE;
            }
        }else{
            iReturnErrorCode =ERROR_CODE_TTS_ENGINE;
        }
    }
}
