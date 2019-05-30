package com.fileDeal;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

public class ClearText {
    private static final Integer ONE = 1;
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        File file = new File("D:\\single\\stq\\workspace\\yks\\src\\fileDeal\\source");
        /* 读取数据 */
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"));
            String lineTxt = null;
            while ((lineTxt = br.readLine()) != null) {
            	
            }
            br.close();
        } catch (Exception e) {
            System.err.println("read errors :" + e);
        }

        /* 输出数据 */
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File("D:/结果.txt")),
                                                                          "UTF-8"));

            for (String name : map.keySet()) {
                bw.write(name + " " + map.get(name));
                bw.newLine();
            }
            bw.close();
        } catch (Exception e) {
            System.err.println("write errors :" + e);
        }
    }
}
