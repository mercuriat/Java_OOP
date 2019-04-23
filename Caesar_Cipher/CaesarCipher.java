
/**
 * 在这里给出对类 WordPlay 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String newAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        for (int i = 0; i < encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            if (Character.isLowerCase(currChar)){
                currChar = Character.toUpperCase(currChar);
                int id = alphabet.indexOf(currChar);
                char newChar = newAlphabet.charAt(id);
                encrypted.setCharAt(i, Character.toLowerCase(newChar));                
            }
            else{
                int id = alphabet.indexOf(currChar);
                if(id != -1){
                    char newChar = newAlphabet.charAt(id);
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        return encrypted.toString();
    }
    
    public String encryptTwoKeys(String input, int key1, int key2){
        StringBuilder encrypted = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String newAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        String newAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        for (int i = 0; i < encrypted.length(); i++){
            char currChar = encrypted.charAt(i);
            if (Character.isLowerCase(currChar)){
                currChar = Character.toUpperCase(currChar);
                int id = alphabet.indexOf(currChar);
                if (i % 2 == 0){
                    char newChar = newAlphabet1.charAt(id);
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));       
                }
                else{
                    char newChar = newAlphabet2.charAt(id);
                    encrypted.setCharAt(i, Character.toLowerCase(newChar));       
                }
         
            }
            else{
                int id = alphabet.indexOf(currChar);
                if(id != -1){
                    if (i % 2 == 0){
                        char newChar = newAlphabet1.charAt(id);
                        encrypted.setCharAt(i, newChar);       
                    }
                    else{
                        char newChar = newAlphabet2.charAt(id);
                        encrypted.setCharAt(i, newChar);       
                    }
                }
            }
        }
        return encrypted.toString();        
    }
    
    public void testCaeser(){
        int key = 15;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!";
        String encrypted = encryptTwoKeys(message, 8, 21);
        String decrypted = encryptTwoKeys(encrypted, 26 - 8, 26 - 21);
        System.out.println("key is " + key + "\n" + encrypted);
        System.out.println("key is " + key + "\n" + decrypted);
    }
}
