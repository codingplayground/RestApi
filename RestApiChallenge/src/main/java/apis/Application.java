package apis;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;


import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class Application extends SpringBootServletInitializer {
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Application.class);
	}
	  public static void main(final String[] args) {
	        SpringApplication sa =new SpringApplication(Application.class);
	        sa.run(Application.class, args);
	  }        
	      
	  @Bean
	    public Docket myApi() { 
	        return new Docket(DocumentationType.SWAGGER_2)
	          .select()                                  
	          .apis(RequestHandlerSelectors.basePackage("apis"))              
	          .paths(PathSelectors.regex("/api.*"))                          
	          .build()
	        .apiInfo(metaData());                                       
	    }
	  
	    private ApiInfo metaData() {
	        return new ApiInfoBuilder()
	                .title("Spring Boot REST API")
	                .description("\"Spring Boot REST API for Twitter Test Data Challenge\"")
	                .version("1.0.0")
	                .license("Apache License Version 2.0")
	                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
	                .build();
}
	
		}
