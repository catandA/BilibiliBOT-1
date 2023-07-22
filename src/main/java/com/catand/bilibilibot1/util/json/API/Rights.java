package com.catand.bilibilibot1.util.json.API;

import lombok.Data;

@Data
public class Rights {
    private int bp;
    private int elec;
    private int download;
    private int movie;
    private int pay;
    private int hd5;
    private int no_reprint;
    private int autoplay;
    private int ugc_pay;
    private int is_cooperation;
    private int ugc_pay_preview;
    private int no_background;
    private int clean_mode;
    private int is_stein_gate;
    private int is_360;
    private int no_share;

    public Rights(int bp, int elec, int download, int movie, int pay, int hd5, int no_reprint, int autoplay, int ugc_pay, int is_cooperation, int ugc_pay_preview, int no_background, int clean_mode, int is_stein_gate, int is_360, int no_share) {
        this.bp = bp;
        this.elec = elec;
        this.download = download;
        this.movie = movie;
        this.pay = pay;
        this.hd5 = hd5;
        this.no_reprint = no_reprint;
        this.autoplay = autoplay;
        this.ugc_pay = ugc_pay;
        this.is_cooperation = is_cooperation;
        this.ugc_pay_preview = ugc_pay_preview;
        this.no_background = no_background;
        this.clean_mode = clean_mode;
        this.is_stein_gate = is_stein_gate;
        this.is_360 = is_360;
        this.no_share = no_share;
    }

    public Rights() {
    }
}
