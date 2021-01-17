package Advent;

import java.util.*;

public class Day19 {

    public static void solve(List<String> input) {

        System.out.println();
//        System.out.println(getRules(input));
        System.out.println(solvePart1(input));
    }

    private static int solvePart1(List<String> input) {
        List<String> rule0 = getInvertedRule0(input);
        int countMatchedMessages = 0;
        for (String message : getMessages(input))
            for (String rule : rule0)
                if (message.equals(rule)) {
                    countMatchedMessages += 1;
                    break;
                }
        return countMatchedMessages;
    }

    private static List<String> getInvertedRule0(List<String> input) {
        List<String> rule0 = getRules(input).get(0);
        List<String> invertedRule0 = new ArrayList<>();
        while (rule0.size() > 0) {
            List<String> changedRule0 = new ArrayList<>();
            for (String rule : rule0) {
                if (!isAnyNumber(rule)) {
                    invertedRule0.add(rule.replaceAll(" ", ""));
                    continue;
                }
                for (String number : rule.split(" "))
                    if (!is_a_or_b(number)) {
//                        if(getPossibleCombinations(rule, Integer.parseInt(number), input).size() == 1) {
//                            rule = getPossibleCombinations(rule, Integer.parseInt(number), input).get(0);
//                            continue;
//                        }
                        changedRule0.addAll(getPossibleCombinations(rule, Integer.parseInt(number), input));
//                        System.out.println(getPossibleCombinations(rule, Integer.parseInt(number), input));
                        break;
                    }
//                System.out.println("changedRule0: " + changedRule0.size() + " : " + changedRule0);
            }
//                changedRule0.addAll(changeRule(rule, input));
            rule0 = new ArrayList<>(changedRule0);
            System.out.println("rule0 " + rule0.size() + " : Inverted " + invertedRule0.size());
//            System.out.println(rule0);
//            System.out.println(invertedRule0);
//            System.out.println("inverted " + invertedRule0);
        }
        return invertedRule0;
    }

    private static boolean isAnyNumber(String rule) {
        boolean isNumber = false;
        for (String s : rule.split(" "))
            if (!is_a_or_b(s)) {
                isNumber = true;
                break;
            }
        return isNumber;
    }

    private static boolean is_a_or_b(String character) {
        return character.equals("a") || character.equals("b");
    }

    private static List<String> changeRule(String rule, List<String> input) {
        List<String> changedRule = new ArrayList<>();
        changedRule.add(rule);
        for (String number : rule.split(" ")) {
            List<String> rules = new ArrayList<>();
            if (!is_a_or_b(number)) {
                for (String s : changedRule) {
                    rules.addAll(getPossibleCombinations(s, Integer.parseInt(number), input));
                    changedRule = new ArrayList<>(rules);
                }
            }
        }
        return changedRule;
    }

    private static List<String> getPossibleCombinations(String rule, int number, List<String> input) {
        List<String> combinations = new ArrayList<>();
        for (String rules : getRules(input).get(number)) {
            String[] numbers = rule.split(" ");
            for (int i = 0; i < numbers.length; i++)
                if (!is_a_or_b(numbers[i]) && number == Integer.parseInt(numbers[i]))
                    numbers[i] = rules;
            combinations.add(String.join(" ", numbers));
        }
        return combinations;
    }

    private static HashMap<Integer, List<String>> getRules(List<String> input) {
        HashMap<Integer, List<String>> rules = new HashMap<>();
        for (String s : input) {
            if (s.isEmpty())
                break;
            if (s.contains("a") || s.contains("b"))
                s = s.replaceAll("\"", "");
            int number = Integer.parseInt(s.split(":")[0]);
            String rule = (s.split(": ")[1]);
            rules.put(number, Arrays.asList(rule.split(" \\| ").clone()));
        }
        return rules;
    }

    private static List<String> getMessages(List<String> input) {
        List<String> receivedMessages = new ArrayList<>();
        for (String s : input) {
            if (s.contains(":") || s.isEmpty())
                continue;
            receivedMessages.add(s);
        }
        return receivedMessages;
    }
}
