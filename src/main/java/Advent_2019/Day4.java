package Advent_2019;

public class Day4 {

    public static void main(String[] args) {
        String input = "353096-843212";
        solve(input);
    }

    private static void solve(String input) {
        int minRange = Integer.parseInt(input.split("-")[0]);
        int maxRange = Integer.parseInt(input.split("-")[1]);

        System.out.println("Day4 - Part1: " + countValidPasswords(minRange, maxRange));

        System.out.println("Day4 - Part2: " + countValidPasswords2(minRange, maxRange));
    }

    private static int countValidPasswords2 (int minRange, int maxRange) {
        int validPasswords = 0;
        for(int i = minRange; i <= maxRange; i++){
            if(!isPairDigits50(i)
                    && isNotDecreasedDigits(i)
                    && isTheSameAdjacentDigits2(i))
                validPasswords += 1;
        }
        return validPasswords;
    }


    private static boolean isTheSameAdjacentDigits2 (int password){
        char [] digits = String.valueOf(password).toCharArray();
        char toCompare = 0;
        int numberOfDigits = 1;
        for(char digit : digits) {
            if(digit != toCompare && numberOfDigits == 2)
                return true;
            if (digit == toCompare)
                numberOfDigits++;
            if(digit != toCompare)
                numberOfDigits = 1;
            toCompare = digit;
        }
        return numberOfDigits == 2;
    }

    private static int countValidPasswords (int minRange, int maxRange) {
        int validPasswords = 0;
        for(int i = minRange; i <= maxRange; i++){
            if(!isPairDigits50(i)
                    && isNotDecreasedDigits(i)
                    && isTheSameAdjacentDigits(i))
                validPasswords += 1;
        }
        return validPasswords;
    }

    private static boolean isPairDigits50 (int password){
        return String.valueOf(password).contains("50");
    }

    private static boolean isNotDecreasedDigits (int password){
        char [] digits = String.valueOf(password).toCharArray();
        int toCompare = 0;
        for (int digit : digits) {
            if (digit < toCompare)
                return false;
            toCompare = digit;
        }
        return true;
    }

    private static boolean isTheSameAdjacentDigits (int password){
        char [] digits = String.valueOf(password).toCharArray();
        char toCompare = 0;
        for(char digit : digits) {
            if (digit == toCompare)
                return true;
            toCompare = digit;
        }
        return false;
    }
}
