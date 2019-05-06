import java.util.*;
import edu.duke.*;
import java.io.*;

public class VigenereBreaker {
    private StringBuilder Alphabet;
    
    public VigenereBreaker(){
        Alphabet = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
    }
    
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

    public String[] breakVigenere(int klength, String encrypted, char commonletter) {
        //WRITE YOUR CODE HERE
        int[] key = tryKeyLength(encrypted, klength, commonletter);

        VigenereCipher vc = new VigenereCipher(key);
        String decrypted = vc.decrypt(encrypted);
        
        //Mainly for the first line
        //System.out.println(decrypted.substring(0,100));
        
        //Print out the key
        //for(int i = 0; i < klength; i++){
        //    System.out.println(key[i]);
        //}
        
        StringBuilder keyWord = new StringBuilder();
        for(int i : key){
            keyWord.append(Alphabet.charAt(i));
        }
        
        String[] decryptedKey = {keyWord.toString(), decrypted};
        return decryptedKey;
    }
    
    
    public HashSet<String> readDictionary(FileResource fr){
        HashSet<String> dictionary = new HashSet<String>();
        for(String line : fr.lines()){
            line = line.toLowerCase();
            dictionary.add(line);
        }
        return dictionary;
    }
    
    public int countWords(String message, HashSet<String> dictionary){
        ArrayList<String> list = new ArrayList<String>();
        int totalWord = 0;
        for(String word : message.split("\\W")){
            word = word.toLowerCase();
            if(dictionary.contains(word)){
                totalWord ++;
            }
        }
        return totalWord;
    }
    
    public String[] breakForLanguage(String encrypted, HashSet<String> dictionary, int trigger){
        int maxLength = 100;
        int maxWord = 0;
        int kLength = 0;
        char commonletter = mostCommonCharIn(dictionary);
        
        for(int i = 1; i < maxLength; i ++){
            int[] key = tryKeyLength(encrypted, i, commonletter);
            VigenereCipher vc = new VigenereCipher(key);
            String decrypted = vc.decrypt(encrypted);
            int wordNumber = countWords(decrypted, dictionary);
            if(wordNumber > maxWord){
                maxWord = wordNumber;
                kLength = i;
            }
            
            //Only for test
            //if(i == 38){
            //    maxWord = wordNumber;
            //    kLength = i;
            //    break;
            //}
        }
          
        if(trigger == 1){
            System.out.println("The length of key is " + kLength);
            System.out.println("There are totally " + maxWord + " words vaild");
        }
        //System.out.println("The length of key is " + kLength);
        //System.out.println("There are totally " + maxWord + " words vaild");
        
        return breakVigenere(kLength, encrypted, commonletter);
    }
    
    public char mostCommonCharIn(HashSet<String> dictionary){
        HashMap<Character, Integer> alphabet = new HashMap<Character, Integer>();
        for(String k : dictionary){
            StringBuilder word = new StringBuilder(k);
            for(int i = 0; i < word.length(); i ++){
                char temp = word.charAt(i);
                if(!alphabet.containsKey(temp)){
                    alphabet.put(temp, 1);
                }
                else{
                    alphabet.put(temp, alphabet.get(temp) + 1);
                }
            }
        }
        
        int max = 0;
        char mostCommon = 'a';
        for(char letter : alphabet.keySet()){
            int letterCount = alphabet.get(letter);
            if(letterCount > max){
                max = letterCount;
                mostCommon = letter;
            }
        }   
        return mostCommon;
    }
    
    public void breakForAllLangs(String message, HashMap<String, HashSet<String>> languages){
        HashMap<String, Integer> allLangsCount = new HashMap<String, Integer>();
        for(String language : languages.keySet()){
            HashSet<String> dictionary = languages.get(language);
            String[] decryptedKey = breakForLanguage(message, dictionary, 0);
            int wordNumber = countWords(decryptedKey[1], dictionary);
            allLangsCount.put(language, wordNumber);
        }
        
        int max = 0;
        String finalLangs = "";
        for(String language : allLangsCount.keySet()){
            int wordCount = allLangsCount.get(language);
            if(wordCount > max){
                max = wordCount;
                finalLangs = language;
            }
        }
        
        System.out.println("Final languages is " + finalLangs + ", with word counts as "
         + max);
        
        HashSet<String> dictionary = languages.get(finalLangs);
        String[] decryptedKey = breakForLanguage(message, dictionary, 1);
        System.out.println(decryptedKey[1].substring(0,200));
        System.out.println("The key is " + decryptedKey[0]);
    }
    
    
    public void main(){
        FileResource fr = new FileResource("secretmessage3.txt");
        String encrypted = fr.asString();
        //HashSet<String> dictionary = readDictionary(new FileResource());
        HashMap<String, HashSet<String>> languages = new HashMap<String, HashSet<String>>();
        
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            // process each file in turn
            FileResource frs = new FileResource(f);
            languages.put(f.getName(), readDictionary(frs));
        }
        breakForAllLangs(encrypted, languages);
    }
}
