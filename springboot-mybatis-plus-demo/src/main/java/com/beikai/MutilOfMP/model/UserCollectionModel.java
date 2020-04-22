package com.beikai.MutilOfMP.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @author beikai
 * @since 2019-09-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tz_user_collection")
public class UserCollectionModel implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 收藏表
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品id
     */
    private Long prodId;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 收藏时间
     */
    private LocalDateTime createTime;


}
