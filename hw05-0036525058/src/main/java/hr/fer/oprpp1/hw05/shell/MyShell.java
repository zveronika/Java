package hr.fer.oprpp1.hw05.shell;

import java.util.Collections;
import java.util.Scanner;
import java.util.SortedMap;

public class MyShell implements Environment {

	private Scanner sc;
	private SortedMap<String, ShellCommand> commands;

	private Character multiline = '|';
	private Character morelines = '\\';
	private Character prompt = '>';

	public static void main(String[] args) {
		System.out.println("Welcome to MyShell v 1.0");
		
	}

	@Override
	public String readLine() throws ShellIOException {
		try {
			return sc.nextLine();
		} catch (Exception ex) {
			throw new ShellIOException("Error occurred while reading line!");
		}
	}

	@Override
	public void write(String text) throws ShellIOException {
		System.out.printf(text);
	}

	@Override
	public void writeln(String text) throws ShellIOException {
		System.out.println(text);
	}

	@Override
	public SortedMap<String, ShellCommand> commands() {
		return Collections.unmodifiableSortedMap(this.commands);
	}

	@Override
	public Character getMultilineSymbol() {
		return this.multiline;
	}

	@Override
	public void setMultilineSymbol(Character symbol) {
		this.multiline = symbol;
	}

	@Override
	public Character getPromptSymbol() {
		return this.prompt;
	}

	@Override
	public void setPromptSymbol(Character symbol) {
		this.prompt = symbol;
	}

	@Override
	public Character getMorelinesSymbol() {
		return this.morelines;
	}

	@Override
	public void setMorelinesSymbol(Character symbol) {
		this.morelines = symbol;
	}
}
