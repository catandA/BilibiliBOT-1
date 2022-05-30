package com.catand.bilibilibot1.JSONUtil.API;

import lombok.Data;

@Data
public class Stat {
    private int aid;
    private int view;
    private int danmaku;
    private int reply;
    private int favorite;
    private int coin;
    private int share;
    private int now_rank;
    private int his_rank;
    private int like;
    private int dislike;
    private String evaluation;
    private String argue_msg;

    public Stat() {
    }

    public Stat(int aid, int view, int danmaku, int reply, int favorite, int coin, int share, int now_rank, int his_rank, int like, int dislike, String evaluation, String argue_msg) {
        this.aid = aid;
        this.view = view;
        this.danmaku = danmaku;
        this.reply = reply;
        this.favorite = favorite;
        this.coin = coin;
        this.share = share;
        this.now_rank = now_rank;
        this.his_rank = his_rank;
        this.like = like;
        this.dislike = dislike;
        this.evaluation = evaluation;
        this.argue_msg = argue_msg;
    }
}
