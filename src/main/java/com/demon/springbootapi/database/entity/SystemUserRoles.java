package com.demon.springbootapi.database.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户权限表
 * </p>
 *
 * @author demon
 * @since 2020-06-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SystemUserRoles extends Model<SystemUserRoles> {

    private static final long serialVersionUID=1L;

    /**
     * 主键，自增长
     */
    @TableId(value = "ur_id", type = IdType.AUTO)
    private Integer urId;

    /**
     * 用户表id
     */
    @TableField("user_id")
    private String userId;

    /**
     * 角色表id
     */
    @TableField("role_id")
    private Integer roleId;


    @Override
    protected Serializable pkVal() {
        return this.urId;
    }

}
