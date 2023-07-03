package org.character.iras.Command.Manager;

import org.character.iras.Application;
import org.character.iras.Command.Handler.CommandHandler;
import org.character.iras.Exceptions.CommandNotFoundException;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Component("CommandManager")
@Scope
public class CommandManager {

    private final Map<String, CommandHandler> CommandHandlers = new HashMap<>();
    public CommandManager() {
    }

    public void registerCommand(String command, CommandHandler commandHandler){
        CommandHandlers.put(command, commandHandler);
    }

    public Map<String, CommandHandler> getCommandHandlers() {
        return CommandHandlers;
    }

    public boolean handle(String contents) throws CommandNotFoundException {
        if(contents.length() == 0) return true;
        String[] s = contents.split(" ");
        String command = s[0];
        String[] args = Arrays.copyOfRange(s, 1, s.length);
        CommandHandler commandHandler = this.CommandHandlers.get(command);
        if(commandHandler == null) throw new CommandNotFoundException("未找到命令：" + command);
        return commandHandler.onCommand(Application.run, command, args);
    }
}
