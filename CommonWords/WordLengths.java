
/**
 * 在这里给出对类 WordLengths 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;

public class WordLengths {
    public static void countWordLengths(FileResource resource, int[] counts){
        for(String s : resource.words()){
            if(!Character.isLetter(s.charAt(s.length() - 1))){
                s = s.substring(0, (s.length() - 1));
            }
            int wordLength = s.length();
            if(wordLength >= 0){
                counts[wordLength] ++;
            }
        }
        for(int i = 0; i < counts.length; i++){
            System.out.println("The number of " + i + " is: " + counts[i]);
        }
    }
    
    public static int indexOfMax(int[] values){
        int max = values[0];
        int index = 0;
        for(int i = 0; i < values.length; i ++){
            if(max < values[i]){
                max = values[i];
                index = i;
            }   
        }
        return index;
    }
    
    public static void test(){
        FileResource fr = new FileResource();
        int[] numberArray = new int[31];
        countWordLengths(fr, numberArray);
        System.out.println("The max number will be: " + indexOfMax(numberArray));
    }
}
