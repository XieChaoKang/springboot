package com.xck.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xck
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
    }

    @Bean
    public Docket createRestApi() {
        List<ResponseMessage> responseMessageList = new ArrayList<>();
        responseMessageList.add(new ResponseMessageBuilder().code(111).message("账号或密码错误!").responseModel(new ModelRef("Result")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(222).message("以学生身份登录成功!").responseModel(new ModelRef("Result")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(333).message("以老师身份登录成功!").responseModel(new ModelRef("Result")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(234).message("发布学生成绩成功！").responseModel(new ModelRef("Result")).build());
        responseMessageList.add(new ResponseMessageBuilder().code(123).message("发布学生成绩失败！").responseModel(new ModelRef("Result")).build());
        //可以添加多个header或参数
        ParameterBuilder aParameterBuilder = new ParameterBuilder();
        aParameterBuilder
                //参数类型支持header, cookie, body, query etc
                .parameterType("header")
                //参数名
                .name("AccessToken")
                //默认值
                .defaultValue("token")
                .description("header中token字段测试")
                //指定参数值的类型
                .modelRef(new ModelRef("string"))
                //非必需，这里是全局配置，然而在登陆的时候是不用验证的
                .required(false).build();
        List<Parameter> aParameters = new ArrayList<Parameter>();
        aParameters.add(aParameterBuilder.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xck.demo"))
                .paths(PathSelectors.any())
                .build().globalOperationParameters(aParameters)
                .globalResponseMessage(RequestMethod.GET,responseMessageList)
                .globalResponseMessage(RequestMethod.POST,responseMessageList);
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("SpringBoot整合Swagger")
                .description("SpringBoot整合Swagger")
                .build();
    }
}
