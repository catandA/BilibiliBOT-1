package com.catand.bilibilibot1.Util.JSONUtil.API;

import lombok.Data;

@Data
public class User_garb {
    private String url_image_ani_cut;

    public User_garb(String url_image_ani_cut) {
        this.url_image_ani_cut = url_image_ani_cut;
    }

    public User_garb() {
    }
}
