package com.neu.api;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class ServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    // This will tell where are configuration classes
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {WebConfig.class, JPAConfig.class, SwaggerConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    //accept any request starting with /api/
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/api/*"};
    }
}
