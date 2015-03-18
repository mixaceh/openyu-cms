package org.openyu.cms;

public class App {

	public static void main(String[] args) {
		String name = "CMS.J - Content Management System  Java";
		// preTest(args, name);
		System.out.println("--- Pre Test ---");
		System.out.println(name);
		if (args != null) {
			for (String arg : args) {
				System.out.println(arg);
			}
		} else {
			System.out.println("Args is null");
		}
	}
}