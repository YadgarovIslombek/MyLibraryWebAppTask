package uz.islombek.libraryApp.config;


import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class MySpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringConfig.class};
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        registerHiddenFilter(servletContext);
    }
    private void registerHiddenFilter(ServletContext context){
        context.addFilter("hiddenHttpMethodFilter",new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null,true,"/*");
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}

