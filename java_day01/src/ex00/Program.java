package ex00;

import java.util.UUID;

public class Program {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_CYAN = "\u001B[36m";

	public static void main(String[] args) {

		System.out.println(ANSI_CYAN + "Initialisation: " + ANSI_RESET);
		User user1 = new User(UUID.randomUUID(), "Smirnov Ivan", 1000);
		User user2 = new User(UUID.randomUUID(), "Antonov Pavel", 0);
		User user3 = new User(UUID.randomUUID(), "Evseev Oleg", -1000);
		user1.printUserBalance();
		user2.printUserBalance();
		user3.printUserBalance();

		System.out.println(ANSI_CYAN + "Testing Transaction: " + ANSI_RESET);
		Transaction transactionIn = new Transaction(user1, user2, Transaction.Category.INCOME, 23);
		Transaction transactionOut = new Transaction(user2, user1, Transaction.Category.OUTCOME, -23);

		transactionIn.print(ANSI_GREEN);
		transactionOut.print(ANSI_RED);

		System.out.println(ANSI_CYAN + "Balance after Transaction: " + ANSI_RESET);
		user1.printUserBalance();
		user2.printUserBalance();
		user3.printUserBalance();

	}
}
