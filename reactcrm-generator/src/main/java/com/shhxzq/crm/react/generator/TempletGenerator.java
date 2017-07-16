package com.shhxzq.crm.react.generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.alibaba.fastjson.JSON;

/**
 * @author XiaoYi
 * Created on 2017-07-15 17:55:48
 */
public class TempletGenerator {
    
    private String path;
    
    private boolean write(Object obj, String path, String fileName){
        String content = JSON.toJSONString(obj, true);
        File file = new File(path + fileName);
        file.mkdirs();
        try(OutputStream os = new FileOutputStream(file)){
            os.write(content.getBytes());
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
}
