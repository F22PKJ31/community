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
 * @since 2019-03-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BlogCollection implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "collection_id", type = IdType.AUTO)
    private Integer collectionId;

    private Integer blogId;

    private Integer userId;

    private LocalDateTime createTime;

    private String blogTitle;

    private String userName;


}
