package com.catand.bilibilibot1.util.json.API;

import lombok.Data;

@Data
public class Owner {
    private long mid;
    private String name;
    private String face;

    public Owner() {
    }

    public Owner(long mid, String name, String face) {
        this.mid = mid;
        this.name = name;
        this.face = face;
    }
}
