package com.example.modules.project_information.controller;

import com.example.modules.project_information.service.impl.ProjectInformationServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Aerlany
 * @since 2024-04-15
 */
@RestController
@Api(tags = "项目基本信息")
@RequestMapping("/projectInformation")
public class ProjectInformationController {
    @Autowired
    private ProjectInformationServiceImpl projectInformationService;

    @ApiOperation("GET 方法测试")
    @GetMapping("/get")
//    @PreAuthorize("hasAuthority('admin')")
    public Object get() {
        return projectInformationService.getById(1);
    }
}
