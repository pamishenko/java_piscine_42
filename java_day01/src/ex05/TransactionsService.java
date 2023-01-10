package ex05;

import java.util.UUID;

public class TransactionsService {

    private static TransactionsService transactionsService;

    public static UserList getUserList() {
        return userList;
    }

    private static UserList userList;

    private static TransactionsList unpairTransactions;

    private TransactionsService(){
        unpairTransactions = new TransactionsLinkedList();
        userList = new UsersArrayList();
    }

    public static TransactionsService getTransactionsService() {
        if (transactionsService == null) {
            transactionsService = new TransactionsService();
        }
        return transactionsService;
    }

    public void addUser(User user) {
        userList.addUser(user);
    }

    public float getBalanceUser(User user) {
        return user.getUserBalance();
    }

    public void doTransaction(User userSender, User userReceiver, long money){
        if (userSender.getUserBalance() < money)
            throw new IllegalTransactionException();
        UUID uuid = UUID.randomUUID();
        userSender.getTransactionsList().addTransaction(new Transaction(uuid, userSender, userReceiver, Transaction.Category.CREDIT, money));
        userReceiver.getTransactionsList().addTransaction(new Transaction(uuid, userReceiver, userSender, Transaction.Category.DEBIT, -money));
    }

    public TransactionsList getUserTransactions(User user){
        return user.getTransactionsList();
    }

    public TransactionsList getUnpairTransactions() {
        return unpairTransactions;
    }
    public void removeTransactionForUser(User user, UUID idTransaction) throws TransactionNotFoundException {
        Transaction tmp = user.getTransactionsList().getTransaction(idTransaction);
        user.getTransactionsList().removeTransaction(idTransaction);
        removeUnpairTransaction(tmp);
    }

    public void addUnpairTransaction(Transaction transaction){
        unpairTransactions.addTransaction(transaction);
    }
    public void removeUnpairTransaction(Transaction transaction) throws TransactionNotFoundException {
        Transaction tmp = unpairTransactions.getTransaction();
        for (int i = 0; i < unpairTransactions.size(); i++){
            if (transaction == tmp)
                unpairTransactions.removeTransaction(transaction.getId());
        }
    }
}
