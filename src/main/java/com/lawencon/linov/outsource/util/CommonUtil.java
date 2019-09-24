package com.lawencon.linov.outsource.util;


import io.minio.MinioClient;
import io.minio.errors.*;
import org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CommonUtil {

    @Value("${minio.endpoint}")
    private static String endpoint;

    @Value("${minio.access.key}")
    private static String accessKey;

    @Value("${minio.secret.key}")
    private static String secretKey;

    private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);

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

    public static void fileUpload(String bucketName, String objectName, InputStream data, Long size, String contentType) {
        try {
            MinioClient minioClient = new MinioClient("http://127.0.0.1/", 9000, "3Y8GENT45UARZ2949BT3", "k2kl4Zu+dlUHe+RaZPoxqoP9+ddG9rLVlEJ3WIy+");

            boolean found = minioClient.bucketExists(bucketName);

            if (!found) {
                minioClient.makeBucket(bucketName);
            }

            Map<String, String> headerMap = new HashMap<>();
            headerMap.put("Content-Type", contentType);
            minioClient.putObject(bucketName,objectName, data, size, headerMap, null, contentType);
        } catch (InvalidEndpointException | InvalidPortException | InvalidKeyException | InsufficientDataException | NoSuchAlgorithmException | NoResponseException | InvalidResponseException | XmlPullParserException | InvalidArgumentException | InvalidBucketNameException | ErrorResponseException | InternalException | IOException | RegionConflictException e) {
            logger.error(e.getMessage());
        }
    }

    public static void downloadFile(){

    }

    public static String getFileExtension(MultipartFile file) {
        String fileName = Objects.requireNonNull(trimSpace(Objects.requireNonNull(file.getOriginalFilename())));
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
            return fileName.substring(fileName.lastIndexOf(".")+1);
        } else {
            return "";
        }
    }

    public static String trimSpace(String s){
        return s.replaceAll("(^ )|( $)", "");
    }

    public static Integer currentMonth(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.MONTH) + 1;
    }

    public static Integer currentYear(){
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static String trimAllSpaces(String s, String replacement){
        return WordUtils.capitalize(s.replace(" ", replacement));
    }

    public static String getMonth(){
        Calendar calendar = Calendar.getInstance();
        int month = calendar.get(Calendar.MONTH) + 1;
        String monthName = "";

        switch (month) {
            case 1 :
                monthName = "January";
                break;
            case 2:
                monthName = "February";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
                break;
        }
        return monthName;
    }
}
