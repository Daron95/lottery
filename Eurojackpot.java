package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Eurojackpot implements Game {

    private List<Integer> unluckyNumbers;
    FileWriter writer = new FileWriter();

    public Eurojackpot() {
        writeNumbersToList();
    }

    public List<Integer> generateRandomNumbers() {

        List<Integer> randomNumbers1 = new ArrayList<>();
        List<Integer> randomNumbers2 = new ArrayList<>();
        Random random = new Random();

        int randomNumber;

        while (randomNumbers1.size() != 5) {

            randomNumber = random.nextInt(50) + 1;

            if (!randomNumbers1.contains(randomNumber)
                    && (unluckyNumbers == null || !unluckyNumbers.contains(randomNumber))) {
                randomNumbers1.add(randomNumber);
            }
        }
        Collections.sort(randomNumbers1);

        while (randomNumbers2.size() != 2) {

            randomNumber = random.nextInt(10) + 1;

            if (!randomNumbers2.contains(randomNumber)
                    && (unluckyNumbers == null || !unluckyNumbers.contains(randomNumber))) {
                randomNumbers2.add(randomNumber);
            }
        }
        Collections.sort(randomNumbers2);
        randomNumbers1.addAll(randomNumbers2);

        return randomNumbers1;
    }

    public void newUnluckyNumbers(LogFiles logFiles) {
        List<Integer> euroJackpotNumbers = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        System.out.println("\n" + Messages.ENTERING_UNLUCKY_NUMBERS);

        while (euroJackpotNumbers.size() != 6) {
            try {
                int number = input.nextInt();

                if (euroJackpotNumbers.contains(number)) {
                    System.out.println(Messages.ALREADY_EXISTS_UNLUCKY_NUMBER);
                } else if (number >= 1 && number <= 49) {
                    euroJackpotNumbers.add(number);
                } else {
                    System.out.println(Messages.INVALID_UNLUCKY_NUMBER);
                }

            } catch (InputMismatchException e) {
                System.out.println(Messages.INVALID_UNLUCKY_NUMBER);
                input = new Scanner(System.in);
            }
        }

        writer.write(euroJackpotNumbers.toString());
        this.unluckyNumbers = euroJackpotNumbers;
        logFiles.addToLogs("Unlucky numbers : " + this.unluckyNumbers);
    }

    public void deleteUnluckyNumbers() {
        if (unluckyNumbers != null) {
            this.unluckyNumbers.clear();
        }
    }

    public List<Integer> getUnluckyNumbers() {
        return unluckyNumbers;
    }

    public void setUnluckyNumbers(List<Integer> unluckyNumbers) {
        this.unluckyNumbers = unluckyNumbers;
    }

    public void writeNumbersToList() {
        String s = writer.read();
        if (s != null && !s.equals("Empty")) {
            String cleanedString = s.replaceAll("[\\[\\]\\s]", "");

            String[] numberStrings = cleanedString.split(",");

            List<Integer> numberList = new ArrayList<>();
            for (String numberString : numberStrings) {
                numberList.add(Integer.parseInt(numberString));
            }
            this.unluckyNumbers = numberList;
        }
    }
}
