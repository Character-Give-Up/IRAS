package org.character.iras.Service;

import jakarta.annotation.Nullable;
import org.character.iras.Application;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Objects;

@Service
public class UploadFileService {

    private File file;
    private String username;
    private int fileType;
    private MultipartFile multipartFile;

    public static final int RESUME = 0;

    public UploadFileService() {
    }

    /**
     * 设置要上传的文件
     * @param file 多部分文件
     * @return  返回文件绝对路径
     */
    public String putFile(MultipartFile file){
        this.multipartFile = file;
        switch (this.fileType){
            default:
            case 0:
                String absolutePath = Application.AbsolutePath;
                String path = absolutePath + "\\upload\\resumes\\"
                        + username + "\\"
                        + getDateString()
                        + getSuffix(Objects.requireNonNull(this.multipartFile.getOriginalFilename()));
                this.file = new File(path);
                return path;
        }
    }

    /**
     * 设置文件归属的用户
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }




    public void setFileType(int type){
        this.fileType = type;
    }

    public void upload(@Nullable DatabaseUploader uploader) throws IOException {
        file.mkdirs();
        file.createNewFile();
        if (uploader != null) {
            uploader.upload();
        }
    }

    public void upload() throws IOException{
        file.mkdirs();
        file.createNewFile();
    }

    private String getSuffix(String filename){
        return filename.substring(filename.lastIndexOf('.'));
    }

    private String getDateString(){
        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH));
        String date = String.valueOf(calendar.get(Calendar.DATE));
        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        String second = String.valueOf(calendar.get(Calendar.SECOND));
        if(month.length() != 2){
            month = "0" + month;
        }
        if(date.length() != 2){
            date = "0" + date;
        }
        if(hour.length() != 2){
            hour = "0" + hour;
        }
        if(minute.length() != 2){
            minute = "0" + minute;
        }
        if(second.length() != 2){
            second = "0" + second;
        }
        return year + month + date + hour + minute + second;

    }
}
