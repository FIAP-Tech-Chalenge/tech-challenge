
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().addServersItem(new Server().url("https://fiap-production.up.railway.app"))
                .addServersItem(new Server().url("http://localhost:8080"))
                .addServersItem(new Server().url("http://fiap-production.up.railway.app"));
    }
}
