package com.catand.bilibilibot1.JSONUtil.API;

import lombok.Data;

import java.util.List;

@Data
public class Subtitle {
    private boolean allow_submit;
    private List list;

    public Subtitle(boolean allow_submit, List list) {
        this.allow_submit = allow_submit;
        this.list = list;
    }

    public Subtitle() {
    }
}
