package org.hugh;

import org.hugh.components.Conp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Hello world!
 *
 */
@SpringBootConfiguration
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
        //// u
        ConfigurableApplicationContext app = SpringApplication.run(App.class,args);
        Conp bean = app.getBean(Conp.class);
        bean.exe();

    }
}
