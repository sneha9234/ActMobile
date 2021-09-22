package com.example.actmobile;

import android.content.Context;
import android.content.SharedPreferences;

public class prefConfig {
    private SharedPreferences sharedPreferences;
    private Context context;

    public prefConfig(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.pref_file), Context.MODE_PRIVATE);
    }

    public void writeName(String name) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_name), name);
        editor.commit();
    }

    public String readName() {
        return sharedPreferences.getString(context.getString(R.string.pref_name), "");
    }

    public void writeCode(String code) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.pref_code), code);
        editor.commit();
    }

    public String readCode() {
        return sharedPreferences.getString(context.getString(R.string.pref_code), "");
    }
}
