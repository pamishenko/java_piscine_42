package ex03;

import java.util.UUID;

public class Program {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_CYAN = "\u001B[36m";

	static UsersArrayList userList = new UsersArrayList();

	public static void main(String[] args) throws UserNotFoundException, TransactionNotFoundException {
		User	user1;
		User	user2;
		TransactionsList transactionsList;

		System.out.println(ANSI_CYAN + "Initialisation: " + ANSI_RESET);
		user1 = new User("Ivanov Ivan", 234234);
		user2 = new User("Petrov Petr", 927384);
		System.out.println(user1.toString());
		System.out.println(user2.toString());

		user1.getTransactionsList().addTransaction(new Transaction(user1, user2, Transaction.Category.INCOME, 23));
		user2.getTransactionsList().addTransaction(new Transaction(user2, user1, Transaction.Category.OUTCOME, -23));
		user1.getTransactionsList().addTransaction(new Transaction(user2, user1, Transaction.Category.OUTCOME, -24));
		user2.getTransactionsList().addTransaction(new Transaction(user1, user2, Transaction.Category.INCOME, 24));
		user1.getTransactionsList().addTransaction(new Transaction(user1, user2, Transaction.Category.INCOME, 25));
		user2.getTransactionsList().addTransaction(new Transaction(user2, user1, Transaction.Category.OUTCOME, -25));
		user1.getTransactionsList().addTransaction(new Transaction(user2, user1, Transaction.Category.INCOME, 231));
		user2.getTransactionsList().addTransaction(new Transaction(user1, user2, Transaction.Category.OUTCOME, -231));

		System.out.println(user1.getTransactionsList());
		System.out.println(user2.getTransactionsList());

		UUID id = user1.getTransactionsList().getTransaction().getId();
		System.out.printf("\n%sGet transaction by Id %s%s:\n",ANSI_CYAN, id.toString(), ANSI_RESET);
		System.out.println(user1.getTransactionsList().getTransaction(id));
		user1.getTransactionsList().removeTransaction(id);
		System.out.printf("\n%sAfter remove transaction user's: %s%s:\n",ANSI_CYAN, id, ANSI_RESET);
		System.out.println(user1.getTransactionsList());

		System.out.println(ANSI_CYAN + "Balance after transaction: " + ANSI_RESET);
		System.out.println(user1);
		System.out.println(user2);
	}
}
