package org.character.iras;

import org.character.iras.Command.Handler.StopCommandHandler;
import org.character.iras.Command.Manager.CommandManager;
import org.character.iras.DataAccess.MySQLImplments.MySQLInitializer;
import org.character.iras.Exceptions.CommandNotFoundException;
import org.character.iras.Utils.SystemInformationGetter;
import org.jline.reader.History;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.impl.history.DefaultHistory;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ResourceUtils;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.io.File;
import java.util.Map;

@SpringBootApplication

public class Application {
    public static ClassPathXmlApplicationContext context;

    public static JdbcTemplate template;
    public static String AbsolutePath;
    public static ConfigurableApplicationContext run;
    private static Logger logger;
    private static CommandManager commandManager;


    public static void main(String[] args) throws Exception {
        try {
            for (Map.Entry<String, String> stringStringEntry : new SystemInformationGetter().getInformation().entrySet()) {
                System.out.println(stringStringEntry.getKey() + stringStringEntry.getValue());
            }
            run = SpringApplication.run(Application.class, args);
            template = run.getBean(JdbcTemplate.class);
            context = new ClassPathXmlApplicationContext("beans.xml");
            commandManager = run.getBean("CommandManager", CommandManager.class);
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!path.exists()) path = new File("");
            AbsolutePath = path.getAbsolutePath();

            commandManager.registerCommand("stop", new StopCommandHandler());

            logger = LoggerFactory.getLogger(Application.class);

            Terminal terminal = TerminalBuilder.builder().system(true).build();
            History history = new DefaultHistory();
            LineReader lineReader = LineReaderBuilder.builder()
                    .terminal(terminal)
                    .history(history)
                    .build();

            if (run.getBean(MySQLInitializer.class).initialize()) {
                System.out.println("欢迎使用智能简历解析系统");
                System.out.println("此系统目前处于初始化状态，需要您指定初始管理员账号的用户名和密码！");
                String username = "";
                while (username.length() < 3){
                    username = lineReader.readLine(" => 请在右侧输入用户名：");
                }
                String password = "";
                while (password.length() < 6){
                    password = lineReader.readLine(" => 请在右侧输入密码：");
                }
                template.execute("INSERT INTO user(`username`, `password`, `privileged`) VALUE('" + username + "', '" + password + "', 1)");
                System.out.println("初始化成功！");
            }



            while (true){

                String line = lineReader.readLine("> ");

                if(line.length() != 0){
                    try{
                        commandManager.handle(line);
                        logger.info("控制台执行了命令：" + line);
                    }catch (CommandNotFoundException e){
                        logger.info("控制台执行了命令：" + line);
                        logger.warn(e.getMessage());
                    }
                }
                history.add(line);
            }
        }catch (Exception e){
            getLogger().error("运行遇到错误：" + e.getClass().getName() +
                    "\n错误内容：" + e.getMessage());
        }



    }

    public static Logger getLogger(){
        return logger;
    }


}

//class WebGlobalConfig{
//
//    @Bean
//    public CorsFilter corsFilter(){
//        CorsConfiguration config = new CorsConfiguration();
//        config.addAllowedHeader("*");
//        config.addAllowedOrigin("*");
//        config.addAllowedMethod(HttpMethod.GET);
//        config.addAllowedMethod(HttpMethod.POST);
//        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
//        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", config);
//        return new CorsFilter(urlBasedCorsConfigurationSource);
//    }
//}
