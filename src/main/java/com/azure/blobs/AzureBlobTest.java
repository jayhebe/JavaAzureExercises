package com.azure.blobs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AzureBlobTest {
    public static void main(String[] args) {
        String containerName = "sharefiles";
        String fileFullPath = "/data/test.csv";
        List<String> dataList = new ArrayList<>();
        dataList.add("number,name,sex");
        dataList.add("1,Jay,male");
        dataList.add("2,Sherry,female");
        dataList.add("3,Alex,male");
        System.out.println("Writing CSV file...");
        boolean isSuccess = CsvUtils.writeCsv(fileFullPath, dataList);
        if (isSuccess) {
            System.out.println("Compressing CSV file...");
            GzipUtils.compressFile(fileFullPath);
            System.out.println("Uploading CSV file to Azure blob...");
            AzureBlobUtils.uploadBlob(containerName, fileFullPath + ".gz");
        } else {
            System.out.println("Write CSV file failed.");
        }

        System.out.println("-----------------------------------------------");

        List<String> blobs = AzureBlobUtils.listContainerBlobs(containerName);
        for (String blob : blobs) {
            if (blob.endsWith(".gz")) {
                System.out.println("Downloading file " + blob + "...");

                String zipTargetFolder = "/data/Test";
                String zipTargetFullPath = zipTargetFolder + File.separator + blob;
                AzureBlobUtils.downloadBlob(containerName, blob, zipTargetFullPath);

                System.out.println("Decompressing file " + zipTargetFullPath + "...");
                String fileTargetFullPath = zipTargetFullPath.replace(".gz", "");
                GzipUtils.decompressFile(zipTargetFullPath, fileTargetFullPath);

                System.out.println("Reading file " + fileTargetFullPath + "...");
                List<String> csvData = CsvUtils.readCsv(fileTargetFullPath);
                for (int i = 1; i < csvData.size(); i++) {
                    String[] data = csvData.get(i).split(",");
                    System.out.print("序号：" + data[0] + ", ");
                    System.out.print("姓名：" + data[1] + ", ");
                    System.out.print("性别：" + data[2]);
                    System.out.println();
                }
            }
        }
    }
}
