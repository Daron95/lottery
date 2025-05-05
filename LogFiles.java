package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LogFiles {

    public List<String> logs = new ArrayList<>();

    public void addToLogs(String log) {
        logs.add(log);
    }

    public void write() {
        String desktopPath = System.getProperty("user.home") + "/Desktop";

        String fileName = "logs.txt";
        Path filePath = Paths.get(desktopPath, fileName);

        try {
            if (Files.exists(filePath)) {
                Files.deleteIfExists(filePath);
            }
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);

            Files.write(filePath, this.logs);

        } catch (IOException e) {
        }
    }
}
