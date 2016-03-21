package com.example.jinrou2.Data;

import com.example.CharaEnum;

import java.io.Serializable;

/**
 * Created by ryouta on 2015/11/15.
 */
public class PlayerData implements Serializable{
    private String name;
    private int buttonId;
    private int iconId;
    private int buttonLeft;
    private int buttonTop;
    private CharaEnum chara;
    private boolean livingFlug;

    public int getIconId() {
        return iconId;
    }
    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
    public int getButtonLeft() {
        return buttonLeft;
    }
    public void setButtonLeft(int buttonLeft) {
        this.buttonLeft = buttonLeft;
    }
    public int getButtonTop() {
        return buttonTop;
    }
    public void setButtonTop(int buttonTop) {
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
