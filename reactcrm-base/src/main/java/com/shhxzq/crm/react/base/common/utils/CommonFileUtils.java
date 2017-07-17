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

    public static boolean writeJson2File(Object obj, String path, String fileName) {
        if(obj == null){
            return false;
        }
        // String content = JSON.toJSONString(obj, true);
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
        try (OutputStream os = new FileOutputStream(new File(path + fileName))) {
            os.write(content.getBytes());
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
