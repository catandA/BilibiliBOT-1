package com.catand.bilibilibot1.util.json.API;

@lombok.Data
public class BilibiliJSONClass {
    private int code;
    private String message;
    private int ttl;
    private Data data;

    public BilibiliJSONClass(int code, String message, int ttl, Data data) {
        this.code = code;
        this.message = message;
        this.ttl = ttl;
        this.data = data;
    }

    public BilibiliJSONClass() {
    }
}
