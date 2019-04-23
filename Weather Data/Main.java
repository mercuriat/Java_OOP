
/**
 * 在这里给出对类 Main 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;


public class Main {
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldTemperature = null;
        for (CSVRecord currentRow : parser){
            if (coldTemperature == null){
                coldTemperature = currentRow;
            }
            else{
                double temp = Double.parseDouble(currentRow.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldTemperature.get("TemperatureF"));
                if ((temp < coldestTemp) && (temp != -9999)){
                    coldTemperature = currentRow;
                }
            }
        }
        return coldTemperature;
    }
    
    public CSVRecord coldestInYear(){
        CSVRecord coldestTemperature = null;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord coldestInOneDay = coldestHourInFile(parser);
            if (coldestTemperature == null){
                coldestTemperature = coldestInOneDay;
            }
            else{
                double temp = Double.parseDouble(coldestInOneDay.get("TemperatureF"));
                double coldestTemp = Double.parseDouble(coldestTemperature.get("TemperatureF"));
                if (temp < coldestTemp){
                    coldestTemperature = coldestInOneDay;
                }
            }
        }
        
        return coldestTemperature;
    }
    
    public void testForYear(){
        CSVRecord results = coldestInYear();
        System.out.println(results.get("DateUTC") + " : " + results.get("TemperatureF"));
    }
    
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource("2014/weather-2014-05-01.csv");
        CSVParser parser = fr.getCSVParser();
        CSVRecord results = coldestHourInFile(parser);
        System.out.println(results.get("DateUTC") + " : " + results.get("TemperatureF"));
    }
}
