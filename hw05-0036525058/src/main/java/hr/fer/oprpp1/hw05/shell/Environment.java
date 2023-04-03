package hr.fer.oprpp1.hw05.shell;

import java.util.SortedMap;

public interface Environment {

	/**
	 * 
	 * @return
	 * @throws ShellIOException
	 */
	String readLine() throws ShellIOException;

	/**
	 * 
	 * @param text
	 * @throws ShellIOException
	 */
	void write(String text) throws ShellIOException;

	/**
	 * 
	 * @param text
	 * @throws ShellIOException
	 */
	void writeln(String text) throws ShellIOException;

	/**
	 * 
	 * @return
	 */
	SortedMap<String, ShellCommand> commands();

	/**
	 *
	 */
	Character getMultilineSymbol();

	/**
	 * 
	 * @param symbol
	 */
	void setMultilineSymbol(Character symbol);

	/**
	 * 
	 * @return
	 */
	Character getPromptSymbol();

	/**
	 * 
	 * @param symbol
	 */
	void setPromptSymbol(Character symbol);

	/**
	 * 
	 * @return
	 */
	Character getMorelinesSymbol();

	/**
	 * 
	 * @param symbol
	 */
	void setMorelinesSymbol(Character symbol);
}
