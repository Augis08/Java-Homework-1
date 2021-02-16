package Advent_2019;

import java.util.ArrayList;
import java.util.List;

public class Day1 {

    public static void main(String[] args) {

        String path = "C:\\Users\\Augis\\Desktop\\JAVA\\Advent Input\\2019\\Day1.txt";
        List<String> input = Input.readFromFile(path);
        solve(input);
    }

    public static void solve(List<String> input) {
        List<Integer> modules = getModules(input);
        System.out.println("Day1 - Part1: " + getFuelRequirementsForAllModules(modules));
        System.out.println();
        System.out.println("Day1 - Part2: " + getTotalFuelForAllModules(modules));
    }

    public static int getTotalFuelForAllModules(List<Integer> modules) {
        int totalFuelForAllModules = 0;
        for (Integer module : modules)
            totalFuelForAllModules += getTotalFuelRequirements(module);
        return totalFuelForAllModules;
    }

    private static int getTotalFuelRequirements(int moduleMass) {
        int fuelForModuleWithFuel = 0;
        int mass = moduleMass;
        while (mass > 6) {
            mass = getFuelRequirements(mass);
            fuelForModuleWithFuel += mass;
        }
        return fuelForModuleWithFuel;
    }

    private static int getFuelRequirementsForAllModules(List<Integer> modules) {
        int fuelRequirementsForAllModules = 0;
        for (Integer module : modules)
            fuelRequirementsForAllModules += getFuelRequirements(module);
        return fuelRequirementsForAllModules;
    }

    private static int getFuelRequirements(int moduleMass) {
        return moduleMass / 3 - 2;
    }

    private static List<Integer> getModules(List<String> input) {
        List<Integer> modules = new ArrayList<>();
        for (String s : input)
            modules.add(Integer.parseInt(s));
        return modules;
    }
}
