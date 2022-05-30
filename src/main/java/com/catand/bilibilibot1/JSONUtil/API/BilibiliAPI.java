package com.catand.bilibilibot1.JSONUtil.API;

@lombok.Data
public class BilibiliAPI {
    private int code;
    private String message;
    private int ttl;
    private Data data;

    public BilibiliAPI(int code, String message, int ttl, Data data) {
        this.code = code;
        this.message = message;
        this.ttl = ttl;
        this.data = data;
    }

    public BilibiliAPI() {
    }
}
