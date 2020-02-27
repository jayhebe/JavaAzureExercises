package com.azure.blobs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class GZIPUtils {
    public static void decompressFile(String inputFileName, String outputFileName) {
        GZIPInputStream inputFile = null;
        FileOutputStream outputFile = null;

        try {
            inputFile = new GZIPInputStream(new FileInputStream(inputFileName));
            outputFile = new FileOutputStream(outputFileName);

            byte[] data = new byte[1024];
            int length;
            while ((length = inputFile.read(data)) != -1) {
                outputFile.write(data, 0, length);
            }
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

            if (inputFile != null) {
                try {
                    inputFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
