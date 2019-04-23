
/**
 * 在这里给出对类 mystery 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class mystery {
    public String mystery(String dna) {
        int pos = dna.indexOf("T");
        int count = 0;
        int startPos = 0;
        String newDna = "";
        if (pos == -1) {
                return dna;
            }
        while (count < 3) {
            count += 1;
            newDna = newDna + dna.substring(startPos,pos);
            startPos = pos+1;
            pos = dna.indexOf("T", startPos);
            if (pos == -1) {
                break;
            }
        }
        newDna = newDna + dna.substring(startPos);
        return newDna;
    }
    public void test() {
        String dna = "AACTACCTASDTHJKHKHTTTT";
        String output = mystery(dna);
        System.out.println(output);
    }
}
