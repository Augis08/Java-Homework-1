package Advent;

import java.util.*;

public class Day24 {

    public static void solve(List<String> input) {
        Map<List<Integer>, String> tiles = getTiles(input);
        System.out.println("The answer of Day24 Part1 is: " + countBlackTiles(tiles));

        int days = 100;
        flipTilesByDays(tiles, days);
        System.out.println("The answer of Day24 Part2 is: " + countBlackTiles(tiles));
    }

    private static void flipTilesByDays(Map<List<Integer>, String> tiles, int days) {
        while (days > 0) {
            expandTilesArea(tiles);
            flipTiles(tiles);
            days -= 1;
        }
    }

    private static void expandTilesArea(Map<List<Integer>, String> tiles) {
        List<List<Integer>> coordinatesToExpand = new ArrayList<>();
        tiles.keySet().stream().map(Day24::getNeighborsCoordinates).forEach(coordinatesToExpand::addAll);
        coordinatesToExpand.forEach(coordinates -> tiles.putIfAbsent(coordinates, "white"));
    }

    private static void flipTiles(Map<List<Integer>, String> tiles) {
        Set<List<Integer>> coordinatesToFlipTiles = new HashSet<>();
        for (List<Integer> coordinates : tiles.keySet()) {
            String tileSide = tiles.get(coordinates);
            List<List<Integer>> neighborsCoordinates = getNeighborsCoordinates(coordinates);
            long countBlackNeighborsTiles = countBlackNeighborsTiles(tiles, neighborsCoordinates);
            if (tileSide.equals("black") && (countBlackNeighborsTiles == 0 || countBlackNeighborsTiles > 2))
                coordinatesToFlipTiles.add(coordinates);
            if (tileSide.equals("white") && countBlackNeighborsTiles == 2)
                coordinatesToFlipTiles.add(coordinates);
        }
        coordinatesToFlipTiles.forEach(coordinate -> flipTile(tiles, coordinate));
    }

    private static long countBlackNeighborsTiles(Map<List<Integer>, String> tiles, List<List<Integer>> neighborsCoordinates) {
        return neighborsCoordinates.stream()
                .filter(coordinate -> tiles.get(coordinate) != null
                        && tiles.get(coordinate).equals("black"))
                .count();
    }

    private static List<List<Integer>> getNeighborsCoordinates(List<Integer> coordinates) {
        List<List<Integer>> neighborsCoordinates = new ArrayList<>();
        int x = coordinates.get(0);
        int y = coordinates.get(1);
        neighborsCoordinates.add(Arrays.asList(x + 1, y + 1));
        neighborsCoordinates.add(Arrays.asList(x + 2, y));
        neighborsCoordinates.add(Arrays.asList(x + 1, y - 1));
        neighborsCoordinates.add(Arrays.asList(x - 1, y - 1));
        neighborsCoordinates.add(Arrays.asList(x - 2, y));
        neighborsCoordinates.add(Arrays.asList(x - 1, y + 1));
        return neighborsCoordinates;
    }

    private static int countBlackTiles(Map<List<Integer>, String> tiles) {
        return (int) tiles
                .values()
                .stream()
                .filter(s -> s.equals("black"))
                .count();
    }

    private static Map<List<Integer>, String> getTiles(List<String> input) {
        Map<List<Integer>, String> tiles = new HashMap<>();
        for (String s : input) {
            List<String> directions = getDirections(s);
            Integer[] tileCoordinates = {0, 0};
            for (String direction : directions)
                move(direction, tileCoordinates);
            List<Integer> coordinates = Arrays.asList(tileCoordinates);
            if (tiles.get(coordinates) == null)
                tiles.put(coordinates, "black");
            else
                flipTile(tiles, coordinates);
        }
        return tiles;
    }

    private static void flipTile(Map<List<Integer>, String> tiles, List<Integer> coordinates) {
        switch (tiles.get(coordinates)) {
            case "black":
                tiles.replace(coordinates, "white");
                break;
            case "white":
                tiles.replace(coordinates, "black");
                break;
        }
    }

    private static void move(String direction, Integer[] coordinates) {
        switch (direction) {
            case "e": // move one step to east
                coordinates[0] += 2;
                break;
            case "w": // move one step to west
                coordinates[0] -= 2;
                break;
            case "nw": // move one step to northwest
                coordinates[0] -= 1;
                coordinates[1] += 1;
                break;
            case "ne": // move one step to northeast
                coordinates[0] += 1;
                coordinates[1] += 1;
                break;
            case "sw": // move one step to southwest
                coordinates[0] -= 1;
                coordinates[1] -= 1;
                break;
            case "se": // move one step to southeast
                coordinates[0] += 1;
                coordinates[1] -= 1;
                break;
        }
    }

    private static List<String> getDirections(String directionsLine) {
        List<String> directions = new ArrayList<>();
        List<String> toSplit = new ArrayList<>(Arrays.asList(directionsLine.split("")));
        while (toSplit.size() > 0) {
            switch (toSplit.get(0)) {
                case "s":
                case "n":
                    directions.add(String.join("", toSplit.get(0), toSplit.get(1)));
                    toSplit.remove(0);
                    break;
                default:
                    directions.add(toSplit.get(0));
                    break;
            }
            toSplit.remove(0);
        }
        return directions;
    }
}
