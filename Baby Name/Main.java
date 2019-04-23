
/**
 * 在这里给出对类 Main 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import edu.duke.*;
import org.apache.commons.csv.*;
public class Main {
    public void totalBirths(){
        FileResource fr = new FileResource();
        int totalBorn = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            totalBorn += Integer.parseInt(rec.get(2));
        }
        System.out.println("Total Births =" + totalBorn);
    }
    
    public void getRank(String name, String gander){
        FileResource fr = new FileResource();
        int Rank = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals(gander)){
                if (rec.get(0).equals(name)){
                    Rank += 1;
                    break;
                }
                else{
                    Rank += 1;
                }
            }
        }
        System.out.println("Rank = " + Rank);
    }
    
    public void Girlsname(){
        FileResource fr = new FileResource();
        int girlsName = 0;
        for(CSVRecord rec : fr.getCSVParser(false)){
            if(rec.get(1).equals("M")){
                girlsName += 1;
            }
        }
        System.out.println("The number of girls' name = " + girlsName);
    }

    public void test(){
        getRank("Frank", "M");
    }
}
