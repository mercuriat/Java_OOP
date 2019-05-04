import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        StringBuilder sliceMessage = new StringBuilder();
        for(int i = whichSlice; i < message.length(); i += totalSlices){
            char letter = message.charAt(i);
            sliceMessage.append(letter);
        }
        return sliceMessage.toString();
    }
    

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        int[] key = new int[klength];
        //WRITE YOUR CODE HERE
        for(int i = 0; i < klength; i++){
            String sliceMessage =  sliceString(encrypted, i, klength);
            CaesarCracker cc = new CaesarCracker();
            int keyNumber = cc.getKey(sliceMessage);
            //String decrypted = cc.decrypt(sliceMessage);
            key[i] = keyNumber;
        }
        return key;
    }

    public void breakVigenere () {
        //WRITE YOUR CODE HERE
        FileResource fr = new FileResource("secretmessage1.txt");
        String encrypted = fr.asString();
        int klength = 4;
        int[] key = tryKeyLength(encrypted, klength, 'e');

        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(encrypted);
        
        //Mainly for the first line
        System.out.println(decrypted.substring(0,100));
        
        //Print out the key
        for(int i = 0; i < klength; i++){
            System.out.println(key[i]);
        }        
    }
    
}
