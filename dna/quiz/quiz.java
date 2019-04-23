import edu.duke.*;
import java.io.*;
/**
 * 在这里给出对类 quiz 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class quiz {
    public void test() {
        FileResource fr = new FileResource("/Users/zhaotianxiao/Downloads/dna/brca1line.fa");
        String dna = fr.asString();
        System.out.println(dna);
    }
}

