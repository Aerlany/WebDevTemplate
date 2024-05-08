package com.example.modules.project_information.model;

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
 * @since 2024-04-15
 */
@Getter
@Setter
@TableName("project_information")
public class ProjectInformation implements Serializable {

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
     * 项目名称
     */
    private String projectName;

    /**
     * 项目负责人/创建人
     */
    private String projectLeader;

    /**
     * 建设单位
     */
    private String unitBuild;

    /**
     * 项目地区
     */
    private String projectArea;

    /**
     * 项目地址
     */
    private String projectAddress;

    /**
     * 经纬度
     */
    private String longitudeLatitude;

    /**
     * 工程类别
     */
    private String projectType;

    /**
     * 勘察阶段
     */
    private String exploreStage;

    /**
     * 勘察等级
     */
    private String exploreLevel;


}
