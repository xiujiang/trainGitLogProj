package com.blog.utils;

import com.blog.enums.FileEnum;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 提供文件的上传下载
 * @author liuxiujiang
 * @version 1.0
 * @datetime 2019/5/5
 * @since 1.8
 */
public class FileUtils {

    private static final String FAR_SERVICE_DIR = SystemUtils.getSystemUploadPath();

    public static String upload(String data, FileEnum fileEnum, String suffix, String prefix){
        if(FileEnum.PICTURE.equals(fileEnum)){
             return upload(new ByteArrayInputStream(data.getBytes()),fileEnum,suffix,prefix);
        }

        return null;
    }

    public static String upload(InputStream inputStream, FileEnum fileEnum, String suffix, String prefix) {
        if(FileEnum.PICTURE.equals(fileEnum)){
            String fileName = getFileName(suffix,prefix);
            File file = new File(FAR_SERVICE_DIR+fileName);
            if(!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileOutputStream outputStream = null;
            try {
                outputStream = new FileOutputStream(file);
                byte[] bytes = new byte[1024];
                int length = -1;
                while((length = inputStream.read(bytes))!=-1){
                    outputStream.write(bytes);
                }
                outputStream.flush();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(!ObjectUtils.isEmpty(outputStream)){
                    try {
                        outputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        return null;
    }


    /**
     * 格式为：前缀+时间+随机数.后缀
     * @param suffix
     * @param prefix
     * @return
     */
    private static String getFileName(String suffix,String prefix) {
        StringBuffer buffer = new StringBuffer();
        if (!ObjectUtils.isEmpty(prefix)) {
            buffer.append(buffer);
        } else {
            buffer.append("pic");
        }
        buffer.append(LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE));
        buffer.append((int) (Math.random() * 10000));
        buffer.append(".");
        if(!ObjectUtils.isEmpty(suffix)){
            buffer.append(suffix);
        }else{
            buffer.append("jpg");
        }
        return buffer.toString();
    }



    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("abc.jpg");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileUtils.upload(fileInputStream, FileEnum.PICTURE, null, null);

    }

}
