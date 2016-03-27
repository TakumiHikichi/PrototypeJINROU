package com.example.jinrou2.Data;

import android.util.Log;

import com.example.CharaEnum;

import java.io.Serializable;

/**
 * Created by ryouta on 2015/11/15.
 */
public class PlayerData implements Serializable{
    private String name;
    private int buttonId;
    private int iconId;
    private float buttonLeft;
    private float buttonTop;
    private CharaEnum chara;
    private boolean livingFlug;

    public int getIconId() {
        return iconId;
    }
    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
    public float getButtonLeft() {
        return buttonLeft;
    }
    public void setButtonLeft(float buttonLeft) {
        this.buttonLeft = buttonLeft;
    }
    public float getButtonTop() {
        return buttonTop;
    }
    public void setButtonTop(float buttonTop) {
        this.buttonTop = buttonTop;
    }
    public CharaEnum getChara() {
        return chara;
    }
    public void setChara(CharaEnum chara) {
        this.chara = chara;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getButtonId() {
        return buttonId;
    }
    public void setButtonId(int buttonId) {
        this.buttonId = buttonId;
    }
    public boolean getLivingFlug() {
        return livingFlug;
    }
    public void setLivingFlug(boolean livingFlug) {
        this.livingFlug = livingFlug;
    }
}
