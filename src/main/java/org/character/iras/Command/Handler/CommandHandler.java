package org.character.iras.Command.Handler;

import org.springframework.context.ConfigurableApplicationContext;

public interface CommandHandler {
    boolean onCommand(ConfigurableApplicationContext context, String command, String[] args);
}
