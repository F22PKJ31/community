package com.f22pkj31.community.enums;

public enum StateEnum {

    UNREVIEWED(0), PASS(1), NOPASS(-1);

    private int state;

    StateEnum(int state) {
        this.state = state;
    }

    public int getValue() {
        return state;
    }

}
