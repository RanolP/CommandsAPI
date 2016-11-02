package me.ranol.commandsapi;

import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		ExampleCommand c = new ExampleCommand();
		System.out.println(c.getDescription());
		System.out.println(c.getLabel());
		System.out.println(c.getUsage());
		System.out.println(Arrays.toString(c.getAliases()));
		System.out.println(Arrays.toString(c.getPermissions()));
	}
}
