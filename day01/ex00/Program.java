package ex00;

public class Program {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_CYAN = "\u001B[36m";

	public static void main(String[] args) {

		System.out.println(ANSI_CYAN + "Initialisation: " + ANSI_RESET);
		User user1 = new User(1, "Smirnov Ivan", 1000);
		User user2 = new User(2, "Antonov Pavel", 0); // инициируется нулем
		User user3 = new User(3, "Evseev Oleg", -1000); // инициируется нулем
		user1.printUserBalance();
		user2.printUserBalance();
		user3.printUserBalance();

		System.out.println(ANSI_CYAN + "Testing Transaction: " + ANSI_RESET);
		Transaction transactionIn = new Transaction(user1, user2, Transaction.Category.INCOME, 23);
		Transaction transactionOut = new Transaction(user2, user1, Transaction.Category.OUTCOME, -23);

		// Будет ошибка если раскомментить
//			Transaction transactionInTest = new Transaction(user1, user2, Transaction.Category.INCOME, -23);
//			Transaction transactionOutTest = new Transaction(user2, user1, Transaction.Category.OUTCOME, 23);

		transactionIn.print(ANSI_GREEN);
		transactionOut.print(ANSI_RED);
	}
}
