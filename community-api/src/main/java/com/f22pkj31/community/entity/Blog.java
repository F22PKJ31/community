package com.f22pkj31.community.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Blog implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title;

    private String content;

    private LocalDateTime createTime;

    private Integer authorId;

    private Integer readCount;

    private String authorName;

    private Integer categoryId;

    @TableId(value = "blog_id", type = IdType.AUTO)
    private Integer blogId;

    private String categoryName;


}
