package ex03;

public class User {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_CYAN = "\u001B[36m";

	private int    userId;
	private String  userName;
	private int   userBalance;

	public TransactionsList getTransactionsList() {
		return transactionsList;
	}

	public void setTransactionsList(TransactionsList transactionsList) {
		this.transactionsList = transactionsList;
	}

	private TransactionsList transactionsList;

	public User(String userName, int userBalance) {
		this.userId = UserIdGenerator.getInstance().generateId();
		this.userName = userName;
		this.transactionsList = new TransactionsLinkedList();
		if (userBalance < 0)
			this.userBalance = 0;
		else
			this.userBalance = userBalance;
	}

	public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public int getUserBalance() {
		return userBalance;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserBalance(int userBalance) {
		this.userBalance = userBalance;
	}

	@Override
	public String toString() {
		return ("\tUser " + getUserId()
				                   + "\tname -> " + ANSI_CYAN
				                   + getUserName() + ANSI_RESET
				                   + "\tbalance = " + ANSI_YELLOW
				                   + getUserBalance() + ANSI_RESET);
	}
}
