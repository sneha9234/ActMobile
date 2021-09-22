package com.example.actmobile.model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class States {
    @SerializedName("name")
    @Nullable
    public String name;

    @SerializedName("code")
    @Nullable
    public String code;

    @Nullable
    public String getName() {
        return name;
    }

    @Nullable
    public String getCode() {
        return code;
    }
}
