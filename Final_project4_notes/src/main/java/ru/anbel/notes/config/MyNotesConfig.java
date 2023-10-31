package ru.anbel.notes.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

// Класс, настраивающий обработку html шаблонов и их отображение на веб-страницах
@Configuration
@ComponentScan("ru.anbel.notes")
@EnableWebMvc
public class MyNotesConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext; // Поле для хранения контекста приложения

    @Autowired
    public MyNotesConfig(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    // Аннотация, которая указывает, что этот метод создает бин и регистрирует его в SpringResourceTemplateResolver
    public SpringResourceTemplateResolver templateResolver() { // Метод для создания бина типа SpringResourceTemplateResolver
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver(); // Создаем новый объект типа SpringResourceTemplateResolver
        templateResolver.setApplicationContext(applicationContext); // Устанавливаем контекст приложения для ресурсного разрешителя шаблонов
        templateResolver.setPrefix("/WEB-INF/views/"); // Устанавливаем префикс для пути к
        templateResolver.setSuffix(".html"); // Устанавливаем суффикс для расширения файлов
        return templateResolver; // Возвращаем созданный объект шаблонам
    }

    @Bean // Аннотация, которая указывает, что этот метод создает бин и регистрирует его в SpringTemplateEngi@ne
    public SpringTemplateEngine templateEngine() { // Метод для создания бина типа SpringTemplate Engine
        SpringTemplateEngine templateEngine = new SpringTemplateEngine(); // Создаем новый объект типа SpringTemplateEngine разрешитель шаблонов для движка шаблонов
        templateEngine.setTemplateResolver(templateResolver()); // Устанавливаем ресурсный
        templateEngine.setEnableSpringELCompiler(true); // Включаем компилятор Spring Expression Language для движка шаблонов
        return templateEngine; // Возвращаем созданный объект
    }

    @Override // Аннотация, которая указывает, что этот метод переопределяет метод из суперкласса или интерфейса
    public void configureViewResolvers(ViewResolverRegistry registry) { // Метод для настройки разрешителей представлений в реестре
        ThymeleafViewResolver resolver = new ThymeleafViewResolver(); // Создаем новый объект типа ThymeleafViewResolver
        resolver.setTemplateEngine(templateEngine()); // Устанавливаем движок шаблонов для разрешителя представлений
        registry.viewResolver(resolver); // Регистрируем разрешитель представлений в реестре
    }

}
