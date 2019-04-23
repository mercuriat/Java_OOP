
/**
 * 在这里给出对类 CharactersInPlay 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import java.util.*;
import edu.duke.*;

public class CharactersInPlay {
    private ArrayList<String>   MyNames;
    private ArrayList<Integer>  MyFreqs;
    public CharactersInPlay(){
        MyNames = new ArrayList<String> ();
        MyFreqs = new ArrayList<Integer> ();
    }
    
    public void update(String name){
        int index = MyNames.indexOf(name);
        if (index == -1){
            MyNames.add(name);
            MyFreqs.add(1);
        }
        else{
            int number = MyFreqs.get(index);
            number ++;
            MyFreqs.set(index, number);
        }
    }
    
    public void findAllCharacters(){
        FileResource fr = new FileResource();
        for(String line : fr.lines()){
            int firstPeriod = line.indexOf(".");
            if (firstPeriod != -1){
                String name = line.substring(0, firstPeriod);
                update(name);
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
    
    public void tester(){
        findAllCharacters();
        for(int i = 0; i < MyNames.size(); i++){
            if((MyFreqs.get(i) >= 10) && (MyFreqs.get(i) <= 15)){
                System.out.println(MyNames.get(i) + "\t" + "appears " +
                MyFreqs.get(i) + " times");
            }
        }
        System.out.println("There are totally " + MyNames.size() + " names");
        int maxNumber = maxOfIndex(MyFreqs);
        System.out.println("Most common name is " + MyNames.get(maxNumber) + 
        " with " + MyFreqs.get(maxNumber) + " times");
    }
}
