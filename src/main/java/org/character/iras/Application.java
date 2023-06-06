package org.character.iras;

import org.character.iras.Utils.SystemInformationGetter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ResourceUtils;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;

import java.io.File;
import java.util.Map;

@SpringBootApplication
public class Application {
    public static ClassPathXmlApplicationContext context;

    public static JdbcTemplate template;
    public static String AbsolutePath;

    public static void main(String[] args) throws Exception {
        for (Map.Entry<String, String> stringStringEntry : new SystemInformationGetter().getInformation().entrySet()) {
            System.out.println(stringStringEntry.getKey() + stringStringEntry.getValue());
        }
        ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
        template = run.getBean(JdbcTemplate.class);
        context = new ClassPathXmlApplicationContext("beans.xml");
        File path = new File(ResourceUtils.getURL("classpath:").getPath());
        if(!path.exists()) path = new File("");
        AbsolutePath = path.getAbsolutePath();




    }

}
