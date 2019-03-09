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
public class BlogComment implements Serializable {

    private static final long serialVersionUID = 1L;

    private String content;

    private Integer userId;

    private String userName;

    private LocalDateTime createTime;

    private Integer blogId;

    private Integer blogTitle;

    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;


}
