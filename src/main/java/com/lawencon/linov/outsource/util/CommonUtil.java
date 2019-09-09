package com.lawencon.linov.outsource.util;

import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.beans.factory.annotation.Value;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class CommonUtil {

    @Value("")
    private static String endpoint;

    @Value("")
    private static String accessKey;

    @Value("")
    private static String secretKey;

    public static boolean isEmpty(String s){

        if (s.isEmpty()) {
            return true;
        }

        if (s.equals("")) {
            return true;
        }

        if (s.equalsIgnoreCase("")) {
            return true;
        }

        if (s.length() < 1) {
            return true;
        }

        return true;
    }

    public static void FileUpload(String bucketName, String objectName, String fileName) {
        try {
            MinioClient minioClient = new MinioClient(endpoint, accessKey, secretKey);

            boolean found = minioClient.bucketExists(bucketName);

            if (!found) {
                minioClient.makeBucket(bucketName);
            }

            minioClient.putObject(bucketName,objectName, fileName);
//            java.lang.String bucketName,
//            java.lang.String objectName,
//            java.lang.Object data,
//            int length,
//            java.util.Map<java.lang.String,java.lang.String> headerMap,
//            java.lang.String uploadId,
//            int partNumber
        } catch (InvalidEndpointException e) {
            e.printStackTrace();
        } catch (InvalidPortException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InsufficientDataException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoResponseException e) {
            e.printStackTrace();
        } catch (InvalidResponseException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (InvalidArgumentException e) {
            e.printStackTrace();
        } catch (InvalidBucketNameException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (InternalException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RegionConflictException e) {
            e.printStackTrace();
        }

    }
}
