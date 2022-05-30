package com.catand.bilibilibot1.JSONUtil.API;

import lombok.Data;

@Data
public class Page {
    private long cid;
    private int page;
    private String from;
    private String part;
    private int duration;
    private String vid;
    private String weblink;
    private Dimension dimension;
    private String first_frame;

    public Page() {
    }

    public Page(long cid, int page, String from, String part, int duration, String vid, String weblink, Dimension dimension, String first_frame) {
        this.cid = cid;
        this.page = page;
        this.from = from;
        this.part = part;
        this.duration = duration;
        this.vid = vid;
        this.weblink = weblink;
        this.dimension = dimension;
        this.first_frame = first_frame;
    }
}
