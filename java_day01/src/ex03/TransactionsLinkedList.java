package ex03;

import java.util.UUID;

class TransactionNotFoundException extends ClassNotFoundException {
    @Override
    public String getMessage() {
        String message;

        message = " transaction not found ";
        return message;
    }
}
public class TransactionsLinkedList implements TransactionsList{
    Transaction transaction;

    public TransactionsLinkedList(){
        this.transaction = null;
    }
    public TransactionsLinkedList(Transaction transaction) {
        this.transaction = transaction;
        this.transaction.setPreviewTransaction(null);
        this.transaction.setNextTransaction(null);
    }

    public Transaction getTransaction() {
        return this.transaction;
    }

    public Transaction getTransaction(UUID id) throws TransactionNotFoundException {
        Transaction transaction = this.transaction;

        do {
            if (transaction.getId().equals(id))
                return transaction;
            transaction = transaction.getPreviewTransaction();
        } while (transaction.getPreviewTransaction() != null);
        throw new TransactionNotFoundException();
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        if (this.transaction == null) {
            this.transaction = transaction;
            this.transaction.setNextTransaction(null);
            this.transaction.setPreviewTransaction(null);
            return;
        }
        transaction.setPreviewTransaction(this.transaction);
        this.transaction.setNextTransaction(transaction);
        this.transaction = transaction;
        this.transaction.setNextTransaction(null);
    }

    @Override
    public void removeTransaction(UUID id) throws TransactionNotFoundException {
        boolean     flag = false;
        Transaction toRemoveTransaction = getTransaction(id);

                if (toRemoveTransaction.getNextTransaction() == null && toRemoveTransaction.getPreviewTransaction() == null)
                    this.transaction = null;
                else if (toRemoveTransaction.getPreviewTransaction() == null){
                    this.transaction = toRemoveTransaction.getNextTransaction();
                    toRemoveTransaction.setNextTransaction(null);
                }
                else if (toRemoveTransaction.getNextTransaction() == null){
                    this.transaction = toRemoveTransaction.getPreviewTransaction();
                    toRemoveTransaction.setPreviewTransaction(null);
                }
                else {
                    toRemoveTransaction.getNextTransaction().setPreviewTransaction(toRemoveTransaction.getPreviewTransaction());
                    toRemoveTransaction.getPreviewTransaction().setNextTransaction(toRemoveTransaction.getNextTransaction());
                }
    }

    @Override
    public Transaction[] transformToArray() {
        int         arraySize = 1;
        Transaction tempTransaction = this.transaction;
        Transaction arrayTransaction[];

        if (this.transaction == null)
            return null;
        while (tempTransaction.getPreviewTransaction() != null){
            tempTransaction = tempTransaction.getPreviewTransaction();
            arraySize++;
        }
        arrayTransaction = new Transaction[arraySize];
        for (int i = 0; i < arraySize; i++) {
            arrayTransaction[i] = tempTransaction;
            tempTransaction = tempTransaction.getNextTransaction();
        }
        return arrayTransaction;
    }

    @Override
    public String toString() {
        Transaction transaction = this.getTransaction();

        while (transaction != null) {
            System.out.println(transaction.toString());
            transaction = transaction.getPreviewTransaction();
        }
        return "";
    }

}
