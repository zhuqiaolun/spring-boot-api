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
 * swagger 的URL 配置
 * </p>
 *
 * @author demon
 * @since 2020-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SystemSwaggerUrl extends Model<SystemSwaggerUrl> {

    private static final long serialVersionUID=1L;

    @TableId(value = "su_id", type = IdType.AUTO)
    private Integer suId;

    @TableField("su_url")
    private String suUrl;

    /**
     * 请求类型
     */
    @TableField("su_method")
    private String suMethod;

    /**
     * 标签
     */
    @TableField("su_tags")
    private String suTags;

    /**
     * 标题
     */
    @TableField("su_summary")
    private String suSummary;

    /**
     * 描述
     */
    @TableField("su_description")
    private String suDescription;

    /**
     * 标识（可写方法名称）
     */
    @TableField("su_operationId")
    private String suOperationid;

    /**
     * 请求参数类型
     */
    @TableField("su_consumes")
    private String suConsumes;

    /**
     * 返回参数类型
     */
    @TableField("su_produces")
    private String suProduces;

    /**
     * 参数
     */
    @TableField("su_parameters")
    private String suParameters;

    /**
     * 响应
     */
    @TableField("su_responses")
    private String suResponses;

    /**
     * 全局api_key
     */
    @TableField("su_security")
    private String suSecurity;

    /**
     * 是否过时
     */
    @TableField("su_deprecated")
    private Boolean suDeprecated;

    @TableField("su_createtime")
    private Date suCreatetime;


    @Override
    protected Serializable pkVal() {
        return this.suId;
    }

}
