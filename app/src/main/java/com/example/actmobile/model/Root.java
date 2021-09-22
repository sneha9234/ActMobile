package com.example.actmobile.model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Root {

    @SerializedName("code")
    @Nullable
    public String code;

    @SerializedName("result")
    @Nullable
    public List<Result> result = null;

    @SerializedName("extra")
    @Nullable
    public List<String> extra = null;

    public String getCode() {
        return this.code;
    }

    public List<Result> getResult() {
        return this.result;
    }

    public List<String> getExtra() {
        return this.extra;
    }
}
