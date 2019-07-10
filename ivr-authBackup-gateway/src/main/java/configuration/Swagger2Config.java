package configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				// 指定controller存放的目录路径
				.apis(RequestHandlerSelectors.basePackage("com.ctbcbank.ivr.authbackup.gateway.controller")).paths(PathSelectors.any()).build();
	}
}
