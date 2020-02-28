package com.azure.blobs;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

    public static List<String> listContainerBlobs(String containerName) {
        List<String> blobs = new ArrayList<>();
        BlobContainerClient blobContainerClient = getBlobContainerClient(containerName);
        blobContainerClient.listBlobs().forEach(blob ->
                blobs.add(blob.getName()));

        return blobs;
    }

    public static void downloadBlob(String containerName, String blobName, String filePath) {
        BlobContainerClient blobContainerClient = getBlobContainerClient(containerName);
        BlobClient blobClient = blobContainerClient.getBlobClient(blobName);
        blobClient.downloadToFile(filePath);
    }

    public static void uploadBlob(String containerName, String fileFullPath) {
        BlobContainerClient blobContainerClient = getBlobContainerClient(containerName);
        BlobClient blobClient = blobContainerClient.getBlobClient(new File(fileFullPath).getName());
        blobClient.uploadFromFile(fileFullPath);
    }
}
