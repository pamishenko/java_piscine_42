package edu.school21.chat.exeptions;

public class NotSavedSubEntityException extends Throwable{
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_RESET = "\u001B[0m";

    public NotSavedSubEntityException(ClassNotFoundException e) {
        super(ANSI_YELLOW + "No saved this entity" + ANSI_RESET);
    }
}
