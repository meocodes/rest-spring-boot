package codes.meo.restspringboot.logging;

import org.slf4j.MDC;

import javax.annotation.Priority;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.UUID;

@Provider
@PreMatching
@Priority(100)
public class CorrelationFilter implements ContainerRequestFilter {

    public static final String CORRELATION_ID_KEY = "CorrelationID";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        MDC.put(CORRELATION_ID_KEY, createCorrelationId());
    }

    private String createCorrelationId() {
        return UUID.randomUUID().toString();
    }
}