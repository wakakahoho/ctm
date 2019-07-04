package com.thw.ctm.core.enums;

import java.util.Objects;

/**
 * @author duanxiang 2019/7/4 17:00
 **/
public enum DurationUnit {

    MINUTES("min", 1), LIGHTENING("lightning", 5);
    private String name;
    private int value;

    private DurationUnit(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int inMinutes(int duration) {
        return duration * value;
    }

    public DurationUnit of(String name) {
        Objects.requireNonNull(name);
        return valueOf(name);
    }

    public String getName(){
        return name;
    }

    public int inMinutes() {
        return value * DurationUnit.MINUTES.value;
    }

}
