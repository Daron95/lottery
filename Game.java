package com.example;

import java.util.List;

public interface Game {

    public List<Integer> generateRandomNumbers();

    public void newUnluckyNumbers(LogFiles logFiles);

    public void deleteUnluckyNumbers();
}
