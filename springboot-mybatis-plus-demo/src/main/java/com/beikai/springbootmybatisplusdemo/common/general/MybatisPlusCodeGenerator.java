package com.beikai.springbootmybatisplusdemo.common.general;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.beikai.springbootmybatisplusdemo.common.constant.DatabaseConstant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: hanbeikai
 * Date: 2019-09-01
 * Time: 08:52
 * Description: No Description  自动生成代码到通用类
 */
public class MybatisPlusCodeGenerator {
    public static void main(String[] args) {
        String[] tables = new String[]{"tz_user_collection"};
        String[] tablePrefixs = new String[]{"tz_"};
        executeCode("com.beikai.springbootmybatisplus", tables, tablePrefixs,"ReadAndWriterSpilt","/springboot-mybatis-plus");
    }

    private static void executeCode(String pack, String[] tables, String[] tablePrefixs,String modelName,String projectname) {
       // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 是否覆盖已有文件
        gc.setFileOverride(true);
        // 生成文件的输出目录
        String projectPath = System.getProperty("user.dir");
        projectname = StringUtils.isNotEmpty(projectname) ? projectname : "";
        gc.setOutputDir(projectPath + projectname+"/src/main/java");
        gc.setEntityName("%sModel");
        // 开发人员
        gc.setAuthor("beikai");
        // 是否打开输出目录
        gc.setOpen(false);
        // 开启 BaseResultMap
        gc.setBaseResultMap(false);
        // 指定生成的主键的ID类型
        gc.setIdType(IdType.AUTO);
        // 时间类型对应策略: 只使用 java.util.date 代替
        gc.setDateType(DateType.TIME_PACK);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();

        // 从试图获取
        //dsc.setSchemaName("V_LAW_CAMERA");
//		dsc.setUrl("jdbc:oracle:thin:@192.168.1.188:1521:orcl");
//		dsc.setDriverName("oracle.jdbc.OracleDriver");
//		dsc.setUsername("scott2");
//		dsc.setPassword("scott");

        //mysql配置
        dsc.setUrl(DatabaseConstant.writer_url);
        // dsc.setSchemaName("public");
        dsc.setDriverName(DatabaseConstant.writer_DriverName);
        dsc.setUsername(DatabaseConstant.writer_Username);
        dsc.setPassword(DatabaseConstant.writer_Password);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        // 父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
        pc.setParent(pack);
        // Entity包名
        pc.setEntity("model");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        //设置控制器包名
        pc.setController("controller");
        // 设置 model name
        pc.setModuleName(modelName);

        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        final String temp = projectname;
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                if (StringUtils.isEmpty(pc.getModuleName())) {
                    return projectPath + temp+"/src/main/resources/mapper/" + tableInfo.getXmlName() + StringPool.DOT_XML;
                } else {
                    return projectPath +temp+ "/src/main/resources/mapper/" + pc.getModuleName() + "/" + tableInfo.getXmlName() + StringPool.DOT_XML;
                }
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        mpg.setTemplate(new TemplateConfig().setXml(null));

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 数据库表映射到实体的命名策略: 下划线转驼峰命名
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 数据库表字段映射到实体的命名策略: 下划线转驼峰命名
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 【实体】是否为lombok模型（默认 false）
        strategy.setEntityLombokModel(true);
        // 需要包含的表名，允许正则表达式（与exclude二选一配置）
        strategy.setInclude(tables);
        // 驼峰转连字符
        strategy.setControllerMappingHyphenStyle(true);
        // 表前缀
        strategy.setTablePrefix(tablePrefixs);
        //restcontroller
        strategy.setRestControllerStyle(true);
        mpg.setStrategy(strategy);
        //mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
