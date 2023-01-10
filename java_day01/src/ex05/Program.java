package ex05;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Program {

	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_CYAN = "\u001B[36m";


	public static void main(String[] args) throws UserNotFoundException, TransactionNotFoundException {
		Menu menu = Menu.getMenu();
		menu.printMenu(checkDev(getFirstArg(args)));
		menu.listenInput(checkDev(getFirstArg(args)));
	}

	static String getFirstArg(String[] args){
		if (args.length != 1)
			return "";
		try {
			return args[0].split("=")[1];
		}catch (RuntimeException e ){
			return "";
		}

	}
	static boolean checkDev(String flag){
		return flag.equals("dev");
	}
}
