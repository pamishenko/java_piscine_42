package ex05;

public class UserIdGenerator {
	private static UserIdGenerator  userIdGenerator;
	private static long userLastId;

	private UserIdGenerator() {
		userLastId = 0;
	}

	public static UserIdGenerator   getInstance() {
		if (userIdGenerator == null) {
			userIdGenerator = new UserIdGenerator();
		}
		return userIdGenerator;
	}

	public static long  generateId() {
		return ++userLastId;
	}
}
