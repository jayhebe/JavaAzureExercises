package com.azure.blobs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CsvUtils {
    public static ArrayList<String> readCsv(String fileName) {
        ArrayList<String> csvData = csvData = new ArrayList<>();
        BufferedReader inputFile = null;

        try {
            inputFile = new BufferedReader(new FileReader(fileName));
            String line = "";
            while ((line = inputFile.readLine()) != null) {
                csvData.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputFile != null) {
                try {
                    inputFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return csvData;
    }
}
