package ru.anbel.notes.config;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// Класс-инициализатор
public class MyNotesInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() { // Метод, возвращающий классы конфигурации, загружаемые при запуске приложения
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() { // Метод, возвращающий классы конфигурации DispatcherServlet (обработка http запросов)
        return new Class[] {MyNotesConfig.class};
    }

    @Override
    protected String[] getServletMappings() { // Метод, возвращающий Url запросы, которые должен обрабатывать DispatcherServlet
        return new String[] {"/"}; // Любые
    }

    public void onStartup(ServletContext aServletContext) throws ServletException {
        super.onStartup(aServletContext);
        registerHiddenFieldFilter(aServletContext);
    }

    // скрытый PATCH фильтр
    private void registerHiddenFieldFilter(ServletContext aContext) {
        aContext.addFilter("hiddenHttpMethodFilter", new HiddenHttpMethodFilter()).addMappingForUrlPatterns(null, true, "/*");
    }

}
