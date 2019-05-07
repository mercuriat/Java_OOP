
/**
 * 在这里给出对类 main 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;

public class main extends test {
    int a = 3;
    int b = 3;
    public static int numOfLine() {
        FileResource fr = new FileResource();
        int count = 0;
        for (String line: fr.lines()) {
            System.out.println(line);
            count += count;
        }
        return count;
    }
    public static void OutputFile(){
        numOfLine();
        System.out.println("That is all content in the file.");
    }
}
