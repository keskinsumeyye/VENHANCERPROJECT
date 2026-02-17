package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;

    static {
        String path = "configuration.properties";
        try{
            FileInputStream fileInputStream = new FileInputStream(path);
            properties = new Properties();
            properties.load(fileInputStream);
            fileInputStream.close();
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("configuration.properties dosyası bulunamadı! Yolunu kontrol et:" + path);
        }
    }

    public static  String getProperty(String key){
        return properties.getProperty(key);
    }
}
