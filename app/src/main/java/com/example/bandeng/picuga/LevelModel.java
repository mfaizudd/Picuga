package com.example.bandeng.picuga;

/**
 * Created by Bandeng on 7/21/2018.
 */

public class LevelModel {
    public int level;
    public String status;

    public LevelModel() {}

    public LevelModel(int level, String status) {
        this.level = level;
        this.status = status;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
