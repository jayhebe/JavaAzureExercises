package com.azure.blobs;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class AzureBlobUtils {
    private static String azureStorageConnectionString;

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("azureblob.properties"));
            azureStorageConnectionString = properties.getProperty("AZURE_STORAGE_CONNECTION_STRING");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static BlobContainerClient getBlobContainerClient(String containerName) {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(azureStorageConnectionString).buildClient();
        return blobServiceClient.getBlobContainerClient(containerName);
    }

    public static ArrayList<String> listContainerBlobs(String containerName) {
        ArrayList<String> blobs = new ArrayList<>();
        BlobContainerClient blobContainerClient = getBlobContainerClient(containerName);
        blobContainerClient.listBlobs().forEach(blob ->
                blobs.add(blob.getName()));

        return blobs;
    }

    public static void downloadBlobs(String containerName, String blobName, String filePath) {
        BlobContainerClient blobContainerClient = getBlobContainerClient(containerName);
        BlobClient blobClient = blobContainerClient.getBlobClient(blobName);
        blobClient.downloadToFile(filePath);
    }
}
