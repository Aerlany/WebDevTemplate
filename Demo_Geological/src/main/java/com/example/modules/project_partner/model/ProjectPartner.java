package com.example.modules.project_partner.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Aerlany
 * @since 2024-04-17
 */
@Getter
@Setter
@TableName("project_partner")
public class ProjectPartner implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 工程项目编号
     */
    private String projectCode;

    /**
     * 成员名称
     */
    private String partnerName;

    /**
     * 成员类别
     */
    private String partnerType;

    /**
     * 电话号
     */
    private String telephoneNumber;


}
