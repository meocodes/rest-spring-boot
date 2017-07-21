package codes.meo.restspringboot.config;

import codes.meo.restspringboot.store.StoreController;
import codes.meo.restspringboot.swagger.SwaggerController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(StoreController.class);
        register(SwaggerController.class);
    }
}