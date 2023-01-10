package ex01;

public class User {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_CYAN = "\u001B[36m";

	private int id;
	private String	userName;
	private int	userBalance;

	public User(String userName, int userBalance) {
		UserIdGenerator.getInstance();
		this.id = UserIdGenerator.generateId();
		this.userName = userName;
		if (userBalance < 0)
			this.userBalance = 0;
		else
			this.userBalance = userBalance;
	}

	public int getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public int getUserBalance() {
		return userBalance;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserBalance(int userBalance) {
		this.userBalance = userBalance;
	}

	@Override
	public String toString() {
		return ("\tUser " + getId()
				                   + "\tname -> " + ANSI_CYAN
				                   + getUserName() + ANSI_RESET
				                   + "\tbalance = " + ANSI_YELLOW
				                   + getUserBalance() + ANSI_RESET);
	}
}
