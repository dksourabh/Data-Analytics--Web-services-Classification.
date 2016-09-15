/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datacleaningproject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author sourabh_deshkulkarni
 */
public class TDMAssignmentFour {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String csvFile = "/Users/sourabh_deshkulkarni/Downloads/DCProjectInitial_csv_updated_20th.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int count = 1;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        ConcurrentHashMap<String, String> mapValue = new ConcurrentHashMap<String, String>();
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] records = line.split(cvsSplitBy);
                if (records != null) {
                    if (map.containsKey(records[2])) {
                        count = map.get(records[2]);
                        count++;
                        map.put(records[2], count);
                    } else {
                        map.put(records[2], count);
                    }
                }
                count = 1;
            }
            count = 0;
            map = (HashMap<String, Integer>) sortByValue(map);
            Iterator it = map.entrySet().iterator();
            ArrayList<String> finalList = new ArrayList<String>();
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();
                if (pair.getValue() != null) {
                    // System.out.println(pair.getKey() + "//" + pair.getValue() + "//" + count++);
                    if (count <= 40) {
                        if (!String.valueOf(pair.getKey()).equalsIgnoreCase("rest")) {
                            finalList.add((String) pair.getKey());
                        }
                    }
                }
            }
            ArrayList<String[]> arr = getFileToArray();
            ArrayList<String> stopWords = getStopWords();
            
            File file = new File("/Users/sourabh_deshkulkarni/Downloads/TDMAssignment.csv");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            Iterator itValue = null;
            double similarityValue = 0;
            boolean flag = false;
            double max = 0;
            String replaceValue = "";
            String[] tempArr = null;
            for (String[] str : arr) {
                if (max > 0) {
                    tempArr = str[6].split(" ");
                    //if(finalList.contains(str[2])){
//                if (mapValue.isEmpty()) {
//                    mapValue.put(str[13], str[13]);
//                }
//                if (mapValue.containsKey(str[13])) {
                    //mapValue.put(str[2], str[2]);
                    //  System.out.println("Gandlay:" + str[3]);
                    if (tempArr != null) {
                        for (int i = 0; i < tempArr.length; i++) {
                            if (!stopWords.contains(tempArr[i])) {
                                bw.write(tempArr[i]+" ");
                            } else {
                                System.out.println(tempArr[i]);
                            }
                        }
                        
                    }
                    //} }
                } else {
                    bw.write(str[6]);
                }
                max++;
                bw.newLine();
            }

//            for (String[] str : arr) {
//                if (similarity(str[0], str[2]) >0.9) {
//                    bw.write("false");
//                } else {
//                    bw.write("true");
//                }
//                bw.newLine();
//            }
            bw.close();
//            System.out.print("Map size:" + mapValue.size());
//            System.out.print("Flag:" + flag);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            
        }
    }
    
    public static <K, V extends Comparable<? super V>> Map<K, V>
            sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list
                = new LinkedList<Map.Entry<K, V>>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                int result = 0;
                
                result = o1.getValue().compareTo(o2.getValue());
                if (result == -1) {
                    result = 1;
                } else if (result == 1) {
                    result = -1;
                }
                return result;
            }
        });
        
        Map<K, V> result = new LinkedHashMap<K, V>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
    
    public static ArrayList<String[]> getFileToArray() {
        String csvFile = "/Users/sourabh_deshkulkarni/Desktop/Final Data Cleaning term paper/api.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<String[]> arr = new ArrayList<String[]>();
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] records = line.split(cvsSplitBy);
                if (records != null) {
                    arr.add(records);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }
    
   

   

    public static ArrayList<String> getStopWords() {
        int k = 0;
        ArrayList<String> wordVector = new ArrayList<String>();
        String sCurrentLine = "";
        
        try {
            FileReader fr = new FileReader("/Users/sourabh_deshkulkarni/Downloads/stopwordslist.txt");
            BufferedReader br = new BufferedReader(fr);
            while ((sCurrentLine = br.readLine()) != null) {
                wordVector.add(sCurrentLine);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wordVector;
    }    
    
}
