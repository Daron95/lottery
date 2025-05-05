package com.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileWriter {

    public void write(String numbers) {
        String desktopPath = System.getProperty("user.home") + "/Desktop";

        String fileName = "unlucky numbers.txt";
        Path filePath = Paths.get(desktopPath, fileName);

        try {
            if (Files.exists(filePath)) {
                Files.deleteIfExists(filePath);
            }
            Files.createDirectories(filePath.getParent());
            Files.createFile(filePath);

            Files.write(filePath, numbers.getBytes());

        } catch (IOException e) {
        }
    }

    public String read() {
        String desktopPath = System.getProperty("user.home") + "/Desktop";

        String fileName = "unlucky numbers.txt";
        Path filePath = Paths.get(desktopPath, fileName);

        try {
            byte[] fileContent = Files.readAllBytes(filePath);
            return new String(fileContent);

        } catch (IOException e) {
            return "Empty";
        }
    }

    public void delete() {
        String desktopPath = System.getProperty("user.home") + "/Desktop";

        String fileName = "unlucky numbers.txt";
        Path filePath = Paths.get(desktopPath, fileName);

        try {
            if (Files.exists(filePath)) {
                Files.deleteIfExists(filePath);
            }
        } catch (IOException e) {

        }
    }
}
