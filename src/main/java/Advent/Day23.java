package Advent;

import java.util.*;
import java.util.stream.Collectors;

public class Day23 {

    public static void solve(List<String> input) {

        System.out.println(getCupsLabels(input));
        System.out.println(solvePart1(input));
//        System.out.println(getCupsLabels2(input));
//        System.out.println(solvePart2(input));
    }

    private static int solvePart2 (List<String> input){
        int moves = 10000000;
        List<Integer> cups = move(getCupsLabels2(input), moves);
        int cupWithStar1 = cups.get(cups.indexOf(1)-1);
        int cupWithStar2 = cups.get(cups.indexOf(1)+1);

        System.out.println(cupWithStar1 + " : " + cupWithStar2);

        return cupWithStar1*cupWithStar2;
    }

    private static String solvePart1(List<String> input) {
        int moves = 100;
        List<Integer> cups = move(getCupsLabels(input), moves);
        String cupsToString = cups.stream().map(String::valueOf)
                .collect(Collectors.joining(""));
        System.out.println(cupsToString);
        cupsToString = cupsToString.substring(cupsToString.indexOf('1') + 1)
                + cupsToString.substring(0, cupsToString.indexOf('1'));
        return cupsToString;
    }

    private static List<Integer> getCupsLabels2 (List<String> input){
        List<Integer> cups = getCupsLabels(input);
        int maxCup = Collections.max(cups);
        for(int i=maxCup; i<=1000000; i++)
            cups.add(i);
        return cups;
    }

    private static List<Integer> move(List<Integer> cups, int numberOfMoves) {
        List<Integer> reorderedCups = new ArrayList<>(cups);
        int currentCupIndex = 0;
        int moves = 0;
        while (moves < numberOfMoves) {
//            System.out.println("-- move " + (moves + 1) + " --");
//            System.out.println(reorderedCups);
            int currentCup = reorderedCups.get(currentCupIndex);
//            System.out.println("(" + currentCup + ")");
            List<Integer> pickedCups = getPickedUpCups(reorderedCups, currentCupIndex);
//            System.out.println("pick up: " + pickedCups);
            List<Integer> cupsWithoutPickedUpCups = removePickedUpCups(reorderedCups, currentCupIndex);
//            System.out.println("cupsWithout: " + cupsWithoutPickedUpCups);
            int destinationCup = getDestinationCup(cupsWithoutPickedUpCups, reorderedCups.get(currentCupIndex));
//            System.out.println("destination: " + destinationCup);
            reorderedCups = placePickedUpCups(cupsWithoutPickedUpCups, pickedCups, destinationCup);
            currentCupIndex = reorderedCups.indexOf(currentCup) + 1;
            if (currentCupIndex == reorderedCups.size())
                currentCupIndex = 0;
            moves += 1;
            System.out.println(moves);
        }
        return reorderedCups;
    }

    private static List<Integer> removePickedUpCups(List<Integer> cups, int currentCupIndex) {
        List<Integer> cupsWithoutPickedCup = new ArrayList<>(cups);
        int indexForRemove = currentCupIndex + 1;
        for (int i = 0; i < 3; i++) {
            if (indexForRemove == cupsWithoutPickedCup.size())
                indexForRemove = 0;
            cupsWithoutPickedCup.remove(indexForRemove);
        }
        return cupsWithoutPickedCup;
    }

    private static List<Integer> placePickedUpCups(List<Integer> cups, List<Integer> pickedCups, int destination) {
        List<Integer> newCups = new ArrayList<>();
        for (int i : cups) {
            newCups.add(i);
            if (i == destination)
                newCups.addAll(pickedCups);
        }
        return newCups;
    }

    private static int getDestinationCup(List<Integer> cups, int currentCup) {
        int destinationCup = 0;
        List<Integer> sortedCups = new ArrayList<>(cups);
        Collections.sort(sortedCups);
        for (int i = 0; i < sortedCups.size(); i++)
            if (currentCup <= sortedCups.get(i))
                if (i == 0) {
                    destinationCup = sortedCups.get(cups.size() - 1);
                    break;
                } else {
                    destinationCup = sortedCups.get(i - 1);
                    break;
                }
        return destinationCup;
    }

    private static List<Integer> getPickedUpCups(List<Integer> cups, int currentCupIndex) {
        List<Integer> pickedUpCups = new ArrayList<>();
        int i = currentCupIndex + 1;
        while (pickedUpCups.size() < 3) {
            if (i == cups.size())
                i = 0;
            pickedUpCups.add(cups.get(i));
            i += 1;
        }
        return pickedUpCups;
    }

    private static List<Integer> getCupsLabels(List<String> input) {
        List<String> cupsLabel = Arrays.asList(input.get(0).split(""));
        return cupsLabel.stream().map(Integer::parseInt).collect(Collectors.toList());
    }
}
