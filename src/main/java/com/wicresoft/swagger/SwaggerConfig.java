package com.wicresoft.swagger;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author daixin
 */
// 仅在没有Spring-boot的项目中需要开启此配置
@EnableWebMvc
// 启用Swagger2
@EnableSwagger2
// 让Spring来加载该类配置
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket buildDocket(){
        return new Docket(DocumentationType.SWAGGER_2).
                apiInfo(buildApiInf()).
                select().
                apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).
                paths(PathSelectors.any()).
                build();
    }

    private ApiInfo buildApiInf(){
        return new ApiInfoBuilder().
                title("开放接口API").
                termsOfServiceUrl("http://localhost:8099/v2/api-docs").
                description("WicresoftHRMS Rest API").
                contact(new Contact("Kris Dai", "http://www.kris.net/", "v-xida@microsoft.com")).
                version("1.0").
                build();
    }

}
