package in.shuvam.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BiddingSystemGateway {
	@Bean
	public RouteLocator Route(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p->p.path("/products/**")
						.uri("http://localhost:8080/"))
				.route(p->p.path("/users/**")
						.uri("http://localhost:8181/"))
				.build();
		
	}

}
