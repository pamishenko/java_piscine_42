package ex03;

public class UserIdGenerator {
	private static UserIdGenerator  userIdGenerator;
	private static int userLastId;

	private UserIdGenerator() {
		userLastId = -1;
	}

	public static UserIdGenerator   getInstance() {
		if (userIdGenerator == null) {
			userIdGenerator = new UserIdGenerator();
		}
		return userIdGenerator;
	}

	public static int  generateId() {
		return ++userLastId;
	}
}
