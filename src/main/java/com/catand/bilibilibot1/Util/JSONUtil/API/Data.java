package com.catand.bilibilibot1.Util.JSONUtil.API;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
@lombok.Data
public class Data {
    private String bvid;
    private long aid;
    private int videos;
    private int tid;
    private String tname;
    private int copyright;
    private String pic;
    private String title;
    private long pubdate;
    private long ctime;
    private String desc;
    private List<Desc_v2> desc_v2;
    private int state;
    private int duration;
    private long mission_id;
    private Rights rights;
    private Owner owner;
    private Stat stat;
    private String dynamic;
    private long cid;
    private Dimension dimension;

    //TODO
    private Owner premiere;
    private int teenage_mode;
    private boolean no_cache;
    private List<Page> pages;
    private Subtitle subtitle;
    private boolean is_season_display;
    private User_garb user_garb;
    private Honor_reply honor_reply;

    public Data() {
    }

    public Data(String bvid, long aid, int videos, int tid, String tname, int coryright, String pic, String title, long pubdate, long ctime, String desc, List<Desc_v2> desc_v2, int state, int duration, long mission_id, Rights rights, Owner owner, Stat stat, String dynamic, long cid, Dimension dimension, Owner premiere, int teenage_mode, boolean no_cache, List<Page> pages, Subtitle subtitle, boolean is_season_display, User_garb user_garb, Honor_reply honor_reply) {
        this.bvid = bvid;
        this.aid = aid;
        this.videos = videos;
        this.tid = tid;
        this.tname = tname;
        this.copyright = coryright;
        this.pic = pic;
        this.title = title;
        this.pubdate = pubdate;
        this.ctime = ctime;
        this.desc = desc;
        this.desc_v2 = desc_v2;
        this.state = state;
        this.duration = duration;
        this.mission_id = mission_id;
        this.rights = rights;
        this.owner = owner;
        this.stat = stat;
        this.dynamic = dynamic;
        this.cid = cid;
        this.dimension = dimension;
        this.premiere = premiere;
        this.teenage_mode = teenage_mode;
        this.no_cache = no_cache;
        this.pages = pages;
        this.subtitle = subtitle;
        this.is_season_display = is_season_display;
        this.user_garb = user_garb;
        this.honor_reply = honor_reply;
    }
}
