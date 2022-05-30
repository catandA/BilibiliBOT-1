package com.catand.bilibilibot1.JSONUtil.API;

import lombok.Data;

@Data
public class Dimension {
    private int width;
    private int height;
    private int rotate;

    public Dimension(int width, int height, int rotate) {
        this.width = width;
        this.height = height;
        this.rotate = rotate;
    }

    public Dimension() {
    }
}
