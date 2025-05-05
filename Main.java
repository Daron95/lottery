package com.example;

import java.util.List;
import java.util.Scanner;

public class Main implements Runnable {

    volatile boolean booleanRun = true;
    volatile static boolean lotteryBoolean = true;

    private static Lottery lottery = new Lottery();
    private static Eurojackpot eurojackpot = new Eurojackpot();
    private static LogFiles logFiles = new LogFiles();

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        FileWriter file = new FileWriter();
        String numberSelection;

        do {

            logFiles.addToLogs("Start of application");
            System.out.println(Messages.START_COMMAND);
            numberSelection = input.nextLine();

        } while (!numberSelection.isEmpty() && !numberSelection.equals("1") && !numberSelection.equals("2")
                && !numberSelection.equals("3"));

        if (numberSelection.equals("3")) {

            System.out.println(Messages.END_COMMAND);
            System.exit(0);
        } else if (numberSelection.isEmpty() || numberSelection.equals("1")) {

            logFiles.addToLogs("Game selection is lottery");

            while (true) {
                System.out.println(
                        Messages.ACTION_COMMAND);
                String selection = input.next();

                if (selection.equals("0")) {
                    List<Integer> numbers = lottery.generateRandomNumbers();
                    System.out.println("\n 6 out of 49 : " + numbers);
                    logFiles.addToLogs("Random numbers : " + numbers);

                }

                if (selection.equals("1")) {

                    lotteryBoolean = true;
                    infinteGenerate();

                } else if (selection.equals("2")) {

                    lottery.newUnluckyNumbers(logFiles);
                }

                else if (selection.equals("3")) {
                    lottery.deleteUnluckyNumbers();
                    file.delete();

                } else if (selection.equals("4")) {
                    System.out.println("Unlucky numbers : " + file.read());
                }

                else if (selection.equals("5")) {
                    System.out.println(Messages.END_COMMAND);
                    break;
                }
            }
        } else if (numberSelection.equals("2")) {

            logFiles.addToLogs("Game selection is Eurojackpot");

            while (true) {
                System.out.println(
                        Messages.ACTION_COMMAND);
                String selection = input.next();

                if (selection.equals("0")) {

                    List<Integer> numbers = eurojackpot.generateRandomNumbers();
                    logFiles.addToLogs("Random numbers : " + numbers);
                    System.out.print("\n 5 out of 50 : ");
                    
                    for (int n = 0; n < numbers.size() - 2; n++) {
                        System.out.print(numbers.get(n) + " ");
                    }
                    System.out.print("\n 2 out of 10 : ");
                    for (int n = numbers.size() - 2; n < numbers.size(); n++) {
                        System.out.print(numbers.get(n) + " ");
                    }
                    System.out.println();

                } else if (selection.equals("1")) {
                    lotteryBoolean = false;
                    infinteGenerate();

                } else if (selection.equals("2")) {

                    eurojackpot.newUnluckyNumbers(logFiles);
                }

                else if (selection.equals("3")) {
                    eurojackpot.deleteUnluckyNumbers();
                    file.delete();
                }

                else if (selection.equals("4")) {
                    System.out.println("Unlucky numbers : " + file.read());

                } else if (selection.equals("5")) {
                    System.out.println(Messages.END_COMMAND);
                    break;
                }
            }
        }
        logFiles.addToLogs("End of application");
        logFiles.write();
    }

    public static void infinteGenerate() {
        Main main = new Main();
        Thread thread = new Thread(main);
        thread.start();
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextLine())
            ;

        main.booleanRun = false;
        thread.interrupt();
    }

    @Override
    public void run() {

        System.out.println(Messages.ANY_KEY_COMMAND);
        List<Integer> numbers;

        while (booleanRun) {
            if (lotteryBoolean) {
                numbers = lottery.generateRandomNumbers();
                System.out.println("\n 6 out of 49 : " + numbers);
                logFiles.addToLogs("Random numbers : " + numbers);

            } else {
                numbers = eurojackpot.generateRandomNumbers();
                logFiles.addToLogs("Random numbers : " + numbers);

                System.out.print("\n 5 out of 50 : ");
                for (int n = 0; n < numbers.size() - 2; n++) {
                    System.out.print(numbers.get(n) + " ");
                }
                System.out.print("\n 2 out of 10 : ");
                for (int n = numbers.size() - 2; n < numbers.size(); n++) {
                    System.out.print(numbers.get(n) + " ");
                }
                System.out.println();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {

            }
        }
    }
}