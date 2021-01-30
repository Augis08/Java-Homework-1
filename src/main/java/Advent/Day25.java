package Advent;

import java.util.List;

public class Day25 {

     public static void solve (List<String> input){
        long cardPublicKey = Long.parseLong(input.get(0));
        long doorPublicKey = Long.parseLong(input.get(1));

        int cardLoopSize = getLoopSize(cardPublicKey);

         System.out.println("The answer of Day25 is: " + getEncryptionKey(doorPublicKey, cardLoopSize));
    }

    private static int getLoopSize(long publicKey) {
        long value = 1;
        int subjectNumber = 7;

        int loopSize = 0;
        int countLoops = 1;
        while (loopSize == 0) {
            value *= subjectNumber;
            value %= 20201227;
            if (value == publicKey)
                loopSize = countLoops;
            countLoops++;
        }
        return loopSize;
    }

    private static long getEncryptionKey (long publicKey, int loopSize){
        long encryptionKey = 1;
        for(int i = 0; i<loopSize; i++){
            encryptionKey *= publicKey;
            encryptionKey %= 20201227;
        }
        return encryptionKey;
    }
}
