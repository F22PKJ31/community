package com.f22pkj31.community.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class CommonId implements Serializable {

    private int id;
}
