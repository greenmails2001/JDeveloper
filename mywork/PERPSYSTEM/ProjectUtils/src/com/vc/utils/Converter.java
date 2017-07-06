package com.vc.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;




public class Converter {
    
    public void doConvert() {
        File file= new File("E:/Projects/11g/PERPSYSTEM/PerpSasRegistViewController/src/com/perp/sasregistviewcontroller/PerpSasRegistViewControllerBundle.txt");
        
    }
    
    String envi = "src/view/English-Vietnamese.txt";
    TreeMap<String, String> englishMap = new TreeMap<String, String>();
    TreeMap<String, String> vietnameseMap = new TreeMap<String, String>();
    TreeMap<String, String> allMap = new TreeMap<String, String>();


    public TreeMap<String, String> getOrigin() throws UnsupportedEncodingException, FileNotFoundException, 
                                                      IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(envi), "UTF-8"));
        String s = "";
        TreeMap<String, String> origin = new TreeMap<String, String>();
        while ((s = br.readLine()) != null) {
            int i = s.indexOf("=");
            if (i != -1) {
                String key = s.substring(0, i);
                String value = s.substring(i + 1);
                origin.put(key, value);
            }
        }
        br.close();
        return origin;
    }


    public Converter() throws IOException {
        TreeMap<String, String> origin = getOrigin();
        Set<String> realKeys = new TreeSet<String>();
        Set<String> commentKeys = new TreeSet<String>();

        /** Truoc het la Sap xep lai file English-Vietnamese.txt */
        this.englishMap = new TreeMap<String, String>();
        this.vietnameseMap = new TreeMap<String, String>();
        this.allMap = new TreeMap<String, String>();

       
        
        for (String key: commentKeys) {
            this.englishMap.put(key, origin.get(key));
            this.vietnameseMap.put(key, origin.get(key));
            this.allMap.put(key, origin.get(key));
        }

        /** Ghi thong tin vao file */
        this.saveToFile(this.vietnameseMap, "src/view/ApplicationResources.properties");
        this.saveToFile(this.vietnameseMap, "src/view/ApplicationResources_vi.properties");
        //this.saveToFile(this.englishMap, "src/view/ApplicationResources_en.properties");
        this.saveToFile(this.vietnameseMap, "src/view/ApplicationResources_en.properties");
        this.saveToUTF8File(this.allMap, "src/view/EV.properties");
    }

    public void saveToFile(TreeMap<String, String> map, String file) throws IOException {
        Set<String> keys = map.keySet();
        FileWriter fileWriter = new FileWriter(file);
        for (String key: keys) {
            fileWriter.write(key + "=" + map.get(key) + "\n");
        }
        fileWriter.close();
    }


    private void saveToUTF8File(TreeMap<String, String> map, String file) throws IOException {
        Set<String> keys = map.keySet();

        OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        for (String key: keys) {
            out.write(key + "=" + map.get(key) + "\n");
        }
        out.close();
    }

    public static void main(String[] args) {
        try {
            new Converter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
