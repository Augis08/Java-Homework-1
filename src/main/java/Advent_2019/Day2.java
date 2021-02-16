package Advent_2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {
    public static void main(String[] args) {

        String path = "C:\\Users\\Augis\\Desktop\\JAVA\\Advent Input\\2019\\Day2.txt";
        List<String> input = Input.readFromFile(path);

        solvePart1(input);
        solvePart2(input);
    }

    private static void solvePart1(List<String> input) {
        List<Integer> programList = getProgramList(input);
        int position1 = 12;
        int position2 = 2;
        replacePosition1and2(programList, position1, position2);
        restoreProgram(programList);
        System.out.println("Day2 - Part1: " + programList.get(0));
    }

    private static void solvePart2 (List<String> input){
        int output = 19690720;
        for(int i=0; i<100; i++)
            for (int j = 0; j < 100; j++) {
                List<Integer> programList = new ArrayList<>(getProgramList(input));
                replacePosition1and2(programList, i, j);
                restoreProgram(programList);
                if(programList.get(0) == output) {
                    System.out.println("Day2 - Part2: " +  (100 * i + j));
                    return;
            }
        }
    }

    private static void replacePosition1and2(List<Integer> programList, int position1, int position2) {
        programList.set(1, position1);
        programList.set(2, position2);
    }

    private static void restoreProgram(List<Integer> program) {
        int opcodeIndex = 0;
        while (program.get(opcodeIndex) != 99) {
            int replacement = getReplacement(program, opcodeIndex);
            int indexToReplace = program.get(opcodeIndex + 3);
            program.set(indexToReplace, replacement);
            opcodeIndex += 4;
        }
    }

    private static int getReplacement(List<Integer> program, int opcodeIndex) {
        int replacement = 0;
        int index1 = program.get(opcodeIndex + 1);
        int index2 = program.get(opcodeIndex + 2);
        switch (program.get(opcodeIndex)) {
            case 1:
                replacement = program.get(index1) + program.get(index2);
                break;
            case 2:
                replacement = program.get(index1) * program.get(index2);
                break;
        }
        return replacement;
    }

    private static List<Integer> getProgramList(List<String> input) {
        return Arrays.asList(input.get(0).split(","))
                .stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}
