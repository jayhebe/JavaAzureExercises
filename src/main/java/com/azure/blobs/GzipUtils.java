package com.azure.blobs;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtils {
    public static void compressFile(String inputFileFullPath) {
        String outputFileFullPath = inputFileFullPath + ".gz";

        FileInputStream inputFile = null;
        GZIPOutputStream outputFile = null;

        try {
            inputFile = new FileInputStream(inputFileFullPath);
            outputFile = new GZIPOutputStream(new FileOutputStream(outputFileFullPath));

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

    public static void decompressFile(String inputFileFullPath, String outputFileFullPath) {
        GZIPInputStream inputFile = null;
        FileOutputStream outputFile = null;

        try {
            inputFile = new GZIPInputStream(new FileInputStream(inputFileFullPath));
            outputFile = new FileOutputStream(outputFileFullPath);

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
