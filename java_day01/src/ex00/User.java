package ex00;

import java.util.UUID;

public class User {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_CYAN = "\u001B[36m";

	private UUID userId;
	private String  userName;
	private int   userBalance;

	public User(UUID userId, String userName, int userBalance) {
		this.userId = userId;
		this.userName = userName;
		if (userBalance < 0)
			this.userBalance = 0;
		else
			this.userBalance = userBalance;
	}

	public UUID getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public int getUserBalance() {
		return userBalance;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserBalance(int userBalance) {
		this.userBalance = userBalance;
	}
	public void printUserBalance() {
		System.out.printf("\tUser %2s name: %s%15s%s balance = %s%2d%s\n",
				getUserId().toString(),
				ANSI_CYAN, getUserName(), ANSI_RESET,
				ANSI_YELLOW, getUserBalance(), ANSI_RESET);
	}
}
