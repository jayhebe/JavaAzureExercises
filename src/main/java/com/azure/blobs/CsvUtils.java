package com.azure.blobs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {
    public static List<String> readCsv(String fileFullPath) {
        List<String> csvData = csvData = new ArrayList<>();
        BufferedReader inputFile = null;

        try {
            inputFile = new BufferedReader(new FileReader(fileFullPath));
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

    public static boolean writeCsv(String fileFullPath, List<String> csvData) {
        boolean isSuccess = false;
        BufferedWriter outputFile = null;

        try {
            outputFile = new BufferedWriter(new FileWriter(fileFullPath));
            if (csvData != null && !csvData.isEmpty()) {
                for (String data : csvData) {
                    outputFile.write(data);
                    outputFile.newLine();
                }
            }

            isSuccess = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputFile != null) {
                try {
                    outputFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return isSuccess;
    }
}
