
/**
 * 在这里给出对类 countsTester 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import java.util.*;

public class countsTester {
    public void test(){
        LogAnalyzer la = new LogAnalyzer();
        la.readFile("weblog2_log");
        HashMap<String, Integer> counts = la.countVisitsPerIP();
        int max                         = la.mostNumberVisitsByIP(counts);
        ArrayList<String> maxIp         = la.iPsMostVisits(counts);
        HashMap<String, Integer> ipDays = la.iPsForDays();
        String maxIpDays                = la.dayWithMostIPVisits(ipDays);
        
        String setDate = "Sep 30";
        ArrayList<String> ipInMaxDays   = la.iPsWithMostVisitsOnDay(ipDays, setDate);
        
        
        //System.out.println("The max number of IP is: " + max);
        System.out.println("Those IPs are: " + maxIp);
        System.out.println("The counts of IP per day: " + ipDays);
        System.out.println("Those days with max IP are: " + maxIpDays);
        
        System.out.println("Those IPs visiting most on " + setDate +  " are: " + ipInMaxDays);
    }
}
