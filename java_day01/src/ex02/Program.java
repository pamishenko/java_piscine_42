package ex02;

import java.util.Arrays;

public class Program {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_CYAN = "\u001B[36m";

	static UsersArrayList userList = new UsersArrayList();

	public static void main(String[] args) throws UserNotFoundException {
		System.out.println(ANSI_CYAN + "Initialisation: " + ANSI_RESET);
		userInit(5);
		printAllUsersInfo();

		System.out.println(ANSI_CYAN + "Create more users" + ANSI_RESET);
		System.out.printf("\tArray len (capacity) before update = %d, current count users = %d\n",
				userList.arrayUsers.length, userList.getUserCount());
		userInit(100);
		System.out.printf("\tArray len (capacity) after update = %d, current count users = %d\n",
				userList.arrayUsers.length, userList.getUserCount());

		System.out.println(ANSI_CYAN + "Try get user by corrupted index and return exception" + ANSI_RESET);
		userList.getUserByIndex(100500);

	}

	static void userInit(int count) {
		int   amount;

		for (long i = 0; i < count; i++) {
			amount = (int) (234332 / (i + 1));
			userList.addUser("User_" + userList.getUserCount(), amount);
		}
	}

	static void printAllUsersInfo() {
		for (User user : userList.arrayUsers) {
			if (user != null)
				System.out.println(user);
		}
	}
}
