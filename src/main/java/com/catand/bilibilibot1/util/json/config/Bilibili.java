package com.catand.bilibilibot1.util.json.config;

import lombok.Data;

@Data
public class Bilibili {
    private String name;
    private String type;
    private String url;

    public Bilibili(String name, String type, String url) {
        this.name = name;
        this.type = type;
        this.url = url;
    }

    public Bilibili() {
    }
}
