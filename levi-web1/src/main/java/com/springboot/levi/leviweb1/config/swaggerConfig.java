package com.springboot.levi.leviweb1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jianghaihui
 * @date 2019/9/2 10:56
 */
@Configuration
@EnableSwagger2
public class swaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.springboot.levi.leviweb1"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("levi 2.0 interface系统")
                .description("levi 2.0 interface系统")
                .termsOfServiceUrl("****")
                .version("1.0")
                .build();
    }


    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String,String> map = new HashMap<String,String>();
        map.put("strs","hello");
        String res = restTemplate.getForObject("http://localhost:8080/test?strs={strs}",String.class,map);
        System.out.println(res);
    }
}
