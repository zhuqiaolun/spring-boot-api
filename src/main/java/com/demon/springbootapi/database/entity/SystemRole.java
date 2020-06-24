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
 * 角色表
 * </p>
 *
 * @author demon
 * @since 2020-06-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SystemRole extends Model<SystemRole> {

    private static final long serialVersionUID=1L;

    /**
     * 主键，自增长
     */
    @TableId(value = "r_id", type = IdType.AUTO)
    private Integer rId;

    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;


    @Override
    protected Serializable pkVal() {
        return this.rId;
    }

}
