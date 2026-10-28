package com.utility;

import org.junit.Assert;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class FileReaderManager {

    private static FileInputStream fileInputStream;
    private static Properties properties;

    public static void setProperty() {
        String filePath = System.getProperty("testdata.path", "src/main/resources/TestData.properties");
        File file = new File(filePath);
        try {
            fileInputStream = new FileInputStream(file);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            Assert.fail("ERROR: OCCURE DURING FILE LOADING");
        } catch (IOException e){
            Assert.fail("ERROR: OCCURE DURING FILE READING");
        }
    }

    public static String getProperty(String key) {
        setProperty();
        String property = properties.getProperty(key);
        return property;
    }
}
