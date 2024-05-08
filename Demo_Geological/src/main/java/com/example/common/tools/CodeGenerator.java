package com.example.common.tools;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.springframework.util.CollectionUtils;

import java.util.*;

public class CodeGenerator {

    public static void main(String[] args) {
        String password = "123456";
        String username = "root";
        String url = "jdbc:mysql://localhost:3306/geographic_information_data?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&nullCatalogMeansCurrent=true";
        DataSourceConfig.Builder dataSourceConfig = new DataSourceConfig.Builder(url, username, password);

        //String finalProjectPath = System.getProperty("user.dir"); //当前项目根目录
        String finalProjectPath = Objects.requireNonNull(CodeGenerator.class.getClassLoader().getResource(""))
                .getPath().replace("/target/classes/", "");

        List<String> tableList = new ArrayList<>();
        tableList.add("user");    // 生成全部table注释add掉就好

        // 自动生成（固定模板）
//        creteModel(dataSourceConfig, finalProjectPath, Boolean.TRUE, tableList);

        // 手动输入（互动式）
        createSingleModel(dataSourceConfig, finalProjectPath);
    }

    private static void creteModel(DataSourceConfig.Builder dataSourceConfig,
                                   String finalProjectPath,
                                   Boolean isOverride,
                                   List<String> tableList) {
        // dataSourceConfig数据源
        FastAutoGenerator.create(dataSourceConfig)
                // 全局代码配置类
                .globalConfig(builder -> {
                    builder.author("Aerlany") // 设置作者
//          .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir() //禁止打开输出目录
                            .outputDir(finalProjectPath + "/src/main/java"); // 指定输出目录
                })
                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.yeye") // 设置父包名
                            .entity("model") //设置entity包名
                            .controller("web.controller")
                            .mapper("dao")
                            .service("service")
                            .serviceImpl("service.impl")
                            .other("other")
                            .pathInfo(Collections.singletonMap(OutputFile.xml, finalProjectPath + "/src/main/resources/mapper")); // 设置mapperXml生成路径

                })
                // 策略配置
                .strategyConfig(builder -> {

                    if (!CollectionUtils.isEmpty(tableList)) {
                        builder.addInclude(tableList);
                    }

                    builder.addTablePrefix("wms_");// 设置过滤表前缀

                    builder.entityBuilder() // entity配置
                            .enableRemoveIsPrefix()
                            .enableTableFieldAnnotation()
                            .enableLombok();

                    builder.controllerBuilder() // controller配置
                            .enableRestStyle();

                    builder.serviceBuilder() // service配置
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .fileOverride();

                    builder.mapperBuilder() // mapper配置
                            .enableBaseResultMap()
                            .enableMapperAnnotation()
                            .formatMapperFileName("%sDao")
                            .formatXmlFileName("%sMapper")
                            .enableBaseColumnList();
                    if (isOverride) {
                        builder.entityBuilder() // entity配置
                                .fileOverride();
                        builder.controllerBuilder() // controller配置
                                .fileOverride();
                        builder.serviceBuilder() // service配置
                                .fileOverride();
                        builder.mapperBuilder() // mapper配置
                                .fileOverride();
                    }
                })
                // 自定义模版引擎
                .injectionConfig(consumer -> {
                    Map<String, String> customFile = new HashMap<>();
                    // DTO
//        customFile.put("DTO.java", "/templates/entityDTO.java.ftl"); //自定义模版引擎
                    consumer.customFile(customFile);
                })
                // 选择模板引擎
                .templateEngine(new VelocityTemplateEngine())
                // 执行
                .execute();
    }

    private static void createSingleModel(DataSourceConfig.Builder dataSourceConfig, String finalProjectPath) {
        System.out.println("请输入表名，多个英文逗号分隔？所有输入 all");
        Scanner scanner = new Scanner(System.in);
        String tableName = scanner.next();

        FastAutoGenerator.create(dataSourceConfig)
                // 全局配置
                .globalConfig((builder) ->
                        builder.author("Aerlany")
                                .fileOverride()
                                .outputDir(finalProjectPath + "/src/main/java"))

                // 包配置
                .packageConfig(builder -> {
                    builder.parent("com.example.modules." + tableName) // 设置父包名
                            .entity("model") //设置entity包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, finalProjectPath + "/src/main/java/com/example/modules/" + tableName + "/mapper")); // 设置mapperXml生成路径

                })

                // 策略配置
                .strategyConfig((builder) -> builder.addInclude(getTables(tableName))
                        .controllerBuilder().enableRestStyle()
                        .entityBuilder().enableLombok()
                        .mapperBuilder().enableBaseResultMap().enableBaseColumnList()
                        .controllerBuilder().enableRestStyle().fileOverride()
                        .build())

                .execute();
    }

    // 处理 all 情况
    protected static List<String> getTables(String tables) {
        return "all".equals(tables) ? Collections.emptyList() : Arrays.asList(tables.split(","));
    }

}