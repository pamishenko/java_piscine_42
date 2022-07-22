package ex00;

public class User {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_CYAN = "\u001B[36m";

	private long    userId;
	private String  userName;
	private float   userBalance;

	public User(long userId, String userName, float userBalance) {
		this.userId = userId;
		this.userName = userName;
		if (userBalance < 0)
			this.userBalance = 0;
		else
			this.userBalance = userBalance;
	}

	public long getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public float getUserBalance() {
		return userBalance;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserBalance(float userBalance) {
		this.userBalance = userBalance;
	}
	public void printUserBalance() {
		System.out.printf("\tUser %2d name -> %s%15s%s balance = %s%.2f%s\n",
				getUserId(),
				ANSI_CYAN, getUserName(), ANSI_RESET,
				ANSI_YELLOW, getUserBalance(), ANSI_RESET);
	}
}
