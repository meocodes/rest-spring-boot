package codes.meo.restspringboot.config;

import codes.meo.common.api.exception.mapper.ApiExceptionMapper;
import codes.meo.common.api.exception.mapper.DefaultExceptionMapper;
import codes.meo.restspringboot.logging.CorrelationFilter;
import codes.meo.restspringboot.logging.RestLoggingFilter;
import codes.meo.restspringboot.store.StoreController;
import codes.meo.restspringboot.swagger.SwaggerController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(ApiExceptionMapper.class);
        register(CorrelationFilter.class);
        register(DefaultExceptionMapper.class);
        register(RestLoggingFilter.class);
        register(StoreController.class);
        register(SwaggerController.class);
    }
}