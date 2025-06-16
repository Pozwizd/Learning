package org.example.Java._11_InputOutput;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Scanner;
import java.util.UUID;

public class Example {

    public static void main(String[] args) throws IOException {


    }

}

class Task1{

    public static void main(String[] args) throws IOException {
        // 3.1
        File docsDir = new File("uploaded/docs/Task1");
        docsDir.mkdirs();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Введите имя файла (или stop для выхода): ");
            String input = scanner.nextLine();
            if (input.equals("stop")) {
                break;
            }

            String uuid = UUID.randomUUID().toString();
            String fileName = uuid + "_" + input + ".txt";

            File file = new File(docsDir, fileName);
            System.out.print("Введите текст для файла " + fileName + ": ");
            String content = scanner.nextLine();

            Files.write(file.toPath(), content.getBytes());
            System.out.println("Файл " + fileName + " создан");
        }

        System.out.print("Введите слово для поиска: ");
        String keyword = scanner.nextLine();

        File[] files = docsDir.listFiles((dir, name) -> name.endsWith(".txt"));
        for (File file : files) {
            if (Files.readString(file.toPath()).contains(keyword)) {
                System.out.println("Найден файл: " + file.getName());
            }
        }
    }
}

class Task2_3{

    public static void main(String[] args) throws IOException {

        File name = new File("uploaded/docs/Task2/users.txt");
        name.createNewFile();
        Scanner scanner = new Scanner(System.in);
        // 3.2
        File usersFile = new File("uploaded/docs/Task2","users.txt");
        System.out.print("Введите имена через пробел: ");
        String names = scanner.nextLine();
        Files.writeString(usersFile.toPath(), names);

        // 3.3
        String users = Files.readString(usersFile.toPath());
        String[] namesArray = users.split(" ");
        System.out.println(Arrays.toString(namesArray));
    }

}

class Task4{
    public static void main(String[] args) throws IOException {
        // 3.4

        File dir = new File("uploaded/docs/Task4");
        dir.mkdirs();
        String exampleText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip";
        File file1 = new File("uploaded/docs/Task4","file1.txt");
        Files.write(file1.toPath(), exampleText.getBytes());
        System.out.println(Files.readString(file1.toPath()));
        File file2 = new File("uploaded/docs/Task4","file2.txt");
        Files.copy(file1.toPath(), file2.toPath());

        boolean equal = Arrays.equals(Files.readAllBytes(file1.toPath()),
                Files.readAllBytes(file2.toPath()));
        System.out.println("Файлы " + (equal ? "равны" : "не равны"));

    }
}

class Task5{

    public static void removeDir(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File f : files) {
                    removeDir(f);
                    System.out.println("Удален файл " + f.getName());
                }
            }
        }
        dir.delete();
    }
    public static void main(String[] args) throws IOException {
        // 3.5
        File tempDir = new File("uploaded/docs/");
        removeDir(tempDir);
    }
}

class Task6{
    public static void main(String[] args) {

        // 3.6
        String ext = ".txt";
        File folder = new File("uploaded/docs/Task4");
        File[] found = folder.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(ext);
            }
        });
        System.out.println(Arrays.toString(found));
    }
}