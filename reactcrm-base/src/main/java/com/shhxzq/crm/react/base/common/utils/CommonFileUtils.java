package com.shhxzq.crm.react.base.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author XiaoYi Created on 2017-07-16 13:04:08
 */
public class CommonFileUtils {

    public static boolean writeJson2File(Object obj, String path, String fileName, boolean overWrite) {
        if(obj == null){
            return false;
        }
        System.out.println("Start to write the templet file: " + fileName);
        Gson gson = new GsonBuilder().setLenient()// json宽松
                .enableComplexMapKeySerialization()// 支持Map的key为复杂对象的形式
                // .serializeNulls() //智能null
                .setPrettyPrinting()// 调教格式
                .disableHtmlEscaping() // 默认是GSON把HTML 转义的
                .create();
        String content = gson.toJson(obj);
        File fp = new File(path);
        if (!fp.exists()) {
            fp.mkdirs();
        }
        File jsonFile = new File(path + fileName);
        if(jsonFile.exists()){
            if(overWrite){
                System.out.println("The " + fileName + " exist already, would be overWrited.");
            }else{
                System.out.println("The " + fileName + " exist already, would not be overWrited.");
                return false;
            }
        }
        try (OutputStream os = new FileOutputStream(jsonFile)) {
            os.write(content.getBytes());
            System.out.println("Done");
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Failed");
        return false;
    }

    public static String readFileContent(String fileName) {
        try (BufferedReader bf = new BufferedReader(new FileReader(new File(fileName)));) {
            String content = "";
            StringBuilder sb = new StringBuilder();
            while (content != null) {
                content = bf.readLine();
                if (content == null) {
                    break;
                }
                sb.append(content.trim());
                sb.append(System.getProperty("line.separator"));
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
