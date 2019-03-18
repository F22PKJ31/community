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
 * @since 2019-03-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "news_id", type = IdType.AUTO)
    private Integer newsId;

    private String title;

    private Integer userId;

    private Long readCount;

    private String content;

    private LocalDateTime createTime;

    private Integer categoryId;

    private String categoryName;

    private String userName;
    private String imgUrl;


}
