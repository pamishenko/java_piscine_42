package ex04;

import java.util.UUID;

public class Program {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_CYAN = "\u001B[36m";

	static UsersArrayList userList = new UsersArrayList();

	public static void main(String[] args) throws UserNotFoundException, TransactionNotFoundException {

		TransactionsService transactionsService= TransactionsService.getTransactionsService();

		User user1 = new User("Fedor Pastukhov", 107000);
		User user2 = new User("Andrey Mironov", 991711);
		User user3 = new User("Olga Vlasova", 399);
		User user4 = new User("Maxim Samojlov", 9999);

		transactionsService.addUser(user1);
		transactionsService.addUser(user2);
		transactionsService.addUser(user3);
		transactionsService.addUser(user4);

		System.out.println(ANSI_CYAN + "Retrieving a user’s balance:" + ANSI_RESET);
		System.out.println("\t" + transactionsService.getBalanceUser(user1));
		System.out.println("\t" + transactionsService.getBalanceUser(user2));
		System.out.println("\t" + transactionsService.getBalanceUser(user3));
		System.out.println("\t" + transactionsService.getBalanceUser(user4));

		System.out.println("\nPerforming a transfer transaction");
		transactionsService.doTransaction(user2, user1, 77);
		transactionsService.doTransaction(user1, user2, 342);
		transactionsService.doTransaction(user1, user3, 2222);
		transactionsService.doTransaction(user4, user2, 12);

		System.out.println(ANSI_CYAN + "\nRetrieving a user’s balance:" + ANSI_RESET);
		System.out.println("\t" + transactionsService.getBalanceUser(user1));
		System.out.println("\t" + transactionsService.getBalanceUser(user2));
		System.out.println("\t" + transactionsService.getBalanceUser(user3));
		System.out.println("\t" + transactionsService.getBalanceUser(user4));


		System.out.println(ANSI_CYAN + "\nAll transactions all users:" + ANSI_RESET);
		System.out.println(transactionsService.getUserTransactions(user1));
		System.out.println(transactionsService.getUserTransactions(user2));
		System.out.println(transactionsService.getUserTransactions(user3));
		System.out.println(transactionsService.getUserTransactions(user4));

		//Throw exception IllegalTransactionException
//		transactionsService.doTransaction(user1, user2, 3499992.4f);

		UUID idForRemove = user1.getTransactionsList().getTransaction().getId();
		transactionsService.removeTransactionForUser(user1, idForRemove);

		System.out.println(
				ANSI_CYAN + "Transactions Fedor Pastukhov's after remove transaction : " + ANSI_RESET + idForRemove);
		System.out.println(transactionsService.getUserTransactions(user1));
		System.out.println(transactionsService.getUserTransactions(user3));

		System.out.println(ANSI_CYAN + "\nCheck unpair transactions:" + ANSI_RESET);
		System.out.println(transactionsService.getUnpairTransactions());

	}
}
