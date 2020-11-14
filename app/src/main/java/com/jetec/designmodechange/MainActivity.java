package com.jetec.designmodechange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
/**請注意
 * 請至AndroidManifest中的<application...中加入
 * android:name=".SaveStatus"
 * 不然會閃退喔
 * 此名字是綁定儲存現在色彩狀態的SaveStatus.java這個檔案
 * */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**從SharedPreferences中垃取目前背景色彩的設定
         * (備註：若沒有設定前，預設皆為日間模式，詳見
         * this.isNightModeEnabled = mPrefs.getBoolean(NIGHT_MODE, false);
         * 這一行)*/
        if (SaveStatus.getInstance().isNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        setContentView(R.layout.activity_main);

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch sw = findViewById(R.id.switch1);
        /**偵測目前的模式，如果是夜間模式的話switch設定為打開*/
        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES){
            sw.setChecked(true);
        }

        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    /**設定為夜間模式，並將設定寫入SharedPreferences*/
                    SaveStatus.getInstance().setIsNightModeEnabled(true);
                } else {
                    /**設定為日間模式，並將設定寫入SharedPreferences*/
                    SaveStatus.getInstance().setIsNightModeEnabled(false);
                }
                /**將此頁面finish掉後重新打開*/
                Intent intent = getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                startActivity(intent);
            }
        });
    }
}