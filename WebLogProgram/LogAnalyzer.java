
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
     private ArrayList<LogEntry> records;
     
     public LogAnalyzer() {
         // complete constructor
         records = new ArrayList<LogEntry> ();
     }
        
     public void readFile(String filename) {
         // complete method
         FileResource fr = new FileResource(filename);
         for (String line : fr.lines()){
             LogEntry le = WebLogParser.parseEntry(line);
             records.add(le);
         }
     }
        
     public void printAll() {
         for (LogEntry le : records) {
             System.out.println(le);
         }
     }
     
     public int countUniqueIPs(){
         ArrayList<String> uniqueIp = new ArrayList<String>();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             if (!uniqueIp.contains(ip)){
                 uniqueIp.add(ip);
             }
         }
         return uniqueIp.size();
     }
          
     public void printAllHigherThanNum(int num){
         for (LogEntry le : records) {
             int statusCode = le.getStatusCode();
             if (statusCode > num){
                 System.out.println(le);
             }
         }     
     }
     
     public void uniqueIPVisitsOnDay(String someday){
         ArrayList<String> uniqueIp = new ArrayList<String>();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             String date = le.getAccessTime().toString();
             //System.out.println(date);
             if ((!uniqueIp.contains(ip)) && (date.contains(someday))){
                 uniqueIp.add(ip);
                 System.out.println(le);
             }
             
         }   
         System.out.println(uniqueIp.size());
     }
     
     public void countUniqueIPsInRange(int low, int high){
         ArrayList<String> uniqueIp = new ArrayList<String>();
         for (LogEntry le : records) {
             String ip = le.getIpAddress();
             int statusCode = le.getStatusCode();
             //System.out.println(date);
             if ((!uniqueIp.contains(ip)) && (statusCode >= low) && (statusCode <= high)){
                 uniqueIp.add(ip);
                 System.out.println(le);
             }
         }
         System.out.println(uniqueIp.size());
     }         
}

