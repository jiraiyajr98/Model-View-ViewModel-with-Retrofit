package com.ankan.retrofitsqlitemvvm.Retrofit;

import com.google.gson.annotations.SerializedName;

public class Posts {

    @SerializedName("body")
    private String body;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("userId")
    private int userId;

    public String getBody() {
        return body;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getUserId() {
        return userId;
    }
}
