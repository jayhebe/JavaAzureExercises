package com.azure.blobs;

import java.io.File;
import java.util.ArrayList;

public class AzureBlobTest {
    public static void main(String[] args) {
        String containerName = "sharefiles";
        ArrayList<String> blobs = AzureBlobUtils.listContainerBlobs(containerName);
        for (String blob : blobs) {
//            System.out.println(blob);
            System.out.println("Downloading file " + blob + "...");
            AzureBlobUtils.downloadBlobs(containerName, blob, "C:\\Users\\Jay\\Downloads\\Test" + File.separator + blob);
        }
    }
}
