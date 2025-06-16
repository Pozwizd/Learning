package org.example.Java._11_InputOutput;

import java.io.*;

public class Text {

    /*
    Вот основные классы в Java для чтения/записи файлов:
    1. FileReader/FileWriter - позволяют читать/писать файлы посимвольно
    2. FileInputStream/FileOutputStream - побайтовое чтение/запись
    3. BufferedReader/BufferedWriter - символьное чтение/запись с буферизацией
     */
}

class Example1{
    public static void main(String[] args) throws IOException {
        FileWriter writer = new FileWriter("file.txt");
        writer.write('H');
        writer.write('i');
        writer.close();

        FileReader reader = new FileReader("file.txt");
        int character = reader.read();
        while(character != -1){
            System.out.print((char)character);
            character = reader.read();
        }
        reader.close();
        File file = new File("file.txt");
        file.delete();
    }
}

class Example2{
    public static void main(String[] args) throws IOException {

        File file = new File("file.bin");
        if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream output = new FileOutputStream(file);
        output.write(65); // writes byte 'A'
        output.close();

        FileInputStream input = new FileInputStream(file);
        int b = input.read();
        while(b != -1){
            System.out.print((char)b);
            b = input.read();
        }
        input.close();
        file.delete();
    }
}

class Example3{
    public static void main(String[] args) throws IOException {

        File file = new File("file.txt");
        if (!file.exists()) {
            file.createNewFile();
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write("Hello");
        writer.newLine();
        writer.write("World");
        writer.close();

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while(line != null){
            System.out.println(line);
            line = reader.readLine();
        }
        reader.close();
        file.delete();

    }
}

