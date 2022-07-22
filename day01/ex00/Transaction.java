package ex00;

import java.util.UUID;

class ErrorTransaction extends IllegalArgumentException{
	@Override
	public String getMessage() {
		String message;

		message = " error category & amount pair ";
		return message;
	}
}

public class    Transaction {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_CYAN = "\u001B[36m";
	protected enum  Category{INCOME, OUTCOME};

	private UUID      transactionID;
	private User        sender;
	private User        recipient;
	private Category    category;
	private float       amount;

	public Transaction(User sender,
	                   User recipient, Category category, float amount) {
		if (category == Category.INCOME && amount < 0)
			throw new ErrorTransaction();
		if (category == Category.OUTCOME && amount > 0)
			throw new ErrorTransaction();
		this.category = category;
		this.transactionID = UUID.randomUUID();
		this.sender = sender;
		this.recipient = recipient;
		this.amount = amount;
		if (category == Category.INCOME)
			sender.setUserBalance(sender.getUserBalance() - amount);
		else
			sender.setUserBalance(sender.getUserBalance() + amount);
	}

	public UUID getTransactionID() {
		return transactionID;
	}

	public User getSender() {
		return sender;
	}

	public User getRecipient() {
		return recipient;
	}

	public float getAmount() {
		return amount;
	}

	public Category getCategory() {
		return category;
	}

	void print(String color) {
		System.out.printf("%15s -> %-15s %s%10.2f%s, %s%7s%s, transaction %s%s%s\n",
				getSender().getUserName(),
				getRecipient().getUserName(),
				color, getAmount(), ANSI_RESET,
				color, getCategory().toString(), ANSI_RESET,
				color, getTransactionID().toString(), ANSI_RESET);
	}

}

