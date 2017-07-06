package com.vc.web.util;

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

public class UTF8Converter {

    String envi = "src\\com\\vc\\web/ViewControllerBundle.txt";
    TreeMap<String, String> englishMap = new TreeMap<String, String>();
    TreeMap<String, String> vietnameseMap = new TreeMap<String, String>();
    TreeMap<String, String> allMap = new TreeMap<String, String>();


    public TreeMap<String, String> getOrigin() throws UnsupportedEncodingException, 
                                                      FileNotFoundException, 
                                                      IOException {
        File file= new File("src");
        String bundlePath= file.getAbsolutePath()+"\\com\\vc\\web\\ViewControllerBundle.txt";
        System.out.println("Parent :"+bundlePath);
        
        BufferedReader br = 
            new BufferedReader(new InputStreamReader(new FileInputStream(bundlePath), 
                                                     "UTF-8"));
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


    public UTF8Converter() throws IOException {

        TreeMap<String, String> origin = getOrigin();
        //Set<String> realKeys = new TreeSet<String>();
        Set<String> commentKeys = new TreeSet<String>();

        /** Truoc het la Sap xep lai file English-Vietnamese.txt */
        this.vietnameseMap = new TreeMap<String, String>();
        /*
           this.englishMap = new TreeMap<String, String>();
           this.vietnameseMap = new TreeMap<String, String>();
           this.allMap = new TreeMap<String, String>();

           Set<String> keys = origin.keySet();
           for (String key: keys) {
               if (key.endsWith("(" + Language.VIETNAMESE.getCode() + ")") ||
                   key.endsWith("(" + Language.ENGLISH.getCode() + ")")) {
                   key = key.substring(0, key.length() - 4);
                   realKeys.add(key);
               } else {
                   commentKeys.add(key);
               }
           }//*/

        for (String key : origin.keySet()) {
            // String enKey = key + "(" + Language.ENGLISH.getCode() + ")";
            // String viKey = key + "(" + Language.VIETNAMESE.getCode() + ")";
            String viValue = 
                UnicodeToUnicodeSequence.convert(origin.get(key) == null ? 
                                                 key : origin.get(key));
            //  this.englishMap.put(key, origin.get(enKey) == null ? key : origin.get(enKey));

            this.vietnameseMap.put(key, viValue);
            //  this.allMap.put(enKey, origin.get(enKey) == null ? key : origin.get(enKey));
            //  this.allMap.put(viKey, origin.get(viKey) == null ? key : origin.get(viKey));
        }

        for (String key : commentKeys) {
            //  this.englishMap.put(key, origin.get(key));
            this.vietnameseMap.put(key, origin.get(key));
            //  this.allMap.put(key, origin.get(key));
        }

        /** Ghi thong tin vao file */
        this.saveToFile(this.vietnameseMap, 
                        "src\\com\\vc\\web\\ViewControllerBundle.properties");
        //    this.saveToFile(this.vietnameseMap, "src/view/ApplicationResources_vi.properties");
        //  this.saveToFile(this.englishMap, "src/view/ApplicationResources_en.properties");
        ///   this.saveToUTF8File(this.allMap, "src/view/EV.properties");
    }

    public void saveToFile(TreeMap<String, String> map, 
                           String file) throws IOException {
        Set<String> keys = map.keySet();
        System.out.println("Save to file " +file);
        FileWriter fileWriter = new FileWriter(file);
        for (String key : keys) {
            System.out.println("Key :"+key);
            fileWriter.write(key + "=" + map.get(key) + "\n");
        }
        fileWriter.close();
    }


    private void saveToUTF8File(TreeMap<String, String> map, 
                                String file) throws IOException {
        Set<String> keys = map.keySet();

        OutputStreamWriter out = 
            new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        for (String key : keys) {
            out.write(key + "=" + map.get(key) + "\n");
        }
        out.close();
    }

    public static void main(String[] args) {
        try {
            new UTF8Converter();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
