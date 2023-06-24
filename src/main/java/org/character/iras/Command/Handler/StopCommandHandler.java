package org.character.iras.Command.Handler;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class StopCommandHandler implements CommandHandler{
    @Override
    public boolean onCommand(ConfigurableApplicationContext context, String command, String[] args) {
        System.exit(SpringApplication.exit(context));

        return true;
    }
}
