
/**
 * 在这里给出对类 VeginereTester 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import edu.duke.*;

public class VeginereTester {
    public void main(){
        int[] key = {17,14,12,4};
        VigenereCipher vc = new VigenereCipher(key);
        FileResource fr = new FileResource("titus-small.txt");
        String encrypted = vc.encrypt(fr.asString());
        System.out.println(encrypted);
        System.out.println(fr.asString());
    }
}
