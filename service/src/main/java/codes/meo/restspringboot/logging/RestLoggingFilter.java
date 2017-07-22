package codes.meo.restspringboot.logging;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.glassfish.jersey.message.internal.ReaderWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.annotation.Priority;
import javax.ws.rs.container.*;
import javax.ws.rs.ext.Provider;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Provider
@PreMatching
@Priority(200)
public class RestLoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static final Logger LOG = LoggerFactory.getLogger(RestLoggingFilter.class);
    private static final String IN_OUT_FLAG_KEY = "InOutFlag";
    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        MDC.put(IN_OUT_FLAG_KEY, "IN");

        if (requestContext.hasEntity()) {
            LOG.info("{} {} {}", requestContext.getMethod(), getPath(requestContext), getEntityBody(requestContext));
        } else {
            LOG.info("{} {}", requestContext.getMethod(), getPath(requestContext));
        }
    }

    private String getPath(ContainerRequestContext requestContext) {
        return requestContext.getUriInfo().getPath();
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

        MDC.put(IN_OUT_FLAG_KEY, "OUT");

        if (responseContext.hasEntity()) {
            LOG.info("{} {}", responseContext.getStatus(), getEntityBody(responseContext));
        } else {
            LOG.info("{}", responseContext.getStatus());
        }
    }

    private String getEntityBody(ContainerRequestContext requestContext) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream in = requestContext.getEntityStream();

        final StringBuilder b = new StringBuilder();

        try {
            ReaderWriter.writeTo(in, out);

            byte[] requestEntity = out.toByteArray();

            if (requestEntity.length == 0) {
                b.append("");
            } else {
                b.append(new String(requestEntity));
            }

            requestContext.setEntityStream(new ByteArrayInputStream(requestEntity));

        } catch (IOException e) {
            LOG.error("Request entity could not be read.", e);
        }

        return b.toString();
    }

    private String getEntityBody(ContainerResponseContext responseContext) {
        try {
            return mapper.writeValueAsString(responseContext.getEntity());
        } catch (Exception e) {
            LOG.error("Response entity could not be read.", e);
            return "";
        }
    }
}