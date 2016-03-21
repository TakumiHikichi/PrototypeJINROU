package com.example.jinrou2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jinrou2.Base.ActivityBase.Lib.Act001PlayBGM;
import com.example.jinrou2.Lib.ToSpeech;

public class FirstActivity extends Act001PlayBGM implements View.OnClickListener {
    ToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tts=ToSpeech.getInstance(getApplicationContext());
        setContentView(R.layout.first_activity);
        startMusic(R.raw.startpage);

        Button b = (Button)findViewById(R.id.start_game);
        b.setOnClickListener(this);
        Button c = (Button)findViewById(R.id.read_rule);
        c.setOnClickListener(this);
        Button d = (Button)findViewById(R.id.setting);
        d.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch(v.getId()){
            case R.id.start_game:
                intent = new Intent(FirstActivity.this, GameActivity_setting.class);
                break;
            case R.id.read_rule:
                intent = new Intent(FirstActivity.this, GameActivity_confirm.class);
                break;
            case R.id.setting:
                intent = new Intent(FirstActivity.this, SettingActivity.class);
                break;
            default:
                break;
        }
        startActivity(intent);
    }

    @Override
    public void onRestart() {super.onRestart();}
    @Override
    public void onPause() {super.onPause();}
    @Override
    protected void onStart(){super.onStart();}
    @Override
    protected void onResume(){super.onResume();}
    @Override
    protected void onStop() {super.onStop();}
    @Override
    public void onDestroy() {super.onStop();}
}
