
/**
 * 在这里给出对类 UserINout 的描述。
 * A test of the simple calculator that depends on objects.
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */

import java.util.Scanner;

public class calculator_add {
    public static int getInput() {
        Scanner reader = new Scanner(System.in);  // Reading from System.in
        System.out.println("Enter a number: ");
        int n = reader.nextInt(); // Scans the next token of the input as an int.
        reader.close();
        return n;
    }
    
    public static void Add() {
        int a = getInput();
        int b = getInput();
        System.out.println("The answer is: "+(a + b));
    }
    
    public static void Calculator() {
        Add();
    }
}
