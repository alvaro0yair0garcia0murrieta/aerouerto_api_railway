package mx.uv.Aeropuerto;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ErrorManager implements WebMvcConfigurer {
// Configura los métodos HTTP permitidos
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/aeropuertos")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "DELETE", "PATCH") 
                .allowCredentials(false) 
                .maxAge(3600); 
    }
}