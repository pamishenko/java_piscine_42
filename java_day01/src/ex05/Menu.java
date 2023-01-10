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

    public void printMenu(boolean dev) {
        int i = 1;
        System.out.println(i++ +". Add a user");
        System.out.println(i++ +". View user balances");
        System.out.println(i++ +". Perform a transfer");
        System.out.println(i++ +". View all transactions for a specific user");
        if (dev) {
            System.out.println(i++ + ". DEV - remove a transfer by ID");
            System.out.println(i++ + ". DEV - check transfer validity");
        }
        System.out.println(i +". Finish execution");
    }
    public void listenInput(boolean dev) {
        boolean isFinish = false;
        do {
            int input = 42;
            try {
                int i = 1;
                input = scanner.nextInt();
                if (input == i++)
                    addAUser(dev);
                else if (input == i++)
                    viewUserBalances(dev);
                else if (input == i++)
                    performTransfer(dev);
                else if (input == i++)
                    viewTransactionUser(dev);
                if (dev) {
                    if (input == i++)
                        DEVremoveTransfer(dev);
                    else if (input == i++)
                        DEVcheckTransferValidity(dev);
                }
                if (input == i)
                    isFinish = true;
            }catch (Exception e) {
                System.err.println("Not correct choice");
				break;
            }

        } while (!isFinish);
        scanner.close();
    }

    public void addAUser(boolean dev) {
        System.out.print("Enter a user name and balance\n-> ");
        try {
            transactionsService.addUser(new User(scanner.next(), scanner.nextLong()));
        }catch (Exception e){
            System.out.println("error input user");
        }
        System.out.println("---------------------------------------------------------");
        printMenu(dev);
    }

    public void viewUserBalances(boolean dev) {
        System.out.print("Enter a user ID\n-> ");
        try {
            User user = transactionsServicevbyene
                            .getUserList()
                            .getUserByiD(scanner.nextLong()
                            );
            System.out.printf("%s - %d\n", user.getUserName(), user.getUserBalance());
        }catch (Exception e){
            System.out.println("error input user");
        }
        System.out.println("---------------------------------------------------------");
        printMenu(dev);
    }

    public void performTransfer(boolean dev){
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
        printMenu(dev);
    }

    public void viewTransactionUser(boolean dev){
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
        printMenu(dev);
    }

    public void DEVremoveTransfer(boolean dev){
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
        printMenu(dev);
    }

    public void DEVcheckTransferValidity(boolean dev){
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
        printMenu(dev);    }
}
