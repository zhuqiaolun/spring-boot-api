package com.yetech.springbootapi.database.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author demon
 * @since 2020-06-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SystemUser extends Model<SystemUser> {

    private static final long serialVersionUID=1L;

    /**
     * 主键，自增长
     */
    @TableId(value = "u_id", type = IdType.AUTO)
    private Integer uId;

    /**
     * 用户名
     */
    @TableField("app_id")
    private String appId;

    /**
     * 密码
     */
    @TableField("app_key")
    private String appKey;

    @TableField("nick_name")
    private String nickName;

    @TableField("user_name")
    private String userName;

    @TableField("create_time")
    private Date createTime;

    @Override
    protected Serializable pkVal() {
        return this.uId;
    }

}
