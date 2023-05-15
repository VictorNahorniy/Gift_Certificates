package com.epam.esm.config;


import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;


/**
 * The type Web initializer.
 * <p>
 * This class is used to initialize the application.
 * It is used to configure the root config classes.
 * It is used to configure the servlet config classes.
 * It is used to configure the servlet mapping.
 * </p>
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{DevConfig.class, ProdConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
