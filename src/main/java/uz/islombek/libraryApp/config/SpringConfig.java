package uz.islombek.libraryApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;


@Configuration
@ComponentScan("uz.islombek.libraryApp")
@EnableWebMvc
//@PropertySource("classpath:database.properties")
@PropertySource("classpath:database.properties")
@EnableTransactionManagement
public class SpringConfig implements WebMvcConfigurer {

    private final ApplicationContext applicationContext;
    private final Environment environment;
    @Autowired
    public SpringConfig(ApplicationContext applicationContext, Environment environment) {
        this.applicationContext = applicationContext;
        this.environment = environment;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource =  new DriverManagerDataSource();
//        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://localhost:5432/first_db");
//        dataSource.setUsername("postgres");
//        dataSource.setPassword("123");

//        dataSource.setDriverClassName(Objects.requireNonNull(environment.getProperty("driver")));
//        dataSource.setUrl(environment.getProperty("url"));
//        dataSource.setUsername(environment.getProperty("usernameForDB"));
//        dataSource.setPassword(environment.getProperty("password"));

        //hibernate
        dataSource.setDriverClassName(Objects.requireNonNull(environment.getRequiredProperty("hibernate.driver_class")));
        dataSource.setUrl(environment.getRequiredProperty("hibernate.connection.url"));
        dataSource.setUsername(environment.getRequiredProperty("hibernate.connection.username"));
        dataSource.setPassword(environment.getRequiredProperty("hibernate.connection.password"));


//        System.out.println(Objects.requireNonNull(environment.getProperty("driver")));
//        System.out.println(environment.getProperty("url"));
//        System.out.println(environment.getProperty("usernameForDB"));
//        System.out.println(environment.getProperty("password"));
        return dataSource;
    }
//    @Bean
//    public JdbcTemplate jdbcTemplate() {
//
//        //bu jdbc template
//        return new JdbcTemplate(dataSource());
//
//    }
        //bu hibernate
        private Properties hibernateProperties(){
            Properties properties = new Properties();
            properties.put("hibernate.dialect",environment.getRequiredProperty("hibernate.dialect"));
            properties.put("hibernate.show_sql",environment.getRequiredProperty("hibernate.show_sql"));
            return properties;
        }

        @Bean
    public LocalSessionFactoryBean sessionFactory(){
            LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
            sessionFactory.setDataSource(dataSource());
            sessionFactory.setPackagesToScan("uz.islombek.libraryApp.model");
            sessionFactory.setHibernateProperties(hibernateProperties());
            return sessionFactory;
    }
    @Bean
    public PlatformTransactionManager hibernateTransactionManager(){
        HibernateTransactionManager manager = new HibernateTransactionManager();
        manager.setSessionFactory(sessionFactory().getObject());
        return manager;
    }

}
