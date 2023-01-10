package ex01;

import java.util.*;

public class Program {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_CYAN = "\u001B[36m";

	static Map<Long, User> users = new HashMap<>();

	public static void main(String[] args) {

		User[] user = new User[5];

		System.out.println(ANSI_CYAN + "Initialisation: " + ANSI_RESET);
		user[0] = new User("Smirnov Ivan", 1000);
		user[4] = new User("Tolstoy  Lev", 320);
		user[3] = new User("Newton Isaac", 6630);
		user[1] = new User("Frankl Viktor", 324);
		user[2] = new User("Asprin Robert", 5550);

		for (User userToPrint: user) {
			System.out.println(userToPrint.toString());
		}

	}
}