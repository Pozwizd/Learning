package org.example.Java._7_Exception;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class TypeExample {
}

class CheckedExceptionEx {
    public static void main(String[] args) {
        File file = new File("fileException.txt");

        try {
            FileReader fr = new FileReader(file);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to open file: " + file);
        }
    }
}

class UncheckedExceptionEx {
    public static void main(String[] args) {
        String value = null;
        System.out.println(value.toString());
    }
}


class ErrorExceptionEx{
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Integer.MAX_VALUE);
    }

}