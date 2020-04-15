package com.example.aq.Controller;


import java.io.*;

public class test {
    public static void main(String args[]) throws IOException {
        File ff = new File("D:\\read.txt");
        InputStreamReader isr = new InputStreamReader(new FileInputStream(ff), "GBK");
        BufferedReader reader = new BufferedReader(isr);
        int a = Integer.valueOf(reader.readLine());
        int b = Integer.valueOf(reader.readLine());
        int c = Integer.valueOf(reader.readLine());
        int d = Integer.valueOf(reader.readLine());
        String s = reader.readLine();
        System.out.println(a + "" + b + "" + c + "" + d + "\n" + s);
    }
}
