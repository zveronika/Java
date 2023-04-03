package hr.fer.oprpp1.hw05.shell;

import java.util.List;

public interface ShellCommand {

	/**
	 * 
	 * @param env
	 * @param arguments
	 * @return
	 */
	ShellStatus executeCommand(Environment env, String arguments);
	
	/**
	 * 
	 * @return
	 */
	String getCommandName();
	
	/**
	 * 
	 * @return
	 */
	List<String> getCommandDescription();
}
