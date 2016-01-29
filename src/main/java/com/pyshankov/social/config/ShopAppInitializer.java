package com.pyshankov.social.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by pyshankov on 27.01.2016.
 */
public class ShopAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected String[] getServletMappings() {
        // Map DispatcherServlet to
        return new String[] { "/" };
    }
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class };
    }
    @Override
    protected Class<?>[] getServletConfigClasses() {
        //  Specify configuration class
        return new Class<?>[] { WebConfig.class };
    }
}
