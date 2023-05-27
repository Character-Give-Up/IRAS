package org.character.iras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ResourceUtils;

import java.io.File;

@SpringBootApplication
public class Application {
    public static ClassPathXmlApplicationContext context;

    public static JdbcTemplate template;
    public static String AbsolutePath;

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        template = run.getBean(JdbcTemplate.class);
        context = new ClassPathXmlApplicationContext("beans.xml");
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!path.exists()) path = new File("");
        AbsolutePath = path.getAbsolutePath();




    }

}
