package com.f22pkj31.community.entity;

import lombok.Data;

@Data
public class PageIn<T> {

    private long size = 10;

    private long current = 0;

    private T t;
}
