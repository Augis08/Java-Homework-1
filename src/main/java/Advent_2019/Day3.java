package Advent_2019;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day3 {
    public static void main(String[] args) {

        String path = "C:\\Users\\Augis\\Desktop\\JAVA\\Advent Input\\2019\\Day3.txt";
        List<String> input = Input.readFromFile(path);

        solve(input);
    }

    private static void solve(List<String> input) {
        List<Point2D> wire1Points = getWiresPoints(getWiresPath(input.get(0)));
        List<Point2D> wire2Points = getWiresPoints(getWiresPath(input.get(1)));
        List<Point2D> intersections = getIntersections(wire1Points, wire2Points);

        System.out.println("Day3 - Part1: " + findMinDistance(intersections));

        System.out.println("Day3 - Part2: " + countFewestSteps(intersections, wire1Points, wire2Points));
    }

    private static int countFewestSteps(List<Point2D> intersections, List<Point2D> wire1, List<Point2D> wire2) {
        int fewestSteps = wire1.indexOf(intersections.get(0)) + 1 + wire2.indexOf(intersections.get(0)) + 1;
        for (Point2D point : intersections) {
            int steps = wire1.indexOf(point) + 1 + wire2.indexOf(point) + 1;
            if (steps < fewestSteps)
                fewestSteps = steps;
        }
        return fewestSteps;
    }

    private static int findMinDistance(List<Point2D> intersections) {
        int minDistance = getDistance(intersections.get(0));
        for (Point2D point : intersections)
            if (getDistance(point) < minDistance)
                minDistance = getDistance(point);
        return minDistance;
    }

    private static int getDistance(Point2D point) {
        return (int) (Math.abs(point.getX()) + Math.abs(point.getY()));
    }

    private static List<Point2D> getIntersections(List<Point2D> wire1, List<Point2D> wire2) {
        List<Point2D> intersections = new ArrayList<>();
        for (Point2D point : wire1)
            if (wire2.contains(point)) {
                intersections.add(point);
            }
        return intersections;
    }

    private static List<Point2D> getWiresPoints(List<String> wiresPath) {
        List<Point2D> wiresPoints = new ArrayList<>();
        int x = 0;
        int y = 0;
        for (String s : wiresPath) {
            switch (getDirection(s)) {
                case 'R':
                    for (int i = 1; i <= getSteps(s); i++) {
                        x += 1;
                        wiresPoints.add(new Point2D.Double(x, y));
                    }
                    break;
                case 'L':
                    for (int i = 1; i <= getSteps(s); i++) {
                        x -= 1;
                        wiresPoints.add(new Point2D.Double(x, y));
                    }
                    break;
                case 'U':
                    for (int i = 1; i <= getSteps(s); i++) {
                        y += 1;
                        wiresPoints.add(new Point2D.Double(x, y));
                    }
                    break;
                case 'D':
                    for (int i = 1; i <= getSteps(s); i++) {
                        y -= 1;
                        wiresPoints.add(new Point2D.Double(x, y));
                    }
                    break;
            }
        }
        return wiresPoints;
    }

    private static int getSteps(String direction) {
        return Integer.parseInt(direction.substring(1));
    }

    private static char getDirection(String direction) {
        return direction.charAt(0);
    }

    private static List<String> getWiresPath(String line) {
        return Arrays.asList(line.split(","));
    }
}
