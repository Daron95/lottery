package com.example;

public class Messages {

    private Messages() {
    }

    public static final String START_COMMAND = "Please choose the game:  1-Lotto    2-Eurojackpot    3-Exit \n";
    public static final String END_COMMAND = "Thanks for playing! See you soon!";

    public static final String ENTERING_UNLUCKY_NUMBERS = "Please enter 6 unlucky numbers from [1 , 49] ";
    public static final String INVALID_UNLUCKY_NUMBER = "Invalid number! Please enter the remaining numbers from [1 , 49] ";

    public static final String ALREADY_EXISTS_UNLUCKY_NUMBER = "You have already entered the same number. Please choose another number";

    public static final String ACTION_COMMAND = "\n Please choose : 0- Generate Random Numbers   1- Unlimited Generate Random Numbers   2- New Unlucky Numbers   3- Delete Unlucky Numbers  \n 4- See Unlucky Numbers   5- Exit ";

    public static final String ANY_KEY_COMMAND = "\n Press Enter to stop :  \n";
}
