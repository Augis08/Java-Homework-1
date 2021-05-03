package Advent_2019;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day6 {

    private static final String path = "C:\\Users\\Augis\\Desktop\\JAVA\\Advent Input\\2019\\Day6.txt";
    private static final List<String> input = Input.readFromFile(path);
    private static final Map<String, String> directlyOrbits = getDirectlyOrbits();

    public static void main(String[] args) {
        solvePart1();
        System.out.println();
        solvePart2();
    }

    private static void solvePart2() {
        List<String> orbits1 = getOrbitsList("YOU");
        List<String> orbits2 = getOrbitsList("SAN");
        String pointOfIntersection = findPointOfIntersection(orbits1, orbits2);

        int numberOfTransfers = orbits1.indexOf(pointOfIntersection) + orbits2.indexOf(pointOfIntersection);

        System.out.println("Part2. The minimum number of orbital transfers required to move from\n" +
                "       the object YOU are orbiting to the object SAN is orbiting is: " + numberOfTransfers);
    }

    private static String findPointOfIntersection(List<String> orbits1, List<String> orbits2) {
        return orbits2.stream().filter(orbits1::contains).findFirst().orElse("");
    }

    private static void solvePart1() {
        int sumOrbits = directlyOrbits.keySet().stream().mapToInt(object -> getOrbitsList(object).size()).sum();

        System.out.println("Part1. The total number of direct and indirect orbits is: " + sumOrbits);
    }

    private static List<String> getOrbitsList(String object) {
        List<String> orbits = new ArrayList<>();
        String orbitsObject = object;
        while (directlyOrbits.get(orbitsObject) != null) {
            orbitsObject = directlyOrbits.get(orbitsObject);
            orbits.add(orbitsObject);
        }
        return orbits;
    }

    private static Map<String, String> getDirectlyOrbits() {
        return input.stream().collect(Collectors.toMap(Day6::getRightObject, Day6::getLeftObject));
    }

    private static String getLeftObject(String objects) {
        return objects.substring(0, objects.indexOf(")"));
    }

    private static String getRightObject(String objects) {
        return objects.substring(objects.indexOf(")") + 1);
    }
}
