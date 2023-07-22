package com.catand.bilibilibot1.util.json.config;

import lombok.Data;

import java.util.List;

@Data
public class Config {
    List<Bilibili> bilibiliVideos;
    List<String> targetGroups;

    public Config(List<Bilibili> bilibiliVideos, List<String> targetGroups) {
        this.bilibiliVideos = bilibiliVideos;
        this.targetGroups = targetGroups;
    }

    public Config() {
    }
}
