package com.example.jinrou2.Lib;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by ryouta on 2016/02/15.
 */
public class MakePosiNegaDialog extends DialogFragment {
    private String header;
    private String body;
    private boolean positiveButtonFlug;
    private boolean negativeButtonFlug;
    private String positiveButtonText;
    private String negativeButtonText;
    private DialogInterface.OnClickListener onClickListener;

    public MakePosiNegaDialog(DialogInterface.OnClickListener onClickListener){
        positiveButtonFlug=false;
        negativeButtonFlug=false;
        this.onClickListener=onClickListener;
    }
    public void showDialog(Activity act){
        this.show(act.getFragmentManager(),"");
    }
    public void setHeader(String header){
        this.header=header;
    }
    public void setBody(String body){
        this.body=body;
    }
    public void setPositiveButtonText(String positiveButtonText){
        positiveButtonFlug=true;
        this.positiveButtonText = positiveButtonText;
    }
    public void setNegativeButtonText(String negativeButtonText){
        negativeButtonFlug=true;
        this.negativeButtonText=negativeButtonText;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(this.header);
        builder.setMessage(this.body);
        if(positiveButtonFlug){
            builder.setPositiveButton(this.positiveButtonText,onClickListener);
        }
        if(negativeButtonFlug){
            builder.setNegativeButton(this.negativeButtonText,onClickListener);
        }
        return builder.create();
    }
}
