
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer{
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
     
     public HashMap<String, Integer> countVisitsPerIP(){
         HashMap<String, Integer> counts = new HashMap<String, Integer>();
         for(LogEntry le : records){
             String ip = le.getIpAddress();
             if(! counts.containsKey(ip)){
                 counts.put(ip, 1);
             }
             else{
                 counts.put(ip, counts.get(ip) + 1);
             }
         }
         return counts;
     }
     
     public int mostNumberVisitsByIP(HashMap<String, Integer> counts){
         int max = 1;
         for(int i : counts.values()){
             if(i > max){
                 max = i;
             }
         }
         return max;
     }
     
     public ArrayList<String> iPsMostVisits(HashMap<String, Integer> counts){
         ArrayList<String> maxIp = new ArrayList<String>();
         int max = mostNumberVisitsByIP(counts);
         for(String key : counts.keySet()){
             int keyCount = counts.get(key);
             if(keyCount == max){
                 maxIp.add(key);
             }
         }
         return maxIp;
     }
     
     public HashMap<String, Integer> iPsForDays(){
         HashMap<String, Integer> ipDays = new HashMap<String, Integer>();
         for(LogEntry le : records){
             String date = le.getAccessTime().toString().substring(4, 10);
             if(! ipDays.containsKey(date)){
                 ipDays.put(date, 1);
             }
             else{
                 ipDays.put(date, ipDays.get(date) + 1);
             }             
         }
         return ipDays;
     }
     
     public String dayWithMostIPVisits(HashMap<String, Integer> ipDays){
         int max = mostNumberVisitsByIP(ipDays);
         String maxIpDays = "Date Error";
         for(String key : ipDays.keySet()){
             int keyCount = ipDays.get(key);
             if(keyCount == max){
                 maxIpDays = key;
             }
         }    
         return maxIpDays;
     }
     
     public ArrayList<String> iPsWithMostVisitsOnDay(HashMap<String, Integer> ipDays,
                                                    String setDate){
         ArrayList<String> maxIpInDays = new ArrayList<String>();
         HashMap<String, Integer> ipInDays = new HashMap<String, Integer>();
         for(LogEntry le : records){
             String date = le.getAccessTime().toString().substring(4, 10);
             String ip = le.getIpAddress();
             if(date.equals(setDate)){
                 if(! ipInDays.containsKey(ip)){
                     ipInDays.put(ip, 1);
                 }
                 else{
                     ipInDays.put(ip, ipInDays.get(ip) + 1);
                 }
             }
         }
         return iPsMostVisits(ipInDays);
     }
}

