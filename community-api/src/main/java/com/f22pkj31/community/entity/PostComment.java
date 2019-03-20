package com.f22pkj31.community.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author f22pkj31
 * @since 2019-03-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PostComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "comment_id", type = IdType.AUTO)
    private Integer commentId;

    private String postTitle;

    private String content;

    private Integer userId;

    private LocalDateTime createTime;

    private String userName;

    private Integer postId;


}
