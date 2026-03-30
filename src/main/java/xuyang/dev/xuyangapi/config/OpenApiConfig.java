package xuyang.dev.xuyangapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080/xuyang-api")
                                .description("local"),
                        new Server()
                                .url("https://api-dev.xuyang.dev/xuyang-api")
                                .description("production gateway")
                ));

    }

}

