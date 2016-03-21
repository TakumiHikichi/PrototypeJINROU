package com.example.jinrou2.Lib;

/**
 * Created by ryouta on 2015/11/04.
 */
public interface MyErrorCodeIF {
    public final int ERROR_CODE_TTS_STAY=0;
    public final int ERROR_CODE_TTS_LOCALE=1;
    public final int ERROR_CODE_TTS_ENGINE=2;
    public final int ERROR_CODE_TTS_NO_PROBREM=3;
    public int getErrorCode();
}
