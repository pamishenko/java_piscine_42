package ex05;

import java.util.UUID;

class IllegalTransactionException extends IllegalArgumentException {
    @Override
    public String getMessage() {
        String message;

        message = "amount exceeding user’s residual balance";
        return message;
    }
}

class TransactionNotFoundException extends ClassNotFoundException {
    @Override
    public String getMessage() {
        String message;

        message = " transaction not found ";
        return message;
    }
}
public class TransactionsLinkedList implements TransactionsList {
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

    public Transaction getTransaction(UUID id){
        try {
            Transaction transaction = this.transaction;
            do {
                if (transaction.getId().equals(id))
                    return transaction;
                transaction = transaction.getPreviewTransaction();
            } while (transaction.getPreviewTransaction() != null);
        }catch (Exception e) {
        }
        return null;
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
        Transaction toRemoveTransaction = getTransaction(id);
        TransactionsService.getTransactionsService().addUnpairTransaction(
                toRemoveTransaction == toRemoveTransaction.getRecipient().getTransactionsList().getTransaction(id) ?
                        toRemoveTransaction.getSender().getTransactionsList().getTransaction(id) :
                        toRemoveTransaction.getRecipient().getTransactionsList().getTransaction(id)
        );
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
    public int size(){
        int size = 1;
        Transaction transactionTemp = transaction;
        if (transactionTemp == null)
            return 0;
        while (transactionTemp.getNextTransaction() != null) {
            transactionTemp = transactionTemp.getNextTransaction();
            size++;
        }
        return size;
    }

    @Override
    public String toString() {
        Transaction transaction = this.getTransaction();
        String result = "";
        while (transaction != null) {
            result += transaction.toString() + '\n';
            transaction = transaction.getPreviewTransaction();
        }
        return result;
    }
}
