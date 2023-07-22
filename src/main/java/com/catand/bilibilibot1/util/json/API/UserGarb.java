package com.catand.bilibilibot1.util.json.API;

import lombok.Data;

@Data
public class UserGarb {
    private String url_image_ani_cut;

    public UserGarb(String url_image_ani_cut) {
        this.url_image_ani_cut = url_image_ani_cut;
    }

    public UserGarb() {
    }
}
