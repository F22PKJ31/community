package com.f22pkj31.community.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author f22pkj31
 * @since 2019-03-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class NewsComment implements Serializable {

    private static final long serialVersionUID = 1L;

    private String content;

    private Integer userId;

    private String userName;

    private LocalDateTime createTime;

    private Integer newsId;


}
