package ex03;

import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transaction);
    void removeTransaction(UUID id) throws TransactionNotFoundException;
    Transaction[] transformToArray();

    Transaction getTransaction();
    Transaction getTransaction(UUID id) throws TransactionNotFoundException;

    String toString();
}
