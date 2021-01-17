package main.java;

import Advent.*;

import java.util.List;

public class Demo {
    public static void main(String[] args) {

        String path = "C:\\Users\\Augis\\Desktop\\JAVA\\Advent Input\\Test.txt";
        List<String> input = Input.readFromFile(path);

        Day23.solve(input);
    }
}
