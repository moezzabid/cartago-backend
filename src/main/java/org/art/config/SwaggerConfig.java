package org.art.config;



import io.swagger.v3.oas.models.info.Info;

import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

  @Value("${url.cartago}")
  private String urlCartago;


  private Info apiInfo() {
    Info info = new Info();
    info.title("Swagger Documentation");
    info.description("This project is developed to explore about spring doc configurations");
    return info;
  }

  private List<Server> servers() {
    Server server = new Server();
    // you can add your production url as host
    server.url(urlCartago);
    server.description("API URL");
    return List.of(server);
  }


}
