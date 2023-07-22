package com.catand.bilibilibot1.util.json.API;

import lombok.Data;

@Data
public class Desc {
    private String raw_text;
    private int type;
    private int biz_id;

    public Desc() {
    }

    public Desc(String raw_text, int type, int biz_id) {
        this.raw_text = raw_text;
        this.type = type;
        this.biz_id = biz_id;
    }
}
