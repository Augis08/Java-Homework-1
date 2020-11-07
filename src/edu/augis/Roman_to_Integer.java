package edu.augis;

public class Roman_to_Integer {

    public static int romanToInt(String s) {

        char[] simbols = s.toCharArray();
        int[] numberArray = new int[s.length()+1];
        for (int i = 0; i < s.length(); i++) {
            char symbol = s.charAt(i);
            switch (symbol) {
                case 'I':
                    numberArray[i] = 1;
                    break;
                case 'V':
                    numberArray[i] = 5;
                    break;
                case 'X':
                    numberArray[i] = 10;
                    break;
                case 'L':
                    numberArray[i] = 50;
                    break;
                case 'C':
                    numberArray[i] = 100;
                    break;
                case 'D':
                    numberArray[i] = 500;
                    break;
                case 'M':
                    numberArray[i] = 1000;
                    break;
            }

        }

        int result = 0;
        for (int i = 0; i < numberArray.length-1; i++) {
            if (numberArray[i] < numberArray[i+1]) {
                numberArray[i] = numberArray[i] * -1;
            }
            result += numberArray[i];
        }
        return result;
    }
}
