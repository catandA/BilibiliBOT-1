package com.catand.bilibilibot1.JSONUtil.API;

import lombok.Data;

@Data
public class Desc_v2 {
    private String raw_text;
    private int type;
    private int biz_id;

    public Desc_v2() {
    }

    public Desc_v2(String raw_text, int type, int biz_id) {
        this.raw_text = raw_text;
        this.type = type;
        this.biz_id = biz_id;
    }
}
