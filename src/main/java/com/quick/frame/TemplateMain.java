package com.quick.frame;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 对象功能 模板生成类
 * 开发人员：曾煜
 * 创建时间：2020/8/11 18:12
 * </pre>
 **/
public class TemplateMain {
    public static void main(String[] args) {

        //作者
        String author="zengyu";
        //是否覆盖文件
        boolean fileOverride=true;
        //是否打开swagger2
        boolean openSwagger2=true;


        //项目目录
        String projectPath = System.getProperty("user.dir");
        //全局策略
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setActiveRecord(true)   //支持AR模式
                .setAuthor(author)              //作者
                .setOutputDir(projectPath + "/src/main/java") //生成路径
                .setFileOverride(fileOverride)      //是否覆盖文件
                .setIdType(IdType.ASSIGN_UUID)     //主键策略
                .setOpen(false)              //是否打开输出目录
                .setSwagger2(openSwagger2)          //开启swagger2
                .setBaseResultMap(true)
                .setBaseColumnList(true)
                .setSwagger2(true)
                .setMapperName("%sDao");
               

        //数据源配置
        DataSourceConfig dbConfig = new DataSourceConfig();
        dbConfig.setDbType(DbType.MYSQL);
        dbConfig.setUrl("jdbc:mysql://47.106.218.109:3306/frame?useUnicode=true&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dbConfig.setDriverName("com.mysql.jdbc.Driver");
        dbConfig.setUsername("zy");
        dbConfig.setPassword("123456");


        //策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true);//全局大写命名
        //下划线转驼峰
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        //生成Rest控制器
        strategyConfig.setRestControllerStyle(true);
        //设置Lombok
        strategyConfig.setEntityLombokModel(true);
        //设置逻辑删除
        strategyConfig.setLogicDeleteFieldName("is_delete");
        //设置自动填充配置
        TableFill createTime =new TableFill("create_time", FieldFill.INSERT);
        TableFill modifyTime =new TableFill("update_time", FieldFill.INSERT_UPDATE);
        List<TableFill> tableFill=new ArrayList<>();
        tableFill.add(createTime);
        tableFill.add(modifyTime);
        strategyConfig.setTableFillList(tableFill);
        //乐观锁配置
        //strategyConfig.setVersionFieldName("version");

        //strategyConfig.setInclude(new String[] { "user_info" });  //只生成指定的表

        String templatePath = "/templates/mapper.xml.vm";

        //包名
        PackageConfig packageConfig=new PackageConfig();
        //设置父包
        packageConfig.setParent("com.quick.frame");
        packageConfig.setMapper("dao");
        packageConfig.setXml(null);

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/" + packageConfig.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        cfg.setFileOutConfigList(focList);

        //整合配置
        AutoGenerator autoGenerator=new AutoGenerator();
        autoGenerator.setCfg(cfg);
        autoGenerator.setGlobalConfig(globalConfig);
        autoGenerator.setDataSource(dbConfig);
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.setPackageInfo(packageConfig);

        //执行
        autoGenerator.execute();

    }
}
