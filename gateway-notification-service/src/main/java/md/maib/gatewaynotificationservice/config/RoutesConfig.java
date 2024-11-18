package md.maib.gatewaynotificationservice.config;

import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class RoutesConfig {

    @Bean
    public RouterFunction<ServerResponse> notificationRouteService() {
        return GatewayRouterFunctions.route("auth-service")
                .route(RequestPredicates.POST("/api/notifications/send"), HandlerFunctions.http("http://localhost:8080"))
                .build();
    }
    
}
