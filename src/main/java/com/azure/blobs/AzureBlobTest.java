package com.azure.blobs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AzureBlobTest {
    public static void main(String[] args) {
//        String containerName = "sharefiles";
//        List<String> blobs = AzureBlobUtils.listContainerBlobs(containerName);
//        for (String blob : blobs) {
//            if (blob.endsWith(".gz")) {
//                System.out.println("Downloading file " + blob + "...");
//
//                String zipTargetFolder = "/data";
//                String zipTargetFullPath = zipTargetFolder + File.separator + blob;
//                AzureBlobUtils.downloadBlob(containerName, blob, zipTargetFullPath);
//
//                System.out.println("Decompressing file " + zipTargetFullPath + "...");
//                String fileTargetFullPath = zipTargetFullPath.replace(".gz", "");
//                GzipUtils.decompressFile(zipTargetFullPath, fileTargetFullPath);
//
//                System.out.println("Reading file " + fileTargetFullPath + "...");
//                List<String> csvData = CsvUtils.readCsv(fileTargetFullPath);
//                for (int i = 1; i < csvData.size(); i++) {
//                    String[] data = csvData.get(i).split(",");
//                    System.out.print("学号：" + data[0] + ", ");
//                    System.out.print("班级：" + data[1] + ", ");
//                    System.out.print("姓名：" + data[2] + ", ");
//                    System.out.print("性别：" + data[3] + ", ");
//                    System.out.print("英语：" + data[4] + ", ");
//                    System.out.print("体育：" + data[5] + ", ");
//                    System.out.print("军训：" + data[6] + ", ");
//                    System.out.print("数分：" + data[7] + ", ");
//                    System.out.print("高代：" + data[8] + ", ");
//                    System.out.print("解几：" + data[9]);
//                    System.out.println();
//                }
//            }
//        }
        String fileFullPath = "/data";
        List<String> dataList=new ArrayList<String>();
        dataList.add("number,name,sex");
        dataList.add("1,Jay,male");
        dataList.add("2,Sherry,female");
        dataList.add("3,Alex,male");
        CsvUtils.writeCsv(fileFullPath, dataList);
    }
}
