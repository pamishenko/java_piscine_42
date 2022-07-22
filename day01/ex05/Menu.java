package ex05;

import java.util.Scanner;
import java.util.UUID;

public class Menu {
    private static Menu menu;
    private static TransactionsService transactionsService;
    private final Scanner scanner = new Scanner(System.in);

    Menu() {
        transactionsService = TransactionsService.getTransactionsService();
    }

    public static Menu getMenu() {
        if (menu == null)
            menu = new Menu();
        return menu;
    }

    public void printMenu() {

        System.out.println("1. Add a user");
        System.out.println("2. View user balances");
        System.out.println("3. Perform a transfer");
        System.out.println("4. View all transactions for a specific user");
        System.out.println("5. DEV - remove a transfer by ID");
        System.out.println("6. DEV - check transfer validity");
        System.out.println("7. Finish execution");
    }
    public void listenInput() {
        boolean isFinish = false;
        do {
            int input = 42;
            try {
                input = scanner.nextInt();
                if (input == 1)
                    addAUser();
                else if (input == 2)
                    viewUserBalances();
                else if (input == 3)
                    performTransfer();
                else if (input == 4)
                    viewTransactionUser();
                else if (input == 5)
                    DEVremoveTransfer();
                else if (input == 6)
                    DEVcheckTransferValidity();
                else if (input == 7)
                    isFinish = true;
            }catch (Exception e) {
                System.err.println("Not correct chioce");
				break;
            }

        } while (!isFinish);
        scanner.close();
    }

    public void addAUser() {
        System.out.print("Enter a user name and balance\n-> ");
        try {
            transactionsService.addUser(new User(scanner.next(), scanner.nextLong()));
        }catch (Exception e){
            System.out.println("error input user");
        }
        System.out.println("---------------------------------------------------------");
        printMenu();
    }

    public void viewUserBalances() {
        System.out.print("Enter a user ID\n-> ");
        try {
            User user = transactionsService
                            .getUserList()
                            .getUserByiD(scanner.nextLong()
                            );
            System.out.printf("%s - %d\n", user.getUserName(), user.getUserBalance());
        }catch (Exception e){
            System.out.println("error input user");
        }
        System.out.println("---------------------------------------------------------");
        printMenu();
    }

    public void performTransfer(){
        System.out.print("Enter a sender ID, a recipient ID, and a transfer amount\n-> ");
        try {
            User sender = transactionsService
                    .getUserList()
                    .getUserByiD(scanner.nextLong()
                    );
            User recipient = transactionsService
                    .getUserList()
                    .getUserByiD(scanner.nextLong()
                    );
            transactionsService.doTransaction(sender, recipient, scanner.nextLong());
        }catch (Exception e){
            System.out.println("error input user");
        }
        System.out.println("---------------------------------------------------------");
        printMenu();
    }

    public void viewTransactionUser(){
        System.out.print("Enter a user ID\n-> ");
        try {
            User user = transactionsService
                    .getUserList()
                    .getUserByiD(scanner.nextLong()
                    );
            System.out.println(user.getTransactionsList());
        }catch (Exception e){
            System.out.println("error input user");
        }
        System.out.println("---------------------------------------------------------");
        printMenu();
    }

    public void DEVremoveTransfer(){
        System.out.print("Enter a user ID and a transfer ID\n-> ");
        try {
            User user = transactionsService
                    .getUserList()
                    .getUserByiD(scanner.nextLong()
                    );
            UUID uuid = UUID.fromString(scanner.next());
            Transaction transaction = user.getTransactionsList().getTransaction(uuid);
            try {
                transactionsService.removeTransactionForUser(user, uuid);
                System.out.printf("Transfer To %s(id = %d) %d removed\n",
                        transaction.getRecipient().getUserName(),
                        transaction.getRecipient().getUserId(),
                        transaction.getAmount());
            } catch (Exception e)
            {
                System.err.println("error remove");
            }
        }catch (Exception e){
            System.out.println("error input user");
        }
        System.out.println("---------------------------------------------------------");
        printMenu();
    }

    public void DEVcheckTransferValidity(){
        Transaction unpairTransactions = transactionsService.getUnpairTransactions().getTransaction();
        while (unpairTransactions != null) {
            System.out.printf("%s(id = %d) has an unacknowledged transfer id = %s from %s(id = %d) for %d\n",
                    unpairTransactions.getRecipient().getUserName(),
                    unpairTransactions.getRecipient().getUserId(),
                    unpairTransactions.getId().toString(),
                    unpairTransactions.getSender().getUserName(),
                    unpairTransactions.getSender().getUserId(),
                    unpairTransactions.getAmount()
                    );
            unpairTransactions = unpairTransactions.getPreviewTransaction();
        }
        System.out.println("---------------------------------------------------------");
        printMenu();    }
}
