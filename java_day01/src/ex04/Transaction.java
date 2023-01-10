package ex04;

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

	protected enum  Category{CREDIT, DEBIT};

	private UUID		id;
	private User sender;
	private User recipient;
	private Category	category;
	private int		amount;
	private Transaction previewTransaction;
	private Transaction	nextTransaction;

	public Transaction(UUID id,
					   User sender,
					   User recipient,
					   Category category,
					   int amount) {
		if (category == Category.CREDIT && amount < 0)
			throw new ErrorTransaction();
		if (category == Category.DEBIT && amount > 0)
			throw new ErrorTransaction();
		this.category = category;
		this.id = id;
		this.sender = sender;
		this.recipient = recipient;
		this.amount = amount;
		if (category == Category.CREDIT)
			sender.setUserBalance(sender.getUserBalance() + amount);
		else
			sender.setUserBalance(sender.getUserBalance() - amount);
	}

	public UUID getId() {
		return id;
	}

	public User getSender() {
		return sender;
	}

	public User getRecipient() {
		return recipient;
	}

	public int getAmount() {
		return amount;
	}

	public Category getCategory() {
		return category;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public void setRecipient(User recipient) {
		this.recipient = recipient;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Transaction getPreviewTransaction() {
		return previewTransaction;
	}

	public void setPreviewTransaction(Transaction previewTransaction) {
		this.previewTransaction = previewTransaction;
	}

	public Transaction getNextTransaction() {
		return nextTransaction;
	}

	public void setNextTransaction(Transaction nextTransaction) {
		this.nextTransaction = nextTransaction;
	}

	@Override
	public String toString() {
		String color = ANSI_RESET;
		if (getCategory() == Category.CREDIT)
			color = ANSI_GREEN;
		else if (getCategory() == Category.DEBIT)
			color = ANSI_RED;
		return (getSender().getUserName() + " -> "
				+ getRecipient().getUserName() + ", "
				+ color + getAmount() + ANSI_RESET + ", "
				+ color + getCategory() + ANSI_RESET + ", transaction "
				+ color + getId() + ANSI_RESET);
	}
}

