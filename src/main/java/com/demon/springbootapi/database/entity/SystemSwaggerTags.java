package com.demon.springbootapi.database.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * swagger 标签
 * </p>
 *
 * @author demon
 * @since 2020-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SystemSwaggerTags extends Model<SystemSwaggerTags> {

    private static final long serialVersionUID=1L;

    @TableId(value = "st_id", type = IdType.AUTO)
    private Integer stId;

    /**
     * 标签名称
     */
    @TableField("st_name")
    private String stName;

    /**
     * 标签描述
     */
    @TableField("st_description")
    private String stDescription;

    @TableField("st_createtime")
    private Date stCreatetime;


    @Override
    protected Serializable pkVal() {
        return this.stId;
    }

}
