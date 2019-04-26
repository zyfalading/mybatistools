package com.tribe.generators;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * mybatis-逆向工程
 */
public class GeneratorSqlmap {
    public void generator() throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException
    {
        //创建警告列表
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        //指定 逆向工程配置文件
        File configFile = new File("retail-service/src/main/resources/mybatis-generator.xml");
        System.out.println(configFile.getCanonicalPath());
        //创建配置解析器
        ConfigurationParser cp= new ConfigurationParser(warnings);
        //调用配置解析器创建配置对象（Configuration对象非常简单，可以简单理解为包含两个列表，一个列表是List<Context> contexts，包含了解析出来的Context对象，一个是List<String> classPathEntries，包含了配置的classPathEntry的location值）
        System.out.println(configFile);
        Configuration config = cp.parseConfiguration(configFile);
        //创建一个默认的ShellCallback对象，之前说过，shellcallback接口主要用来处理文件的创建和合并，传入overwrite参数；默认的shellcallback是不支持文件合并的；
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        //创建一个MyBatisGenerator对象。MyBatisGenerator类是真正用来执行生成动作的类
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                callback, warnings);
        myBatisGenerator.generate(null);
    }
    public static void main(String[] args) {
        try {
            GeneratorSqlmap generatorSqlmap = new GeneratorSqlmap();
            generatorSqlmap.generator();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
