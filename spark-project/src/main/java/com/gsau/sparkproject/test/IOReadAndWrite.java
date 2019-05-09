package com.gsau.sparkproject.test;

import java.io.*;

/**
   * @Description: IO流测试
   * @author: wangguoqing
   * @date: 2018/10/29 11:01
  */
public class IOReadAndWrite {
     public void WriteStringToFile(String filePath) {
         try {
             File file = new File(filePath);
             PrintStream ps = new PrintStream(new FileOutputStream(file));
             ps.println("http://www.jb51.net");               // 往文件里写入字符串
             ps.append("http://www.jb51.net");                // 在已有的基础上添加字符串
         } catch (FileNotFoundException e) {
             e.printStackTrace();
         }
     }

     public void WriteStringToFile2(String filePath) {
         try {
             FileWriter fw = new FileWriter(filePath, true);
             BufferedWriter bw = new BufferedWriter(fw);
             bw.append("在已有的基础上添加字符串");
             bw.write("abc\r\n ");// 往已有的文件上添加字符串
             bw.write("def\r\n ");
             bw.write("hijk ");
             bw.close();
             fw.close();
         } catch (Exception e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
     }

     public void WriteStringToFile3(String filePath) {
         try {
             PrintWriter pw = new PrintWriter(new FileWriter(filePath));
             pw.println("abc ");
             pw.println("def ");
             pw.println("hef ");
             pw.close();
         } catch (IOException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
     }

     public void WriteStringToFile4(String filePath) {
         try {
             RandomAccessFile rf = new RandomAccessFile(filePath, "rw");
             rf.writeBytes("op\r\n");
             rf.writeBytes("app\r\n");
             rf.writeBytes("hijklllll");
             rf.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

     public void WriteStringToFile5(String filePath) {
         try {
             FileOutputStream fos = new FileOutputStream(filePath);
             String s = "http://www.jb51.netl";
             fos.write(s.getBytes());
             fos.close();
         } catch (Exception e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
     }

     public static void main(String[] args) {
         String filePath = "E:\\link.txt";
         // new WriteStringToTxt().WriteStringToFile(filePath);
         // new WriteStringToTxt().WriteStringToFile2(filePath);
         // new WriteStringToTxt().WriteStringToFile3(filePath);
         // new WriteStringToTxt().WriteStringToFile4(filePath);
         new IOReadAndWrite().WriteStringToFile5(filePath);
     }
}
