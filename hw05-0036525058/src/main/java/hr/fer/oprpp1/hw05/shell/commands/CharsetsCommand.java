package hr.fer.oprpp1.hw05.shell.commands;

import hr.fer.oprpp1.hw05.shell.Environment;
import hr.fer.oprpp1.hw05.shell.ShellCommand;
import hr.fer.oprpp1.hw05.shell.ShellStatus;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

public class CharsetsCommand implements ShellCommand {

    @Override
    public ShellStatus executeCommand(Environment env, String arguments) {
        if(arguments.length()>0) {
            env.writeln("Expected 0 arguments. Too many arguments given.");
            return ShellStatus.CONTINUE;
        }
        Charset.availableCharsets().keySet().stream()
                .forEach((charsetName)->env.writeln(charsetName));
        return ShellStatus.CONTINUE;
    }

    @Override
    public String getCommandName() {
        return "charsets";
    }

    @Override
    public List<String> getCommandDescription() {
        return Arrays.asList();
    }
}
