package com.wikia.pandora.api.filters;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ResponseInspectionFilter implements ContainerResponseFilter {

    @Override
    public ContainerResponse filter(ContainerRequest requestContext, ContainerResponse responseContext) {
        Logger logger = LoggerFactory.getLogger("com.wikia.pandora.api");
        responseContext.getHttpHeaders().add("X-Powered-By", "Jersey :-)");
        logger.info(responseContext.getEntity().toString());
        return responseContext;

    }
}
