package org.typhon.apigateway;
	
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
	
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
	
@Configuration
@SpringBootApplication
//@EnableSwagger2
public class ApigatewayApplication {

	@Bean
	RouteLocator gatewayArtifactServiceRouter(RouteLocatorBuilder builder){
        return builder.routes()
				.route(r -> r.path("/clerance/**").and().method("GET")
                        .filters(f-> f.rewritePath("/clerance","/clerance"))
                        .uri("http://localhost:8091"))
                .route(r -> r.path("/clerance").and().method("POST")
                        .filters(f-> f.rewritePath("/clerance","/clerance"))
                        .uri("http://localhost:8091"))
                .route(r -> r.path("/clerance/**").and().method("PUT")
                        .filters(f-> f.rewritePath("/clerance","/clerance"))
                        .uri("http://localhost:8091"))
                .route(r -> r.path("/clerance/**").and().method("DELETE")
                        .filters(f-> f.rewritePath("/clerance","/clerance"))
                        .uri("http://localhost:8091"))

				.route(r -> r.path("/customer/**").and().method("GET")
                        .filters(f-> f.rewritePath("/customer","/customer"))
                        .uri("http://localhost:8092"))
                .route(r -> r.path("/customer").and().method("POST")
                        .filters(f-> f.rewritePath("/customer","/customer"))
                        .uri("http://localhost:8092"))
                .route(r -> r.path("/customer/**").and().method("PUT")
                        .filters(f-> f.rewritePath("/customer","/customer"))
                        .uri("http://localhost:8092"))
                .route(r -> r.path("/customer/**").and().method("DELETE")
                        .filters(f-> f.rewritePath("/customer","/customer"))
                        .uri("http://localhost:8092"))

				.route(r -> r.path("/customerdetails/**").and().method("GET")
                        .filters(f-> f.rewritePath("/customerdetails","/customerdetails"))
                        .uri("http://localhost:8093"))
                .route(r -> r.path("/customerdetails").and().method("POST")
                        .filters(f-> f.rewritePath("/customerdetails","/customerdetails"))
                        .uri("http://localhost:8093"))
                .route(r -> r.path("/customerdetails/**").and().method("PUT")
                        .filters(f-> f.rewritePath("/customerdetails","/customerdetails"))
                        .uri("http://localhost:8093"))
                .route(r -> r.path("/customerdetails/**").and().method("DELETE")
                        .filters(f-> f.rewritePath("/customerdetails","/customerdetails"))
                        .uri("http://localhost:8093"))

				.route(r -> r.path("/inventory/**").and().method("GET")
                        .filters(f-> f.rewritePath("/inventory","/inventory"))
                        .uri("http://localhost:8094"))
                .route(r -> r.path("/inventory").and().method("POST")
                        .filters(f-> f.rewritePath("/inventory","/inventory"))
                        .uri("http://localhost:8094"))
                .route(r -> r.path("/inventory/**").and().method("PUT")
                        .filters(f-> f.rewritePath("/inventory","/inventory"))
                        .uri("http://localhost:8094"))
                .route(r -> r.path("/inventory/**").and().method("DELETE")
                        .filters(f-> f.rewritePath("/inventory","/inventory"))
                        .uri("http://localhost:8094"))

				.route(r -> r.path("/purchasedetails/**").and().method("GET")
                        .filters(f-> f.rewritePath("/purchasedetails","/purchasedetails"))
                        .uri("http://localhost:8095"))
                .route(r -> r.path("/purchasedetails").and().method("POST")
                        .filters(f-> f.rewritePath("/purchasedetails","/purchasedetails"))
                        .uri("http://localhost:8095"))
                .route(r -> r.path("/purchasedetails/**").and().method("PUT")
                        .filters(f-> f.rewritePath("/purchasedetails","/purchasedetails"))
                        .uri("http://localhost:8095"))
                .route(r -> r.path("/purchasedetails/**").and().method("DELETE")
                        .filters(f-> f.rewritePath("/purchasedetails","/purchasedetails"))
                        .uri("http://localhost:8095"))

				.route(r -> r.path("/similarity/**").and().method("GET")
                        .filters(f-> f.rewritePath("/similarity","/similarity"))
                        .uri("http://localhost:8096"))
                .route(r -> r.path("/similarity").and().method("POST")
                        .filters(f-> f.rewritePath("/similarity","/similarity"))
                        .uri("http://localhost:8096"))
                .route(r -> r.path("/similarity/**").and().method("PUT")
                        .filters(f-> f.rewritePath("/similarity","/similarity"))
                        .uri("http://localhost:8096"))
                .route(r -> r.path("/similarity/**").and().method("DELETE")
                        .filters(f-> f.rewritePath("/similarity","/similarity"))
                        .uri("http://localhost:8096"))

				.route(r -> r.path("/song/**").and().method("GET")
                        .filters(f-> f.rewritePath("/song","/song"))
                        .uri("http://localhost:8097"))
                .route(r -> r.path("/song").and().method("POST")
                        .filters(f-> f.rewritePath("/song","/song"))
                        .uri("http://localhost:8097"))
                .route(r -> r.path("/song/**").and().method("PUT")
                        .filters(f-> f.rewritePath("/song","/song"))
                        .uri("http://localhost:8097"))
                .route(r -> r.path("/song/**").and().method("DELETE")
                        .filters(f-> f.rewritePath("/song","/song"))
                        .uri("http://localhost:8097"))

				.route(r -> r.path("/track/**").and().method("GET")
                        .filters(f-> f.rewritePath("/track","/track"))
                        .uri("http://localhost:8098"))
                .route(r -> r.path("/track").and().method("POST")
                        .filters(f-> f.rewritePath("/track","/track"))
                        .uri("http://localhost:8098"))
                .route(r -> r.path("/track/**").and().method("PUT")
                        .filters(f-> f.rewritePath("/track","/track"))
                        .uri("http://localhost:8098"))
                .route(r -> r.path("/track/**").and().method("DELETE")
                        .filters(f-> f.rewritePath("/track","/track"))
                        .uri("http://localhost:8098"))
				.build();
    }
		
	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}
}
