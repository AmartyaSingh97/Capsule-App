package com.example.capsuleapp.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

import com.example.capsuleapp.R;
import com.google.android.material.snackbar.Snackbar;

public class SnackBuilder {
    private static SnackBuilder ourInstance;

    public SnackBuilder() {
    }

    public static synchronized SnackBuilder getInstance(){
        if (ourInstance==null){
            ourInstance=new SnackBuilder();
        }
        return ourInstance;
    }

    /*snackbar length attributes*/

    public static final int LENGTH_INDEFINITE = -2;
    public static final int LENGTH_SHORT = -1;
    public static final int LENGTH_LONG = 0;
    private Snackbar snackbar;
    private String message;
    private int messageColor=0;
    private String actionTitle;
    private int backgroundType;
    private int showLength;
    private Context mCtx;
    private ActionCallBack actionCallBack;
    private int leftMargin=0;
    private int rightMargin=0;
    private int topMargin=0;
    private int bottomMargin=0;

    public SnackBuilder setMargin(int leftMargin, int rightMargin, int topMargin, int bottomMargin){
        this.leftMargin=leftMargin;
        this.rightMargin=rightMargin;
        this.topMargin=topMargin;
        this.bottomMargin=bottomMargin;
        return this;
    }




    public SnackBuilder setActionCallBack(ActionCallBack actionCallBack) {
        this.actionCallBack = actionCallBack;
        return this;
    }

    public SnackBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public SnackBuilder setMessageColor(int messageColor) {
        this.messageColor = messageColor;
        return this;
    }

    public SnackBuilder setActionTitle(String actionTitle) {
        this.actionTitle = actionTitle;
        return this;
    }

    public SnackBuilder setBackgroundType(int backgroundType) {
        this.backgroundType = backgroundType;
        return this;
    }

    public SnackBuilder setShowLength(int showLength) {
        this.showLength = showLength;
        return this;
    }

    public SnackBuilder setmCtx(Context mCtx) {
        this.mCtx = mCtx;
        return this;
    }
    public interface ActionCallBack{
        void onAction();
    }


    public void show() {
        snackbar = Snackbar.make(((Activity) mCtx).getWindow().getDecorView().findViewById(android.R.id.content), message,showLength);
        configSnackbar(snackbar);
        View sbView = snackbar.getView();
        TextView textView = sbView.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(messageColor==0 ? mCtx.getResources().getColor(R.color.white):mCtx.getResources().getColor(messageColor));
        snackbar.setActionTextColor(mCtx.getResources().getColor(R.color.white));
        setRoundBordersBg(mCtx, snackbar, backgroundType);
        snackbar.show();
        snackbar.setAction(actionTitle, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (actionCallBack !=null){
                    actionCallBack.onAction();
                }
            }
        });
    }

    public void dismiss() {
        if (snackbar != null) {
            try {
                snackbar.dismiss();
            } catch (Exception e) {
            }
        }
        snackbar = null;
    }

    private void configSnackbar(Snackbar snack) {
        addMargins(snack);
        ViewCompat.setElevation(snack.getView(), 6f);
    }

    private  void addMargins(Snackbar snack) {
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) snack.getView().getLayoutParams();
        params.setMargins(leftMargin, topMargin, rightMargin, bottomMargin);
        snack.getView().setLayoutParams(params);
    }

    private  void setRoundBordersBg(Context context, Snackbar snackbar, int type) {
        if (type==1)
            snackbar.getView().setBackground(context.getDrawable(R.drawable.snack_bg_red));
        if (type==2)
            snackbar.getView().setBackground(context.getDrawable(R.drawable.snack_bg_green));
        if (type==3)
            snackbar.getView().setBackground(context.getDrawable(R.drawable.snack_bg));
    }



}