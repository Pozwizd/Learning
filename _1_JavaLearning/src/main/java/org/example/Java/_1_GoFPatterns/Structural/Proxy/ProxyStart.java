package org.example.Java._1_GoFPatterns.Structural.Proxy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

interface MyFileReader {
    String read(String filename);
}

class RealFileReader implements MyFileReader {
    public String read(String filename) {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return content.toString();
    }
}

class LoggingFileReader implements MyFileReader {
    private final MyFileReader fileReader;

    public LoggingFileReader(MyFileReader fileReader) {
        this.fileReader = fileReader;
    }

    public String read(String filename) {
        String content = fileReader.read(filename);
        System.out.println("Файл " + filename + " прочитан");
        return content;
    }
}

public class ProxyStart {
    public static void main(String[] args) {
        MyFileReader reader = new LoggingFileReader(new RealFileReader());

        String content = reader.read("example.txt");
        System.out.println("Содержимое файла: " + content);
    }
}
