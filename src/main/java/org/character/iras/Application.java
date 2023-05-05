package org.character.iras;

import org.character.iras.DataAccess.MySQLImplments.MySQLTokenDataAccess;
import org.character.iras.DataAccess.MySQLImplments.MySQLUserDataAccess;
import org.character.iras.Entity.Token;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class Application {
    public static ClassPathXmlApplicationContext context;

    public static JdbcTemplate template;

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        template = run.getBean(JdbcTemplate.class);
        context = new ClassPathXmlApplicationContext("beans.xml");




    }

}
