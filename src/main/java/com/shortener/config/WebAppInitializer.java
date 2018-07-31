package com.shortener.config;

        import org.springframework.context.annotation.Configuration;
        import org.springframework.web.WebApplicationInitializer;
        import org.springframework.web.context.ContextLoaderListener;
        import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
        import org.springframework.web.filter.CharacterEncodingFilter;
        import org.springframework.web.servlet.DispatcherServlet;

        import javax.servlet.FilterRegistration;
        import javax.servlet.ServletContext;
        import javax.servlet.ServletRegistration;

@Configuration
public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) {
        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.setServletContext(servletContext);
        webContext.setConfigLocation("com.shortener.config");
        servletContext.addListener(new ContextLoaderListener(webContext));

        ServletRegistration.Dynamic reg = servletContext.addServlet("dispatcherServlet",
                new DispatcherServlet(webContext));
        reg.setLoadOnStartup(1);
        reg.addMapping("/");

        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        FilterRegistration.Dynamic characterEncoding = servletContext.addFilter("encodingFilter",
                characterEncodingFilter);
        characterEncoding.setInitParameter("encoding", "UTF-8");
        characterEncoding.setInitParameter("forceEncoding", "true");
        characterEncoding.addMappingForUrlPatterns(null, true, "/*");
    }

}