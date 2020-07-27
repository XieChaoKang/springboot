package com.xck.demo.config;

/**
 * @author 谢朝康
 * @date 2020/4/8 0:31
 */
@org.springframework.context.annotation.Configuration
public class MybatisConfig {

    //支持驼峰命名法
    /*@Bean
    public ConfigurationCustomizer configurationCustomizer(){
        return new ConfigurationCustomizer() {
            @Override
            public void customize(Configuration configuration) {
                configuration.setMapUnderscoreToCamelCase(true);
            }
        };
    }*/
}
