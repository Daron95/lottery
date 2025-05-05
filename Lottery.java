package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Lottery implements Game {

    private List<Integer> unluckyNumbers;
    FileWriter writer = new FileWriter();

    public Lottery() {
        writeNumbersToList();
    }

    public List<Integer> generateRandomNumbers() {

        List<Integer> randomNumbers = new ArrayList<>();
        Random random = new Random();
        int randomNumber;

        while (randomNumbers.size() != 6) {

            randomNumber = random.nextInt(49) + 1;

            if (!randomNumbers.contains(randomNumber)
                    && (unluckyNumbers == null || !unluckyNumbers.contains(randomNumber))) {
                randomNumbers.add(randomNumber);
            }
        }
        Collections.sort(randomNumbers);
        return randomNumbers;
    }

    public void newUnluckyNumbers(LogFiles logFiles) {
        List<Integer> lottoNumbers = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        System.out.println("\n" + Messages.ENTERING_UNLUCKY_NUMBERS);

        while (lottoNumbers.size() != 6) {
            try {
                int number = input.nextInt();

                if (lottoNumbers.contains(number)) {
                    System.out.println(Messages.ALREADY_EXISTS_UNLUCKY_NUMBER);
                } else if (number >= 1 && number <= 49) {
                    lottoNumbers.add(number);
                } else {
                    System.out.println(Messages.INVALID_UNLUCKY_NUMBER);
                }

            } catch (InputMismatchException e) {
                System.out.println(Messages.INVALID_UNLUCKY_NUMBER);
                input = new Scanner(System.in);
            }
        }
        writer.write(lottoNumbers.toString());
        this.unluckyNumbers = lottoNumbers;
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
