
/**
 * 在这里给出对类 WordFrequencies 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import java.util.ArrayList;
import edu.duke.*;

public class WordFrequencies {
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){
        myWords.clear();
        myFreqs.clear();
        FileResource fr = new FileResource();
        for(String s : fr.words()){
            s = s.toLowerCase();
            //StringBuilder word = new StringBuilder(s);
            //char lastLetter = word.charAt(word.length() - 1);
            //if(!Character.isLetter(lastLetter)){
            //   s = s.substring(0, s.length() - 1); 
            //}
            int index = myWords.indexOf(s);
            if(index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }
            else{
                int count = myFreqs.get(index);
                count ++;
                myFreqs.set(index, count);
            }
        }
    }
    
    public int maxOfIndex(ArrayList<Integer> list){
        int max = list.get(0);
        int index = 0;
        for(int i = 1; i < list.size(); i++){
            if(list.get(i) > max){
                max = list.get(i);
                index = i;
            }
        }
        return index;
    }
    
    public void printout(){
        findUnique();
        System.out.println("There are totally " + myWords.size() +
        " unique words");
        for(int i = 0; i < myWords.size(); i++){
            System.out.println("The word: " + myWords.get(i) + "\t" + 
            " have occured " + myFreqs.get(i) + "tiems");
        }
        int maxIndex = maxOfIndex(myFreqs);
        System.out.println("The most frequent word is " + myWords.get(maxIndex) + 
        ", with the number: " + myFreqs.get(maxIndex));
        System.out.println("There are totally " + myWords.size() + " words");
    }
}
