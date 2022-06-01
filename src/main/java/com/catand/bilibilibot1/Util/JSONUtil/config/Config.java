package com.catand.bilibilibot1.Util.JSONUtil.config;

import lombok.Data;

import java.util.List;

@Data
public class Config {
    List<Bilibili> bilibilis;

    public Config(List<Bilibili> bilibilis) {
        this.bilibilis = bilibilis;
    }

    public Config() {
    }
}
