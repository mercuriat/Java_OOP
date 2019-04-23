
/**
 * 在这里给出对类 main 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import edu.duke.*;
import org.apache.commons.csv.*;

public class main {
    public void getExportName(CSVParser parser, String name1, String name2){
        for (CSVRecord record : parser){
            String Export = record.get("Exports");
            if (Export.contains(name1) && Export.contains(name2)){
                String results = record.get("Country");
                System.out.print(results + " : ");
                System.out.print(name1 + " & ");
                System.out.println(name2);
            }
        }
    }
    
    public void numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for (CSVRecord record : parser){
            String Export = record.get("Exports");
            if (Export.contains(exportItem)){
                count = count + 1;
            }
        }
        System.out.println(count);
    }
    
    public void bigExporters(CSVParser parser, String money){
        for (CSVRecord record : parser){
            String totalExport = record.get("Value (dollars)");
            //totalExport = totalExport.substring(1);
            //money = money.substring(1);
            //int threshold = Integer.parseInt(money);
            //int i = Integer.parseInt(totalExport);
            if (totalExport.length() > money.length()){
                String results = record.get("Country");
                System.out.print(results + " : ");
                System.out.println(totalExport);
            }
        }
    }
    
    
    public void test(){
        FileResource fr = new FileResource("exportdata.csv");
        CSVParser parser = fr.getCSVParser();
        //getExportName(parser, "cotton", "flowers");
        //numberOfExporters(parser, "cocoa");
        bigExporters(parser, "$999,999,999,999");
    }
}
