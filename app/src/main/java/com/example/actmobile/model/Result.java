package com.example.actmobile.model;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("name")
    @Nullable
    public String name;

    @SerializedName("code")
    @Nullable
    public String code;

    @SerializedName("states")
    @Nullable
    public List<States> states = null;

    @Nullable
    public List<States> getStates() {
        return states;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }
}
