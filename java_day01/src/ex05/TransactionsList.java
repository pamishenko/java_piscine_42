package ex05;

import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transaction);
    void removeTransaction(UUID id) throws TransactionNotFoundException;

    int size();

    Transaction getTransaction();
    Transaction getTransaction(UUID id) throws TransactionNotFoundException;

    String toString();
}
